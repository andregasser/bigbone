package social.bigbone.api.method

import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.Parameters
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
        account.username shouldBeEqualTo "test"
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
        val client = MockClient.mock("accounts_verify_credentials_success.json")
        val accountMethods = AccountMethods(client)
        val account = accountMethods.verifyCredentials().execute()
        account.acct shouldBeEqualTo "trwnh"
        account.displayName shouldBeEqualTo "infinite love ⴳ"
        account.username shouldBeEqualTo "trwnh"
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
    fun `Given client returning success, when updating credentials with valid profile fields, then ensure correct endpoint parameters`() {
        val client = MockClient.mock("accounts_update_credentials_success.json")
        val accountMethods = AccountMethods(client)
        val profileFields = AccountMethods.ProfileFields(
            first = AccountMethods.ProfileFieldName("Location") to AccountMethods.ProfileFieldValue("Amsterdam, NL 🇳🇱"),
            second = AccountMethods.ProfileFieldName("Pronouns") to AccountMethods.ProfileFieldValue("he/they"),
            third = AccountMethods.ProfileFieldName("Website") to AccountMethods.ProfileFieldValue("https://example.com"),
            fourth = null
        )

        accountMethods.updateCredentials(
            displayName = null,
            note = null,
            avatar = null,
            header = null,
            locked = null,
            bot = null,
            discoverable = null,
            hideCollections = null,
            indexable = null,
            profileFields = profileFields
        ).execute()

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.patch(
                path = "api/v1/accounts/update_credentials",
                body = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo
                "fields_attributes[0][name]=Location" +
                "&fields_attributes[0][value]=Amsterdam%2C+NL+%F0%9F%87%B3%F0%9F%87%B1" +
                "&fields_attributes[1][name]=Pronouns" +
                "&fields_attributes[1][value]=he%2Fthey" +
                "&fields_attributes[2][name]=Website" +
                "&fields_attributes[2][value]=https%3A%2F%2Fexample.com"
        }
    }

    @Test
    fun `Given string with more than 255 characters, when creating ProfileFieldName, then fail with exception`() {
        val tooLongProfileFieldName = "abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "0123456789" +
            "abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "01234567890123456789" +
            "01234567890" +
            "abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "toomanycharacters"

        invoking { AccountMethods.ProfileFieldName(tooLongProfileFieldName) }
            .shouldThrow(IllegalArgumentException::class)
            .withMessage(
                "Name of profile field must not be longer than 255 characters but was: " +
                    "$tooLongProfileFieldName (${tooLongProfileFieldName.length} characters)."
            )
    }

    @Test
    fun `Given string with more than 255 characters, when creating ProfileFieldValue, then fail with exception`() {
        val tooLongProfileFieldValue = "abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "0123456789" +
            "abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "01234567890123456789" +
            "01234567890" +
            "abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "toomanycharacters"

        invoking { AccountMethods.ProfileFieldValue(tooLongProfileFieldValue) }
            .shouldThrow(IllegalArgumentException::class)
            .withMessage(
                "Value of profile field must not be longer than 255 characters but was: " +
                    "$tooLongProfileFieldValue (${tooLongProfileFieldValue.length} characters)."
            )
    }

    @Test
    fun updateCredentials() {
        val client = MockClient.mock("accounts_update_credentials_success.json")
        val accountMethods = AccountMethods(client)
        val account = accountMethods.updateCredentials(
            displayName = "test",
            note = "test",
            avatar = "test",
            header = "test",
            locked = false,
            bot = false,
            discoverable = false,
            hideCollections = false,
            indexable = true,
            profileFields = null
        ).execute()
        account.acct shouldBeEqualTo "trwnh"
        account.displayName shouldBeEqualTo "infinite love ⴳ"
        account.username shouldBeEqualTo "trwnh"
    }

    @Test
    fun updateCredentialsWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val accountMethods = AccountMethods(client)
            accountMethods.updateCredentials(
                displayName = "test",
                note = "test",
                avatar = "test",
                header = "test",
                locked = false,
                bot = false,
                discoverable = false,
                hideCollections = false,
                indexable = true,
                profileFields = null
            ).execute()
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
        account.username shouldBeEqualTo "test"
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
        account.username shouldBeEqualTo "test"
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
        account.username shouldBeEqualTo "A"
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
