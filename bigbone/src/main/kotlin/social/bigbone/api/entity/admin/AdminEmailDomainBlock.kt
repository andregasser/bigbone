package social.bigbone.api.entity.admin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime
import social.bigbone.api.entity.data.History

/**
 * Represents an email domain that cannot be used to sign up.
 *
 * @see <a href="https://docs.joinmastodon.org/entities/Admin_EmailDomainBlock/">Mastodon API Admin::EmailDomainBlock documentation</a>
 */
@Serializable
data class AdminEmailDomainBlock(
    /**
     * The ID of the EmailDomainBlock in the database.
     * String cast from an Integer, but not guaranteed to be a number.
     */
    @SerialName("id")
    val id: String,

    /**
     * The email domain that is not allowed to be used for signups.
     */
    @SerialName("domain")
    val domain: String,

    /**
     * The timestamp of the notification.
     */
    @SerialName("created_at")
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: PrecisionDateTime = PrecisionDateTime.InvalidPrecisionDateTime.Unavailable,

    /**
     * Usage statistics for given days (typically the past week).
     */
    @SerialName("history")
    val history: List<History>
)
