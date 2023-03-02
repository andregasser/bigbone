package social.bigbone.api

import social.bigbone.Parameters

/**
 * A range can be used to narrow down the data to be returned by the Mastodon API.
 */
class Range @JvmOverloads constructor(
    val maxId: String? = null,
    val sinceId: String? = null,
    val limit: Int? = null
) {
    fun toParameters() = Parameters().apply {
        maxId?.let { append("max_id", it) }
        sinceId?.let { append("since_id", it) }
        limit?.let { append("limit", it) }
    }
}
