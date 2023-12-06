package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Suggestion
import social.bigbone.api.method.SuggestionMethods

/**
 * Reactive implementation of [SuggestionMethods].
 * Allows access to API methods with endpoints having an "api/vX/suggestions" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/suggestions/">Mastodon suggestions API methods</a>
 */
class RxSuggestionMethods(client: MastodonClient) {

    private val suggestionMethods = SuggestionMethods(client)

    /**
     * Accounts that are promoted by staff, or that the user has had past positive interactions with, but is not yet following.
     * @param limit to limit number of results
     * @see <a href="https://docs.joinmastodon.org/methods/suggestions/#v2">Mastodon API documentation: methods/suggestions/#v2</a>
     */
    @JvmOverloads
    fun getSuggestions(limit: Int? = null): Single<List<Suggestion>> = Single.fromCallable {
        suggestionMethods.getSuggestions(limit).execute()
    }

    /**
     * Remove an account from follow suggestions.
     * @param accountId of the account of interest
     * @see <a href="https://docs.joinmastodon.org/methods/suggestions/#v1">Mastodon API documentation: methods/suggestions/#v1</a>
     */
    fun removeSuggestion(accountId: String): Completable = Completable.fromAction {
        suggestionMethods.removeSuggestion(accountId)
    }
}
