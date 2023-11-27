package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
import social.bigbone.MastodonClient
import social.bigbone.api.method.EmailMethods

/**
 * Reactive implementation of [EmailMethods].
 *  Request a new confirmation email, potentially to a new email address.
 * @see <a href="https://docs.joinmastodon.org/methods/emails/">Mastodon emails API methods</a>
 */
class RxEmailMethods(client: MastodonClient) {

    private val emailMethods = EmailMethods(client)

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
    fun resendConfirmationEmail(emailAddress: String? = null): Completable = Completable.fromAction {
        emailMethods.resendConfirmationEmail(emailAddress = emailAddress)
    }
}
