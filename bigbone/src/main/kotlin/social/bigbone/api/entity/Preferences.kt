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
    val defaultVisibility: String = Visibility.PUBLIC.value,

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
    val expandMedia: String = Media.DEFAULT.value,

    /**
     * Whether CWs should be expanded by default.
     */
    @SerialName("reading:expand:spoilers")
    val expandSpoilers: Boolean = false
) {
    /**
     * Specifies the notification type.
     */
    enum class Media(val value: String) {
        DEFAULT("default"),
        SHOW_ALL("show_all"),
        HIDE_ALL("hide_all")
    }
}