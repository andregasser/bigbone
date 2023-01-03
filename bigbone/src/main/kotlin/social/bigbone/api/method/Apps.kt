package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameter
import social.bigbone.api.Scope
import social.bigbone.api.entity.auth.AccessToken
import social.bigbone.api.entity.auth.AppRegistration

/**
 * Register client applications that can be used to obtain OAuth tokens.
 * @see <a href="https://docs.joinmastodon.org/methods/apps/">Mastodon apps API methods</a>
 *
 * Generate and manage OAuth tokens.
 * @see <a href="https://docs.joinmastodon.org/methods/oauth/">Mastodon oauth API methods</a>
 */
class Apps(private val client: MastodonClient) {

    /**
     * Create a new application to obtain OAuth2 credentials.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/apps/#create">Mastodon apps API methods #create</a>
     */
    @JvmOverloads
    fun createApp(
        clientName: String,
        redirectUris: String = "urn:ietf:wg:oauth:2.0:oob",
        scope: Scope = Scope(Scope.Name.ALL),
        website: String? = null
    ): MastodonRequest<AppRegistration> {
        scope.validate()

        val parameters = Parameter()
            .append("client_name", clientName)
            .append("scopes", scope.toString())
            .append("redirect_uris", redirectUris)
        website?.let {
            parameters.append("website", it)
        }

        return MastodonRequest(
            {
                client.post("api/v1/apps", parameters)
            },
            {
                client.getSerializer().fromJson(it, AppRegistration::class.java).apply {
                    this.instanceName = client.getInstanceName()
                }
            }
        )
    }

    /**
     * Returns a URL that can be used to display an authorization form to the user. If approved,
     * it will create and return an authorization code, then redirect to the desired redirectUri,
     * or show the authorization code if urn:ietf:wg:oauth:2.0:oob was requested.
     * The authorization code can be used while requesting a token to obtain access to user-level methods.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/oauth/#authorize">Mastodon oauth API methods #authorize</a>
     */
    fun getOAuthUrl(clientId: String, scope: Scope, redirectUri: String = "urn:ietf:wg:oauth:2.0:oob"): String {
        val endpoint = "oauth/authorize"
        val params = Parameter()
            .append("client_id", clientId)
            .append("redirect_uri", redirectUri)
            .append("response_type", "code")
            .append("scope", scope.toString())
        return MastodonClient.fullUrl(client.getInstanceName(), endpoint, params).toString()
    }

    /**
     * Obtain an access token, to be used during API calls that are not public.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/oauth/#token">Mastodon oauth API methods #token</a>
     */
    @JvmOverloads
    fun getAccessToken(
        clientId: String,
        clientSecret: String,
        redirectUri: String = "urn:ietf:wg:oauth:2.0:oob",
        code: String,
        grantType: String = "authorization_code"
    ): MastodonRequest<AccessToken> {
        return client.getMastodonRequest(
            endpoint = "oauth/token",
            method = MastodonClient.Method.POST,
            parameters = Parameter().apply {
                append("client_id", clientId)
                append("client_secret", clientSecret)
                append("redirect_uri", redirectUri)
                append("code", code)
                append("grant_type", grantType)
            }
        )
    }
}
