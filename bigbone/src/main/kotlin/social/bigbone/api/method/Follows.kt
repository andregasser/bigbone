package social.bigbone.api.method

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
        return MastodonRequest<Account>(
            {
                client.post("api/v1/follows", parameters)
            },
            {
                client.getSerializer().fromJson(it, Account::class.java)
            }
        )
    }
}
