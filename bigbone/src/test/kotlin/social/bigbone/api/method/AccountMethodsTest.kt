package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class AccountMethodsTest {
    @Test
    fun getAccount() {
        val client = MockClient.mock("account.json")
        val accountMethods = AccountMethods(client)
        val account = accountMethods.getAccount("1").execute()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test
    fun getAccountWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val accountMethods = AccountMethods(client)
            accountMethods.getAccount("1").execute()
        }
    }

    @Test
    fun verifyCredentials() {
        val client = MockClient.mock("account.json")
        val accountMethods = AccountMethods(client)
        val account = accountMethods.verifyCredentials().execute()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test
    fun verifyCredentialsWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val accountMethods = AccountMethods(client)
            accountMethods.verifyCredentials().execute()
        }
    }

    // TODO getVerifyCredentialsWith401

    @Test
    fun updateCredentials() {
        val client = MockClient.mock("account.json")
        val accountMethods = AccountMethods(client)
        val account = accountMethods.updateCredentials("test", "test", "test", "test").execute()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test
    fun updateCredentialsWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val accountMethods = AccountMethods(client)
            accountMethods.updateCredentials("test", "test", "test", "test").execute()
        }
    }

    @Test
    fun getFollowers() {
        val client = MockClient.mock("accounts.json")
        val accountMethods = AccountMethods(client)
        val pageable = accountMethods.getFollowers("1").execute()
        val account = pageable.part.first()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test
    fun getFollowersWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val accountMethods = AccountMethods(client)
            accountMethods.getFollowers("1").execute()
        }
    }

    @Test
    fun getFollowing() {
        val client = MockClient.mock("accounts.json")
        val accountMethods = AccountMethods(client)
        val pageable = accountMethods.getFollowing("1").execute()
        val account = pageable.part.first()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test
    fun getFollowingWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val accountMethods = AccountMethods(client)
            accountMethods.getFollowing("1").execute()
        }
    }

    @Test
    fun getStatuses() {
        val client = MockClient.mock("statuses.json")
        val accountMethods = AccountMethods(client)
        val pageable = accountMethods.getStatuses("1", false).execute()
        val status = pageable.part.first()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun getStatusesWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val accountMethods = AccountMethods(client)
            accountMethods.getStatuses("1", false).execute()
        }
    }

    @Test
    fun followAccount() {
        val client = MockClient.mock("relationship.json")
        val accountMethods = AccountMethods(client)
        val relationship = accountMethods.followAccount("1").execute()
        relationship.id shouldBeEqualTo "3361"
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test
    fun followAccountWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val accountMethods = AccountMethods(client)
            accountMethods.followAccount("1").execute()
        }
    }

    @Test
    fun unfollowAccount() {
        val client = MockClient.mock("relationship.json")
        val accountMethods = AccountMethods(client)
        val relationship = accountMethods.unfollowAccount("1").execute()
        relationship.id shouldBeEqualTo "3361"
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test
    fun unfollowAccountWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val accountMethods = AccountMethods(client)
            accountMethods.unfollowAccount("1").execute()
        }
    }

    @Test
    fun blockAccount() {
        val client = MockClient.mock("relationship.json")
        val accountMethods = AccountMethods(client)
        val relationship = accountMethods.blockAccount("1").execute()
        relationship.id shouldBeEqualTo "3361"
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test
    fun blockAccountWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val accountMethods = AccountMethods(client)
            accountMethods.blockAccount("1").execute()
        }
    }

    @Test
    fun unblockAccount() {
        val client = MockClient.mock("relationship.json")
        val accountMethods = AccountMethods(client)
        val relationship = accountMethods.unblockAccount("1").execute()
        relationship.id shouldBeEqualTo "3361"
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test
    fun unblockAccountWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val accountMethods = AccountMethods(client)
            accountMethods.unblockAccount("1").execute()
        }
    }

    @Test
    fun muteAccount() {
        val client = MockClient.mock("relationship.json")
        val accountMethods = AccountMethods(client)
        val relationship = accountMethods.muteAccount("1").execute()
        relationship.id shouldBeEqualTo "3361"
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test
    fun muteAccountWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val accountMethods = AccountMethods(client)
            accountMethods.muteAccount("1").execute()
        }
    }

    @Test
    fun unmuteAccount() {
        val client = MockClient.mock("relationship.json")
        val accountMethods = AccountMethods(client)
        val relationship = accountMethods.unmuteAccount("1").execute()
        relationship.id shouldBeEqualTo "3361"
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test
    fun unmuteAccountWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val accountMethods = AccountMethods(client)
            accountMethods.unmuteAccount("1").execute()
        }
    }

    @Test
    fun getRelationships() {
        val client = MockClient.mock("relationships.json")
        val accountMethods = AccountMethods(client)
        val relationships = accountMethods.getRelationships(listOf("1")).execute()
        val relationship = relationships.first()
        relationship.id shouldBeEqualTo "3361"
        relationship.isFollowing shouldBeEqualTo true
        relationship.isFollowedBy shouldBeEqualTo false
        relationship.isBlocking shouldBeEqualTo false
        relationship.isMuting shouldBeEqualTo false
        relationship.isRequested shouldBeEqualTo false
    }

    @Test
    fun getRelationshipsWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val accountMethods = AccountMethods(client)
            accountMethods.getRelationships(listOf("1")).execute()
        }
    }

    @Test
    fun searchAccounts() {
        val client = MockClient.mock("account_search.json")
        val accountMethods = AccountMethods(client)
        val result = accountMethods.searchAccounts("test").execute()
        val account = result.first()
        account.acct shouldBeEqualTo "A"
        account.displayName shouldBeEqualTo ""
        account.userName shouldBeEqualTo "A"
    }

    @Test
    fun searchAccountsWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val accountMethods = AccountMethods(client)
            accountMethods.searchAccounts("test").execute()
        }
    }
}
