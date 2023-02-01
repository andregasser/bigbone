package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("accounts")
    val accounts: List<Account> = emptyList(),

    @SerializedName("statuses")
    val statuses: List<Status> = emptyList(),

    @SerializedName("hashtags")
    val hashtags: List<Tag> = emptyList()
)
