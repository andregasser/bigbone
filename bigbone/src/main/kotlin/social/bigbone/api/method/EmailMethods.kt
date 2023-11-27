package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.Parameters

/**
 * Request a new confirmation email, potentially to a new email address.
 * @see <a href="https://docs.joinmastodon.org/methods/emails/">Mastodon emails API methods</a>
 */
class EmailMethods(private val client: MastodonClient) {

    private val emailsEndpoint = "api/v1/emails"

    /**
     * Request a new confirmation email for an unconfirmed user, potentially to a new email address.
     *
     * OAuth info: You need to use the user token issued to the client that created the unconfirmed user!
     *
     * @param emailAddress Optional new email address to send a confirmation email to. If this is null (default),
     * the original email address will be used.
     * @see <a href="https://docs.joinmastodon.org/methods/emails/#confirmation">Mastodon API documentation: methods/emails/#confirmation</a>
     */
    @JvmOverloads
    fun resendConfirmationEmail(emailAddress: String? = null) {
        client.performAction(
            endpoint = "$emailsEndpoint/confirmations",
            method = MastodonClient.Method.POST,
            parameters = emailAddress?.let { Parameters().append("email", emailAddress) }
        )
    }
}
