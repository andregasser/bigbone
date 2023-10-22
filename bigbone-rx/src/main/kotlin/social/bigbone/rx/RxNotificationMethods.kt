package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Notification
import social.bigbone.api.method.NotificationMethods

/**
 * Reactive implementation of [NotificationMethods].
 * Allows access to API methods with endpoints having an "api/vX/notifications" or "api/vX/notification" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/notifications/">Mastodon notifications API methods</a>
 */
class RxNotificationMethods(client: MastodonClient) {

    private val notificationMethods = NotificationMethods(client)

    @JvmOverloads
    fun getAllNotifications(
        excludeTypes: List<Notification.Type>? = null,
        range: Range = Range()
    ): Single<Pageable<Notification>> = Single.fromCallable {
        notificationMethods.getAllNotifications(excludeTypes, range).execute()
    }

    fun getNotification(id: String): Single<Notification> = Single.fromCallable {
        notificationMethods.getNotification(id).execute()
    }

    fun dismissAllNotifications(): Completable = Completable.fromAction {
        notificationMethods.dismissAllNotifications()
    }

    fun dismissNotification(notificationId: String): Completable = Completable.fromAction {
        notificationMethods.dismissNotification(notificationId)
    }
}
