package social.bigbone.api.entity.streaming

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Event types that can be used for streaming specific timelines,
 * or received as part of [RawStreamEvent] when using streaming APIs.
 * @see <a href="https://docs.joinmastodon.org/methods/streaming/#events">Mastodon streaming#events entities</a>
 */
@Serializable
internal enum class StreamType {

    /**
     * All public posts known to the server.
     * Analogous to the federated timeline.
     * Available since v1.0.0
     */
    @SerialName("public")
    PUBLIC,

    /**
     * All public posts known to the server, filtered for media attachments.
     * Analogous to the federated timeline with “only media” enabled.
     * Available since v2.4.0
     */
    @SerialName("public:media")
    PUBLIC_MEDIA,

    /**
     * All public posts originating from this server.
     * Analogous to the local timeline.
     * Available since v1.1
     */
    @SerialName("public:local")
    PUBLIC_LOCAL,

    /**
     * All public posts originating from this server, filtered for media attachments.
     * Analogous to the local timeline with “only media” enabled.
     * Available since v2.4.0
     */
    @SerialName("public:local:media")
    PUBLIC_LOCAL_MEDIA,

    /**
     * All public posts originating from other servers.
     * Available since v3.1.4
     */
    @SerialName("public:remote")
    PUBLIC_REMOTE,

    /**
     * All public posts originating from other servers, filtered for media attachments.
     * Available since v3.1.4
     */
    @SerialName("public:remote:media")
    PUBLIC_REMOTE_MEDIA,

    /**
     * All public posts using a certain hashtag.
     * Available since v1.0.0
     */
    @SerialName("hashtag")
    HASHTAG,

    /**
     * All public posts using a certain hashtag, originating from this server.
     * Available since v1.1
     */
    @SerialName("hashtag:local")
    HASHTAG_LOCAL,

    /**
     * Events related to the current user, such as home feed updates and notifications.
     * Available since v1.0.0
     */
    @SerialName("user")
    USER,

    /**
     * Notifications for the current user.
     * Available since v1.4.2
     */
    @SerialName("user:notification")
    USER_NOTIFICATION,

    /**
     * Updates to a specific list.
     * Available since v2.1.0
     */
    @SerialName("list")
    LIST,

    /**
     * Updates to direct conversations.
     * Available since v2.4.0
     */
    @SerialName("direct")
    DIRECT;

    @OptIn(ExperimentalSerializationApi::class)
    val apiName: String get() = StreamType.serializer().descriptor.getElementName(ordinal)
}
