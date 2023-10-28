package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.FeaturedTag
import social.bigbone.api.entity.Tag
import social.bigbone.api.method.FeaturedTagMethods

/**
 * Reactive implementation of [FeaturedTagMethods].
 * View information about Feature tags that you use frequently on your profile.
 * @see <a href="https://docs.joinmastodon.org/methods/featured_tags/">Mastodon featured_tags API methods</a>
 */
class RxFeaturedTagMethods(client: MastodonClient) {

    private val featuredTagsMethods = FeaturedTagMethods(client)

    /**
     * List all hashtags featured on your profile.
     * @return List of [FeaturedTag]s on your profile
     */
    fun getFeaturedTags(): Single<List<FeaturedTag>> = Single.fromCallable {
        featuredTagsMethods.getFeaturedTags().execute()
    }

    /**
     * Promote a hashtag on your profile.
     * @param tagName The hashtag to be featured, without the hash sign.
     * @return The [FeaturedTag] successfully created
     */
    fun featureTag(tagName: String): Single<FeaturedTag> = Single.fromCallable {
        featuredTagsMethods.featureTag(tagName).execute()
    }

    /**
     * Stop promoting a hashtag on your profile.
     * @param tagId The ID of the FeaturedTag in the database you want to stop promoting.
     */
    fun unfeatureTag(tagId: String): Completable = Completable.fromAction { featuredTagsMethods.unfeatureTag(tagId) }

    /**
     * Shows up to 10 recently-used tags.
     * @return List of up to 10 recently-used [Tag]s to feature.
     */
    fun getSuggestedTags(): Single<List<Tag>> = Single.fromCallable {
        featuredTagsMethods.getSuggestedTags().execute()
    }
}
