package social.bigbone.api

import social.bigbone.api.entity.streaming.Event

fun interface WebSocketCallback {

    fun onEvent(event: WebSocketEvent)

}

sealed interface WebSocketEvent {

    data object Open : WebSocketEvent

    data class Closing(
        val code: Int,
        val reason: String
    ) : WebSocketEvent

    data class Closed(
        val code: Int,
        val reason: String
    ) : WebSocketEvent

    data class StreamEvent(val event: Event) : WebSocketEvent

    data class Failure(val error: Throwable) : WebSocketEvent
}

