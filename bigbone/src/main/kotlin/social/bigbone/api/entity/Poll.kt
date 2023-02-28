package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents a poll attached to a status.
 * @see <a href="https://docs.joinmastodon.org/entities/Poll/">Mastodon API Poll</a>
 */
data class Poll(
    /**
     * The ID of the poll in the database.
     */
    @SerializedName("id")
    val id: String = "",

    /**
     * When the poll ends. ISO 8601 Datetime string, or null if the poll does not end.
     */
    @SerializedName("expires_at")
    val expiresAt: String? = null,

    /**
     * Is the poll currently expired?
     */
    @SerializedName("expired")
    val expired: Boolean = false,

    /**
     * Does the poll allow multiple-choice answers?
     */
    @SerializedName("multiple")
    val multiple: Boolean = false,

    /**
     * How many votes have been received.
     */
    @SerializedName("votes_count")
    val votesCount: Int = 0,

    /**
     * How many unique accounts have voted on a multiple-choice poll; integer, or null if [multiple] is false.
     */
    @SerializedName("voters_count")
    val votersCount: Int? = null,

    /**
     * Possible answers for the poll.
     */
    @SerializedName("options")
    val options: List<Option> = emptyList(),

    /**
     * Custom emoji to be used for rendering poll options.
     */
    @SerializedName("emojis")
    val emojis: List<CustomEmoji> = emptyList(),

    /**
     * When called with a user token, has the authorized user voted?
     */
    @SerializedName("voted")
    val voted: Boolean? = null,

    /**
     * When called with a user token, which options has the authorized user chosen? Contains an array of index values for [options].
     */
    @SerializedName("own_votes")
    val ownVotes: List<Int>? = null

) {
    /**
     * Possible answers for the poll.
     * @see <a href="https://docs.joinmastodon.org/entities/Poll/#Option">Mastodon API Poll::Option</a>
     */
    data class Option(
        /**
         * The text value of the poll option.
         */
        @SerializedName("title")
        val title: String = "",

        /**
         * The total number of received votes for this option; integer, or null if results are not published yet.
         */
        @SerializedName("votes_count")
        val votesCount: Int? = null
    )
}
