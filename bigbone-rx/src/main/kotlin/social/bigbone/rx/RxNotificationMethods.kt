package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Notification
import social.bigbone.api.entity.NotificationType
import social.bigbone.api.method.NotificationMethods

/**
 * Reactive implementation of [NotificationMethods].
 * Allows access to API methods with endpoints having an "api/vX/notifications" or "api/vX/notification" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/notifications/">Mastodon notifications API methods</a>
 */
class RxNotificationMethods(client: MastodonClient) {

    private val notificationMethods = NotificationMethods(client)

    /**
     * Notifications concerning the user.
     * @param includeTypes Types to include in the results.
     * @param excludeTypes Types to exclude from the results.
     * @param accountId Return only notifications received from the specified account.
     * @param range optional Range for the pageable return value.
     * @see <a href="https://docs.joinmastodon.org/methods/notifications/#get">Mastodon API documentation: methods/notifications/#get</a>
     */
    @JvmOverloads
    fun getAllNotifications(
        includeTypes: List<NotificationType>? = null,
        excludeTypes: List<NotificationType>? = null,
        accountId: String? = null,
        range: Range = Range()
    ): Single<Pageable<Notification>> = Single.fromCallable {
        notificationMethods.getAllNotifications(includeTypes, excludeTypes, accountId, range).execute()
    }

    /**
     * View information about a notification with a given ID.
     * @param id ID of the notification to view
     * @see <a href="https://docs.joinmastodon.org/methods/notifications/#get-one">Mastodon API documentation: methods/notifications/#get-one</a>
     */
    fun getNotification(id: String): Single<Notification> = Single.fromCallable {
        notificationMethods.getNotification(id).execute()
    }

    /**
     * Clear all notifications from the server.
     * @see <a href="https://docs.joinmastodon.org/methods/notifications/#clear">Mastodon API documentation: methods/notifications/#clear</a>
     */
    fun dismissAllNotifications(): Completable = Completable.fromAction {
        notificationMethods.dismissAllNotifications()
    }

    /**
     * Dismiss a single notification from the server.
     * @param notificationId The ID of the Notification in the database to be deleted.
     * @see <a href="https://docs.joinmastodon.org/methods/notifications/#dismiss">Mastodon API documentation: methods/notifications/#dismiss</a>
     */
    fun dismissNotification(notificationId: String): Completable = Completable.fromAction {
        notificationMethods.dismissNotification(notificationId)
    }
}
