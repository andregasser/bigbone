package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.entity.Marker
import social.bigbone.api.entity.Markers
import social.bigbone.api.exception.BigBoneRequestException

/**
 * Allows access to API methods with endpoints having an "api/vX/markers" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/markers/">Mastodon markers API methods</a>
 */
class MarkerMethods(private val client: MastodonClient) {
    /**
     * Get saved timeline positions.
     * @param timeline specifies for which timelines the position markers should be returned. This value
     *         is chosen from the [Timeline] enum.
     */
    fun getMarkers(timeline: Timeline): MastodonRequest<Markers> {
        return client.getMastodonRequest(
            endpoint = "/api/v1/markers",
            method = MastodonClient.Method.GET,
            parameters = Parameters().apply {
                when (timeline) {
                    Timeline.ALL -> {
                        append("timeline", "home")
                        append("timeline", "notifications")
                    }

                    Timeline.HOME -> append("timeline", "home")
                    Timeline.NOTIFICATIONS -> append("timeline", "notifications")
                }
            }
        )
    }

    /**
     * Saves the timeline position.
     * @param timeline specifies for which timeline the position marker should be updated. This value
     *         is chosen from the [Timeline] enum.
     * @param lastReadId the id of the last read post.
     */
    @Throws(BigBoneRequestException::class)
    fun updateMarker(timeline: Timeline, lastReadId: Int): MastodonRequest<Marker> {
        return client.getMastodonRequest(
            endpoint = "api/v1/markers",
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                when (timeline) {
                    Timeline.HOME -> append("home[last_read_id]", lastReadId)
                    Timeline.NOTIFICATIONS -> append("notifications[last_read_id]", lastReadId)
                    else -> throw BigBoneRequestException("Invalid timeline type specified")
                }
            }
        )

    }
}

/**
 * Specifies the type of timeline. This enum is typically used with the Marker API calls.
 * @see <a href="https://docs.joinmastodon.org/entities/Marker/">Mastodon API Marker</a>
 */
enum class Timeline {
    /**
     * All timelines (home and notification).
     */
    ALL,

    /**
     * Home timeline.
     */
    HOME,

    /**
     * Notification timeline.
     */
    NOTIFICATIONS
}
