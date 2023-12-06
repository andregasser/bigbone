package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account

/**
 * Feature other profiles on your own profile. See also accounts/:id/{pin,unpin}.
 * @see <a href="https://docs.joinmastodon.org/methods/endorsements/">Mastodon endorsement API methods</a>
 */
class EndorsementMethods(private val client: MastodonClient) {
    private val endorsementsEndpoint = "/api/v1/endorsements"

    /**
     * Accounts that the user is currently featuring on their profile.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/endorsements/#get">Mastodon API documentation: methods/endorsements/#get</a>
     * @return [Pageable] of [Account]s the user is currently featuring on their profile
     */
    @JvmOverloads
    fun getEndorsements(range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return client.getPageableMastodonRequest<Account>(
            endpoint = endorsementsEndpoint,
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }
}
