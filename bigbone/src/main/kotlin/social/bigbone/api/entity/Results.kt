package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

class Results(
    @SerializedName("accounts")
    val accounts: List<Account> = emptyList(), // An array of matched Accounts

    @SerializedName("statuses")
    val statuses: List<Status> = emptyList(), // An array of matched Statuses

    @SerializedName("hashtags")
    val hashtags: List<String> = emptyList() // An array of matched hashtags, as strings
)
