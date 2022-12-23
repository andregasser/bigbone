package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#context
 */
class Context(
    @SerializedName("ancestors")
    val ancestors: List<Status> = emptyList(),

    @SerializedName("descendants")
    val descendants: List<Status> = emptyList()
)
