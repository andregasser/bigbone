package social.bigbone.api.entity.streaming

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Streaming event constantly emitted by the streaming APIs.
 * @see <a href="https://docs.joinmastodon.org/methods/streaming/#events">Mastodon streaming#events entity docs</a>
 */
@Serializable
data class Event(

    /**
     * Types of stream as subscribed to via the streaming API call, represented by [StreamType].
     */
    @SerialName("stream")
    val stream: List<StreamType>? = null,

    /**
     * Type of event, such as status update, represented by [EventType].
     */
    @SerialName("event")
    val event: EventType? = null,

    /**
     * Payload sent with the event. Content depends on [event] type.
     * See [EventType] for documentation about possible payloads.
     */
    @SerialName("payload")
    val payload: String? = null
)
