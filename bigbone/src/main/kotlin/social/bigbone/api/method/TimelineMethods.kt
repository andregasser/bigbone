package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Status
import social.bigbone.api.exception.BigBoneRequestException

/**
 * Allows access to API methods with endpoints having an "api/vX/timelines" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/timelines/">Mastodon timelines API methods</a>
 */
class TimelineMethods(private val client: MastodonClient) {
    /**
     * Public timelines can consist of only local statuses, only remote (=federated) statuses, or a combination of both.
     */
    enum class StatusOrigin {
        LOCAL,
        REMOTE,
        LOCAL_AND_REMOTE
    }

    /**
     * Gets the home timeline of statuses.
     * @param range restrict result to a specific range
     * @see <a href="https://docs.joinmastodon.org/methods/timelines/#home">Mastodon API documentation: methods/timelines/#home</a>
     */
    @JvmOverloads
    @Throws(BigBoneRequestException::class)
    fun getHomeTimeline(range: Range = Range()): MastodonRequest<Pageable<Status>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/timelines/home",
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }

    /**
     * Gets a list timeline of statuses for the given list.
     * @param listID ID of the list for which a timeline should be returned
     * @param range restrict result to a specific range
     * @see <a href="https://docs.joinmastodon.org/methods/timelines/#list">Mastodon API documentation: methods/timelines/#list</a>
     */
    @Throws(BigBoneRequestException::class)
    fun getListTimeline(listID: String, range: Range = Range()): MastodonRequest<Pageable<Status>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/timelines/list/$listID",
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
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
    fun getPublicTimeline(
        range: Range = Range(),
        statusOrigin: StatusOrigin = StatusOrigin.LOCAL_AND_REMOTE
    ): MastodonRequest<Pageable<Status>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/timelines/public",
            method = MastodonClient.Method.GET,
            parameters = range.toParameters().apply {
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
    fun getTagTimeline(
        tag: String,
        range: Range = Range(),
        statusOrigin: StatusOrigin = StatusOrigin.LOCAL_AND_REMOTE
    ): MastodonRequest<Pageable<Status>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/timelines/tag/$tag",
            method = MastodonClient.Method.GET,
            parameters = range.toParameters().apply {
                when (statusOrigin) {
                    StatusOrigin.LOCAL -> append("local", true)
                    StatusOrigin.REMOTE -> append("remote", true)
                    else -> { /* append neither, combined results will be shown */ }
                }
            }
        )
    }
}
