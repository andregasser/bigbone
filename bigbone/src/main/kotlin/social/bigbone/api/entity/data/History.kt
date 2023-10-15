package social.bigbone.api.entity.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Usage statistics for given days (typically the past week).
 * @see <a href="https://docs.joinmastodon.org/entities/Tag/#history">Mastodon API Tag history</a>
 */
@Serializable
data class History(

    /**
     * UNIX timestamp on midnight of the given day.
     */
    @SerialName("day")
    val day: String = "",

    /**
     * The counted usage of the tag within that day, string cast from integer.
     */
    @SerialName("uses")
    val uses: String = "",

    /**
     * The total of accounts using the tag within that day, string cast from integer.
     */
    @SerialName("accounts")
    val accounts: String = ""
)
