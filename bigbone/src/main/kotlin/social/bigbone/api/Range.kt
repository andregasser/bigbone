@file:Suppress("ktlint:standard:annotation")

package social.bigbone.api

import social.bigbone.Parameters

/**
 * A range can be used to narrow down the data to be returned by the Mastodon API.
 */
class Range @JvmOverloads constructor(
    val maxId: String? = null,
    val minId: String? = null,
    val sinceId: String? = null,
    val limit: Int? = null
) {
    @JvmOverloads
    fun toParameters(parameters: Parameters = Parameters()) = parameters.apply {
        maxId?.let { append("max_id", maxId) }
        minId?.let { append("min_id", minId) }
        sinceId?.let { append("since_id", sinceId) }
        limit?.let { append("limit", limit) }
    }
}
