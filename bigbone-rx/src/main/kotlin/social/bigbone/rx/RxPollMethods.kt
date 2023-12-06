package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Poll
import social.bigbone.api.method.PollMethods

/**
 * Reactive implementation of [PollMethods].
 * Allows access to API methods with endpoints having an "api/vX/polls" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/polls/">Mastodon polls API methods</a>
 */
class RxPollMethods(client: MastodonClient) {
    private val pollMethods = PollMethods(client)

    /**
     * View a poll attached to a status. To discover poll ID, you will need to GET a Status first and then check for
     *  a poll property.
     * @param pollId the ID of the Poll in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/polls/#get">Mastodon API documentation: methods/polls/#get</a>
     */
    fun viewPoll(pollId: String): Single<Poll> = Single.fromCallable { pollMethods.viewPoll(pollId).execute() }

    /**
     * Vote on a poll attached to a status. To discover poll ID, you will need to GET a Status first and then check for
     *  a poll property.
     * @param pollId the ID of the Poll in the database.
     * @param choices list of Integer. Provide your own votes as an index for each option (starting from 0).
     * @see <a href="https://docs.joinmastodon.org/methods/polls/#vote">Mastodon API documentation: methods/polls/#vote</a>
     */
    fun voteOnPoll(
        pollId: String,
        choices: List<Int>
    ): Single<Poll> = Single.fromCallable { pollMethods.voteOnPoll(pollId, choices).execute() }
}
