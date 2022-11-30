package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class FollowRequestsTest {
    @Test
    fun getFollowRequests() {
        val client = MockClient.mock("follow_requests.json")

        val followRequests = FollowRequests(client)
        val pageable = followRequests.getFollowRequests().execute()
        val account = pageable.part.first()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test
    fun getFollowRequestsWithException() {
        Assertions.assertThrows(Mastodon4jRequestException::class.java) {
            val client = MockClient.ioException()
            val followRequests = FollowRequests(client)
            followRequests.getFollowRequests().execute()
        }
    }
}
