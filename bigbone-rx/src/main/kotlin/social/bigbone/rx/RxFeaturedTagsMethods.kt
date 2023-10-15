package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.entity.FeaturedTag
import social.bigbone.api.entity.Tag
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.api.method.FeaturedTagsMethods

/**
 * Reactive implementation of [FeaturedTagsMethods].
 * View information about Feature tags that you use frequently on your profile.
 * @see <a href="https://docs.joinmastodon.org/methods/featured_tags/">Mastodon featured_tags API methods</a>
 */
class RxFeaturedTagsMethods(client: MastodonClient) {

    private val featuredTagsMethods = FeaturedTagsMethods(client)

    /**
     * List all hashtags featured on your profile.
     * @return List of [FeaturedTag]s on your profile
     */
    fun getFeaturedTags(): Single<MastodonRequest<List<FeaturedTag>>> {
        return Single.create {
            try {
                it.onSuccess(featuredTagsMethods.getFeaturedTags())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    /**
     * Promote a hashtag on your profile.
     * @param tagName The hashtag to be featured, without the hash sign.
     * @return The [FeaturedTag] successfully created
     */
    fun featureTag(tagName: String): Single<MastodonRequest<FeaturedTag>> {
        return Single.create {
            try {
                it.onSuccess(featuredTagsMethods.featureTag(tagName))
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    /**
     * Stop promoting a hashtag on your profile.
     * @param tagId The ID of the FeaturedTag in the database you want to stop promoting.
     */
    @Throws(BigBoneRequestException::class)
    fun unfeatureTag(tagId: String): Single<Any> {
        return Single.create {
            try {
                it.onSuccess(featuredTagsMethods.unfeatureTag(tagId))
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    /**
     * Shows up to 10 recently-used tags.
     * @return List of up to 10 recently-used [Tag]s to feature.
     */
    fun getSuggestedTags(): Single<MastodonRequest<List<Tag>>> {
        return Single.create {
            try {
                it.onSuccess(featuredTagsMethods.getSuggestedTags())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
