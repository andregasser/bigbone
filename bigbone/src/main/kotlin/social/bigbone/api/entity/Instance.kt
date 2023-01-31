package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents the software instance of Mastodon running on this domain.
 * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
 */
data class Instance(
    /**
     * The domain name of the instance.
     */
    @SerializedName("uri")
    val uri: String = "",

    /**
     * The title of the website.
     */
    @SerializedName("title")
    val title: String = "",

    /**
     * A short, plain-text description defined by the admin.
     */
    @SerializedName("short_description")
    val shortDescription: String = "",

    /**
     * An HTML-permitted description of the Mastodon site.
     */
    @SerializedName("description")
    val description: String = "",

    /**
     * An email that may be contacted for any inquiries.
     */
    @SerializedName("email")
    val email: String = "",

    /**
     * The version of Mastodon installed on the instance.
     */
    @SerializedName("version")
    val version: String = "",

    /**
     * URLs of interest for clients apps.
     */
    @SerializedName("urls")
    val urls: InstanceUrls? = null,

    /**
     * Statistics about how much information the instance contains.
     */
    @SerializedName("stats")
    val stats: InstanceStats? = null,

    /**
     * Banner image for the website.
     */
    @SerializedName("thumbnail")
    val thumbnail: String = "",

    /**
     * Primary languages of the website and its staff.
     */
    @SerializedName("languages")
    val languages: List<String> = emptyList(),

    /**
     * Whether registrations are enabled.
     */
    @SerializedName("registrations")
    val registrations: Boolean = false,

    /**
     * Whether registrations require moderator approval.
     */
    @SerializedName("approval_required")
    val approvalRequired: Boolean = false,

    /**
     * Whether invites are enabled.
     */
    @SerializedName("invites_enabled")
    val invitesEnabled: Boolean = false,

    /**
     * A user that can be contacted, as an alternative to email.
     */
    @SerializedName("contact_account")
    val contactAccount: Account? = null
)
