package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.entity.Poll
import social.bigbone.api.exception.BigBoneRequestException

/**
 * Allows access to API methods with endpoints having an "api/vX/polls" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/polls/">Mastodon polls API methods</a>
 */
class PollMethods(private val client: MastodonClient) {

    private val endpoint = "api/v1/polls"

    /**
     * View a poll attached to a status. To discover poll ID, you will need to GET a Status first and then check for
     *  a poll property.
     * @param pollId the ID of the Poll in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/polls/#get">Mastodon API documentation: methods/polls/#get</a>
     */
    @Throws(BigBoneRequestException::class)
    fun viewPoll(pollId: String): MastodonRequest<Poll> {
        return client.getMastodonRequest(
            endpoint = "$endpoint/$pollId",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Vote on a poll attached to a status. To discover poll ID, you will need to GET a Status first and then check for
     *  a poll property.
     * @param pollId the ID of the Poll in the database.
     * @param choices list of Integer. Provide your own votes as an index for each option (starting from 0).
     * @see <a href="https://docs.joinmastodon.org/methods/polls/#vote">Mastodon API documentation: methods/polls/#vote</a>
     */
    @Throws(BigBoneRequestException::class)
    fun voteOnPoll(pollId: String, choices: List<Int>): MastodonRequest<Poll> {
        return client.getMastodonRequest(
            endpoint = "$endpoint/$pollId/votes",
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                appendInts("choices", choices)
            }
        )
    }
}
