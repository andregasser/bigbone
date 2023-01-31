package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Scope
import social.bigbone.api.entity.auth.AccessToken
import social.bigbone.api.method.OAuthMethods
import social.bigbone.rx.extensions.onErrorIfNotDisposed

class RxOAuthMethods(client: MastodonClient) {
    private val oauth = OAuthMethods(client)

    fun getAccessTokenWithAuthorizationCodeGrant(
        clientId: String,
        clientSecret: String,
        redirectUri: String = "urn:ietf:wg:oauth:2.0:oob",
        code: String
    ): Single<AccessToken> {
        return Single.create {
            try {
                val accessToken = oauth.getAccessTokenWithAuthorizationCodeGrant(clientId, clientSecret, redirectUri, code)
                it.onSuccess(accessToken.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getAccessTokenWithPasswordGrant(
        clientId: String,
        clientSecret: String,
        scope: Scope = Scope(Scope.Name.READ),
        username: String,
        password: String
    ): Single<AccessToken> {
        return Single.create {
            try {
                val accessToken = oauth.getAccessTokenWithPasswordGrant(clientId, clientSecret, scope, username, password)
                it.onSuccess(accessToken.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
