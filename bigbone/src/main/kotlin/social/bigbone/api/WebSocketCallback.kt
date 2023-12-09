package social.bigbone.api

import social.bigbone.api.entity.streaming.EventType
import social.bigbone.api.entity.streaming.ParsedStreamEvent
import social.bigbone.api.method.StreamingMethods

/**
 * Callback interface you need to implement to receive [WebSocketEvent]s that are sent
 * when using streaming functions in [StreamingMethods].
 */
fun interface WebSocketCallback {

    /**
     * Callback function that is called for every event received during websocket connection.
     *
     * @param event Change events for the websocket connection established during streaming.
     */
    fun onEvent(event: WebSocketEvent)
}

/**
 * Events received during websocket connection.
 *
 * [StreamEvent] is likely the one you’ll be most interested in as it wraps the return type returned
 * by the Mastodon API when streaming via a websocket connection.
 * All others are more technical events pertaining to the websocket connection itself.
 */
sealed interface WebSocketEvent

/**
 * Technical events that occurred during websocket connection, such as [Open] or [Closed].
 */
sealed interface TechnicalEvent : WebSocketEvent

/**
 * (Ideally parsed) event received through the websocket from the Mastodon server.
 * In most situations, this—and specifically [StreamEvent]—is likely what you’re looking for when streaming.
 */
sealed interface MastodonApiEvent : WebSocketEvent

/**
 * The websocket has been opened. A connection is established.
 */
data object Open : TechnicalEvent

/**
 * The websocket is about to close.
 */
data class Closing(
    val code: Int,
    val reason: String
) : TechnicalEvent

/**
 * The websocket is now closed.
 */
data class Closed(
    val code: Int,
    val reason: String
) : TechnicalEvent

/**
 * An event from the Mastodon API has been received via the websocket.
 *
 * @property event The parsed stream event. May be null if we got an [EventType] we don’t know yet.
 */
data class StreamEvent(val event: ParsedStreamEvent?) : MastodonApiEvent

/**
 * A message received via the websocket that could not be parsed to a [ParsedStreamEvent].
 * Instead of [StreamEvent], an object of this type with the [text] received verbatim is returned.
 */
data class GenericMessage(val text: String) : MastodonApiEvent

/**
 * An error occurred during the websocket connection.
 * This is a final event:
 * No further calls to the [WebSocketCallback] emitting these events will be made.
 */
data class Failure(val error: Throwable) : TechnicalEvent
