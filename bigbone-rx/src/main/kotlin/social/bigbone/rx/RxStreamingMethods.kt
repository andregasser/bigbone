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
 * @see <a href="https://docs.joinmastodon.org/methods/streaming/#streams">Mastodon streaming API methods</a>
 */
class RxStreamingMethods(client: MastodonClient) {

    private val streamingMethods = StreamingMethods(client)

    /**
     * Stream all public posts known to this server. Analogous to the federated timeline.
     *
     * @param onlyMedia Filter for media attachments. Analogous to the federated timeline with “only media” enabled.
     */
    fun federatedPublic(onlyMedia: Boolean): Flowable<WebSocketEvent> = streamTimeline {
        streamingMethods.federatedPublic(onlyMedia, it)
    }

    /**
     * Stream all public posts originating from this server. Analogous to the local timeline.
     *
     * @param onlyMedia Filter for media attachments. Analogous to the local timeline with “only media” enabled.
     */
    fun localPublic(onlyMedia: Boolean): Flowable<WebSocketEvent> = streamTimeline {
        streamingMethods.localPublic(onlyMedia, it)
    }

    /**
     * Stream all public posts originating from other servers.
     *
     * @param onlyMedia Filter for media attachments.
     */
    fun remotePublic(onlyMedia: Boolean): Flowable<WebSocketEvent> = streamTimeline {
        streamingMethods.remotePublic(onlyMedia, it)
    }

    /**
     * Stream all public posts using the hashtag [tagName].
     *
     * @param tagName Hashtag the public posts you want to stream should have.
     * @param onlyFromThisServer Filter for public posts originating from this server.
     */
    fun hashtag(
        tagName: String,
        onlyFromThisServer: Boolean
    ): Flowable<WebSocketEvent> = streamTag { callback ->
        streamingMethods.hashtag(
            tagName = tagName,
            onlyFromThisServer = onlyFromThisServer,
            callback = callback
        )
    }

    /**
     * Stream all events related to the current user, such as home feed updates and notifications.
     */
    fun user(): Flowable<WebSocketEvent> = streamTimeline(streamingMethods::user)

    /**
     * Stream all notifications for the current user.
     */
    fun userNotifications(): Flowable<WebSocketEvent> = streamTimeline(streamingMethods::userNotifications)

    /**
     * Stream updates to the list with [listId].
     *
     * @param listId List you want to receive updates for.
     */
    fun list(listId: String): Flowable<WebSocketEvent> = streamList { callback ->
        streamingMethods.list(
            listId = listId,
            callback = callback
        )
    }

    /**
     * Stream all updates to direct conversations.
     */
    fun directConversations(): Flowable<WebSocketEvent> = streamTimeline(streamingMethods::directConversations)

    private fun streamTimeline(streamMethod: (WebSocketCallback) -> Closeable): Flowable<WebSocketEvent> {
        return Flowable.create({ emitter ->
            val closeable = streamMethod(emitter.fromWebSocketCallback())
            emitter.setCancellable(closeable::close)
        }, BackpressureStrategy.BUFFER)
    }

    private fun streamList(streamMethod: (WebSocketCallback) -> Closeable): Flowable<WebSocketEvent> {
        return Flowable.create({ emitter ->
            val closeable = streamMethod(emitter.fromWebSocketCallback())
            emitter.setCancellable(closeable::close)
        }, BackpressureStrategy.BUFFER)
    }

    private fun streamTag(streamMethod: (WebSocketCallback) -> Closeable): Flowable<WebSocketEvent> {
        return Flowable.create({ emitter ->
            val closeable = streamMethod(emitter.fromWebSocketCallback())
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
                is WebSocketEvent.StreamEvent,
                is WebSocketEvent.GenericMessage -> onNext(webSocketEvent)
            }
        }
}
