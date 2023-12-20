package social.bigbone.api.entity.admin

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime

/**
 * Represents an IP address range that cannot be used to sign up.
 * @see <a href="https://docs.joinmastodon.org/entities/Admin_IpBlock/">Mastodon API Admin::IpBlock/</a>
 */
@Serializable
data class AdminIpBlock(
    /**
     * The ID of the DomainBlock in the database.
     */
    @SerialName("id")
    val id: String = "0",

    /**
     * The IP address range that is not allowed to federate.
     */
    @SerialName("ip")
    val ip: String = "",

    /**
     * The associated policy with this IP block.
     */
    @SerialName("severity")
    val severity: Severity? = null,

    /**
     * The recorded reason for this IP block.
     */
    @SerialName("comment")
    val comment: String = "",

    /**
     * When the IP block was created.
     */
    @SerialName("created_at")
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: PrecisionDateTime = PrecisionDateTime.InvalidPrecisionDateTime.Unavailable,

    /**
     * When the IP block will expire.
     */
    @SerialName("expires_at")
    @Serializable(with = DateTimeSerializer::class)
    val expiresAt: PrecisionDateTime = PrecisionDateTime.InvalidPrecisionDateTime.Unavailable
) {
    /**
     * Specifies the severity of the blocked ip.
     */
    @Serializable
    enum class Severity {

        /**
         *  Any signup from this IP range will create a pending account.
         */
        @SerialName("sign_up_requires_approval")
        SIGN_UP_REQUIRES_APPROVAL,

        /**
         *  Any signup from this IP range will be rejected.
         */
        @SerialName("sign_up_block")
        SIGN_UP_BLOCK,

        /**
         *  Any activity from this IP range will be rejected entirely.
         */
        @SerialName("no_access")
        NO_ACCESS;

        @OptIn(ExperimentalSerializationApi::class)
        val apiName: String get() = serializer().descriptor.getElementName(ordinal)
    }
}
