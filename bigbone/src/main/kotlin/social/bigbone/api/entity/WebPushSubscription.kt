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
    val id: String = "",

    /**
     * Where push alerts will be sent to.
     */
    @SerialName("endpoint")
    val endpoint: String = "",

    /**
     * The streaming server’s VAPID key.
     */
    @SerialName("server_key")
    val serverKey: String = "",

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
     * Receive a push notification when someone else has mentioned you in a status?
     */
    @SerialName("mention")
    val mention: Boolean? = false,

    /**
     * Receive a push notification when a subscribed account posts a status?
     */
    @SerialName("status")
    val status: Boolean? = false,

    /**
     * Receive a push notification when a status you created has been boosted by someone else?
     */
    @SerialName("reblog")
    val reblog: Boolean? = false,

    /**
     * Receive a push notification when someone has followed you?
     */
    @SerialName("follow")
    val follow: Boolean? = false,

    /**
     * Receive a push notification when someone has requested to followed you?
     */
    @SerialName("follow_request")
    val followRequest: Boolean? = false,

    /**
     * Receive a push notification when a status you created has been favourited by someone else?
     */
    @SerialName("favourite")
    val favourite: Boolean? = false,

    /**
     * Receive a push notification when a poll you voted in or created has ended?
     */
    @SerialName("poll")
    val poll: Boolean? = false,

    /**
     * Receive a push notification when a status you interacted with has been edited?
     */
    @SerialName("update")
    val update: Boolean? = false,

    /**
     * Receive a push notification when a new user has signed up?
     */
    @SerialName("admin.sign_up")
    val adminSignUp: Boolean? = false,

    /**
     * Receive a push notification when a new report has been filed?
     */
    @SerialName("admin.report")
    val adminReport: Boolean? = false
)
