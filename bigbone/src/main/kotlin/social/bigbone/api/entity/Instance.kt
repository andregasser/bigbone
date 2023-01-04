package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

data class Instance(
    @SerializedName("uri")
    val uri: String = "",

    @SerializedName("title")
    val title: String = "",

    @SerializedName("short_description")
    val shortDescription: String = "",

    @SerializedName("description")
    val description: String = "",

    @SerializedName("email")
    val email: String = "",

    @SerializedName("version")
    val version: String = "",

    @SerializedName("urls")
    val urls: InstanceUrls? = null,

    @SerializedName("stats")
    val stats: InstanceStats? = null,

    @SerializedName("thumbnail")
    val thumbnail: String = "",

    @SerializedName("languages")
    val languages: List<String> = emptyList(),

    @SerializedName("registrations")
    val registrations: Boolean = false,

    @SerializedName("approval_required")
    val approvalRequired: Boolean = false,

    @SerializedName("invites_enabled")
    val invitesEnabled: Boolean = false,

    @SerializedName("contact_account")
    val contactAccount: Account? = null
)
