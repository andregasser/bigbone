package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("access_token")
    val accessToken: String = "",

    @SerializedName("token_type")
    val tokenType: String = "",

    @SerializedName("scope")
    val scope: String = "",

    @SerializedName("created_at")
    val createdAt: Long = 0L
)
