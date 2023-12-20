package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents an emoji reaction to an Announcement.
 * @see <a href="https://docs.joinmastodon.org/entities/Reaction/">Mastodon API Reaction</a>
 */
@Serializable
data class Reaction(
    /**
     * The emoji used for the reaction. Either a unicode emoji, or a custom emoji’s shortcode.
     */
    @SerialName("name")
    val name: String = "",

    /**
     * The total number of users who have added this reaction.
     */
    @SerialName("count")
    val count: Int = 0,

    /**
     * If there is a currently authorized user: Have you added this reaction?
     */
    @SerialName("me")
    val me: Boolean = true,

    /**
     * If the reaction is a custom emoji: A link to the custom emoji.
     */
    @SerialName("url")
    val url: String? = null,

    /**
     * If the reaction is a custom emoji: A link to a non-animated version of the custom emoji.
     */
    @SerialName("static_url")
    val staticUrl: String? = null
)
