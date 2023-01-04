package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

class Relationship(
    @SerializedName("id")
    val id: String = "0",

    @SerializedName("following")
    val isFollowing: Boolean = false,

    @SerializedName("followed_by")
    val isFollowedBy: Boolean = false,

    @SerializedName("blocking")
    val isBlocking: Boolean = false,

    @SerializedName("muting")
    val isMuting: Boolean = false,

    @SerializedName("requested")
    val isRequested: Boolean = false
)
