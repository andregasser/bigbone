package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

data class InstanceUrls(
    @SerializedName("streaming_api")
    val streamingApi: String = ""
)
