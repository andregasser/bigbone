package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.entity.Preferences
import social.bigbone.api.exception.BigBoneRequestException

/**
 * Allows access to API methods with endpoint equal to an "api/vX/preferences".
 * @see [Mastodon preferences API methods](https://docs.joinmastodon.org/methods/preferences/)
 */
class PreferenceMethods(private val client: MastodonClient) {

    private val endpoint = "api/v1/preferences"

    /**
     * Get preferences about an account.
     * @see <a href="https://docs.joinmastodon.org/methods/preferences/#get">Mastodon API documentation: methods/preferences/#get</a>
     */
    @Throws(BigBoneRequestException::class)
    fun getPreferences(): MastodonRequest<Preferences> {
        return client.getMastodonRequest(
            endpoint = endpoint,
            method = MastodonClient.Method.GET
        )
    }
}
