package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.entity.FeaturedTag
import social.bigbone.api.exception.BigBoneRequestException

/**
 * View information about Feature tags that you use frequently on your profile.
 * @see <a href="https://docs.joinmastodon.org/methods/featured_tags/">Mastodon featured_tags API methods</a>
 */
class FeaturedTagsMethods(private val client: MastodonClient) {

    private val featuredTagsEndpoint = "api/v1/featured_tags"

    /**
     * List all hashtags featured on your profile.
     * @return List of [FeaturedTag]s on your profile
     */
    fun getFeaturedTags(): MastodonRequest<List<FeaturedTag>> {
        return client.getMastodonRequestForList(
            endpoint = featuredTagsEndpoint,
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Promote a hashtag on your profile.
     * @param tagName The hashtag to be featured, without the hash sign.
     * @return The [FeaturedTag] successfully created
     */
    fun featureTag(tagName: String): MastodonRequest<FeaturedTag> {
        return client.getMastodonRequest(
            endpoint = featuredTagsEndpoint,
            method = MastodonClient.Method.POST,
            parameters = Parameters().append("name", tagName)
        )
    }

    /**
     * Stop promoting a hashtag on your profile.
     * @param tagId The ID of the FeaturedTag in the database you want to stop promoting.
     */
    @Throws(BigBoneRequestException::class)
    fun unfeatureTag(tagId: String) {
        client.performAction(
            endpoint = featuredTagsEndpoint,
            method = MastodonClient.Method.DELETE,
            parameters = Parameters().append("id", tagId)
        )
    }

}