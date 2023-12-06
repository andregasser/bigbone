package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a custom emoji.
 * @see <a href="https://docs.joinmastodon.org/entities/CustomEmoji/">Mastodon API CustomEmoji</a>
 */
@Suppress("ktlint:standard:no-blank-line-in-list")
@Serializable
data class CustomEmoji(
    @SerialName("shortcode")
    val shortcode: String = "",

    /**
     * A link to a static copy of the custom emoji.
     */
    @SerialName("static_url")
    val staticUrl: String = "",

    /**
     * A link to the custom emoji.
     */
    @SerialName("url")
    val url: String = "",

    /**
     * Whether this Emoji should be visible in the picker or unlisted.
     */
    @SerialName("visible_in_picker")
    val visibleInPicker: Boolean = true,

    /**
     * Used for sorting custom emoji in the picker.
     */
    @SerialName("category")
    val category: String = ""
)
