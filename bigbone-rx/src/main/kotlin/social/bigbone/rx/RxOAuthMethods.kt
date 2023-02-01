package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.auth.Token
import social.bigbone.api.method.OAuthMethods
import social.bigbone.rx.extensions.onErrorIfNotDisposed

class RxOAuthMethods(client: MastodonClient) {
    private val oauth = OAuthMethods(client)

    fun getAccessToken(
        clientId: String,
        clientSecret: String,
        redirectUri: String = "urn:ietf:wg:oauth:2.0:oob",
        code: String,
        grantType: String = "authorization_code"
    ): Single<Token> {
        return Single.create {
            try {
                val accessToken = oauth.getAccessToken(clientId, clientSecret, redirectUri, code, grantType)
                it.onSuccess(accessToken.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
