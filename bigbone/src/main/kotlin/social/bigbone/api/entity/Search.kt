package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents the results of a search.
 * @see <a href="https://docs.joinmastodon.org/entities/Search/">Mastodon API Search</a>
 */
data class Search(
    @SerializedName("accounts")
    val accounts: List<Account> = emptyList(),

    /**
     * Statuses which match the given query.
     */
    @SerializedName("statuses")
    val statuses: List<Status> = emptyList(),

    /**
     * Hashtags which match the given query.
     */
    @SerializedName("hashtags")
    val hashtags: List<Tag> = emptyList()
)
