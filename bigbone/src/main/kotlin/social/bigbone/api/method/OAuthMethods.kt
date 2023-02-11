package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.Scope
import social.bigbone.api.entity.Token

/**
 * Allows access to API methods with endpoints having an "oauth" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/oauth/">Mastodon oauth API methods</a>
 */
class OAuthMethods(private val client: MastodonClient) {

    /**
     * Returns a URL that can be used to display an authorization form to the user. If approved,
     * it will create and return an authorization code, then redirect to the desired redirectUri,
     * or show the authorization code if urn:ietf:wg:oauth:2.0:oob was requested.
     * The authorization code can be used while requesting a token to obtain access to user-level methods.
     * @param clientId The client ID, obtained during app registration.
     * @param scope List of requested OAuth scopes, separated by spaces. Must be a subset of scopes declared during app registration.
     * @param redirectUri Set a URI to redirect the user to. Defaults to "urn:ietf:wg:oauth:2.0:oob",
     *  which will display the authorization code to the user instead of redirecting to a web page.
     *  Must match one of the redirect_uris declared during app registration.
     * @see <a href="https://docs.joinmastodon.org/methods/oauth/#authorize">Mastodon oauth API methods #authorize</a>
     */
    fun getOAuthUrl(clientId: String, scope: Scope, redirectUri: String = "urn:ietf:wg:oauth:2.0:oob"): String {
        val endpoint = "oauth/authorize"
        val params = Parameters()
            .append("client_id", clientId)
            .append("redirect_uri", redirectUri)
            .append("response_type", "code")
            .append("scope", scope.toString())
        return MastodonClient.fullUrl(client.getInstanceName(), endpoint, params).toString()
    }

    /**
     * Obtain a client access token using OAuth2 client credentials grant type.
     * @param clientId The client ID, obtained during app registration.
     * @param clientSecret The client secret, obtained during app registration.
     * @param redirectUri Set a URI to redirect the user to. Defaults to "urn:ietf:wg:oauth:2.0:oob",
     *  which will display the authorization code to the user instead of redirecting to a web page.
     *  Must match one of the redirect_uris declared during app registration.
     *  @see <a href="https://docs.joinmastodon.org/methods/oauth/#token">Mastodon oauth API methods #token</a>
     */
    fun getClientAccessToken(
        clientId: String,
        clientSecret: String,
        redirectUri: String = "urn:ietf:wg:oauth:2.0:oob",
    ): MastodonRequest<Token> {
        return client.getMastodonRequest(
            endpoint = "oauth/token",
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                append("client_id", clientId)
                append("client_secret", clientSecret)
                append("redirect_uri", redirectUri)
                append("grant_type", "client_credentials")
            }
        )
    }

    /**
     * Obtain a user access token using OAuth 2 authorization code grant type. To be used during API calls that are not public.
     * @param clientId The client ID, obtained during app registration.
     * @param clientSecret The client secret, obtained during app registration.
     * @param redirectUri Set a URI to redirect the user to. Defaults to "urn:ietf:wg:oauth:2.0:oob",
     *  which will display the authorization code to the user instead of redirecting to a web page.
     *  Must match one of the redirect_uris declared during app registration.
     * @param code A user authorization code, obtained via the URL received from getOAuthUrl()
     * @see <a href="https://docs.joinmastodon.org/methods/oauth/#token">Mastodon oauth API methods #token</a>
     */
    @JvmOverloads
    fun getUserAccessTokenWithAuthorizationCodeGrant(
        clientId: String,
        clientSecret: String,
        redirectUri: String = "urn:ietf:wg:oauth:2.0:oob",
        code: String
    ): MastodonRequest<Token> {
        return client.getMastodonRequest(
            endpoint = "oauth/token",
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                append("client_id", clientId)
                append("client_secret", clientSecret)
                append("redirect_uri", redirectUri)
                append("code", code)
                append("grant_type", "authorization_code")
            }
        )
    }

    /**
     * Obtain a user access token using OAuth 2 password grant type, to be used for bots and other single-user applications.
     * Where possible, [getUserAccessTokenWithAuthorizationCodeGrant] should be used instead.
     * @param clientId The client ID, obtained during app registration.
     * @param clientSecret The client secret, obtained during app registration.
     * @param scope Requested OAuth scopes
     * @param username The Mastodon account username.
     * @param password The Mastodon account password.
     * @see <a href="https://docs.joinmastodon.org/methods/oauth/#token">Mastodon oauth API methods #token</a>
     */
    @JvmOverloads
    fun getUserAccessTokenWithPasswordGrant(
        clientId: String,
        clientSecret: String,
        scope: Scope = Scope(Scope.Name.READ),
        username: String,
        password: String
    ): MastodonRequest<Token> {
        return client.getMastodonRequest(
            endpoint = "oauth/token",
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                append("client_id", clientId)
                append("client_secret", clientSecret)
                append("scope", scope.toString())
                append("username", username)
                append("password", password)
                append("grant_type", "password")
            }
        )
    }
}
