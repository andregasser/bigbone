package social.bigbone.api.entity.streaming

import kotlinx.serialization.SerialName
import social.bigbone.api.entity.Reaction

/**
 * "Hash" returned as part of [ParsedStreamEvent.AnnouncementReactionReceived] containing information on the reaction.
 *
 * This is slightly different from [Reaction] and thus is in its own class here.
 *
 * @see <a href="https://docs.joinmastodon.org/methods/streaming/#events">Mastodon API streaming events</a>
 */
data class StreamingAnnouncementReaction(
    /**
     * The emoji used for the reaction. Either a unicode emoji, or a custom emoji’s shortcode.
     */
    @SerialName("name")
    val name: String,

    /**
     * The total number of users who have added this reaction.
     */
    @SerialName("count")
    val count: Int,

    /**
     * The ID of the announcement in the database.
     */
    @SerialName("announcement_id")
    val announcementId: String
)
