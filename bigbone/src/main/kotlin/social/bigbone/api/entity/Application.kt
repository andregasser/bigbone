package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

data class Application(
    @SerializedName("name")
    val name: String = "",

    @SerializedName("website")
    val website: String? = null
)
