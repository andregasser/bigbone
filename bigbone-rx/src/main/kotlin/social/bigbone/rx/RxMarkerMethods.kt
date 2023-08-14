package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Marker
import social.bigbone.api.entity.Markers
import social.bigbone.api.method.MarkerMethods
import social.bigbone.api.method.Timeline
import social.bigbone.rx.extensions.onErrorIfNotDisposed

/**
 * Reactive implementation of [MarkerMethods].
 * Allows access to API methods with endpoints having an "api/vX/markers" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/markers/">Mastodon markers API methods</a>
 */
class RxMarkerMethods(client: MastodonClient) {
    private val markerMethods = MarkerMethods(client)

    fun getMarkers(timeline: Timeline): Single<Markers> {
        return Single.create {
            try {
                val markers = markerMethods.getMarkers(timeline)
                it.onSuccess(markers.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun updateMarker(timeline: Timeline, lastReadId: Int): Single<Marker> {
        return Single.create {
            try {
                val marker = markerMethods.updateMarker(timeline, lastReadId)
                it.onSuccess(marker.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
