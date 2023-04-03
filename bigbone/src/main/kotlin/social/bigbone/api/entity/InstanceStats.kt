package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Statistics about how much information the instance contains.
 * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
 */
data class InstanceStats(
    /**
     * Total users on this instance.
     */
    @SerializedName("user_count")
    val userCount: Long = 0,

    /**
     * Total statuses on this instance.
     */
    @SerializedName("status_count")
    val statusCount: Long = 0,

    /**
     * Total domains discovered by this instance.
     */
    @SerializedName("domain_count")
    val domainCount: Long = 0
)
