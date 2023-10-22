package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.api.entity.data.Visibility

/**
 * Represents a Preferences about an account.
 * @see <a href="https://docs.joinmastodon.org/entities/Preferences/">Mastodon API Preferences</a>
 */
@Serializable
data class Preferences(
    /**
     * Default visibility for new posts.
     */
    @SerialName("posting:default:visibility")
    val defaultVisibility: Visibility = Visibility.PUBLIC,

    /**
     * Default sensitivity flag for new posts.
     */
    @SerialName("posting:default:sensitive")
    val defaultSensitive: Boolean = false,

    /**
     * Default language for new posts  (ISO 639-1 language two-letter code).
     */
    @SerialName("posting:default:language")
    val defaultLanguage: String? = null,

    /**
     * Whether media attachments should be automatically displayed or blurred/hidden.
     */
    @SerialName("reading:expand:media")
    val expandMedia: Media = Media.DEFAULT,

    /**
     * Whether CWs should be expanded by default.
     */
    @SerialName("reading:expand:spoilers")
    val expandSpoilers: Boolean = false
) {
    /**
     * Specifies whether media attachments should be automatically displayed or blurred/hidden.
     */
    @Serializable
    enum class Media {
        @SerialName("default")
        DEFAULT,

        @SerialName("show_all")
        SHOW_ALL,

        @SerialName("hide_all")
        HIDE_ALL
    }
}
