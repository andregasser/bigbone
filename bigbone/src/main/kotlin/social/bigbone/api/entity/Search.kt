package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the results of a search.
 * @see <a href="https://docs.joinmastodon.org/entities/Search/">Mastodon API Search</a>
 */
@Suppress("ktlint:standard:no-blank-line-in-list")
@Serializable
data class Search(
    @SerialName("accounts")
    val accounts: List<Account> = emptyList(),

    /**
     * Statuses which match the given query.
     */
    @SerialName("statuses")
    val statuses: List<Status> = emptyList(),

    /**
     * Hashtags which match the given query.
     */
    @SerialName("hashtags")
    val hashtags: List<Tag> = emptyList()
)
