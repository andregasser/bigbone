package social.bigbone.api.entity.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Usage statistics for given days (typically the past week).
 * @see <a href="https://docs.joinmastodon.org/entities/Tag/#history">Mastodon API Tag history</a>
 * @see <a href="https://docs.joinmastodon.org/entities/PreviewCard/#history">Mastodon API PreviewCard history
 */
@Suppress("ktlint:standard:no-blank-line-in-list")
@Serializable
data class History(
    /**
     * UNIX timestamp on midnight of the given day.
     */
    @SerialName("day")
    val day: String = "",

    /**
     * The counted statuses or usages of the tag within that day.
     * String cast from integer.
     */
    @SerialName("uses")
    val uses: String = "",

    /**
     * The total of accounts using the tag or link within that day.
     * String cast from integer.
     */
    @SerialName("accounts")
    val accounts: String = ""
)
