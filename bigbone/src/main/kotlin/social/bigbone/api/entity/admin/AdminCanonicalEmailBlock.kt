package social.bigbone.api.entity.admin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.api.method.admin.AdminCanonicalEmailBlockMethods

/**
 * Block certain email addresses by their hash.
 *
 * @see <a href="https://docs.joinmastodon.org/entities/Admin_CanonicalEmailBlock/">Mastodon documentation Admin::CanonicalEmailBlock</a>
 */
@Serializable
data class AdminCanonicalEmailBlock(
    /**
     * The ID of the email block in the database.
     * String cast from Integer, but not guaranteed to be a number.
     */
    @SerialName("id")
    val id: String,

    /**
     * The SHA256 hash of the canonical email address.
     */
    @SerialName("canonical_email_hash")
    val canonicalEmailHash: String
)

/**
 * Means of blocking a canonical email address.
 *
 * When blocking a canonical email address via [AdminCanonicalEmailBlockMethods.blockCanonicalEmail], the Mastodon API offers 2 variants:
 *
 *   1. By email address
 *   2. By canonical email hash
 *
 * If either one is supplied, the other one is ignored. To ensure that this fact is understood, this sealed class
 * is used to wrap those two options, disallowing consumers to ever use both at the same time.
 */
sealed class BlockCanonicalEmailVariant(val apiKey: String, val value: String) {
    /**
     * Used to block a canonical email via an email address.
     *
     * @property email The email address to block
     */
    data class ByEmail(val email: String) : BlockCanonicalEmailVariant(
        apiKey = "email",
        value = email
    )

    /**
     * Used to block a canonical email via a hash.
     *
     * @property canonicalEmailHash The email hash to block
     */
    data class ByHash(val canonicalEmailHash: String) : BlockCanonicalEmailVariant(
        apiKey = "canonical_email_hash",
        value = canonicalEmailHash
    )
}
