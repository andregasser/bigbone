package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName
import social.bigbone.api.entity.data.Privacy

/**
 * Represents a Preferences about an account.
 * @see <a href="https://docs.joinmastodon.org/entities/Preferences/">Mastodon API Preferences</a>
 */
data class Preferences(
    /**
     * Default visibility for new posts.
     */
    @SerializedName("posting:default:visibility")
    val defaultVisibility: String = Privacy.PUBLIC.value,

    /**
     * Default sensitivity flag for new posts.
     */
    @SerializedName("posting:default:sensitive")
    val defaultSensitive: Boolean = false,

    /**
     * Default language for new posts  (ISO 639-1 language two-letter code).
     */
    @SerializedName("posting:default:language")
    val defaultLanguage: String? = null,

    /**
     * Whether media attachments should be automatically displayed or blurred/hidden.
     */
    @SerializedName("reading:expand:media")
    val expandMedia: String = Media.DEFAULT.value,

    /**
     * Whether CWs should be expanded by default.
     */
    @SerializedName("reading:expand:spoilers")
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