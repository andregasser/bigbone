package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.method.EndorsementMethods

/**
 * Reactive implementation of [EndorsementMethods].
 * Feature other profiles on your own profile. See also accounts/:id/{pin,unpin}.
 * @see <a href="https://docs.joinmastodon.org/methods/endorsements/">Mastodon endorsement API methods</a>
 */
class RxEndorsementMethods(client: MastodonClient) {

    private val endorsementMethods = EndorsementMethods(client)

    /**
     * Accounts that the user is currently featuring on their profile.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/endorsements/#get">Mastodon API documentation: methods/endorsements/#get</a>
     * @return [Pageable] of [Account]s the user is currently featuring on their profile
     */
    @JvmOverloads
    fun getEndorsements(
        range: Range = Range()
    ): Single<Pageable<Account>> {
        return Single.create { emitter ->
            try {
                emitter.onSuccess(endorsementMethods.getEndorsements(range).execute())
            } catch (error: Throwable) {
                emitter.onError(error)
            }
        }
    }
}
