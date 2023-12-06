package social.bigbone.api

import social.bigbone.Parameters

/**
 * A range can be used to narrow down the data to be returned by the Mastodon API.
 */
@Suppress("ktlint:standard:annotation")
class Range @JvmOverloads constructor(
    val maxId: String? = null,
    val minId: String? = null,
    val sinceId: String? = null,
    val limit: Int? = null
) {
    fun toParameters() =
        Parameters().apply {
            maxId?.let { append("max_id", maxId) }
            minId?.let { append("min_id", minId) }
            sinceId?.let { append("since_id", sinceId) }
            limit?.let { append("limit", limit) }
        }
}
