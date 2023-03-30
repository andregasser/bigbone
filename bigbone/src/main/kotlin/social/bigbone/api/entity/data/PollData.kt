package social.bigbone.api.entity.data

import com.google.gson.annotations.SerializedName

/**
 * Poll data to be attached to the status. This data class is used when creating or editing poll statuses (see
 * [social.bigbone.api.method.StatusMethods]) and as part of the [social.bigbone.api.entity.ScheduledStatus] entity
 * data class.
 */
data class PollData(
    /**
     * The poll options to be used.
     * @see <a href="https://docs.joinmastodon.org/entities/ScheduledStatus/#params-poll">Mastodon API ScheduledStatus params-poll</a>
     */
    @SerializedName("options")
    val options: List<String> = emptyList(),

    /**
     * How many seconds the poll should last before closing (String cast from Integer).
     */
    @SerializedName("expires_in")
    val expiresIn: String = "",

    /**
     * Whether the poll allows multiple choices.
     */
    @SerializedName("multiple")
    val multiple: Boolean? = null,

    /**
     * Whether the poll should hide total votes until after voting has ended.
     */
    @SerializedName("hide_totals")
    val hideTotals: Boolean? = null
)
