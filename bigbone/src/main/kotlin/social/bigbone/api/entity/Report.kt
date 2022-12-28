package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#reports
 */
class Report(
    @SerializedName("id")
    val id: String = "0", // The ID of the report
    @SerializedName("action_taken")
    val actionTaken: String = "" // The action taken in response to the report
)
