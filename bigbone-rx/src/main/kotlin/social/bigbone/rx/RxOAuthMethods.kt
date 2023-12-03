package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Scope
import social.bigbone.api.entity.Token
import social.bigbone.api.method.OAuthMethods

/**
 * Reactive implementation of [OAuthMethods].
 * Allows access to API methods with endpoints having an "oauth" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/oauth/">Mastodon oauth API methods</a>
 */
class RxOAuthMethods(client: MastodonClient) {

    private val oAuthMethods = OAuthMethods(client)

    @JvmOverloads
    fun getOAuthUrl(
        clientId: String,
        redirectUri: String,
        scope: Scope? = null
    ): Single<String> = Single.fromCallable {
        oAuthMethods.getOAuthUrl(clientId, redirectUri, scope)
    }

    fun getUserAccessTokenWithAuthorizationCodeGrant(
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        code: String
    ): Single<Token> = Single.fromCallable {
        oAuthMethods.getUserAccessTokenWithAuthorizationCodeGrant(
            clientId,
            clientSecret,
            redirectUri,
            code
        ).execute()
    }

    @JvmOverloads
    fun getUserAccessTokenWithPasswordGrant(
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        username: String,
        password: String,
        scope: Scope = Scope(Scope.Name.READ)
    ): Single<Token> = Single.fromCallable {
        oAuthMethods.getUserAccessTokenWithPasswordGrant(
            clientId,
            clientSecret,
            redirectUri,
            username,
            password,
            scope
        ).execute()
    }
}
