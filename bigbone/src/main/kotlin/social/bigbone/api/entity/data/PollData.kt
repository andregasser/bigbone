package social.bigbone.api.entity.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Poll data to be attached to the status. This data class is used when creating or editing poll statuses (see
 * [social.bigbone.api.method.StatusMethods]) and as part of the [social.bigbone.api.entity.ScheduledStatus] entity
 * data class.
 */
@Serializable
data class PollData(
    /**
     * The poll options to be used.
     * @see <a href="https://docs.joinmastodon.org/entities/ScheduledStatus/#params-poll">Mastodon API ScheduledStatus params-poll</a>
     */
    @SerialName("options")
    val options: List<String> = emptyList(),

    /**
     * How many seconds the poll should last before closing (String cast from Integer).
     */
    @SerialName("expires_in")
    val expiresIn: String = "",

    /**
     * Whether the poll allows multiple choices.
     */
    @SerialName("multiple")
    val multiple: Boolean? = null,

    /**
     * Whether the poll should hide total votes until after voting has ended.
     */
    @SerialName("hide_totals")
    val hideTotals: Boolean? = null
)
