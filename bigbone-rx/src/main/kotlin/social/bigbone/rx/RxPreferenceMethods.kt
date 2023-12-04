package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Preferences
import social.bigbone.api.method.PreferenceMethods

/**
 * Reactive implementation of [PreferenceMethods].
 * Allows access to API methods with endpoint equal to an "api/vX/preferences".
 * @see [Mastodon preferences API methods](https://docs.joinmastodon.org/methods/preferences/)
 */
class RxPreferenceMethods(client: MastodonClient) {

    private val preferenceMethods = PreferenceMethods(client)

    /**
     * Get preferences about an account.
     * @see <a href="https://docs.joinmastodon.org/methods/preferences/#get">Mastodon API documentation: methods/preferences/#get</a>
     */
    fun getPreferences(): Single<Preferences> {
        return Single.create {
            try {
                val preferences = preferenceMethods.getPreferences()
                it.onSuccess(preferences.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
