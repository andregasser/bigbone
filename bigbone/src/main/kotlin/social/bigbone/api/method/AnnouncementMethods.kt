package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.entity.Announcement
import social.bigbone.api.exception.BigBoneRequestException

/**
 * Allows access to API methods with endpoints having an "api/vX/announcements" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/notifications/">Mastodon notifications API methods</a>
 */
class AnnouncementMethods(private val client: MastodonClient) {

    private val announcementsEndpoint = "api/v1/announcements"

    /**
     * See all currently active announcements set by admins.
     * @param withDismissed Boolean If true, response will include announcements dismissed by the user. Defaults to false.
     * @see <a href="https://docs.joinmastodon.org/methods/announcements/#get">Mastodon API documentation: methods/announcements/#get</a>
     */
    @JvmOverloads
    fun getAllAnnouncements(
        withDismissed: Boolean = false
    ): MastodonRequest<List<Announcement>> {
        return client.getMastodonRequestForList(
            endpoint = announcementsEndpoint,
            method = MastodonClient.Method.GET,
            parameters = Parameters()
                .append("with_dismissed", withDismissed)
        )
    }

    /**
     * Allows a user to mark the announcement as read.
     * @param announcementId String The ID of the Announcement in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/announcements/#dismiss">Mastodon API documentation: methods/announcements/#dismiss</a>
     */
    @Throws(BigBoneRequestException::class)
    fun dismissAnnouncement(announcementId: String) {
        client.performAction(
            endpoint = "$announcementsEndpoint/$announcementId/dismiss",
            method = MastodonClient.Method.POST
        )
    }

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
    ) {
        client.performAction(
            endpoint = "$announcementsEndpoint/$announcementId/reactions/$emojiName",
            method = MastodonClient.Method.PUT
        )
    }

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
    ) {
        client.performAction(
            endpoint = "$announcementsEndpoint/$announcementId/reactions/$emojiName",
            method = MastodonClient.Method.DELETE
        )
    }
}
