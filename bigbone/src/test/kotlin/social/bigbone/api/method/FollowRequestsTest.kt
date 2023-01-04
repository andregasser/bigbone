package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigboneRequestException
import social.bigbone.testtool.MockClient

class FollowRequestsTest {
    @Test
    fun getFollowRequests() {
        val client = MockClient.mock("follow_requests.json")

        val followRequests = FollowRequests(client)
        val pageable = followRequests.getFollowRequests().execute()
        val account = pageable.part.first()
        account.acct shouldBeEqualTo "gandalf@example.org"
        account.displayName shouldBeEqualTo "Gandalf"
        account.userName shouldBeEqualTo "gandalf"
    }

    @Test
    fun getFollowRequestsWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val followRequests = FollowRequests(client)
            followRequests.getFollowRequests().execute()
        }
    }
}
