package social.bigbone.api.entity.streaming

import kotlinx.serialization.json.Json
import social.bigbone.JSON_SERIALIZER
import social.bigbone.api.entity.Announcement
import social.bigbone.api.entity.Conversation
import social.bigbone.api.entity.Notification
import social.bigbone.api.entity.Status

/**
 * Stream events emitted by the Mastodon API websocket stream for each event type that can occur.
 * This is the parsed variant of the [RawStreamEvent.eventType] and [RawStreamEvent.payload]
 * turned into easily usable data classes and objects for type-specific consumption.
 */
sealed interface ParsedStreamEvent {

    /**
     * A new Status has appeared.
     * Payload contains a Status cast to a string.
     * Available since Mastodon server version v1.0.0
     */
    data class StatusCreated(val createdStatus: Status) : ParsedStreamEvent

    /**
     * A Status has been edited.
     * Available since Mastodon server version v3.5.0
     */
    data class StatusEdited(val editedStatus: Status) : ParsedStreamEvent

    /**
     * A status has been deleted.
     * Available since Mastodon server version v1.0.0
     */
    data class StatusDeleted(val deletedStatusId: String) : ParsedStreamEvent

    /**
     * A new notification has appeared.
     * Available since Mastodon server version v1.4.2
     */
    data class NewNotification(val newNotification: Notification) : ParsedStreamEvent

    /**
     * Keyword filters have been changed.
     * Does not contain a payload for WebSocket connections.
     * Available since Mastodon server version v2.4.3
     */
    data object FiltersChanged : ParsedStreamEvent

    /**
     * A direct conversation has been updated.
     * Available since Mastodon server version v2.6.0
     */
    data class ConversationUpdated(val updatedConversation: Conversation) : ParsedStreamEvent

    /**
     * An announcement has been published.
     * Available since Mastodon server version v3.1.0
     */
    data class AnnouncementPublished(val publishedAnnouncement: Announcement) : ParsedStreamEvent

    /**
     * An announcement has received an emoji reaction.
     * Available since Mastodon server version v3.1.0
     */
    data class AnnouncementReactionReceived(val reaction: StreamingAnnouncementReaction) : ParsedStreamEvent

    /**
     * An announcement has been deleted.
     * Available since Mastodon server version v3.1.0
     */
    data class AnnouncementDeleted(val deletedAnnouncementId: String) : ParsedStreamEvent

    /**
     * An encrypted message has been received.
     * Implemented in v3.2.0 but currently unused
     */
    data object EncryptedMessageReceived : ParsedStreamEvent

    /**
     * Type received via the Mastodon API that is not (yet) available in BigBone.
     */
    data class UnknownType(val eventType: String, val payload: String?) : ParsedStreamEvent

    companion object {
        internal fun RawStreamEvent.toStreamEvent(json: Json = JSON_SERIALIZER): ParsedStreamEvent {
            return when (eventType) {
                "update" -> {
                    requireNotNull(payload) { "Payload was null for update $eventType but mustn't be." }
                    StatusCreated(createdStatus = json.decodeFromString(payload))
                }

                "delete" -> {
                    requireNotNull(payload) { "Payload was null for update $eventType but mustn't be." }
                    StatusDeleted(deletedStatusId = payload)
                }

                "notification" -> {
                    requireNotNull(payload) { "Payload was null for update $eventType but mustn't be." }
                    NewNotification(newNotification = json.decodeFromString(payload))
                }

                "conversation" -> {
                    requireNotNull(payload) { "Payload was null for update $eventType but mustn't be." }
                    ConversationUpdated(updatedConversation = json.decodeFromString(payload))
                }

                "announcement" -> {
                    requireNotNull(payload) { "Payload was null for update $eventType but mustn't be." }
                    AnnouncementPublished(publishedAnnouncement = json.decodeFromString(payload))
                }

                "announcement.reaction" -> {
                    requireNotNull(payload) { "Payload was null for update $eventType but mustn't be." }
                    AnnouncementReactionReceived(reaction = json.decodeFromString(payload))
                }

                "announcement.delete" -> {
                    requireNotNull(payload) { "Payload was null for update $eventType but mustn't be." }
                    AnnouncementDeleted(deletedAnnouncementId = payload)
                }

                "status.update" -> {
                    requireNotNull(payload) { "Payload was null for update $eventType but mustn't be." }
                    StatusEdited(editedStatus = json.decodeFromString(payload))
                }

                "filters_changed" -> FiltersChanged
                "encrypted_message" -> EncryptedMessageReceived
                else -> UnknownType(eventType, payload)
            }
        }
    }
}
