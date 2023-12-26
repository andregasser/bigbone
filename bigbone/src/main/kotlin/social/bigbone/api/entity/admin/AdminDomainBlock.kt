package social.bigbone.api.entity.admin

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime

/**
 * Represents a domain limited from federating.
 *
 * @see <a href="https://docs.joinmastodon.org/entities/Admin_DomainBlock/">Mastodon documentation Admin::DomainBlock</a>
 */
@Serializable
data class AdminDomainBlock(
    /**
     * The ID of the DomainBlock in the database.
     * String, cast from an integer, but not guaranteed to be a number.
     */
    @SerialName("id")
    val id: String,

    /**
     * The domain that is not allowed to federate.
     */
    @SerialName("domain")
    val domain: String,

    /**
     * When the domain was blocked from federating.
     */
    @SerialName("created_at")
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: PrecisionDateTime = PrecisionDateTime.InvalidPrecisionDateTime.Unavailable,

    /**
     * The policy to be applied by this domain block.
     */
    @SerialName("severity")
    val severity: Severity,

    /**
     * Whether to reject media attachments from this domain.
     */
    @SerialName("reject_media")
    val rejectMedia: Boolean,

    /**
     * Whether to reject reports from this domain.
     */
    @SerialName("reject_reports")
    val rejectReports: Boolean,

    /**
     * Private note about this domain block, visible only to admins.
     */
    @SerialName("private_comment")
    val privateComment: String?,

    /**
     * Public note about this domain block, optionally shown on the About page.
     */
    @SerialName("public_comment")
    val publicComment: String?,

    /**
     * Whether to obfuscate public displays of this domain block.
     */
    @SerialName("obfuscate")
    val obfuscate: Boolean
) {
    /**
     * The policy to be applied by this domain block.
     */
    @Serializable
    enum class Severity {
        /**
         * Account statuses from this domain will be hidden by default.
         */
        @SerialName("silence")
        SILENCE,

        /**
         * All incoming data from this domain will be rejected.
         */
        @SerialName("suspend")
        SUSPEND,

        /**
         * Do nothing. Allows for rejecting media or reports.
         */
        @SerialName("noop")
        NOOP;

        @OptIn(ExperimentalSerializationApi::class)
        val apiName: String get() = serializer().descriptor.getElementName(ordinal)
    }
}
