package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameter
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Instance
import social.bigbone.api.entity.Results
import social.bigbone.api.entity.Status

class Public(private val client: MastodonClient) {
    /**
     * Retrieve instance details.
     *
     * GET /api/v1/instance
     * @see https://docs.joinmastodon.org/entities/V1_Instance/
     */
    fun getInstance(): MastodonRequest<Instance> {
        return client.getMastodonRequest(
            endpoint = "api/v1/instance",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Search for content in accounts, statuses and hashtags.
     *
     * GET /api/v1/search
     * q: The search query
     * resolve: Whether to resolve non-local accounts
     * @see https://docs.joinmastodon.org/methods/search/
     */
    @JvmOverloads
    fun getSearch(query: String, resolve: Boolean = false): MastodonRequest<Results> {
        return client.getMastodonRequest(
            endpoint = "api/v1/search",
            method = MastodonClient.Method.GET,
            parameters = Parameter().apply {
                append("q", query)
                if (resolve) {
                    append("resolve", true)
                }
            }
        )
    }

    /**
     *
     * GET /api/v1/timelines/public
     * @see https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#timelines
     */
    private fun getPublic(local: Boolean, range: Range): MastodonRequest<Pageable<Status>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/timelines/public",
            method = MastodonClient.Method.GET,
            parameters = range.toParameter().apply {
                if (local) {
                    append("local", true)
                }
            }
        )
    }

    @JvmOverloads
    fun getLocalPublic(range: Range = Range()) = getPublic(true, range)

    @JvmOverloads
    fun getFederatedPublic(range: Range = Range()) = getPublic(false, range)

    /**
     * GET /api/v1/timelines/tag/:tag
     * @see https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#timelines
     */
    private fun getTag(tag: String, local: Boolean, range: Range): MastodonRequest<Pageable<Status>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/timelines/tag/$tag",
            method = MastodonClient.Method.GET,
            parameters = range.toParameter().apply {
                if (local) {
                    append("local", true)
                }
            }
        )
    }

    @JvmOverloads
    fun getLocalTag(tag: String, range: Range = Range()) = getTag(tag, true, range)

    @JvmOverloads
    fun getFederatedTag(tag: String, range: Range = Range()) = getTag(tag, false, range)
}
