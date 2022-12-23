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
        return MastodonRequest(
                {
                    client.get("api/v1/instance")
                },
                { json ->
                    client.getSerializer().fromJson(json, Instance::class.java)
                }
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
        return MastodonRequest<Results>(
                {
                    client.get(
                            "api/v1/search",
                            Parameter().apply {
                                append("q", query)
                                if (resolve) {
                                    append("resolve", resolve)
                                }
                            }
                    )
                },
                {
                    client.getSerializer().fromJson(it, Results::class.java)
                }
        )
    }

    /**
     *
     * GET /api/v1/timelines/public
     * @see https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#timelines
     */
    private fun getPublic(local: Boolean, range: Range): MastodonRequest<Pageable<Status>> {
        val parameter = range.toParameter()
        if (local) {
            parameter.append("local", local)
        }
        return MastodonRequest<Pageable<Status>>(
                {
                    client.get("api/v1/timelines/public", parameter)
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        ).toPageable()
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
        val parameter = range.toParameter()
        if (local) {
            parameter.append("local", local)
        }
        return MastodonRequest<Pageable<Status>>(
                {
                    client.get(
                            "api/v1/timelines/tag/$tag",
                            parameter
                    )
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        ).toPageable()
    }

    @JvmOverloads
    fun getLocalTag(tag: String, range: Range = Range()) = getTag(tag, true, range)

    @JvmOverloads
    fun getFederatedTag(tag: String, range: Range = Range()) = getTag(tag, false, range)
}
