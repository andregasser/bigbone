package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

class Attachment(
    @SerializedName("id")
    val id: String = "0",

    @SerializedName("type")
    val type: String = Type.Image.value,

    @SerializedName("url")
    val url: String = "",

    @SerializedName("remote_url")
    val remoteUrl: String? = null,

    @SerializedName("preview_url")
    val previewUrl: String = "",

    @SerializedName("text_url")
    val textUrl: String? = null
) {
    enum class Type(val value: String) {
        Image("image"),
        Video("video"),
        Gifv("gifv")
    }
}
