package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Status

/**
 * Allows access to API methods with endpoints having an "api/vX/bookmarks" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/bookmarks/">Mastodon bookmarks API methods</a>
 */
class BookmarkMethods(private val client: MastodonClient) {
    /**
     * View bookmarked statuses.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/bookmarks/#get">Mastodon API documentation: methods/bookmarks/#get</a>
     */
    @JvmOverloads
    fun getBookmarks(range: Range = Range()): MastodonRequest<Pageable<Status>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/bookmarks",
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }
}
