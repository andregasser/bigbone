package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * URLs of interest for clients apps.
 * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
 */
data class InstanceUrls(
    /**
     * The Websockets URL for connecting to the streaming API.
     */
    @SerializedName("streaming_api")
    val streamingApi: String = ""
)
