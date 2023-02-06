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
    val website: String? = null,

    /**
     * Used for Push Streaming API. Returned with POST /api/v1/apps. Equivalent to [WebPushSubscription#serverKey].
     */
    @SerializedName("vapid_key")
    val vapidKey: String = "",

    /**
     * Client ID key, to be used for obtaining OAuth tokens.
     */
    @SerializedName("client_id")
    val clientId: String? = null,

    /**
     * Client secret key, to be used for obtaining OAuth tokens.
     */
    @SerializedName("client_secret")
    val clientSecret: String? = null
)
