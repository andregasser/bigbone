package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Announcement
import social.bigbone.api.method.AnnouncementMethods

/**
 * Reactive implementation of [AnnouncementMethods].
 * Allows access to API methods with endpoints having an "api/vX/announcements" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/announcements/"> Mastodon announcements API methods </a>
 */
class RxAnnouncementMethods(client: MastodonClient) {
    private val announcementMethods = AnnouncementMethods(client)

    @JvmOverloads
    fun getAllAnnouncements(withDismissed: Boolean = false): Single<List<Announcement>> =
        Single.fromCallable { announcementMethods.getAllAnnouncements(withDismissed).execute() }

    fun dismissAnnouncement(announcementId: String): Completable =
        Completable.fromAction { announcementMethods.dismissAnnouncement(announcementId) }

    fun addReactionToAnnouncement(
        emojiName: String,
        announcementId: String
    ): Completable = Completable.fromAction { announcementMethods.addReactionToAnnouncement(emojiName, announcementId) }

    fun removeReactionFromAnnouncement(
        emojiName: String,
        announcementId: String
    ): Completable =
        Completable.fromAction { announcementMethods.removeReactionFromAnnouncement(emojiName, announcementId) }
}
