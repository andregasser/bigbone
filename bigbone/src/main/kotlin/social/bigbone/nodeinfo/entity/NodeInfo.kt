package social.bigbone.nodeinfo.entity

import com.google.gson.annotations.SerializedName

/**
 * Data class representing information returned by the well-known NodeInfo URL.
 * @see <a href="https://github.com/jhass/nodeinfo/blob/main/PROTOCOL.md">NodeInfo protocol</a>
 */
data class NodeInfo(
    @SerializedName("links")
    val links: List<Link> = emptyList()
) {
    /**
     * Representation of a link to NodeInfo server information.
     */
    data class Link(
        /**
         * URL of a JSON object containing server information.
         */
        @SerializedName("href")
        val href: String = "",

        /**
         * Schema information about the JSON object located at [href].
         */
        @SerializedName("rel")
        val rel: String = ""
    )
}
