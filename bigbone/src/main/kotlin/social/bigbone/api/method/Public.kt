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
     * Public timelines can consist of only local statuses, only remote (=federated) statuses, or a combination of both.
     */
    enum class StatusOrigin {
        LOCAL,
        REMOTE,
        LOCAL_AND_REMOTE
    }

    /**
     * Retrieve instance details.
     * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API documentation: entities/V1_Instance</a>
     */
    fun getInstance(): MastodonRequest<Instance> {
        return client.getMastodonRequest(
            endpoint = "api/v1/instance",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Search for content in accounts, statuses and hashtags.
     * @param query string to search for
     * @param resolve whether to resolve non-local accounts
     * @see <a href="https://docs.joinmastodon.org/methods/search/">Mastodon API documentation: methods/search</a>
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
     * Get the public timeline of the configured instance. Defaults to a combination of local and remote statuses,
     * but can be restricted to either.
     * @param range restrict result to a specific range
     * @param statusOrigin optionally restrict result to either local or remote (=federated) statuses; defaults to all
     * @see <a href="https://docs.joinmastodon.org/methods/timelines/#public">Mastodon API documentation: methods/timelines/#public</a>
     */
    @JvmOverloads
    fun getPublic(
        range: Range = Range(),
        statusOrigin: StatusOrigin = StatusOrigin.LOCAL_AND_REMOTE
    ): MastodonRequest<Pageable<Status>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/timelines/public",
            method = MastodonClient.Method.GET,
            parameters = range.toParameter().apply {
                when (statusOrigin) {
                    StatusOrigin.LOCAL -> append("local", true)
                    StatusOrigin.REMOTE -> append("remote", true)
                    else -> { /* append neither, combined results will be shown */ }
                }
            }
        )
    }

    /**
     * Get the public timeline for a specific hashtag of the configured instance.
     * Defaults to a combination of local and remote (=federated) statuses, but can be restricted to either.
     * @param tag the hashtag for which a timeline should be returned
     * @param range restrict result to a specific range
     * @param statusOrigin optionally restrict result to either local or remote statuses; defaults to all
     * @see <a href="https://docs.joinmastodon.org/methods/timelines/#tag">Mastodon API documentation: methods/timelines/#tag</a>
     */
    @JvmOverloads
    fun getTag(
        tag: String,
        range: Range = Range(),
        statusOrigin: StatusOrigin = StatusOrigin.LOCAL_AND_REMOTE
    ): MastodonRequest<Pageable<Status>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/timelines/tag/$tag",
            method = MastodonClient.Method.GET,
            parameters = range.toParameter().apply {
                when (statusOrigin) {
                    StatusOrigin.LOCAL -> append("local", true)
                    StatusOrigin.REMOTE -> append("remote", true)
                    else -> { /* append neither, combined results will be shown */ }
                }
            }
        )
    }
}
