package social.bigbone.api.entity.admin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime

/**
 * Represents a domain allowed to federate.
 * @see <a href="https://docs.joinmastodon.org/entities/Admin_DomainAllow/">Mastodon API Admin::DomainAllow/</a>
 */
@Serializable
data class AdminDomainAllow(
    /**
     * The ID of the DomainAllow in the database.
     */
    @SerialName("id")
    val id: String = "0",

    /**
     * The domain that is allowed to federate.
     */
    @SerialName("domain")
    val domain: String = "",

    /**
     * When the domain was allowed to federate.
     */
    @SerialName("created_at")
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: PrecisionDateTime = PrecisionDateTime.InvalidPrecisionDateTime.Unavailable
)
