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

    @JvmOverloads
    fun getSuggestions(limit: Int? = null): Single<List<Suggestion>> =
        Single.fromCallable { suggestionMethods.getSuggestions(limit).execute() }

    fun removeSuggestion(accountId: String): Completable =
        Completable.fromAction { suggestionMethods.removeSuggestion(accountId) }
}
