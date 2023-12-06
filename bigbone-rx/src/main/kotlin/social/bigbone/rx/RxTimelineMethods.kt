package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Status
import social.bigbone.api.method.TimelineMethods

/**
 * Reactive implementation of [TimelineMethods].
 * Allows access to API methods with endpoints having an "api/vX/timelines" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/timelines/">Mastodon timelines API methods</a>
 */
class RxTimelineMethods(client: MastodonClient) {
    private val timelineMethods = TimelineMethods(client)

    /**
     * Gets the home timeline of statuses.
     * @param range restrict result to a specific range
     * @see <a href="https://docs.joinmastodon.org/methods/timelines/#home">Mastodon API documentation: methods/timelines/#home</a>
     */
    @JvmOverloads
    fun getHomeTimeline(range: Range = Range()): Single<Pageable<Status>> = Single.fromCallable { timelineMethods.getHomeTimeline(range).execute() }

    /**
     * Gets a list timeline of statuses for the given list.
     * @param listId ID of the list for which a timeline should be returned
     * @param range restrict result to a specific range
     * @see <a href="https://docs.joinmastodon.org/methods/timelines/#list">Mastodon API documentation: methods/timelines/#list</a>
     */
    @JvmOverloads
    fun getListTimeline(
        listId: String,
        range: Range = Range()
    ): Single<Pageable<Status>> = Single.fromCallable { timelineMethods.getListTimeline(listId, range).execute() }

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
        statusOrigin: TimelineMethods.StatusOrigin = TimelineMethods.StatusOrigin.LOCAL_AND_REMOTE,
        onlyMedia: Boolean? = null,
        range: Range = Range()
    ): Single<Pageable<Status>> = Single.fromCallable { timelineMethods.getPublicTimeline(statusOrigin, onlyMedia, range).execute() }

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
        statusOrigin: TimelineMethods.StatusOrigin = TimelineMethods.StatusOrigin.LOCAL_AND_REMOTE,
        range: Range = Range(),
        any: List<String>? = null,
        all: List<String>? = null,
        none: List<String>? = null,
        onlyMedia: Boolean? = null
    ): Single<Pageable<Status>> =
        Single.fromCallable {
            timelineMethods.getTagTimeline(
                tag = tag,
                statusOrigin = statusOrigin,
                range = range,
                any = any,
                all = all,
                none = none,
                onlyMedia = onlyMedia
            ).execute()
        }
}
