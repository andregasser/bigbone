package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a subscription to the push streaming server.
 * @see <a href="https://docs.joinmastodon.org/entities/WebPushSubscription/">Mastodon API Push</a>
 */
@Serializable
data class WebPushSubscription(

    /**
     * The ID of the Web Push subscription in the database.
     */
    @SerialName("id")
    val id: String,

    /**
     * Where push alerts will be sent to.
     */
    @SerialName("endpoint")
    val endpoint: String,

    /**
     * The streaming server’s VAPID key.
     */
    @SerialName("server_key")
    val serverKey: String,

    /**
     * Which alerts should be delivered to the endpoint.
     */
    @SerialName("alerts")
    val alerts: Alerts
)

/**
 * Which alerts should be delivered to the endpoint.
 * @see <a href="https://docs.joinmastodon.org/entities/WebPushSubscription/">Mastodon API Push</a>
 */
@Serializable
data class Alerts(
    /**
     * Receive mention notifications?.
     */
    @SerialName("mention")
    val mention: Boolean? = false,
    /**
     * Receive new subscribed account notifications?
     */
    @SerialName("status")
    val status: Boolean? = false,
    /**
     * Receive reblog notifications?
     */
    @SerialName("reblog")
    val reblog: Boolean? = false,
    /**
     * Receive follow notifications?
     */
    @SerialName("follow")
    val follow: Boolean? = false,
    /**
     * Receive follow request notifications?
     */
    @SerialName("follow_request")
    val followRequest: Boolean? = false,
    /**
     * Receive favourite notifications?
     */
    @SerialName("favourite")
    val favourite: Boolean? = false,
    /**
     * Receive poll notifications?
     */
    @SerialName("poll")
    val poll: Boolean? = false,
    /**
     * Receive status edited notifications?
     */
    @SerialName("update")
    val update: Boolean? = false,
    /**
     * Receive new user signup notifications? Must have a role with the appropriate permissions.
     */
    @SerialName("admin_sign_up")
    val adminSignUp: Boolean? = false,
    /**
     * Receive new report notifications? Must have a role with the appropriate permissions.
     */
    @SerialName("admin_report")
    val adminReport: Boolean? = false
)
