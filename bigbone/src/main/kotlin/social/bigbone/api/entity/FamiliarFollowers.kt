package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a subset of your follows who also follow some other user.
 *
 * @see <a href="https://docs.joinmastodon.org/entities/FamiliarFollowers/">Mastodon API FamiliarFollowers</a>
 */
@Serializable
data class FamiliarFollowers(
    /**
     * The ID of the [Account] in the database.
     * String cast from an Integer, but not guaranteed to be a number.
     */
    @SerialName("id")
    val id: String,

    /**
     * Accounts you follow that also follow this account.
     */
    @SerialName("accounts")
    val accounts: List<Account>
)
