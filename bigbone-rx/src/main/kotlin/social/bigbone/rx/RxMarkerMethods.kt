package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Marker
import social.bigbone.api.entity.Markers
import social.bigbone.api.method.MarkerMethods
import social.bigbone.api.method.Timeline

/**
 * Reactive implementation of [MarkerMethods].
 * Allows access to API methods with endpoints having an "api/vX/markers" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/markers/">Mastodon markers API methods</a>
 */
class RxMarkerMethods(client: MastodonClient) {

    private val markerMethods = MarkerMethods(client)

    /**
     * Get saved timeline positions.
     * @param timeline specifies for which timelines the position markers should be returned. This value
     *         is chosen from the [Timeline] enum. When this value is not provided, all known markers
     *         will be returned.
     */
    @JvmOverloads
    fun getMarkers(timeline: Timeline? = null): Single<Markers> = Single.fromCallable {
        markerMethods.getMarkers(timeline).execute()
    }

    /**
     * Saves the timeline position.
     * @param timeline specifies for which timeline the position marker should be updated. This value
     *         is chosen from the [Timeline] enum.
     * @param lastReadId the id of the last read post.
     */
    fun updateMarker(timeline: Timeline, lastReadId: Int): Single<Marker> = Single.fromCallable {
        markerMethods.updateMarker(timeline, lastReadId).execute()
    }
}
