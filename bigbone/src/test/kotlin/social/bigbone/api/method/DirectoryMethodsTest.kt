package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import social.bigbone.testtool.MockClient

class DirectoryMethodsTest {
    @Test
    fun listAccounts() {
        val client = MockClient.mock("accounts.json")

        val directoryMethods = DirectoryMethods(client)
        val accounts = directoryMethods.listAccounts(true).execute()
        accounts.size shouldBeEqualTo 1

        val account = accounts.first()
        account.id shouldBeEqualTo "11111"
        account.acct shouldBeEqualTo "test@test.com"
    }
}
