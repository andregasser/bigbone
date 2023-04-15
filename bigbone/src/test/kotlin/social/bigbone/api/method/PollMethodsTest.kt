package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class PollMethodsTest {
    @Test
    fun viewPoll() {
        val client = MockClient.mock("poll.json")
        val pollMethods = PollMethods(client)
        val poll = pollMethods.viewPoll("34873").execute()
        poll.id shouldBeEqualTo "34873"
        poll.multiple shouldBeEqualTo true
        poll.votesCount shouldBeEqualTo 5
        poll.options.first().title shouldBeEqualTo "option 0"
    }

    @Test
    fun viewPollWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val pollMethods = PollMethods(client)
            pollMethods.viewPoll("pollId").execute()
        }
    }

    @Test
    fun voteOnPoll() {
        val client = MockClient.mock("poll.json")
        val pollMethods = PollMethods(client)
        val poll = pollMethods.voteOnPoll("34873", listOf(0, 2, 4, 9, 6)).execute()
        poll.id shouldBeEqualTo "34873"
        poll.voted shouldBeEqualTo true
        poll.ownVotes?.contains(2) shouldBeEqualTo true
        poll.ownVotes?.contains(5) shouldBeEqualTo false
    }

    @Test
    fun voteOnPollWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val pollMethods = PollMethods(client)
            pollMethods.voteOnPoll("pollId", listOf(0)).execute()
        }
    }
}
