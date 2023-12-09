package social.bigbone.api.entity.streaming

import kotlinx.serialization.json.Json
import social.bigbone.JSON_SERIALIZER
import social.bigbone.api.entity.Announcement
import social.bigbone.api.entity.Conversation
import social.bigbone.api.entity.Notification
import social.bigbone.api.entity.Status
import social.bigbone.api.entity.streaming.EventType.ANNOUNCEMENT
import social.bigbone.api.entity.streaming.EventType.ANNOUNCEMENT_DELETE
import social.bigbone.api.entity.streaming.EventType.ANNOUNCEMENT_REACTION
import social.bigbone.api.entity.streaming.EventType.CONVERSATION
import social.bigbone.api.entity.streaming.EventType.DELETE
import social.bigbone.api.entity.streaming.EventType.ENCRYPTED_MESSAGE
import social.bigbone.api.entity.streaming.EventType.FILTERS_CHANGED
import social.bigbone.api.entity.streaming.EventType.NOTIFICATION
import social.bigbone.api.entity.streaming.EventType.STATUS_UPDATE
import social.bigbone.api.entity.streaming.EventType.UPDATE

/**
 * Stream events emitted by the Mastodon API websocket stream for each [EventType] that can potentially occur.
 * This is the parsed variant of the [RawStreamEvent.eventType] and [RawStreamEvent.payload]
 * turned into easily usable data classes and objects for type-specific consumption.
 */
sealed interface ParsedStreamEvent {

    /**
     * A new Status has appeared.
     * Payload contains a Status cast to a string.
     * Available since v1.0.0
     */
    data class StatusCreated(val createdStatus: Status) : ParsedStreamEvent

    /**
     * A Status has been edited.
     * Available since v3.5.0
     */
    data class StatusEdited(val editedStatus: Status) : ParsedStreamEvent

    /**
     * A status has been deleted.
     * Available since v1.0.0
     */
    data class StatusDeleted(val deletedStatusId: String) : ParsedStreamEvent

    /**
     * A new notification has appeared.
     * Available since v1.4.2
     */
    data class NewNotification(val newNotification: Notification) : ParsedStreamEvent

    /**
     * Keyword filters have been changed.
     * Does not contain a payload for WebSocket connections.
     * Available since v2.4.3
     */
    data object FiltersChanged : ParsedStreamEvent

    /**
     * A direct conversation has been updated.
     * Available since v2.6.0
     */
    data class ConversationUpdated(val updatedConversation: Conversation) : ParsedStreamEvent

    /**
     * An announcement has been published.
     * Available since v3.1.0
     */
    data class AnnouncementPublished(val publishedAnnouncement: Announcement) : ParsedStreamEvent

    /**
     * An announcement has received an emoji reaction.
     * Available since v3.1.0
     */
    data class AnnouncementReactionReceived(val reactionPayload: String) : ParsedStreamEvent

    /**
     * An announcement has been deleted.
     * Available since v3.1.0
     */
    data class AnnouncementDeleted(val deletedAnnouncementId: String) : ParsedStreamEvent

    /**
     * An encrypted message has been received.
     * Implemented in v3.2.0 but currently unused
     */
    data object EncryptedMessageReceived : ParsedStreamEvent

    companion object {
        internal fun RawStreamEvent.toStreamEvent(json: Json = JSON_SERIALIZER): ParsedStreamEvent? {
            return when (eventType) {
                UPDATE -> {
                    requireNotNull(payload) { "Payload was null for update $eventType but mustn’t be." }
                    StatusCreated(createdStatus = json.decodeFromString(payload))
                }

                DELETE -> {
                    requireNotNull(payload) { "Payload was null for update $eventType but mustn’t be." }
                    StatusDeleted(deletedStatusId = payload)
                }

                NOTIFICATION -> {
                    requireNotNull(payload) { "Payload was null for update $eventType but mustn’t be." }
                    NewNotification(newNotification = json.decodeFromString(payload))
                }

                CONVERSATION -> {
                    requireNotNull(payload) { "Payload was null for update $eventType but mustn’t be." }
                    ConversationUpdated(updatedConversation = json.decodeFromString(payload))
                }

                ANNOUNCEMENT -> {
                    requireNotNull(payload) { "Payload was null for update $eventType but mustn’t be." }
                    AnnouncementPublished(publishedAnnouncement = json.decodeFromString(payload))
                }

                ANNOUNCEMENT_REACTION -> {
                    requireNotNull(payload) { "Payload was null for update $eventType but mustn’t be." }
                    AnnouncementReactionReceived(reactionPayload = json.decodeFromString(payload))
                }

                ANNOUNCEMENT_DELETE -> {
                    requireNotNull(payload) { "Payload was null for update $eventType but mustn’t be." }
                    AnnouncementDeleted(deletedAnnouncementId = payload)
                }

                STATUS_UPDATE -> {
                    requireNotNull(payload) { "Payload was null for update $eventType but mustn’t be." }
                    StatusEdited(editedStatus = json.decodeFromString(payload))
                }

                FILTERS_CHANGED -> FiltersChanged
                ENCRYPTED_MESSAGE -> EncryptedMessageReceived
                else -> null
            }
        }
    }
}
