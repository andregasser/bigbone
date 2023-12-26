package social.bigbone.api.method.admin

import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test
import social.bigbone.MastodonClient
import social.bigbone.Parameters
import social.bigbone.PrecisionDateTime.ValidPrecisionDateTime.ExactTime
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.admin.AdminEmailDomainBlock
import social.bigbone.testtool.MockClient
import java.time.Instant

class AdminEmailDomainBlockMethodsTest {

    @Test
    fun `Given client returning success, when getting all blocked Email domains without Range, then call correct endpoint and parse response`() {
        val client = MockClient.mock("admin_email_domain_blocks_all_blocked_success.json")
        val adminEmailDomainBlockMethods = AdminEmailDomainBlockMethods(client)

        val emailDomainBlocks: Pageable<AdminEmailDomainBlock> = adminEmailDomainBlockMethods
            .getAllEmailDomainBlocks()
            .execute()

        with(emailDomainBlocks.part) {
            shouldHaveSize(1)

            with(get(0)) {
                id shouldBeEqualTo "1"
                domain shouldBeEqualTo "foo"
                createdAt shouldBeEqualTo ExactTime(Instant.parse("2022-11-16T06:09:36.176Z"))

                history shouldHaveSize 7
                history[0].day shouldBeEqualTo "1668556800"
                history[0].accounts shouldBeEqualTo "0"
                history[0].uses shouldBeEqualTo "0"
            }
        }
        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.get(
                path = "api/v1/admin/email_domain_blocks",
                query = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery().shouldBeEmpty()
        }
    }

    @Test
    fun `Given client returning success, when getting all blocked Email domains with Range, then use correct parameters`() {
        val client = MockClient.mock("admin_email_domain_blocks_all_blocked_success.json")
        val adminEmailDomainBlockMethods = AdminEmailDomainBlockMethods(client)
        val range = Range(limit = 5, sinceId = "1234")

        adminEmailDomainBlockMethods.getAllEmailDomainBlocks(range).execute()

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.get(
                path = "api/v1/admin/email_domain_blocks",
                query = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "since_id=${range.sinceId}&limit=${range.limit}"
        }
    }

    @Test
    fun `Given client returning success, when getting single Email domain, then call correct endpoint and parse response`() {
        val client = MockClient.mock("admin_email_domain_blocks_single_success.json")
        val adminEmailDomainBlockMethods = AdminEmailDomainBlockMethods(client)
        val requestedId = "1"

        val emailDomainBlock: AdminEmailDomainBlock = adminEmailDomainBlockMethods
            .getEmailDomainBlock(requestedId)
            .execute()

        with(emailDomainBlock) {
            id shouldBeEqualTo "1"
            domain shouldBeEqualTo "foo"
            createdAt shouldBeEqualTo ExactTime(Instant.parse("2022-11-16T06:09:36.176Z"))

            history shouldHaveSize 7
            history[0].day shouldBeEqualTo "1668556800"
            history[0].accounts shouldBeEqualTo "0"
            history[0].uses shouldBeEqualTo "0"
        }
        verify {
            client.get(
                path = "api/v1/admin/email_domain_blocks/$requestedId",
                query = null
            )
        }
    }

    @Test
    fun `Given client returning success, when blocking an Email domain, then call correct endpoint and parse response`() {
        val client = MockClient.mock("admin_email_domain_blocks_block_success.json")
        val adminEmailDomainBlockMethods = AdminEmailDomainBlockMethods(client)
        val requestedDomain = "foo"

        val emailDomainBlock: AdminEmailDomainBlock = adminEmailDomainBlockMethods
            .blockEmailDomain(requestedDomain)
            .execute()

        with(emailDomainBlock) {
            id shouldBeEqualTo "1"
            domain shouldBeEqualTo "foo"
            createdAt shouldBeEqualTo ExactTime(Instant.parse("2022-11-16T06:09:36.176Z"))

            history shouldHaveSize 7
            history[0].day shouldBeEqualTo "1668556800"
            history[0].accounts shouldBeEqualTo "0"
            history[0].uses shouldBeEqualTo "0"
        }
        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.post(
                path = "api/v1/admin/email_domain_blocks",
                body = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "domain=$requestedDomain"
        }
    }

    @Test
    fun `Given client returning success, when removing an Email domain block, then call correct endpoint`() {
        val client = MockClient.mock("admin_email_domain_blocks_delete_success.json")
        val adminEmailDomainBlockMethods = AdminEmailDomainBlockMethods(client)
        val requestedId = "foo"

        adminEmailDomainBlockMethods.removeEmailDomainBlock(requestedId)

        verify {
            client.performAction(
                endpoint = "api/v1/admin/email_domain_blocks/$requestedId",
                method = MastodonClient.Method.DELETE,
                parameters = null
            )
        }
    }
}
