package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class MutesMethodsTest {
    @Test
    fun getMutes() {
        val client = MockClient.mock("mutes.json")
        val mutesMethods = MutesMethods(client)
        val pageable = mutesMethods.getMutes().execute()
        val account = pageable.part.first()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test
    fun getMutesWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val mutesMethods = MutesMethods(client)
            mutesMethods.getMutes().execute()
        }
    }
}
