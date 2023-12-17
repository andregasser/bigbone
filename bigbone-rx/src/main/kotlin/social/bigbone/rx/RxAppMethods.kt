package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Scope
import social.bigbone.api.entity.Application
import social.bigbone.api.method.AppMethods

/**
 * Reactive implementation of [AppMethods].
 * Allows access to API methods with endpoints having an "api/vX/apps" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/apps/">Mastodon apps API methods</a>
 */
class RxAppMethods(client: MastodonClient) {

    private val appMethods = AppMethods(client)

    /**
     * Create a new application to obtain OAuth2 credentials.
     * @param clientName A name for your application
     * @param redirectUris Where the user should be redirected after authorization.
     * @param website A URL to the homepage of your app, defaults to null.
     * @param scope Space separated list of scopes. Defaults to null, which is interpreted as requesting full "read" scope.
     * @see <a href="https://docs.joinmastodon.org/methods/apps/#create">Mastodon apps API methods #create</a>
     */
    @JvmOverloads
    fun createApp(
        clientName: String,
        redirectUris: String,
        website: String? = null,
        scope: Scope? = null
    ): Single<Application> = Single.fromCallable {
        appMethods.createApp(clientName, redirectUris, website, scope).execute()
    }

    /**
     * Confirm that the app’s OAuth2 credentials work.
     * @see <a href="https://docs.joinmastodon.org/methods/apps/#verify_credentials">Mastodon API documentation: methods/apps/#verify_credentials</a>
     */
    fun verifyCredentials(): Single<Application> = Single.fromCallable {
        appMethods.verifyCredentials().execute()
    }
}
