package social.bigbone.api.method

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameter
import social.bigbone.api.entity.Account

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#follows
 */
class Follows(private val client: MastodonClient) {
    /**
     * POST /api/v1/follows
     * @param uri: username@domain of the person you want to follow
     */
    fun postRemoteFollow(uri: String): MastodonRequest<Account> {
        val parameters = Parameter()
            .append("uri", uri)
            .build()
        return MastodonRequest<Account>(
            {
                client.post(
                    "api/v1/follows",
                    parameters
                        .toRequestBody("application/x-www-form-urlencoded; charset=utf-8".toMediaTypeOrNull())
                )
            },
            {
                client.getSerializer().fromJson(it, Account::class.java)
            }
        )
    }
}
