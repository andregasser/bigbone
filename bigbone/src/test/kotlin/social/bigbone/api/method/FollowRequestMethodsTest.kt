package social.bigbone.api.method

import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeFalse
import org.amshove.kluent.shouldBeTrue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.entity.Relationship
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class FollowRequestMethodsTest {
    @Test
    fun getFollowRequests() {
        val client = MockClient.mock("follow_requests.json")

        val followRequestMethods = FollowRequestMethods(client)
        val pageable = followRequestMethods.getFollowRequests().execute()
        val account = pageable.part.first()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.username shouldBeEqualTo "test"
    }

    @Test
    fun getFollowRequestsWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val followRequestMethods = FollowRequestMethods(client)
            followRequestMethods.getFollowRequests().execute()
        }
    }

    @Test
    fun `Given client returning success, when accepting follow requests, then call correct endpoint and parse response`() {
        val client = MockClient.mock("follow_requests_accept_follow_requests_success.json")
        val followRequestMethods = FollowRequestMethods(client)
        val accountId = "8889777"

        val authorizedRelationship: Relationship = followRequestMethods.authorizeFollowRequest(accountId).execute()
        with(authorizedRelationship) {
            id shouldBeEqualTo "8889777"
            isFollowing.shouldBeFalse()
            isShowingReblogs.shouldBeFalse()
            isFollowedBy.shouldBeTrue()
            isBlocking.shouldBeFalse()
            isBlockedBy.shouldBeFalse()
            isMuting.shouldBeFalse()
            isMutingNotifications.shouldBeFalse()
            isRequested.shouldBeFalse()
            isDomainBlocking.shouldBeFalse()
            endorsed.shouldBeFalse()
        }

        verify {
            client.post(
                path = "api/v1/follow_requests/$accountId/authorize",
                body = null,
                addIdempotencyKey = false
            )
        }
    }

    @Test
    fun `Given client returning success, when rejecting follow requests, then call correct endpoint and parse response`() {
        val client = MockClient.mock("follow_requests_accept_follow_requests_success.json")
        val followRequestMethods = FollowRequestMethods(client)
        val accountId = "8889777"

        val authorizedRelationship: Relationship = followRequestMethods.rejectFollowRequest(accountId).execute()
        with(authorizedRelationship) {
            id shouldBeEqualTo "8889777"
            isFollowing.shouldBeFalse()
            isShowingReblogs.shouldBeFalse()
            isFollowedBy.shouldBeTrue()
            isBlocking.shouldBeFalse()
            isBlockedBy.shouldBeFalse()
            isMuting.shouldBeFalse()
            isMutingNotifications.shouldBeFalse()
            isRequested.shouldBeFalse()
            isDomainBlocking.shouldBeFalse()
            endorsed.shouldBeFalse()
        }

        verify {
            client.post(
                path = "api/v1/follow_requests/$accountId/reject",
                body = null,
                addIdempotencyKey = false
            )
        }
    }
}
