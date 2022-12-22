package social.bigbone.rx

import io.reactivex.Completable
import io.reactivex.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Notification
import social.bigbone.api.method.Notifications

class RxNotifications(client: MastodonClient) {
    val notifications = Notifications(client)

    fun getNotifications(range: Range): Single<Pageable<Notification>> {
        return Single.create {
            try {
                val notifications = notifications.getNotifications(range)
                it.onSuccess(notifications.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun getNotification(id: Long): Single<Notification> {
        return Single.create {
            try {
                val notification = notifications.getNotification(id)
                it.onSuccess(notification.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun clearNotifications(): Completable {
        return Completable.create {
            try {
                notifications.clearNotifications()
                it.onComplete()
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
