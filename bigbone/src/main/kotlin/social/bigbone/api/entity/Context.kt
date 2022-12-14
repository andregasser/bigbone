package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

class Context(
    @SerializedName("ancestors")
    val ancestors: List<Status> = emptyList(),

    @SerializedName("descendants")
    val descendants: List<Status> = emptyList()
)
