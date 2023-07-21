package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Conversation

/**
 * Allows access to API methods with endpoints having an "api/vX/lists" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/lists/">Mastodon lists API methods</a>
 */
class MarkerMethods(private val client: MastodonClient) {

    fun getMarkers(timeline: Timeline) {
        return client.getMastodonRequest(
            endpoint = "/api/v1/markers",
            method = MastodonClient.Method.GET,
            parameters =
        )
    }

    fun updateMarkers() {

    }


    /**
     * View your direct conversations with other participants.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/conversations/#get">Mastodon API documentation: methods/conversations/#get</a>
     */
    @JvmOverloads
    fun getConversations(range: Range = Range()): MastodonRequest<Pageable<Conversation>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/conversations",
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }


}

enum class Timeline {
    ALL,
    HOME,
    NOTIFICATIONS
}
