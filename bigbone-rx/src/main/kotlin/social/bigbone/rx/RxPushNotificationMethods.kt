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

    @JvmOverloads
    fun subscribePushNotification(endpoint: String, userPublicKey: String, userAuthSecret: String,
                                  mention: Boolean? = false, status: Boolean? = false, reblog: Boolean? = false, follow: Boolean? = false,
                                  followRequest: Boolean? = false, favourite: Boolean? = false, poll: Boolean? = false,
                                  update: Boolean? = false, adminSignUp: Boolean? = false, adminReport: Boolean? = false,
                                  policy: PushNotificationMethods.PushDataPolicy? = null): Single<WebPushSubscription> =
        Single.fromCallable { pushNotificationMethods.subscribePushNotification(endpoint, userPublicKey, userAuthSecret, mention, status, reblog, follow, followRequest, favourite, poll, update, adminSignUp, adminReport, policy).execute() }


    @JvmOverloads
    fun updatePushSubscription(mention: Boolean? = false, status: Boolean? = false, reblog: Boolean? = false, follow: Boolean? = false,
                               followRequest: Boolean? = false, favourite: Boolean? = false, poll: Boolean? = false,
                               update: Boolean? = false, adminSignUp: Boolean? = false,
                               adminReport: Boolean? = false, policy: PushNotificationMethods.PushDataPolicy? = null): Single<WebPushSubscription>  =
        Single.fromCallable { pushNotificationMethods.updatePushSubscription(mention, status, reblog, follow, followRequest, favourite, poll, update, adminSignUp, adminReport, policy).execute() }


    fun getPushNotification(): Single<WebPushSubscription> = Single.fromCallable { pushNotificationMethods.getPushNotification().execute() }

    fun removePushSubscription(): Completable = Completable.fromAction { pushNotificationMethods.removePushSubscription() }
}