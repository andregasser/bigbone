package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.Scope
import social.bigbone.api.entity.auth.AppRegistration

/**
 * Allows access to API methods with endpoints having an "api/vX/apps" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/apps/">Mastodon apps API methods</a>
 */
class AppMethods(private val client: MastodonClient) {

    /**
     * Create a new application to obtain OAuth2 credentials.
     * @param clientName A name for your application
     * @param redirectUris Where the user should be redirected after authorization. Defaults to "urn:ietf:wg:oauth:2.0:oob",
     *  which will display the authorization code to the user instead of redirecting to a web page.
     * @param scope Space separated list of scopes. Defaults to "read".
     * @param website A URL to the homepage of your app, defaults to null.
     * @see <a href="https://docs.joinmastodon.org/methods/apps/#create">Mastodon apps API methods #create</a>
     */
    @JvmOverloads
    fun createApp(
        clientName: String,
        redirectUris: String = "urn:ietf:wg:oauth:2.0:oob",
        scope: Scope = Scope(Scope.Name.READ),
        website: String? = null
    ): MastodonRequest<AppRegistration> {
        scope.validate()

        val parameters = Parameters()
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
                client.getSerializer().fromJson(it, AppRegistration::class.java)
            }
        )
    }
}
