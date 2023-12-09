package social.bigbone.api

import social.bigbone.api.WebSocketEvent.StreamEvent
import social.bigbone.api.entity.streaming.Event
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
sealed interface WebSocketEvent {

    /**
     * The websocket has been opened. A connection is established.
     */
    data object Open : WebSocketEvent

    /**
     * The websocket is about to close.
     */
    data class Closing(
        val code: Int,
        val reason: String
    ) : WebSocketEvent

    /**
     * The websocket is now closed.
     */
    data class Closed(
        val code: Int,
        val reason: String
    ) : WebSocketEvent

    /**
     * An event from the Mastodon API has been received via the websocket.
     */
    data class StreamEvent(val event: Event) : WebSocketEvent

    /**
     * An error occurred during the websocket connection.
     * This is a final event: No further calls to the [WebSocketCallback] emitting these events
     * will be made.
     */
    data class Failure(val error: Throwable) : WebSocketEvent
}
