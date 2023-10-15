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

    fun viewPoll(pollId: String): Single<Poll> = Single.fromCallable(pollMethods.viewPoll(pollId)::execute)

    fun voteOnPoll(pollId: String, choices: List<Int>): Single<Poll> =
        Single.fromCallable(pollMethods.voteOnPoll(pollId, choices)::execute)
}
