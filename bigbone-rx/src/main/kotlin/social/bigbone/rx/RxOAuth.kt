package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.auth.AccessToken
import social.bigbone.api.method.OAuth
import social.bigbone.rx.extensions.onErrorIfNotDisposed

class RxOAuth(client: MastodonClient) {
    private val oauth = OAuth(client)

    fun getAccessToken(
        clientId: String,
        clientSecret: String,
        redirectUri: String = "urn:ietf:wg:oauth:2.0:oob",
        code: String,
        grantType: String = "authorization_code"
    ): Single<AccessToken> {
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
