package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

class Error(
    @SerializedName("error")
    val error: String = ""
)
