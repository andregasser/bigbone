package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigboneRequestException
import social.bigbone.testtool.MockClient

class MutesTest {
    @Test
    fun getMutes() {
        val client = MockClient.mock("mutes.json")
        val mutes = Mutes(client)
        val pageable = mutes.getMutes().execute()
        val account = pageable.part.first()
        account.acct shouldBeEqualTo "gandalf"
        account.displayName shouldBeEqualTo ""
        account.userName shouldBeEqualTo "gandalf"
    }

    @Test
    fun getMutesWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val mutes = Mutes(client)
            mutes.getMutes().execute()
        }
    }
}
