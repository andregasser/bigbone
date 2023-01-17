package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameter
import social.bigbone.api.Scope
import social.bigbone.api.entity.auth.AppRegistration

/**
 * Register client applications that can be used to obtain OAuth tokens.
 * @see <a href="https://docs.joinmastodon.org/methods/apps/">Mastodon apps API methods</a>
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
}
