package social.bigbone.api.entity.streaming

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Event types that can be received as part of [RawStreamEvent] when using streaming APIs.
 * @see <a href="https://docs.joinmastodon.org/methods/streaming/#events">Mastodon streaming#events entities</a>
 */
@Serializable
internal enum class EventType {

    /**
     * A new Status has appeared.
     * Payload contains a Status cast to a string.
     * Available since v1.0.0
     */
    @SerialName("update")
    UPDATE,

    /**
     * A status has been deleted.
     * Payload contains the String ID of the deleted Status.
     * Available since v1.0.0
     */
    @SerialName("delete")
    DELETE,

    /**
     * A new notification has appeared.
     * Payload contains a Notification cast to a string.
     * Available since v1.4.2
     */
    @SerialName("notification")
    NOTIFICATION,

    /**
     * Keyword filters have been changed.
     * Either does not contain a payload (for WebSocket connections),
     * or contains an undefined payload (for HTTP connections).
     * Available since v2.4.3
     */
    @SerialName("filters_changed")
    FILTERS_CHANGED,

    /**
     * A direct conversation has been updated.
     * Payload contains a Conversation cast to a string.
     * Available since v2.6.0
     */
    @SerialName("conversation")
    CONVERSATION,

    /**
     * An announcement has been published.
     * Payload contains an Announcement cast to a string.
     * Available since v3.1.0
     */
    @SerialName("announcement")
    ANNOUNCEMENT,

    /**
     * An announcement has received an emoji reaction.
     * Payload contains a Hash (with name, count, and announcement_id) cast to a string.
     * Available since v3.1.0
     */
    @SerialName("announcement.reaction")
    ANNOUNCEMENT_REACTION,

    /**
     * An announcement has been deleted.
     * Payload contains the String ID of the deleted Announcement.
     * Available since v3.1.0
     */
    @SerialName("announcement.delete")
    ANNOUNCEMENT_DELETE,

    /**
     * A Status has been edited.
     * Payload contains a Status cast to a string.
     * Available since v3.5.0
     */
    @SerialName("status.update")
    STATUS_UPDATE,

    /**
     * An encrypted message has been received.
     * Implemented in v3.2.0 but currently unused
     */
    @SerialName("encrypted_message")
    ENCRYPTED_MESSAGE;

    @OptIn(ExperimentalSerializationApi::class)
    val apiName: String get() = EventType.serializer().descriptor.getElementName(ordinal)
}
