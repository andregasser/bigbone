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

    private val endpoint = "api/v1/timelines"

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
            endpoint = "$endpoint/home",
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }

    /**
     * Gets a list timeline of statuses for the given list.
     * @param listId ID of the list for which a timeline should be returned
     * @param range restrict result to a specific range
     * @see <a href="https://docs.joinmastodon.org/methods/timelines/#list">Mastodon API documentation: methods/timelines/#list</a>
     */
    @JvmOverloads
    @Throws(BigBoneRequestException::class)
    fun getListTimeline(listId: String, range: Range = Range()): MastodonRequest<Pageable<Status>> {
        return client.getPageableMastodonRequest(
            endpoint = "$endpoint/list/$listId",
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }

    /**
     * Get the public timeline of the configured instance. Defaults to a combination of local and remote statuses,
     * but can be restricted to either.
     * @param statusOrigin optionally restrict result to either local or remote (=federated) statuses; defaults to all
     * @param onlyMedia Show only statuses with media attached? Defaults to false.
     * @param range restrict result to a specific range
     * @see <a href="https://docs.joinmastodon.org/methods/timelines/#public">Mastodon API documentation: methods/timelines/#public</a>
     */
    @JvmOverloads
    fun getPublicTimeline(
        statusOrigin: StatusOrigin = StatusOrigin.LOCAL_AND_REMOTE,
        onlyMedia: Boolean? = null,
        range: Range = Range()
    ): MastodonRequest<Pageable<Status>> {
        return client.getPageableMastodonRequest(
            endpoint = "$endpoint/public",
            method = MastodonClient.Method.GET,
            parameters = range.toParameters().apply {
                when (statusOrigin) {
                    StatusOrigin.LOCAL -> append("local", true)
                    StatusOrigin.REMOTE -> append("remote", true)
                    // append neither, combined results will be shown
                    else -> {}
                }
                onlyMedia?.let { append("only_media", onlyMedia) }
            }
        )
    }

    /**
     * Get the public timeline for a specific hashtag of the configured instance.
     * Defaults to a combination of local and remote (=federated) statuses, but can be restricted to either.
     * @param tag the hashtag for which a timeline should be returned
     * @param statusOrigin optionally restrict result to either local or remote statuses; defaults to all
     * @param range restrict result to a specific range
     * @param any Return statuses that contain any of these additional tags.
     * @param all Return statuses that contain all of these additional tags.
     * @param none Return statuses that contain none of these additional tags.
     * @param onlyMedia Show only statuses with media attached? Defaults to false.
     * @see <a href="https://docs.joinmastodon.org/methods/timelines/#tag">Mastodon API documentation: methods/timelines/#tag</a>
     */
    @JvmOverloads
    fun getTagTimeline(
        tag: String,
        statusOrigin: StatusOrigin = StatusOrigin.LOCAL_AND_REMOTE,
        range: Range = Range(),
        any: List<String>? = null,
        all: List<String>? = null,
        none: List<String>? = null,
        onlyMedia: Boolean? = null
    ): MastodonRequest<Pageable<Status>> {
        return client.getPageableMastodonRequest(
            endpoint = "$endpoint/tag/$tag",
            method = MastodonClient.Method.GET,
            parameters = range.toParameters().apply {
                when (statusOrigin) {
                    StatusOrigin.LOCAL -> append("local", true)
                    StatusOrigin.REMOTE -> append("remote", true)
                    // append neither, combined results will be shown
                    else -> {}
                }
                any?.let { append("any", any) }
                all?.let { append("all", all) }
                none?.let { append("none", none) }
                onlyMedia?.let { append("only_media", onlyMedia) }
            }
        )
    }
}
