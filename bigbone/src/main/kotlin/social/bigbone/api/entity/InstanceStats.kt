package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

data class InstanceStats(
    @SerializedName("user_count")
    val userCount: Int = 0,

    @SerializedName("status_count")
    val statusCount: Int = 0,

    @SerializedName("domain_count")
    val domainCount: Int = 0
)
