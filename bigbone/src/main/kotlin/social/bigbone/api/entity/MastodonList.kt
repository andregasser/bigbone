package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

class MastodonList(
    @SerializedName("id")
    val id: String = "0",

    @SerializedName("title")
    val title: String = ""
)
