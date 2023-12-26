package social.bigbone.api.entity.admin


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
