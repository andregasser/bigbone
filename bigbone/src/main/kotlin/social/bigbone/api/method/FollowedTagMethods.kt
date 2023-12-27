package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Tag

/**
 * Allows access to API methods with endpoints having an "api/vX/followed_tags" prefix.
 * View your followed hashtags.
 * @see <a href="https://docs.joinmastodon.org/methods/followed_tags/">Mastodon followed_tags API methods</a>
 */
class FollowedTagMethods(private val client: MastodonClient) {

    private val endpoint = "api/v1/followed_tags"

    /**
     * View your followed hashtags.
     *
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/followed_tags/#get">Mastodon API documentation: methods/followed_tags/#get</a>
     */
    @JvmOverloads
    fun viewAllFollowedTags(range: Range = Range()): MastodonRequest<Pageable<Tag>> {
        return client.getPageableMastodonRequest<Tag>(
            endpoint = endpoint,
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }
}
