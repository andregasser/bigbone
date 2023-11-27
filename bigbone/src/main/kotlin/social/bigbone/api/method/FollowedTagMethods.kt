package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Tag

private const val QUERY_RESULT_LIMIT = 200

/**
 * Allows access to API methods with endpoints having an "api/vX/followed_tags" prefix.
 * View your followed hashtags.
 * @see <a href="https://docs.joinmastodon.org/methods/followed_tags/">Mastodon followed_tags API methods</a>
 */
class FollowedTagMethods(private val client: MastodonClient) {

    private val followedTagsEndpoint = "api/v1/followed_tags"

    /**
     * View your followed hashtags.
     *
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/followed_tags/#get">Mastodon API documentation: methods/followed_tags/#get</a>
     */
    @JvmOverloads
    fun viewAllFollowedTags(range: Range = Range()): MastodonRequest<Pageable<Tag>> {
        with(range) {
            if (limit != null && (limit <= 0 || limit > QUERY_RESULT_LIMIT)) {
                throw IllegalArgumentException("Limit param must be between 0 and $QUERY_RESULT_LIMIT but was $limit")
            }
        }

        return client.getPageableMastodonRequest<Tag>(
            endpoint = followedTagsEndpoint,
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }
}
