package social.bigbone.api.entity.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the subscription data needed to be sent to subscribe to push notification.
 * @see <a href="https://docs.joinmastodon.org/methods/push/">Mastodon API Push</a>
 */
@Serializable
data class PushSubscription(
    /**
     * Nested object holding endpoint and authentication user keys.
     */
    @SerialName("endpoint")
    val subscription: Subscription,
    /**
     * Nested object with many flags and policy from which to receive pushes.
     */
    @SerialName("data")
    val data: Data
)



@Serializable
data class Subscription(
    /**
     * The endpoint URL that is called when a notification event occurs.
     */
    @SerialName("endpoint")
    val endpoint: String,
    /**
     * Keys nested object holding user public key and secret.
     */
    @SerialName("keys")
    val keys: Keys
)


@Serializable
data class Keys(
    /**
     * User agent public key. Base64 encoded string of a public key from a ECDH keypair using.
     */
    @SerialName("p256dh")
    val p256dh: String,
    /**
     * Auth secret. Base64 encoded string of 16 bytes of random data.
     */
    @SerialName("auth")
    val auth: String
)

@Serializable
data class Data(
    /**
     * Represents nested object holding many boolean flags.
     */
    @SerialName("alerts")
    val alerts: Alerts? = null,
    /**
     * @see PushDataPolicy
     */
    @SerialName("policy")
    val policy: PushDataPolicy? = PushDataPolicy.NONE
)

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

/**
 * Specify whether to receive push notifications from all, followed, follower, or none users.
 */
@Serializable
enum class PushDataPolicy {
    @SerialName("all")
    ALL,
    @SerialName("followed")
    FOLLOWED,
    @SerialName("follower")
    FOLLOWER,
    @SerialName("none")
    NONE
}