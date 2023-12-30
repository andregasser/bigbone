package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.entity.FeaturedTag
import social.bigbone.api.entity.Tag
import social.bigbone.api.exception.BigBoneRequestException

/**
 * View information about Feature tags that you use frequently on your profile.
 * @see <a href="https://docs.joinmastodon.org/methods/featured_tags/">Mastodon featured_tags API methods</a>
 */
class FeaturedTagMethods(private val client: MastodonClient) {

    private val endpoint = "api/v1/featured_tags"

    /**
     * List all hashtags featured on your profile.
     * @return List of [FeaturedTag]s on your profile
     */
    fun getFeaturedTags(): MastodonRequest<List<FeaturedTag>> {
        return client.getMastodonRequestForList(
            endpoint = endpoint,
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Promote a hashtag on your profile.
     * @param tagName The hashtag to be featured, without the hash character.
     * @return The [FeaturedTag] successfully created
     * @throws IllegalArgumentException if [tagName] contains a #
     */
    @Throws(IllegalArgumentException::class)
    fun featureTag(tagName: String): MastodonRequest<FeaturedTag> {
        require(!tagName.contains('#')) { "Tag name to be featured must not contain '#'" }

        return client.getMastodonRequest(
            endpoint = endpoint,
            method = MastodonClient.Method.POST,
            parameters = Parameters().append("name", tagName)
        )
    }

    /**
     * Stop promoting a hashtag on your profile.
     * @param tagId The ID of the FeaturedTag in the database you want to stop promoting.
     * @throws IllegalArgumentException if [tagId] is blank
     * @throws BigBoneRequestException if request to unfeature tag failed for any reason
     */
    @Throws(BigBoneRequestException::class, IllegalArgumentException::class)
    fun unfeatureTag(tagId: String) {
        require(tagId.isNotBlank()) { "Tag ID must not be blank" }

        client.performAction(
            endpoint = endpoint,
            method = MastodonClient.Method.DELETE,
            parameters = Parameters().append("id", tagId)
        )
    }

    /**
     * Shows up to 10 recently-used tags.
     * @return List of up to 10 recently-used [Tag]s to feature.
     */
    fun getSuggestedTags(): MastodonRequest<List<Tag>> {
        return client.getMastodonRequestForList(
            endpoint = "$endpoint/suggestions",
            method = MastodonClient.Method.GET
        )
    }
}
