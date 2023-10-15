package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the tree around a given status. Used for reconstructing threads of statuses.
 * @see <a href="https://docs.joinmastodon.org/entities/Context/">Mastodon API Context</a>
 */
@Serializable
data class Context(
    /**
     * Parents in the thread.
     */
    @SerialName("ancestors")
    val ancestors: List<Status> = emptyList(),

    /**
     * Children in the thread.
     */
    @SerialName("descendants")
    val descendants: List<Status> = emptyList()
)
