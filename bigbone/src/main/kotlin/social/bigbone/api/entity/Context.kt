package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents the tree around a given status. Used for reconstructing threads of statuses.
 * @see <a href="https://docs.joinmastodon.org/entities/Context/">Mastodon API Context</a>
 */
data class Context(
    /**
     * Parents in the thread.
     */
    @SerializedName("ancestors")
    val ancestors: List<Status> = emptyList(),

    /**
     * Children in the thread.
     */
    @SerializedName("descendants")
    val descendants: List<Status> = emptyList()
)
