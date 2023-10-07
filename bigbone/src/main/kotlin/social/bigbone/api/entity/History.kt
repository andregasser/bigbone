package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Usage statistics for given days (typically the past week).
 * @see <a href="https://docs.joinmastodon.org/entities/Tag/#history">Mastodon API Tag history</a>
 */
data class History(

    /**
     * UNIX timestamp on midnight of the given day.
     */
    @SerializedName("day")
    val day: String = "",

    /**
     * The counted usage of the tag within that day, string cast from integer.
     */
    @SerializedName("uses")
    val uses: String = "",

    /**
     * The total of accounts using the tag within that day, string cast from integer.
     */
    @SerializedName("accounts")
    val accounts: String = ""
)
