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

    /**
     * See all currently active announcements set by admins.
     * @param withDismissed Boolean If true, response will include announcements dismissed by the user. Defaults to false.
     * @see <a href="https://docs.joinmastodon.org/methods/announcements/#get">Mastodon API documentation: methods/announcements/#get</a>
     */
    @JvmOverloads
    fun getAllAnnouncements(withDismissed: Boolean = false): Single<List<Announcement>> =
        Single.fromCallable { announcementMethods.getAllAnnouncements(withDismissed).execute() }

    /**
     * Allows a user to mark the announcement as read.
     * @param announcementId String The ID of the Announcement in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/announcements/#dismiss">Mastodon API documentation: methods/announcements/#dismiss</a>
     */
    fun dismissAnnouncement(announcementId: String): Completable = Completable.fromAction { announcementMethods.dismissAnnouncement(announcementId) }

    /**
     * React to an announcement with an emoji.
     * @param emojiName String Unicode emoji, or the shortcode of a custom emoji.
     * @param announcementId String The ID of the Announcement in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/announcements/#put-reactions">
     * Mastodon API documentation:methods/announcements/#put-reactions</a>
     */
    fun addReactionToAnnouncement(
        emojiName: String,
        announcementId: String
    ): Completable = Completable.fromAction { announcementMethods.addReactionToAnnouncement(emojiName, announcementId) }

    /**
     * Undo a react emoji to an announcement.
     * @param emojiName String Unicode emoji, or the shortcode of a custom emoji.
     * @param announcementId String The ID of the Announcement in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/announcements/#delete-reactions">
     * Mastodon API documentation:methods/announcements/#delete-reactions</a>
     */
    fun removeReactionFromAnnouncement(
        emojiName: String,
        announcementId: String
    ): Completable = Completable.fromAction { announcementMethods.removeReactionFromAnnouncement(emojiName, announcementId) }
}
