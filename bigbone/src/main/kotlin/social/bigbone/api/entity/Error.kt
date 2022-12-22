package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#error
 */
class Error(
    @SerializedName("error")
    val error: String = ""
)
