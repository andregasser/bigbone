package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

class MastodonList(
    @SerializedName("id")
    val id: Long = 0L,

    @SerializedName("title")
    val title: String = ""
)
