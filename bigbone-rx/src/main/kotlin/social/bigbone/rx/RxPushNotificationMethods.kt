package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.WebPushSubscription
import social.bigbone.api.method.PushNotificationMethods

/**
 * Reactive implementation of [PushNotificationMethods].
 * Allows access to API methods with endpoints having an "api/vX/push" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/push/">Mastodon push notification API methods</a>
 */
class RxPushNotificationMethods(client: MastodonClient) {

    private val pushNotificationMethods = PushNotificationMethods(client)

    /**
     * Add a Web Push API subscription to receive notifications.
     * Each access token can have one push subscription. If you create a new subscription, the old subscription is deleted.
     * @param endpoint The endpoint URL that is called when a notification event occurs.
     * @param userPublicKey  User agent public key. Base64 encoded string of a public key from a ECDH keypair using the prime256v1 curve.
     * @param userAuthSecret Auth secret, Base64 encoded string of 16 bytes of random data.
     * @param mention Receive mention notifications?
     * @param status Receive new subscribed account notifications?
     * @param reblog Receive reblog notifications?
     * @param follow Receive follow notifications?
     * @param followRequest Receive follow request notifications?
     * @param favourite Receive favourite notifications?
     * @param poll Receive poll notifications?
     * @param update Receive status edited notifications?
     * @param adminSignUp Receive new user signup notifications? Defaults to false. Must have a role with the appropriate permissions.
     * @param adminReport Receive new report notifications? Defaults to false. Must have a role with the appropriate permissions.
     * @param policy Specify which to receive push notifications from.
     * @see <a href="https://docs.joinmastodon.org/methods/push/#create">Mastodon API documentation: methods/push/#create</a>
     */
    @JvmOverloads
    fun subscribePushNotification(
        endpoint: String,
        userPublicKey: String,
        userAuthSecret: String,
        mention: Boolean? = false,
        status: Boolean? = false,
        reblog: Boolean? = false,
        follow: Boolean? = false,
        followRequest: Boolean? = false,
        favourite: Boolean? = false,
        poll: Boolean? = false,
        update: Boolean? = false,
        adminSignUp: Boolean? = false,
        adminReport: Boolean? = false,
        policy: PushNotificationMethods.PushDataPolicy? = null
    ): Single<WebPushSubscription> =
        Single.fromCallable {
            pushNotificationMethods.subscribePushNotification(
                endpoint,
                userPublicKey,
                userAuthSecret,
                mention,
                status,
                reblog,
                follow,
                followRequest,
                favourite,
                poll,
                update,
                adminSignUp,
                adminReport,
                policy
            ).execute()
        }

    /**
     * Updates the current push subscription. Only the data part can be updated.
     * To change fundamentals, a new subscription must be created instead.
     * @param mention Receive mention notifications?
     * @param status Receive new subscribed account notifications?
     * @param reblog Receive reblog notifications?
     * @param follow Receive follow notifications?
     * @param followRequest Receive follow request notifications?
     * @param favourite Receive favourite notifications?
     * @param poll Receive poll notifications?
     * @param update Receive status edited notifications?
     * @param adminSignUp Receive new user signup notifications? Defaults to false. Must have a role with the appropriate permissions.
     * @param adminReport Receive new report notifications? Defaults to false. Must have a role with the appropriate permissions.
     * @param policy Specify which to receive push notifications from.
     * @see <a href="https://docs.joinmastodon.org/methods/push/#update">Mastodon API documentation: methods/push/#update</a>
     */
    @JvmOverloads
    fun updatePushSubscription(
        mention: Boolean? = false,
        status: Boolean? = false,
        reblog: Boolean? = false,
        follow: Boolean? = false,
        followRequest: Boolean? = false,
        favourite: Boolean? = false,
        poll: Boolean? = false,
        update: Boolean? = false,
        adminSignUp: Boolean? = false,
        adminReport: Boolean? = false,
        policy: PushNotificationMethods.PushDataPolicy? = null
    ): Single<WebPushSubscription> =
        Single.fromCallable {
            pushNotificationMethods.updatePushSubscription(
                mention,
                status,
                reblog,
                follow,
                followRequest,
                favourite,
                poll,
                update,
                adminSignUp,
                adminReport,
                policy
            ).execute()
        }

    /**
     * View the PushSubscription currently associated with this access token.
     * @see <a href="https://docs.joinmastodon.org/methods/push/#get">Mastodon API documentation: methods/push/#get</a>
     */
    fun getPushNotification(): Single<WebPushSubscription> = Single.fromCallable { pushNotificationMethods.getPushNotification().execute() }

    /**
     * Removes the current Web Push API subscription.
     * @see <a href="https://docs.joinmastodon.org/methods/push/#delete">Mastodon API documentation: methods/push/#delete</a>
     */
    fun removePushSubscription(): Completable = Completable.fromAction { pushNotificationMethods.removePushSubscription() }
}
