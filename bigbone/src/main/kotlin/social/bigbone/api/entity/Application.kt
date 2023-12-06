package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents an application that interfaces with the REST API to access accounts or post statuses.
 * @see <a href="https://docs.joinmastodon.org/entities/Application/">Mastodon API Application</a>
 */
@Suppress("ktlint:standard:no-blank-line-in-list")
@Serializable
data class Application(
    /**
     * The name of your application.
     */
    @SerialName("name")
    val name: String = "",

    /**
     * The website associated with your application.
     */
    @SerialName("website")
    val website: String? = null,

    /**
     * Used for Push Streaming API. Returned with POST /api/v1/apps. Equivalent to [WebPushSubscription#serverKey].
     */
    @SerialName("vapid_key")
    val vapidKey: String = "",

    /**
     * Client ID key, to be used for obtaining OAuth tokens.
     */
    @SerialName("client_id")
    val clientId: String? = null,

    /**
     * Client secret key, to be used for obtaining OAuth tokens.
     */
    @SerialName("client_secret")
    val clientSecret: String? = null
)
