package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents an application that interfaces with the REST API to access accounts or post statuses.
 * @see <a href="https://docs.joinmastodon.org/entities/Application/">Mastodon API Application</a>
 */
data class Application(
    /**
     * The name of your application.
     */
    @SerializedName("name")
    val name: String = "",

    /**
     * The website associated with your application.
     */
    @SerializedName("website")
    val website: String? = null
)
