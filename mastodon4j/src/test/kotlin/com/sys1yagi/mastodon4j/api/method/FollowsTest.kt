package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class FollowsTest {
    @Test
    fun postRemoteFollow() {
        val client = MockClient.mock("follow.json")

        val follows = Follows(client)
        val account = follows.postRemoteFollow("test").execute()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test
    fun postRemoteFollowWithException() {
        Assertions.assertThrows(Mastodon4jRequestException::class.java) {
            val client = MockClient.ioException()
            val follows = Follows(client)
            follows.postRemoteFollow("test").execute()
        }
    }
}
