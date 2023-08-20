package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Scope
import social.bigbone.api.entity.Token
import social.bigbone.api.method.OAuthMethods
import social.bigbone.rx.extensions.onErrorIfNotDisposed

/**
 * Reactive implementation of [OAuthMethods].
 * Allows access to API methods with endpoints having an "oauth" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/oauth/">Mastodon oauth API methods</a>
 */
class RxOAuthMethods(client: MastodonClient) {
    private val oAuthMethods = OAuthMethods(client)

    fun getOAuthUrl(clientId: String, scope: Scope, redirectUri: String): Single<String> {
        return Single.create {
            try {
                val oAuthUrl = oAuthMethods.getOAuthUrl(clientId, scope, redirectUri)
                it.onSuccess(oAuthUrl)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getUserAccessTokenWithAuthorizationCodeGrant(
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        code: String
    ): Single<Token> {
        return Single.create {
            try {
                val accessToken = oAuthMethods.getUserAccessTokenWithAuthorizationCodeGrant(clientId, clientSecret, redirectUri, code)
                it.onSuccess(accessToken.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    @JvmOverloads
    fun getUserAccessTokenWithPasswordGrant(
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        username: String,
        password: String,
        scope: Scope = Scope(Scope.Name.READ)
    ): Single<Token> {
        return Single.create {
            try {
                val accessToken = oAuthMethods.getUserAccessTokenWithPasswordGrant(clientId, clientSecret, redirectUri, username, password, scope)
                it.onSuccess(accessToken.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
