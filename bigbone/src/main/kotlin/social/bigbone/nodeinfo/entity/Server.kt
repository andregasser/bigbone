package social.bigbone.nodeinfo.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Minimal data class for information returned by a server via NodeInfo. We are only interested in the software running
 * on this server.
 * @see <a href="https://github.com/jhass/nodeinfo/blob/main/schemas/2.0/schema.json">NodeInfo schema 2.0</a>
 */
@Suppress("ktlint:standard:no-blank-line-in-list")
@Serializable
data class Server(
    /**
     * Schema version information.
     */
    @SerialName("version")
    val schemaVersion: String = "",

    /**
     * Software running on this server.
     */
    @SerialName("software")
    val software: Software? = null
) {
    /**
     * Information about the software running on a server.
     */
    @Serializable
    data class Software(
        /**
         * Canonical name of the software running on this server.
         */
        @SerialName("name")
        val name: String = "",

        /**
         * Version of the software running on this server.
         */
        @SerialName("version")
        val version: String = ""
    )
}
