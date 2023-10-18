package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Suggestion
import social.bigbone.api.method.SuggestionMethods
import io.reactivex.rxjava3.core.Completable
import social.bigbone.rx.extensions.onErrorIfNotDisposed

/**
 * Reactive implementation of [SuggestionMethods].
 * Allows access to API methods with endpoints having an "api/vX/suggestions" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/suggestions/">Mastodon suggestions API methods</a>
 */
class RxSuggestionMethods(client: MastodonClient) {

    private val suggestionMethods = SuggestionMethods(client)

    fun getSuggestions(limit: Int? = null): Single<List<Suggestion>> {
        return Single.create {
            try {
                val suggestion = suggestionMethods.getSuggestions(limit)
                it.onSuccess(suggestion.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun removeSuggestion(accountId: String): Completable {
        return Completable.create {
            try {
                suggestionMethods.removeSuggestion(accountId)
                it.onComplete()
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}