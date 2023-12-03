package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
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

    /**
     * Returns a URL that can be used to display an authorization form to the user. If approved,
     * it will create and return an authorization code, then redirect to the desired redirectUri,
     * or show the authorization code if redirectUri is "urn:ietf:wg:oauth:2.0:oob".
     * The authorization code can be used while requesting a token to obtain access to user-level methods.
     * @param clientId The client ID, obtained during app registration.
     * @param redirectUri Set a URI to redirect the user to. Must match one of the redirect_uris declared during app registration.
     * @param scope List of requested OAuth scopes, separated by spaces. Must be a subset of scopes declared during app registration.
     * @param forceLogin Forces the user to re-login, which is necessary for authorizing with multiple accounts from the same instance.
     * @param languageCode The ISO 639-1 two-letter language code to use while rendering the authorization form.
     * @see <a href="https://docs.joinmastodon.org/methods/oauth/#authorize">Mastodon oauth API methods #authorize</a>
     */
    @JvmOverloads
    fun getOAuthUrl(
        clientId: String,
        redirectUri: String,
        scope: Scope? = null,
        forceLogin: Boolean? = null,
        languageCode: String? = null
    ): Single<String> = Single.fromCallable {
        oAuthMethods.getOAuthUrl(
            clientId,
            redirectUri,
            scope,
            forceLogin,
            languageCode
        )
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

    /**
     * Revoke a token.
     *
     * @param clientId The client ID, obtained during app registration.
     * @param clientSecret The client secret, obtained during app registration.
     * @param token The previously obtained token, to be invalidated.
     * @see <a href="https://docs.joinmastodon.org/methods/oauth/#revoke">Mastodon oauth API methods #revoke</a>
     */
    fun revokeToken(
        clientId: String,
        clientSecret: String,
        token: String
    ): Completable = Completable.fromAction { oAuthMethods.revokeToken(clientId, clientSecret, token) }
}
