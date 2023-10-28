package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.CustomEmoji
import social.bigbone.api.method.CustomEmojiMethods

/**
 * Reactive implementation of [CustomEmojiMethods].
 * Allows access to API methods with endpoints having an "api/vX/custom_emojis" prefix.
 * Each site can define and upload its own custom emoji to be attached to profiles or statuses.
 * @see <a href="https://docs.joinmastodon.org/methods/custom_emojis/">Mastodon custom_emojis API methods</a>
 */
class RxCustomEmojiMethods(client: MastodonClient) {

    private val customEmojiMethods = CustomEmojiMethods(client)

    /**
     * Returns custom emojis that are available on the server.
     * * @see <a href="https://docs.joinmastodon.org/methods/custom_emojis/#get">Mastodon API documentation: methods/custom_emojis/#get</a>
     */
    fun getAllCustomEmojis(): Single<List<CustomEmoji>> = Single.fromCallable {
        customEmojiMethods.getAllCustomEmojis().execute()
    }
}
