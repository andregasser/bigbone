package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("error")
    val error: String = ""
)
