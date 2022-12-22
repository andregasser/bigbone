package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigboneRequestException
import social.bigbone.testtool.MockClient

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
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val follows = Follows(client)
            follows.postRemoteFollow("test").execute()
        }
    }
}
