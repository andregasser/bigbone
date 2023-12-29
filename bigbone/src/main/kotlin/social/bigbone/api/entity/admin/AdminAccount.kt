package social.bigbone.api.entity.admin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime
import social.bigbone.api.entity.Account
import social.bigbone.api.entity.Role

/**
 * Admin-level information about a given account.
 */
@Serializable
data class AdminAccount(
    /**
     * The ID of the account in the database.
     * String cast from an Integer, but not guaranteed to be a number.
     */
    @SerialName("id")
    val id: String,

    /**
     * The username of the account.
     */
    @SerialName("username")
    val username: String,

    /**
     * The domain of the account, if it is remote.
     * Will be null for local accounts.
     */
    @SerialName("domain")
    val domain: String? = null,

    /**
     * When the account was first discovered.
     */
    @SerialName("created_at")
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: PrecisionDateTime = PrecisionDateTime.InvalidPrecisionDateTime.Unavailable,

    /**
     * The email address associated with the account.
     */
    @SerialName("email")
    val email: String,

    /**
     * The IP address last used to log in to this account.
     */
    @SerialName("ip")
    val ip: String? = null,

    /**
     * All known IP addresses associated with this account.
     */
    @SerialName("ips")
    val adminIPs: List<IP>,

    /**
     * The current role of the account.
     */
    @SerialName("role")
    val role: Role,

    /**
     * Whether the account has confirmed their email address.
     */
    @SerialName("confirmed")
    val confirmed: Boolean,

    /**
     * Whether the account is currently suspended.
     */
    @SerialName("suspended")
    val suspended: Boolean,

    /**
     * Whether the account is currently silenced.
     */
    @SerialName("silenced")
    val silenced: Boolean,

    /**
     * Whether the account is currently disabled.
     */
    @SerialName("disabled")
    val disabled: Boolean,

    /**
     * Whether the account is currently approved.
     */
    @SerialName("approved")
    val approved: Boolean,

    /**
     * Whether these account’s post are currently flagged sensitive.
     */
    @SerialName("sensitized")
    val sensitized: Boolean? = null,

    /**
     * The Locale of the account.
     * String containing the ISO 639 Part 1 two-letter language code.
     */
    @SerialName("locale")
    val locale: String? = null,

    /**
     * The reason given when requesting an invitation.
     * Applies only to instances that require manual approval of registrations.
     */
    @SerialName("invite_request")
    val inviteRequest: String? = null,

    /**
     * User-level information about the account.
     */
    @SerialName("account")
    val account: Account,

    /**
     * The ID of the Application that created this account, if applicable.
     * String cast from an Integer, but not guaranteed to be a number.
     */
    @SerialName("created_by_application_id")
    val createdByApplicationId: String? = null,

    /**
     * The ID of the Account that invited this user, if applicable.
     * String cast from an Integer, but not guaranteed to be a number.
     */
    @SerialName("invited_by_application_id")
    val invitedByApplicationId: String? = null
) {
    /**
     * The IP address last used to log in to this account.
     */
    @Serializable
    data class IP(
        /**
         * The IP address.
         */
        @SerialName("ip")
        val ipAddress: String,

        /**
         * The timestamp of when the IP address was last used for this account.
         */
        @SerialName("used_at")
        @Serializable(with = DateTimeSerializer::class)
        val usedAt: PrecisionDateTime = PrecisionDateTime.InvalidPrecisionDateTime.Unavailable
    )
}
