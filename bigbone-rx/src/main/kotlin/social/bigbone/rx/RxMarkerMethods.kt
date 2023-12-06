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

    @JvmOverloads
    fun getMarkers(timeline: Timeline? = null): Single<Markers> =
        Single.fromCallable { markerMethods.getMarkers(timeline).execute() }

    fun updateMarker(
        timeline: Timeline,
        lastReadId: Int
    ): Single<Marker> = Single.fromCallable { markerMethods.updateMarker(timeline, lastReadId).execute() }
}
