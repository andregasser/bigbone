package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

class Tag(
    @SerializedName("name")
    val name: String = "",

    @SerializedName("url")
    val url: String = ""
)
