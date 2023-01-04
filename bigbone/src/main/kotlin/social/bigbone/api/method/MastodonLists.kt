package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.MastodonList
import social.bigbone.api.entity.Status
import social.bigbone.api.exception.BigboneRequestException

class MastodonLists(private val client: MastodonClient) {

    /**
     * Gets all lists for the current user.
     * @see <a href="https://docs.joinmastodon.org/methods/lists/#get">Mastodon API documentation: methods/lists/#get</a>
     */
    fun getLists(): MastodonRequest<List<MastodonList>> {
        return client.getMastodonRequestForList(
            endpoint = "api/v1/lists",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Gets a list timeline of statuses for the given list.
     * @param listID ID of the list for which a timeline should be returned
     * @param range restrict result to a specific range
     * @see <a href="https://docs.joinmastodon.org/methods/timelines/#list">Mastodon API documentation: methods/timelines/#list</a>
     */
    @Throws(BigboneRequestException::class)
    fun getListTimeline(listID: String, range: Range = Range()): MastodonRequest<Pageable<Status>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/timelines/list/$listID",
            method = MastodonClient.Method.GET,
            parameters = range.toParameter()
        )
    }
}
