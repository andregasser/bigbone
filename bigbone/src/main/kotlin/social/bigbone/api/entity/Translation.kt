package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents the result of machine translating some status content.
 * @see <a href="https://docs.joinmastodon.org/entities/Translation/">Mastodon API Translation</a>
 */
data class Translation(
    /**
     * The translated text of the status as an HTML string.
     */
    @SerializedName("content")
    val content: String = "",

    /**
     * The language of the source text, as auto-detected by the machine translation provider (ISO 639 language code).
     */
    @SerializedName("detected_source_language")
    val detectedSourceLanguage: String = "",

    /**
     * The service that provided the machine translation.
     */
    @SerializedName("provider")
    val provider: String = ""
)
