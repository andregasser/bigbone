package social.bigbone.api.method

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.entity.WebPushSubscription
import social.bigbone.api.exception.BigBoneRequestException

/**
 * Allows access to API methods with endpoints having an "api/vX/push" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/push/">Mastodon push notification API methods</a>
 */
class PushNotificationMethods(private val client: MastodonClient) {

    private val pushEndpoint = "/api/v1/push/subscription"

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

    @Throws(BigBoneRequestException::class)
    @JvmOverloads
    fun subscribePushNotification(endpoint: String, userPublicKey: String, userAuthSecret: String,
                                  mention: Boolean? = false, status: Boolean? = false, reblog: Boolean? = false, follow: Boolean? = false,
                                  followRequest: Boolean? = false, favourite: Boolean? = false, poll: Boolean? = false,
                                  update: Boolean? = false, adminSignUp: Boolean? = false, adminReport: Boolean? = false,
                                  policy: PushDataPolicy? = null): MastodonRequest<WebPushSubscription> {
        return client.getMastodonRequest(
            endpoint = pushEndpoint,
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                append("subscription[endpoint]", endpoint)
                append("subscription[keys][p256dh]", userPublicKey)
                append("subscription[keys][auth]", userAuthSecret)
                mention?.let { append("data[alerts][mention]", it) }
                status?.let { append("data[alerts][status]", it) }
                reblog?.let { append("data[alerts][reblog]", it) }
                follow?.let { append("data[alerts][follow]", it) }
                followRequest?.let { append("data[alerts][follow_request]", it) }
                favourite?.let { append("data[alerts][favourite]", it) }
                poll?.let { append("data[alerts][poll]", it) }
                update?.let { append("data[alerts][update]", it) }
                adminSignUp?.let { append("data[alerts][admin.sign_up]", it) }
                adminReport?.let { append("data[alerts][admin.report]", it) }
                append("data[policy]", policy?.name?.lowercase() ?: PushDataPolicy.NONE.name.lowercase())
            }
        )
    }

    @Throws(BigBoneRequestException::class)
    @JvmOverloads
    fun updatePushSubscription(mention: Boolean? = false, status: Boolean? = false, reblog: Boolean? = false, follow: Boolean? = false,
                               followRequest: Boolean? = false, favourite: Boolean? = false, poll: Boolean? = false,
                               update: Boolean? = false, adminSignUp: Boolean? = false,
                               adminReport: Boolean? = false, policy: PushDataPolicy? = null): MastodonRequest<WebPushSubscription> {
        return client.getMastodonRequest(
            endpoint = pushEndpoint,
            method = MastodonClient.Method.PUT,
            parameters = Parameters().apply {
                mention?.let { append("data[alerts][mention]", it) }
                status?.let { append("data[alerts][status]", it) }
                reblog?.let { append("data[alerts][reblog]", it) }
                follow?.let { append("data[alerts][follow]", it) }
                followRequest?.let { append("data[alerts][follow_request]", it) }
                favourite?.let { append("data[alerts][favourite]", it) }
                poll?.let { append("data[alerts][poll]", it) }
                update?.let { append("data[alerts][update]", it) }
                adminSignUp?.let { append("data[alerts][admin.sign_up]", it) }
                adminReport?.let { append("data[alerts][admin.report]", it) }
                append("policy", policy?.name?.lowercase() ?: PushDataPolicy.NONE.name.lowercase())
            }
        )
    }

    @Throws(BigBoneRequestException::class)
    fun getPushNotification(): MastodonRequest<WebPushSubscription> {
        return client.getMastodonRequest(
            endpoint = pushEndpoint,
            method = MastodonClient.Method.GET
        )
    }

    @Throws(BigBoneRequestException::class)
    fun removePushSubscription() {
        client.performAction(
            endpoint = pushEndpoint,
            method = MastodonClient.Method.DELETE
        )
    }
}