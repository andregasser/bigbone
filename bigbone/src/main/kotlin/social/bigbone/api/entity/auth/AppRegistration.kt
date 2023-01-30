package social.bigbone.api.entity.auth

import com.google.gson.annotations.SerializedName

data class AppRegistration(
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("client_id")
    val clientId: String = "",

    @SerializedName("client_secret")
    val clientSecret: String = "",

    @SerializedName("redirect_uri")
    val redirectUri: String = "",
)
