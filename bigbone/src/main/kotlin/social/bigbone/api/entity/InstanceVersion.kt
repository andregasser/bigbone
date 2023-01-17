package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

data class InstanceVersion(
    @SerializedName("version")
    val version: String = "",
)
