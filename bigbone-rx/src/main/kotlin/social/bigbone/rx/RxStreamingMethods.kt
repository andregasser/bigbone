package social.bigbone.rx

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableEmitter
import social.bigbone.MastodonClient
import social.bigbone.api.WebSocketCallback
import social.bigbone.api.WebSocketEvent
import social.bigbone.api.method.StreamingMethods
import java.io.Closeable

/**
 * Reactive implementation of [StreamingMethods].
 * Allows access to API methods with endpoints having an "api/vX/streaming" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/streaming/">Mastodon streaming API methods</a>
 */
class RxStreamingMethods(client: MastodonClient) {

    private val streamingMethods = StreamingMethods(client)

    fun federatedPublic(accessToken: String, onlyMedia: Boolean): Flowable<WebSocketEvent> = streamTimeline {
        streamingMethods.federatedPublic(accessToken, onlyMedia, it)
    }

    fun localPublic(accessToken: String, onlyMedia: Boolean): Flowable<WebSocketEvent> = streamTimeline {
        streamingMethods.localPublic(accessToken, onlyMedia, it)
    }

    fun remotePublic(accessToken: String, onlyMedia: Boolean): Flowable<WebSocketEvent> = streamTimeline {
        streamingMethods.remotePublic(accessToken, onlyMedia, it)
    }

    fun hashtag(
        accessToken: String,
        tagName: String,
        onlyFromThisServer: Boolean
    ): Flowable<WebSocketEvent> = streamTag(tagName, onlyFromThisServer) { tag, onlyLocal, callback ->
        streamingMethods.hashtag(
            accessToken = accessToken,
            tagName = tag,
            onlyFromThisServer = onlyLocal,
            callback = callback
        )
    }

    fun user(accessToken: String): Flowable<WebSocketEvent> = streamTimeline {
        streamingMethods.user(accessToken, it)
    }

    fun list(
        accessToken: String,
        listId: String,
    ): Flowable<WebSocketEvent> = streamList(listId) { list, callback ->
        streamingMethods.list(
            accessToken = accessToken,
            listId = list,
            callback = callback
        )
    }

    fun directConversations(accessToken: String): Flowable<WebSocketEvent> = streamTimeline {
        streamingMethods.directConversations(accessToken, it)
    }

    private fun streamTimeline(streamMethod: (WebSocketCallback) -> Closeable): Flowable<WebSocketEvent> {
        return Flowable.create({ emitter ->
            val closeable = streamMethod(emitter.fromWebSocketCallback())
            emitter.setCancellable(closeable::close)
        }, BackpressureStrategy.BUFFER)
    }

    private fun streamList(
        listId: String,
        streamMethod: (String, WebSocketCallback) -> Closeable
    ): Flowable<WebSocketEvent> {
        return Flowable.create({ emitter ->
            val closeable = streamMethod(listId, emitter.fromWebSocketCallback())
            emitter.setCancellable(closeable::close)
        }, BackpressureStrategy.BUFFER)
    }

    private fun streamTag(
        tagName: String,
        onlyFromThisServer: Boolean,
        streamMethod: (String, Boolean, WebSocketCallback) -> Closeable
    ): Flowable<WebSocketEvent> {
        return Flowable.create({ emitter ->
            val closeable = streamMethod(tagName, onlyFromThisServer, emitter.fromWebSocketCallback())
            emitter.setCancellable(closeable::close)
        }, BackpressureStrategy.BUFFER)
    }

    private fun FlowableEmitter<WebSocketEvent>.fromWebSocketCallback(): (event: WebSocketEvent) -> Unit =
        { webSocketEvent ->
            when (webSocketEvent) {
                is WebSocketEvent.Closed -> onComplete()
                is WebSocketEvent.Failure -> tryOnError(webSocketEvent.error)
                WebSocketEvent.Open,
                is WebSocketEvent.Closing,
                is WebSocketEvent.StreamEvent -> onNext(webSocketEvent)
            }
        }
}
