package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Tag
import social.bigbone.api.method.FollowedTagMethods

/**
 * Reactive implementation of [FollowedTagMethods].
 * Allows access to API methods with endpoints having an "api/vX/followed_tags" prefix.
 * View your followed hashtags.
 * @see <a href="https://docs.joinmastodon.org/methods/followed_tags/">Mastodon followed_tags API methods</a>
 */
class RxFollowedTagMethods(client: MastodonClient) {
    private val followedTagMethods = FollowedTagMethods(client)

    /**
     * View your followed hashtags.
     *
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/followed_tags/#get">Mastodon API documentation: methods/followed_tags/#get</a>
     */
    @JvmOverloads
    fun viewAllFollowedTags(range: Range = Range()): Single<Pageable<Tag>> =
        Single.fromCallable { followedTagMethods.viewAllFollowedTags(range).execute() }
}
