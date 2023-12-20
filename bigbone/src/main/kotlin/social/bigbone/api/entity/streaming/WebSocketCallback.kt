package social.bigbone.api.entity.streaming

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
