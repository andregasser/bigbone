package social.bigbone.api

/**
 * Represents an HTTP link header that is returned as part of a Mastodon API response.
 */
data class Link(
    val linkHeader: String,
    val nextPath: String,
    val prevPath: String,
    val maxId: String,
    val sinceId: String
) {
    companion object {
        @JvmStatic
        fun parse(linkHeader: String?): Link? {
            return linkHeader?.let {
                val links = it.split(",")
                val nextRel = ".*max_id=([0-9]+).*rel=\"next\"".toRegex()
                val prevRel = ".*since_id=([0-9]+).*rel=\"prev\"".toRegex()
                var nextPath = ""
                var maxId = "0"
                var prevPath = ""
                var sinceId = "0"
                links.forEach {
                    val link = it.trim()
                    nextRel.matchEntire(link)?.let {
                        nextPath = it.value.replace("; rel=\"next\"", "")
                        maxId = it.groupValues[1]
                    }

                    prevRel.matchEntire(link)?.let {
                        prevPath = it.value.replace("; rel=\"prev\"", "")
                        sinceId = it.groupValues[1]
                    }
                }
                Link(it, nextPath, prevPath, maxId, sinceId)
            }
        }
    }
}
