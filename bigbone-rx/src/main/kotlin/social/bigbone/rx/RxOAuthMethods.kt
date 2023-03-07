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
    private val oauth = OAuthMethods(client)

    fun getUserAccessTokenWithAuthorizationCodeGrant(
        clientId: String,
        clientSecret: String,
        redirectUri: String = "urn:ietf:wg:oauth:2.0:oob",
        code: String
    ): Single<Token> {
        return Single.create {
            try {
                val accessToken = oauth.getUserAccessTokenWithAuthorizationCodeGrant(clientId, clientSecret, redirectUri, code)
                it.onSuccess(accessToken.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getUserAccessTokenWithPasswordGrant(
        clientId: String,
        clientSecret: String,
        scope: Scope = Scope(Scope.Name.READ),
        redirectUri: String = "urn:ietf:wg:oauth:2.0:oob",
        username: String,
        password: String
    ): Single<Token> {
        return Single.create {
            try {
                val accessToken = oauth.getUserAccessTokenWithPasswordGrant(clientId, clientSecret, scope, redirectUri, username, password)
                it.onSuccess(accessToken.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
