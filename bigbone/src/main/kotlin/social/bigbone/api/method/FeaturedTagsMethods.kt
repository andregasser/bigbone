package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.entity.FeaturedTag

/**
 * View information about Feature tags that you use frequently on your profile.
 * @see <a href="https://docs.joinmastodon.org/methods/featured_tags/">Mastodon featured_tags API methods</a>
 */
class FeaturedTagsMethods(private val client: MastodonClient) {

    /**
     * List all hashtags featured on your profile.
     * @return List of [FeaturedTag]s on your profile
     */
    fun getFeaturedTags(): MastodonRequest<List<FeaturedTag>> {
        return client.getMastodonRequestForList(
            endpoint = "api/v1/featured_tags",
            method = MastodonClient.Method.GET
        )
    }

}