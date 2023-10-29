package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime
import social.bigbone.PrecisionDateTime.InvalidPrecisionDateTime

/**
 * Represents a poll attached to a status.
 * @see <a href="https://docs.joinmastodon.org/entities/Poll/">Mastodon API Poll</a>
 */
@Serializable
data class Poll(
    /**
     * The ID of the poll in the database.
     */
    @SerialName("id")
    val id: String = "",

    /**
     * When the poll ends.
     * [InvalidPrecisionDateTime.Unavailable] if the poll does not end.
     */
    @SerialName("expires_at")
    @Serializable(with = DateTimeSerializer::class)
    val expiresAt: PrecisionDateTime = InvalidPrecisionDateTime.Unavailable,

    /**
     * Is the poll currently expired?
     */
    @SerialName("expired")
    val expired: Boolean = false,

    /**
     * Does the poll allow multiple-choice answers?
     */
    @SerialName("multiple")
    val multiple: Boolean = false,

    /**
     * How many votes have been received.
     */
    @SerialName("votes_count")
    val votesCount: Long = 0,

    /**
     * How many unique accounts have voted on a multiple-choice poll; integer, or null if [multiple] is false.
     */
    @SerialName("voters_count")
    val votersCount: Long? = null,

    /**
     * Possible answers for the poll.
     */
    @SerialName("options")
    val options: List<Option> = emptyList(),

    /**
     * Custom emoji to be used for rendering poll options.
     */
    @SerialName("emojis")
    val emojis: List<CustomEmoji> = emptyList(),

    /**
     * When called with a user token, has the authorized user voted?
     */
    @SerialName("voted")
    val voted: Boolean? = null,

    /**
     * When called with a user token, which options has the authorized user chosen? Contains an array of index values for [options].
     */
    @SerialName("own_votes")
    val ownVotes: List<Int>? = null

) {
    /**
     * Possible answers for the poll.
     * @see <a href="https://docs.joinmastodon.org/entities/Poll/#Option">Mastodon API Poll::Option</a>
     */
    @Serializable
    data class Option(
        /**
         * The text value of the poll option.
         */
        @SerialName("title")
        val title: String = "",

        /**
         * The total number of received votes for this option; integer, or null if results are not published yet.
         */
        @SerialName("votes_count")
        val votesCount: Long? = null
    )
}
