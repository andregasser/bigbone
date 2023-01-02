package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigboneRequestException
import social.bigbone.testtool.MockClient

class AccountsTest {
    @Test
    fun getAccount() {
        val client = MockClient.mock("account.json")
        val accounts = Accounts(client)
        val account = accounts.getAccount("1").execute()
        account.acct shouldBeEqualTo "gollum@example.org"
        account.displayName shouldBeEqualTo "gollum fan account"
        account.userName shouldBeEqualTo "gollum"
    }

    @Test
    fun getAccountWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.getAccount("1").execute()
        }
    }

    @Test
    fun getVerifyCredentials() {
        val client = MockClient.mock("account.json")
        val accounts = Accounts(client)
        val account = accounts.getVerifyCredentials().execute()
        account.acct shouldBeEqualTo "gollum@example.org"
        account.displayName shouldBeEqualTo "gollum fan account"
        account.userName shouldBeEqualTo "gollum"
    }

    @Test
    fun getVerifyCredentialsWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.getVerifyCredentials().execute()
        }
    }

    // TODO getVerifyCredentialsWith401

    @Test
    fun updateCredential() {
        val client = MockClient.mock("account.json")
        val accounts = Accounts(client)
        val account = accounts.updateCredential("test", "test", "test", "test").execute()
        account.acct shouldBeEqualTo "gollum@example.org"
        account.displayName shouldBeEqualTo "gollum fan account"
        account.userName shouldBeEqualTo "gollum"
    }

    @Test
    fun updateCredentialWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.updateCredential("test", "test", "test", "test").execute()
        }
    }

    @Test
    fun getFollowers() {
        val client = MockClient.mock("accounts.json")
        val accounts = Accounts(client)
        val pageable = accounts.getFollowers("1").execute()
        val account = pageable.part.first()
        account.acct shouldBeEqualTo "bombadil@example.org"
        account.displayName shouldBeEqualTo "Tom Bombadil"
        account.userName shouldBeEqualTo "bombadil"
        val account2 = pageable.part[1]
        account2.acct shouldBeEqualTo "sauron@example.com"
        account2.displayName shouldBeEqualTo "The one with the rings"
        account2.userName shouldBeEqualTo "Sauron"
    }

    @Test
    fun getFollowersWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.getFollowers("1").execute()
        }
    }

    @Test
    fun getFollowing() {
        val client = MockClient.mock("accounts.json")
        val accounts = Accounts(client)
        val pageable = accounts.getFollowing("1").execute()
        val account = pageable.part.first()
        account.acct shouldBeEqualTo "bombadil@example.org"
        account.displayName shouldBeEqualTo "Tom Bombadil"
        account.userName shouldBeEqualTo "bombadil"
        val account2 = pageable.part[1]
        account2.acct shouldBeEqualTo "sauron@example.com"
        account2.displayName shouldBeEqualTo "The one with the rings"
        account2.userName shouldBeEqualTo "Sauron"
    }

    @Test
    fun getFollowingWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.getFollowing("1").execute()
        }
    }

    @Test
    fun getStatuses() {
        val client = MockClient.mock("statuses.json")
        val accounts = Accounts(client)
        val pageable = accounts.getStatuses("1", false).execute()
        val status = pageable.part.first()
        status.id shouldBeEqualTo "108880211901672326"
    }

    @Test
    fun getStatusesWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.getStatuses("1", false).execute()
        }
    }

    @Test
    fun postFollow() {
        val client = MockClient.mock("relationship.json")
        val accounts = Accounts(client)
        val relationship = accounts.postFollow("1").execute()
        relationship.id shouldBeEqualTo "349578"
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test
    fun postFollowWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.postFollow("1").execute()
        }
    }

    @Test
    fun postUnFollow() {
        val client = MockClient.mock("relationship.json")
        val accounts = Accounts(client)
        val relationship = accounts.postUnFollow("1").execute()
        relationship.id shouldBeEqualTo "349578"
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test
    fun postUnFollowWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.postUnFollow("1").execute()
        }
    }

    @Test
    fun postBlock() {
        val client = MockClient.mock("relationship.json")
        val accounts = Accounts(client)
        val relationship = accounts.postBlock("1").execute()
        relationship.id shouldBeEqualTo "349578"
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test
    fun postBlockWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.postBlock("1").execute()
        }
    }

    @Test
    fun postUnblock() {
        val client = MockClient.mock("relationship.json")
        val accounts = Accounts(client)
        val relationship = accounts.postUnblock("1").execute()
        relationship.id shouldBeEqualTo "349578"
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test
    fun postUnblockWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.postUnblock("1").execute()
        }
    }

    @Test
    fun postMute() {
        val client = MockClient.mock("relationship.json")
        val accounts = Accounts(client)
        val relationship = accounts.postMute("1").execute()
        relationship.id shouldBeEqualTo "349578"
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test
    fun postMuteWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.postMute("1").execute()
        }
    }

    @Test
    fun postUnmute() {
        val client = MockClient.mock("relationship.json")
        val accounts = Accounts(client)
        val relationship = accounts.postUnmute("1").execute()
        relationship.id shouldBeEqualTo "349578"
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test
    fun postUnmuteWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.postUnmute("1").execute()
        }
    }

    @Test
    fun getRelationships() {
        val client = MockClient.mock("relationships.json")
        val accounts = Accounts(client)
        val relationships = accounts.getRelationships(listOf("1")).execute()
        val relationship = relationships.first()
        relationship.id shouldBeEqualTo "3457"
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo true
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test
    fun getRelationshipsWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.getRelationships(listOf("1")).execute()
        }
    }

    @Test
    fun getAccountSearch() {
        val client = MockClient.mock("account_search.json")
        val accounts = Accounts(client)
        val result = accounts.getAccountSearch("test").execute()
        val account = result.first()
        account.acct shouldBeEqualTo "gandalf"
        account.displayName shouldBeEqualTo "Formerly grey, now white"
        account.userName shouldBeEqualTo "gandalf"
        val account2 = result[1]
        account2.acct shouldBeEqualTo "bombadil@example.org"
        account2.displayName shouldBeEqualTo "Tom Bombadil"
        account2.userName shouldBeEqualTo "bombadil"
    }

    @Test
    fun getAccountSearchWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val accounts = Accounts(client)
            accounts.getAccountSearch("test").execute()
        }
    }
}
