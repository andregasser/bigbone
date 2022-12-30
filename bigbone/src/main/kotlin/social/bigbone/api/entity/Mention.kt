package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#mention
 */
class Mention(
    @SerializedName("url")
    val url: String = "",

    @SerializedName("username")
    val username: String = "",

    @SerializedName("acct")
    val acct: String = "",

    @SerializedName("id")
    val id: String = "0"
)
