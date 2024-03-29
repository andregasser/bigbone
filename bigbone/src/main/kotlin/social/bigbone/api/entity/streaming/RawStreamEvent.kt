package social.bigbone.api.entity.streaming

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Raw streaming event constantly emitted by the streaming APIs.
 * Can be parsed to a [ParsedStreamEvent] which then contains specific data classes and objects for the different
 * [eventType]s that can occur here.
 * @see <a href="https://docs.joinmastodon.org/methods/streaming/#events">Mastodon streaming#events entity docs</a>
 */
@Serializable
internal data class RawStreamEvent(

    /**
     * Types of stream as subscribed to via the streaming API call, represented by [StreamType].
     */
    @SerialName("stream")
    val stream: List<StreamType>? = null,

    /**
     * Type of event, such as "status.update".
     */
    @SerialName("event")
    val eventType: String,

    /**
     * Payload sent with the event. Content depends on [eventType] type.
     */
    @SerialName("payload")
    val payload: String? = null
)
