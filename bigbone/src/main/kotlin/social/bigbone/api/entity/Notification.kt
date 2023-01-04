package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

class Notification(
    @SerializedName("id")
    val id: String = "0", // The notification ID

    @SerializedName("type")
    val type: String = Type.Mention.value, // One of: "mention", "reblog", "favourite", "follow"

    @SerializedName("created_at")
    val createdAt: String = "", // The time the notification was created

    @SerializedName("account")
    val account: Account? = null, // The Account sending the notification to the user

    @SerializedName("status")
    val status: Status? = null // The Status associated with the notification, if applicable
) {
    enum class Type(val value: String) {
        Mention("mention"),
        Reblog("reblog"),
        Favourite("favourite"),
        Follow("follow")
    }
}
