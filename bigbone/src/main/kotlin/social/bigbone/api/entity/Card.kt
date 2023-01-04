package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

class Card(
    @SerializedName("url")
    val url: String = "",

    @SerializedName("title")
    val title: String = "",

    @SerializedName("description")
    val description: String = "",

    @SerializedName("image")
    val image: String? = null
)
