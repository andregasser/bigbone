package social.bigbone.api.method.admin

import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeFalse
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldBeTrue
import org.amshove.kluent.shouldContainSame
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test
import social.bigbone.MastodonClient.Method
import social.bigbone.Parameters
import social.bigbone.PrecisionDateTime.ValidPrecisionDateTime.ExactTime
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Role
import social.bigbone.api.entity.admin.AccountOrigin
import social.bigbone.api.entity.admin.AccountStatus
import social.bigbone.api.entity.admin.ActionAgainstAccount
import social.bigbone.api.entity.admin.AdminAccount
import social.bigbone.testtool.MockClient
import java.time.Instant

class AdminAccountMethodsTest {

    @Test
    fun `Given client returning success, when viewing accounts with all possible filters, then call correct endpoint`() {
        val client = MockClient.mock("admin_accounts_view_accounts_success.json")
        val accountMethods = AdminAccountMethods(client)

        val adminAccountPageable: Pageable<AdminAccount> = accountMethods.viewAccounts(
            origin = AccountOrigin.Remote,
            status = AccountStatus.Active,
            permissions = "permissions",
            roleIds = emptyList(),
            invitedById = "12345",
            username = "username",
            displayName = "User Name",
            byDomain = "foo.social",
            emailAddress = "foo.bar@baz.social",
            ipAddress = "203.0.113.42",
            range = Range(limit = 50)
        ).execute()

        with(adminAccountPageable.part) {
            shouldHaveSize(1)
        }
        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.get(
                path = "api/v2/admin/accounts",
                query = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "limit=50" +
                "&origin=remote" +
                "&status=active" +
                "&permissions=permissions" +
                "&invited_by=12345" +
                "&username=username" +
                "&display_name=User+Name" +
                "&by_domain=foo.social" +
                "&email=foo.bar%40baz.social" +
                "&ip=203.0.113.42"
        }
    }

    @Test
    fun `Given client returning success, when viewing account, then call correct endpoint and parse response`() {
        val client = MockClient.mock("admin_accounts_view_account_success.json")
        val accountMethods = AdminAccountMethods(client)
        val accountId = "108267695853695427"

        val adminAccount: AdminAccount = accountMethods.viewAccount(withId = accountId).execute()

        with(adminAccount) {
            id shouldBeEqualTo "108267695853695427"
            username shouldBeEqualTo "admin"
            domain.shouldBeNull()
            createdAt shouldBeEqualTo ExactTime(Instant.parse("2022-05-08T18:18:53.221Z"))
            email shouldBeEqualTo "admin@mastodon.local"
            confirmed.shouldBeTrue()
            suspended.shouldBeFalse()
            silenced.shouldBeFalse()
            disabled.shouldBeFalse()
            approved.shouldBeTrue()
            locale.shouldBeNull()
            inviteRequest.shouldBeNull()

            with(role) {
                this.id shouldBeEqualTo 3
                name shouldBeEqualTo "Owner"
                color.shouldBeEmpty()
                rawPermissions shouldBeEqualTo 1
                getParsedPermissions() shouldContainSame listOf(Role.Permission.Administrator)
                highlighted.shouldBeTrue()
                this.createdAt shouldBeEqualTo ExactTime(Instant.parse("2022-09-08T22:48:07.983Z"))
                this.updatedAt shouldBeEqualTo ExactTime(Instant.parse("2022-09-08T22:48:07.983Z"))
            }

            with(adminIPs) {
                shouldHaveSize(1)
                get(0).ipAddress shouldBeEqualTo "192.168.42.1"
                get(0).usedAt shouldBeEqualTo ExactTime(Instant.parse("2022-09-08T16:10:38.621Z"))
            }
        }
        verify {
            client.get(
                path = "api/v1/admin/accounts/$accountId",
                query = null
            )
        }
    }

    @Test
    fun `Given client returning success, when performing action against account, then call correct endpoint`() {
        val client = MockClient.mock("admin_accounts_view_account_success.json")
        val accountMethods = AdminAccountMethods(client)
        val accountId = "108267695853695427"

        accountMethods.performActionAgainstAccount(
            withId = accountId,
            type = ActionAgainstAccount.Silence,
            text = "Turd",
            reportId = "2345",
            warningPresetId = "13374223",
            sendEmailNotification = false
        )

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.performAction(
                endpoint = "api/v1/admin/accounts/$accountId/action",
                method = Method.POST,
                parameters = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "type=silence" +
                "&text=Turd" +
                "&report_id=2345" +
                "&warning_preset_id=13374223" +
                "&send_email_notification=false"
        }
    }

    @Test
    fun `Given client returning success, when approving pending account, then call correct endpoint`() {
        val client = MockClient.mock("admin_accounts_approve_success.json")
        val accountMethods = AdminAccountMethods(client)
        val accountId = "108965430868193066"

        val adminAccount: AdminAccount = accountMethods.approvePendingAccount(withId = accountId).execute()

        with(adminAccount) {
            approved.shouldBeTrue()
        }
        verify {
            client.post(
                path = "api/v1/admin/accounts/$accountId/approve",
                body = null
            )
        }
    }

    @Test
    fun `Given client returning success, when rejecting pending account, then call correct endpoint`() {
        val client = MockClient.mock("admin_accounts_reject_success.json")
        val accountMethods = AdminAccountMethods(client)
        val accountId = "108965430868193066"

        val adminAccount: AdminAccount = accountMethods.rejectPendingAccount(withId = accountId).execute()

        adminAccount.approved.shouldBeFalse()
        verify {
            client.post(
                path = "api/v1/admin/accounts/$accountId/reject",
                body = null
            )
        }
    }

    @Test
    fun `Given client returning success, when deleting account, then call correct endpoint`() {
        val client = MockClient.mock("admin_accounts_delete_success.json")
        val accountMethods = AdminAccountMethods(client)
        val accountId = "108965430868193066"

        val adminAccount: AdminAccount = accountMethods.deleteAccount(withId = accountId).execute()

        adminAccount.suspended.shouldBeTrue()
        verify {
            client.delete(
                path = "api/v1/admin/accounts/$accountId",
                body = null
            )
        }
    }

    @Test
    fun `Given client returning success, when enabling account, then call correct endpoint`() {
        val client = MockClient.mock("admin_accounts_enable_disabled_success.json")
        val accountMethods = AdminAccountMethods(client)
        val accountId = "108965430868193066"

        val adminAccount: AdminAccount = accountMethods.enableDisabledAccount(withId = accountId).execute()

        adminAccount.suspended.shouldBeFalse()
        verify {
            client.post(
                path = "api/v1/admin/accounts/$accountId/enable",
                body = null,
                addIdempotencyKey = false
            )
        }
    }

    @Test
    fun `Given client returning success, when unsilencing account, then call correct endpoint`() {
        val client = MockClient.mock("admin_accounts_unsilence_success.json")
        val accountMethods = AdminAccountMethods(client)
        val accountId = "108965430868193066"

        val adminAccount: AdminAccount = accountMethods.unsilenceAccount(withId = accountId).execute()

        adminAccount.silenced.shouldBeFalse()
        verify {
            client.post(
                path = "api/v1/admin/accounts/$accountId/unsilence",
                body = null,
                addIdempotencyKey = false
            )
        }
    }

    @Test
    fun `Given client returning success, when unsuspending account, then call correct endpoint`() {
        val client = MockClient.mock("admin_accounts_unsuspend_success.json")
        val accountMethods = AdminAccountMethods(client)
        val accountId = "108965430868193066"

        val adminAccount: AdminAccount = accountMethods.unsuspendAccount(withId = accountId).execute()

        adminAccount.suspended.shouldBeFalse()
        verify {
            client.post(
                path = "api/v1/admin/accounts/$accountId/unsuspend",
                body = null,
                addIdempotencyKey = false
            )
        }
    }

    @Test
    fun `Given client returning success, when unmarking account as sensitive, then call correct endpoint`() {
        val client = MockClient.mock("admin_accounts_unsensitive_success.json")
        val accountMethods = AdminAccountMethods(client)
        val accountId = "108965430868193066"

        val adminAccount: AdminAccount = accountMethods.unmarkAccountAsSensitive(withId = accountId).execute()

        requireNotNull(adminAccount.sensitized)
        adminAccount.sensitized!!.shouldBeFalse()
        verify {
            client.post(
                path = "api/v1/admin/accounts/$accountId/unsensitive",
                body = null,
                addIdempotencyKey = false
            )
        }
    }
}
