package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.entity.CustomEmoji

/**
 * Allows access to API methods with endpoints having an "api/vX/custom_emojis" prefix.
 * Each site can define and upload its own custom emoji to be attached to profiles or statuses.
 * @see <a href="https://docs.joinmastodon.org/methods/custom_emojis/">Mastodon custom_emojis API methods</a>
 */
class CustomEmojiMethods(private val client: MastodonClient) {

    private val endpoint = "api/v1/custom_emojis"

    /**
     * Returns custom emojis that are available on the server.
     * @see <a href="https://docs.joinmastodon.org/methods/custom_emojis/#get">Mastodon API documentation: methods/custom_emojis/#get</a>
     */
    fun getAllCustomEmojis(): MastodonRequest<List<CustomEmoji>> {
        return client.getMastodonRequestForList(
            endpoint = endpoint,
            method = MastodonClient.Method.GET
        )
    }
}
