package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

data class Application(
    @SerializedName("name")
    val name: String = "",

    @SerializedName("website")
    val website: String? = null,

    @SerializedName("vapid_key")
    val vapidKey: String = "",

    @SerializedName("client_id")
    val clientId: String? = null,

    @SerializedName("client_secret")
    val clientSecret: String? = null
)
