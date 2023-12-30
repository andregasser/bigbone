package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.entity.Tag

/**
 * View information about or follow/unfollow hashtags.
 * @see <a href="https://docs.joinmastodon.org/methods/tags/">Mastodon tags API methods</a>
 */
class TagMethods(private val client: MastodonClient) {

    private val endpoint = "api/v1/tags"

    /**
     * Show a hashtag and its associated information.
     * @param tagId The name of the hashtag
     * @return information about a single tag
     */
    fun getTag(tagId: String): MastodonRequest<Tag> {
        return client.getMastodonRequest(
            endpoint = "$endpoint/$tagId",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Follow a hashtag. Posts containing a followed hashtag will be inserted into your home timeline.
     * @param tagId The name of the hashtag
     * @return information about a single tag
     */
    fun followTag(tagId: String): MastodonRequest<Tag> {
        return client.getMastodonRequest(
            endpoint = "$endpoint/$tagId/follow",
            method = MastodonClient.Method.POST
        )
    }

    /**
     * Unfollow a hashtag. Posts containing this hashtag will no longer be inserted into your home timeline.
     * @param tagId The name of the hashtag
     * @return information about a single tag
     */
    fun unfollowTag(tagId: String): MastodonRequest<Tag> {
        return client.getMastodonRequest(
            endpoint = "$endpoint/$tagId/unfollow",
            method = MastodonClient.Method.POST
        )
    }
}
