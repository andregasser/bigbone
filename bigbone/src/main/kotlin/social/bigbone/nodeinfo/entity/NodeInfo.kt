package social.bigbone.nodeinfo.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data class representing information returned by the well-known NodeInfo URL.
 * @see <a href="https://github.com/jhass/nodeinfo/blob/main/PROTOCOL.md">NodeInfo protocol</a>
 */
@Suppress("ktlint:standard:no-blank-line-in-list")
@Serializable
data class NodeInfo(
    @SerialName("links")
    val links: List<Link> = emptyList()
) {
    /**
     * Representation of a link to NodeInfo server information.
     */
    @Serializable
    data class Link(
        /**
         * URL of a JSON object containing server information.
         */
        @SerialName("href")
        val href: String = "",

        /**
         * Schema information about the JSON object located at [href].
         */
        @SerialName("rel")
        val rel: String = ""
    )
}
