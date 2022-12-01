package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#instance
 */
data class InstanceStats(
        @SerializedName("user_count")
        val userCount: Int = 0,

        @SerializedName("status_count")
        val statusCount: Int = 0,

        @SerializedName("domain_count")
        val domainCount: Int = 0) {
}
