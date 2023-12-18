package social.bigbone.rx

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import social.bigbone.MastodonClient
import social.bigbone.api.WebSocketCallback
import social.bigbone.api.entity.streaming.MastodonApiEvent.GenericMessage
import social.bigbone.api.entity.streaming.MastodonApiEvent.StreamEvent
import social.bigbone.api.entity.streaming.TechnicalEvent.Closed
import social.bigbone.api.entity.streaming.TechnicalEvent.Closing
import social.bigbone.api.entity.streaming.TechnicalEvent.Failure
import social.bigbone.api.entity.streaming.TechnicalEvent.Open
import social.bigbone.api.entity.streaming.WebSocketEvent
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
     * Verify that the streaming service is alive before connecting to it.
     *
     * @return Completable that will complete if the server is “healthy” or emit an error via onError otherwise.
     * @see <a href="https://docs.joinmastodon.org/methods/streaming/#health">streaming/#health API documentation</a>
     */
    fun checkServerHealth(): Completable = Completable.fromAction {
        streamingMethods.checkServerHealth()
    }

    /**
     * Stream all public posts known to this server. Analogous to the federated timeline.
     *
     * @param onlyMedia Filter for media attachments. Analogous to the federated timeline with “only media” enabled.
     */
    fun federatedPublic(onlyMedia: Boolean): Flowable<WebSocketEvent> = stream {
        streamingMethods.federatedPublic(onlyMedia, it)
    }

    /**
     * Stream all public posts originating from this server. Analogous to the local timeline.
     *
     * @param onlyMedia Filter for media attachments. Analogous to the local timeline with “only media” enabled.
     */
    fun localPublic(onlyMedia: Boolean): Flowable<WebSocketEvent> = stream {
        streamingMethods.localPublic(onlyMedia, it)
    }

    /**
     * Stream all public posts originating from other servers.
     *
     * @param onlyMedia Filter for media attachments.
     */
    fun remotePublic(onlyMedia: Boolean): Flowable<WebSocketEvent> = stream {
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
    ): Flowable<WebSocketEvent> = stream { callback ->
        streamingMethods.hashtag(
            tagName = tagName,
            onlyFromThisServer = onlyFromThisServer,
            callback = callback
        )
    }

    /**
     * Stream all events related to the current user, such as home feed updates and notifications.
     */
    fun user(): Flowable<WebSocketEvent> = stream(streamingMethods::user)

    /**
     * Stream all notifications for the current user.
     */
    fun userNotifications(): Flowable<WebSocketEvent> = stream(streamingMethods::userNotifications)

    /**
     * Stream updates to the list with [listId].
     *
     * @param listId List you want to receive updates for.
     */
    fun list(listId: String): Flowable<WebSocketEvent> = stream { callback ->
        streamingMethods.list(
            listId = listId,
            callback = callback
        )
    }

    /**
     * Stream all updates to direct conversations.
     */
    fun directConversations(): Flowable<WebSocketEvent> = stream(streamingMethods::directConversations)

    private fun stream(streamMethod: (WebSocketCallback) -> Closeable): Flowable<WebSocketEvent> =
        Flowable.create({ emitter ->
            val closeable = streamMethod { webSocketEvent ->
                when (webSocketEvent) {
                    is Closed -> emitter.onComplete()
                    is Failure -> emitter.tryOnError(webSocketEvent.error)
                    Open,
                    is Closing,
                    is StreamEvent,
                    is GenericMessage -> emitter.onNext(webSocketEvent)
                }
            }
            emitter.setCancellable { closeable.close() }
        }, BackpressureStrategy.BUFFER)
}
