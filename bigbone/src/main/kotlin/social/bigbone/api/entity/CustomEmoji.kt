package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents a custom emoji.
 * @see <a href="https://docs.joinmastodon.org/entities/CustomEmoji/">Mastodon API CustomEmoji</a>
 */
data class CustomEmoji(
    @SerializedName("shortcode")
    val shortcode: String = "",

    /**
     * A link to a static copy of the custom emoji.
     */
    @SerializedName("static_url")
    val staticUrl: String = "",

    /**
     * A link to the custom emoji.
     */
    @SerializedName("url")
    val url: String = "",

    /**
     * Whether this Emoji should be visible in the picker or unlisted.
     */
    @SerializedName("visible_in_picker")
    val visibleInPicker: Boolean = true
)
