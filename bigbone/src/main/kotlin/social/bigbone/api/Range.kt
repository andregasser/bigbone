package social.bigbone.api

import social.bigbone.Parameters

class Range @JvmOverloads constructor(val maxId: String? = null, val sinceId: String? = null, val limit: Int = 20) {
    fun toParameters() = Parameters().apply {
        maxId?.let { append("max_id", it) }
        sinceId?.let { append("since_id", it) }
        append("limit", limit)
    }
}
