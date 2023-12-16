package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.Parameters
import social.bigbone.api.WebSocketCallback
import social.bigbone.api.entity.streaming.StreamType
import social.bigbone.api.entity.streaming.StreamType.DIRECT
import social.bigbone.api.entity.streaming.StreamType.HASHTAG
import social.bigbone.api.entity.streaming.StreamType.HASHTAG_LOCAL
import social.bigbone.api.entity.streaming.StreamType.LIST
import social.bigbone.api.entity.streaming.StreamType.PUBLIC
import social.bigbone.api.entity.streaming.StreamType.PUBLIC_LOCAL
import social.bigbone.api.entity.streaming.StreamType.PUBLIC_LOCAL_MEDIA
import social.bigbone.api.entity.streaming.StreamType.PUBLIC_MEDIA
import social.bigbone.api.entity.streaming.StreamType.PUBLIC_REMOTE
import social.bigbone.api.entity.streaming.StreamType.PUBLIC_REMOTE_MEDIA
import social.bigbone.api.entity.streaming.StreamType.USER
import social.bigbone.api.entity.streaming.StreamType.USER_NOTIFICATION
import social.bigbone.api.entity.streaming.WebSocketEvent
import java.io.Closeable

/**
 * Allows access to API methods with endpoints having an "api/vX/streaming" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/streaming/">Mastodon streaming API methods</a>
 */
class StreamingMethods(private val client: MastodonClient) {

    private fun stream(
        streamType: StreamType,
        callback: WebSocketCallback,
        listId: String? = null,
        tagName: String? = null
    ): Closeable {
        val webSocket = client.stream(
            parameters = Parameters().apply {
                append("stream", streamType.apiName)

                if (streamType == LIST) {
                    requireNotNull(listId) {
                        "When requesting $streamType for stream, a non-null list ID needs to be specified"
                    }
                    append("list", listId)
                }

                if (streamType == HASHTAG || streamType == HASHTAG_LOCAL) {
                    requireNotNull(tagName) {
                        "When requesting $streamType for stream, a non-null tag name needs to be specified"
                    }
                    append("tag", tagName)
                }
            },
            callback = callback
        )

        return Closeable {
            webSocket.close(
                /*
                1000 indicates a normal closure,
                meaning that the purpose for which the connection was established has been fulfilled.
                see: https://datatracker.ietf.org/doc/html/rfc6455#section-7.4
                 */
                code = 1000,
                reason = null
            )
        }
    }

    /**
     * Verify that the streaming service is alive before connecting to it.
     * Returns "OK" if the streaming service is alive.
     * @see <a href="https://docs.joinmastodon.org/methods/streaming/#health">streaming/#health API documentation</a>
     */
    fun checkServerHealth() {
        client.performAction(
            endpoint = "api/v1/streaming/health",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Stream all public posts known to this server. Analogous to the federated timeline.
     *
     * @param onlyMedia Filter for media attachments. Analogous to the federated timeline with “only media” enabled.
     * @param callback Your implementation of [WebSocketCallback] to receive stream of [WebSocketEvent]s.
     * @return [Closeable] for you to close the websocket once you’re done with streaming.
     */
    fun federatedPublic(
        onlyMedia: Boolean,
        callback: WebSocketCallback
    ): Closeable {
        return stream(
            streamType = if (onlyMedia) PUBLIC_MEDIA else PUBLIC,
            callback = callback
        )
    }

    /**
     * Stream all public posts originating from this server. Analogous to the local timeline.
     *
     * @param onlyMedia Filter for media attachments. Analogous to the local timeline with “only media” enabled.
     * @param callback Your implementation of [WebSocketCallback] to receive stream of [WebSocketEvent]s.
     * @return [Closeable] for you to close the websocket once you’re done with streaming.
     */
    fun localPublic(
        onlyMedia: Boolean,
        callback: WebSocketCallback
    ): Closeable {
        return stream(
            streamType = if (onlyMedia) PUBLIC_LOCAL_MEDIA else PUBLIC_LOCAL,
            callback = callback
        )
    }

    /**
     * Stream all public posts originating from other servers.
     *
     * @param onlyMedia Filter for media attachments.
     * @param callback Your implementation of [WebSocketCallback] to receive stream of [WebSocketEvent]s.
     * @return [Closeable] for you to close the websocket once you’re done with streaming.
     */
    fun remotePublic(
        onlyMedia: Boolean,
        callback: WebSocketCallback
    ): Closeable {
        return stream(
            streamType = if (onlyMedia) PUBLIC_REMOTE_MEDIA else PUBLIC_REMOTE,
            callback = callback
        )
    }

    /**
     * Stream all public posts using the hashtag [tagName].
     *
     * @param tagName Hashtag the public posts you want to stream should have.
     * @param onlyFromThisServer Filter for public posts originating from this server.
     * @param callback Your implementation of [WebSocketCallback] to receive stream of [WebSocketEvent]s.
     * @return [Closeable] for you to close the websocket once you’re done with streaming.
     */
    fun hashtag(
        tagName: String,
        onlyFromThisServer: Boolean,
        callback: WebSocketCallback
    ): Closeable {
        return stream(
            streamType = if (onlyFromThisServer) HASHTAG_LOCAL else HASHTAG,
            tagName = tagName,
            callback = callback
        )
    }

    /**
     * Stream all events related to the current user, such as home feed updates and notifications.
     *
     * @param callback Your implementation of [WebSocketCallback] to receive stream of [WebSocketEvent]s.
     * @return [Closeable] for you to close the websocket once you’re done with streaming.
     */
    fun user(callback: WebSocketCallback): Closeable {
        return stream(
            streamType = USER,
            callback = callback
        )
    }

    /**
     * Stream all notifications for the current user.
     *
     * @param callback Your implementation of [WebSocketCallback] to receive stream of [WebSocketEvent]s.
     * @return [Closeable] for you to close the websocket once you’re done with streaming.
     */
    fun userNotifications(callback: WebSocketCallback): Closeable {
        return stream(
            streamType = USER_NOTIFICATION,
            callback = callback
        )
    }

    /**
     * Stream updates to the list with [listId].
     *
     * @param listId List you want to receive updates for.
     * @param callback Your implementation of [WebSocketCallback] to receive stream of [WebSocketEvent]s.
     * @return [Closeable] for you to close the websocket once you’re done with streaming.
     */
    fun list(
        listId: String,
        callback: WebSocketCallback
    ): Closeable {
        return stream(
            streamType = LIST,
            listId = listId,
            callback = callback
        )
    }

    /**
     * Stream all updates to direct conversations.
     *
     * @param callback Your implementation of [WebSocketCallback] to receive stream of [WebSocketEvent]s.
     * @return [Closeable] for you to close the websocket once you’re done with streaming.
     */
    fun directConversations(callback: WebSocketCallback): Closeable {
        return stream(
            streamType = DIRECT,
            callback = callback
        )
    }
}
