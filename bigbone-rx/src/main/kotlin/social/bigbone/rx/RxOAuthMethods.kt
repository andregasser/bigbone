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

    /**
     * Returns a URL that can be used to display an authorization form to the user. If approved,
     * it will create and return an authorization code, then redirect to the desired redirectUri,
     * or show the authorization code if redirectUri is "urn:ietf:wg:oauth:2.0:oob".
     * The authorization code can be used while requesting a token to obtain access to user-level methods.
     * @param clientId The client ID, obtained during app registration.
     * @param scope List of requested OAuth scopes, separated by spaces. Must be a subset of scopes declared during app registration.
     * @param redirectUri Set a URI to redirect the user to. Must match one of the redirect_uris declared during app registration.
     * @see <a href="https://docs.joinmastodon.org/methods/oauth/#authorize">Mastodon oauth API methods #authorize</a>
     */
    fun getOAuthUrl(clientId: String, scope: Scope, redirectUri: String): Single<String> = Single.fromCallable {
        oAuthMethods.getOAuthUrl(clientId, scope, redirectUri)
    }

    /**
     * Obtain a user access token using OAuth 2 authorization code grant type. To be used during API calls that are not public.
     * @param clientId The client ID, obtained during app registration.
     * @param clientSecret The client secret, obtained during app registration.
     * @param redirectUri Set a URI to redirect the user to. Must match one of the redirect_uris declared during app registration.
     * @param code A user authorization code, obtained via the URL received from getOAuthUrl()
     * @see <a href="https://docs.joinmastodon.org/methods/oauth/#token">Mastodon oauth API methods #token</a>
     */
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

    /**
     * Obtain a user access token using OAuth 2 password grant type, to be used for bots and other single-user applications.
     * Where possible, [getUserAccessTokenWithAuthorizationCodeGrant] should be used instead.
     * @param clientId The client ID, obtained during app registration.
     * @param clientSecret The client secret, obtained during app registration.
     * @param redirectUri Set a URI to redirect the user to.
     * @param username The Mastodon account username.
     * @param password The Mastodon account password.
     * @param scope Requested OAuth scopes
     * @see <a href="https://docs.joinmastodon.org/methods/oauth/#token">Mastodon oauth API methods #token</a>
     */
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
