@file:Suppress("MaxLineLength")

package social.bigbone.api.method.admin

import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.AnyException
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeFalse
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldBeTrue
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldNotThrow
import org.junit.jupiter.api.Test
import social.bigbone.MastodonClient
import social.bigbone.Parameters
import social.bigbone.PrecisionDateTime
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.admin.AdminDomainBlock
import social.bigbone.testtool.MockClient
import social.bigbone.testtool.TestUtil.urlEncode
import java.time.Instant

class AdminDomainBlockMethodsTest {

    @Test
    fun `Given client returning success, when getting all domain blocks without Range, then ensure proper deserialisation and correct endpoint and parameter usage`() {
        val client = MockClient.mock("admin_domain_blocks_get_all_success.json")
        val adminDomainBlockMethods = AdminDomainBlockMethods(client)

        val domainBlocks: Pageable<AdminDomainBlock> = adminDomainBlockMethods.getAllBlockedDomains().execute()

        with(domainBlocks.part) {
            shouldHaveSize(1)

            with(get(0)) {
                id shouldBeEqualTo "1"
                domain shouldBeEqualTo "example.com"
                createdAt shouldBeEqualTo PrecisionDateTime.ValidPrecisionDateTime.ExactTime(Instant.parse("2022-11-16T08:15:34.238Z"))
                severity shouldBeEqualTo AdminDomainBlock.Severity.NOOP
                rejectMedia.shouldBeFalse()
                rejectReports.shouldBeFalse()
                obfuscate.shouldBeFalse()
                privateComment.shouldBeNull()
                publicComment.shouldBeNull()
            }
        }

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.get(
                path = "api/v1/admin/domain_blocks",
                query = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery().shouldBeEmpty()
        }
    }

    @Test
    fun `Given client returning success, when getting all domain blocks with Range params, then ensure correct parameter usage`() {
        val client = MockClient.mock("admin_domain_blocks_get_all_success.json")
        val adminDomainBlockMethods = AdminDomainBlockMethods(client)
        val range = Range(
            minId = "1234",
            maxId = "5678"
        )

        val domainBlocks: Pageable<AdminDomainBlock> = adminDomainBlockMethods.getAllBlockedDomains(
            range = range
        ).execute()

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.get(
                path = "api/v1/admin/domain_blocks",
                query = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "max_id=${range.maxId}&min_id=${range.minId}"
        }
    }

    @Test
    fun `Given client returning success, when getting single domain block, then ensure proper deserialisation and correct endpoint and parameter usage`() {
        val client = MockClient.mock("admin_domain_blocks_get_single_success.json")
        val adminDomainBlockMethods = AdminDomainBlockMethods(client)
        val blockedDomainId = "1"

        val domainBlock: AdminDomainBlock = adminDomainBlockMethods.getBlockedDomain(
            id = blockedDomainId
        ).execute()

        with(domainBlock) {
            id shouldBeEqualTo "1"
            domain shouldBeEqualTo "example.com"
            createdAt shouldBeEqualTo PrecisionDateTime.ValidPrecisionDateTime.ExactTime(Instant.parse("2022-11-16T08:15:34.238Z"))
            severity shouldBeEqualTo AdminDomainBlock.Severity.NOOP
            rejectMedia.shouldBeFalse()
            rejectReports.shouldBeFalse()
            obfuscate.shouldBeFalse()
            privateComment.shouldBeNull()
            publicComment.shouldBeNull()
        }

        verify {
            client.get(
                path = "api/v1/admin/domain_blocks/$blockedDomainId",
                query = null
            )
        }
    }

    @Test
    fun `Given client returning success, when blocking a domain, then ensure proper deserialisation and correct endpoint and parameter usage`() {
        val client = MockClient.mock("admin_domain_blocks_block_success.json")
        val adminDomainBlockMethods = AdminDomainBlockMethods(client)
        val domain = "example.com"
        val severity = AdminDomainBlock.Severity.NOOP
        val rejectMedia = true
        val rejectReports = false
        val obfuscate = true
        val privateComment = "private comment"
        val publicComment = "public comment"

        val domainBlock: AdminDomainBlock = adminDomainBlockMethods.blockDomain(
            domain = domain,
            severity = severity,
            rejectMedia = rejectMedia,
            rejectReports = rejectReports,
            obfuscate = obfuscate,
            privateComment = privateComment,
            publicComment = publicComment
        ).execute()

        with(domainBlock) {
            this.id shouldBeEqualTo "1"
            this.domain shouldBeEqualTo "example.com"
            this.createdAt shouldBeEqualTo PrecisionDateTime.ValidPrecisionDateTime.ExactTime(Instant.parse("2022-11-16T08:15:34.238Z"))
            this.severity shouldBeEqualTo AdminDomainBlock.Severity.NOOP
            this.rejectMedia.shouldBeTrue()
            this.rejectReports.shouldBeFalse()
            this.obfuscate.shouldBeTrue()
            this.privateComment shouldBeEqualTo privateComment
            this.publicComment shouldBeEqualTo publicComment
        }

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.post(
                path = "api/v1/admin/domain_blocks",
                body = capture(parametersCapturingSlot),
                addIdempotencyKey = false
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "domain=example.com" +
                "&severity=noop" +
                "&reject_media=true" +
                "&reject_reports=false" +
                "&obfuscate=true" +
                "&private_comment=${privateComment.urlEncode()}" +
                "&public_comment=${publicComment.urlEncode()}"
        }
    }

    @Test
    fun `Given client returning success, when updating a blocked domain, then ensure proper deserialisation and correct endpoint and parameter usage`() {
        val client = MockClient.mock("admin_domain_blocks_update_success.json")
        val adminDomainBlockMethods = AdminDomainBlockMethods(client)
        val blockedDomainId = "1"
        val severity = AdminDomainBlock.Severity.SUSPEND

        val domainBlock: AdminDomainBlock = adminDomainBlockMethods.updateBlockedDomain(
            id = blockedDomainId,
            severity = severity
        ).execute()

        with(domainBlock) {
            this.id shouldBeEqualTo "1"
            this.domain shouldBeEqualTo "example.com"
            this.createdAt shouldBeEqualTo PrecisionDateTime.ValidPrecisionDateTime.ExactTime(Instant.parse("2022-11-16T08:15:34.238Z"))
            this.severity shouldBeEqualTo AdminDomainBlock.Severity.NOOP
            this.rejectMedia.shouldBeFalse()
            this.rejectReports.shouldBeFalse()
            this.obfuscate.shouldBeFalse()
            this.privateComment.shouldBeNull()
            this.publicComment.shouldBeNull()
        }

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.put(
                path = "api/v1/admin/domain_blocks/$blockedDomainId",
                body = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "severity=suspend"
        }
    }

    @Test
    fun `Given client returning success, when removing blocked domain, then do not throw any exception and expect correct endpoint`() {
        val client = MockClient.mock("admin_ip_blocks_single.json")
        val adminDomainBlockMethods = AdminDomainBlockMethods(client)
        val blockedDomainId = "1"

        invoking {
            adminDomainBlockMethods.removeBlockedDomain(id = blockedDomainId)
        } shouldNotThrow AnyException

        verify {
            client.performAction(
                endpoint = "api/v1/admin/domain_blocks/$blockedDomainId",
                method = MastodonClient.Method.DELETE
            )
        }
    }
}
