package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

class Emoji(
    @SerializedName("shortcode")
    val shortcode: String = "",

    @SerializedName("static_url")
    val staticUrl: String = "",

    @SerializedName("url")
    val url: String = "",

    @SerializedName("visible_in_picker")
    val visibleInPicker: Boolean = true
)
