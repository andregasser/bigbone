package social.bigbone.nodeinfo.entity

import com.google.gson.annotations.SerializedName

/**
 * Minimal data class for information returned by a server via NodeInfo. We are only interested in the software running
 * on this server.
 * @see <a href="https://github.com/jhass/nodeinfo/blob/main/schemas/2.0/schema.json">NodeInfo schema 2.0</a>
 */
data class Server(
    /**
     * Schema version information.
     */
    @SerializedName("version")
    val schemaVersion: String = "",

    /**
     * Software running on this server.
     */
    @SerializedName("software")
    val software: Software? = null

) {
    /**
     * Information about the software running on a server.
     */
    data class Software(
        /**
         * Canonical name of the software running on this server.
         */
        @SerializedName("name")
        val name: String = "",

        /**
         * Version of the software running on this server.
         */
        @SerializedName("version")
        val version: String = ""
    )
}
