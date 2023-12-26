package social.bigbone.api.method.admin

import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test
import social.bigbone.MastodonClient
import social.bigbone.Parameters
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.admin.AdminCanonicalEmailBlock
import social.bigbone.api.entity.admin.BlockCanonicalEmailVariant
import social.bigbone.testtool.MockClient
import social.bigbone.testtool.TestUtil.urlEncode

class AdminCanonicalEmailBlockMethodsTest {

    @Test
    fun `Given client returning success, when getting all canonical email blocks without Range, then call correct endpoint and parse response`() {
        val client = MockClient.mock("admin_canonical_email_blocks_all_blocked_success.json")
        val adminCanonicalEmailBlockMethods = AdminCanonicalEmailBlockMethods(client)

        val canonicalEmailBlocks: Pageable<AdminCanonicalEmailBlock> = adminCanonicalEmailBlockMethods
            .getAllCanonicalEmailBlocks()
            .execute()

        with(canonicalEmailBlocks.part) {
            shouldHaveSize(1)

            with(get(0)) {
                id shouldBeEqualTo "1"
                canonicalEmailHash shouldBeEqualTo "b344e55d11b3fc25d0d53194e0475838bf17e9be67ce3e6469956222d9a34f9c"
            }
        }
        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.get(
                path = "api/v1/admin/canonical_email_blocks",
                query = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery().shouldBeEmpty()
        }
    }

    @Test
    fun `Given client returning success, when getting all canonical email blocks with Range, then use correct parameters`() {
        val client = MockClient.mock("admin_canonical_email_blocks_all_blocked_success.json")
        val adminCanonicalEmailBlockMethods = AdminCanonicalEmailBlockMethods(client)
        val range = Range(limit = 5, sinceId = "1234")

        adminCanonicalEmailBlockMethods.getAllCanonicalEmailBlocks(range).execute()

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.get(
                path = "api/v1/admin/canonical_email_blocks",
                query = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "since_id=${range.sinceId}&limit=${range.limit}"
        }
    }

    @Test
    fun `Given client returning success, when getting single canonical email block, then call correct endpoint and parse response`() {
        val client = MockClient.mock("admin_canonical_email_blocks_single_success.json")
        val adminCanonicalEmailBlockMethods = AdminCanonicalEmailBlockMethods(client)
        val requestedId = "1"

        val canonicalEmailBlock: AdminCanonicalEmailBlock = adminCanonicalEmailBlockMethods
            .getCanonicalEmailBlock(requestedId)
            .execute()

        with(canonicalEmailBlock) {
            id shouldBeEqualTo "1"
            canonicalEmailHash shouldBeEqualTo "b344e55d11b3fc25d0d53194e0475838bf17e9be67ce3e6469956222d9a34f9c"
        }
        verify {
            client.get(
                path = "api/v1/admin/canonical_email_blocks/$requestedId",
                query = null
            )
        }
    }

    @Test
    fun `Given client returning success, when blocking a canonical email by email address, then call correct endpoint and parse response`() {
        val client = MockClient.mock("admin_canonical_email_blocks_block_success.json")
        val adminCanonicalEmailBlockMethods = AdminCanonicalEmailBlockMethods(client)
        val requestedEmailAddress = BlockCanonicalEmailVariant.ByEmail(email = "foo")

        val canonicalEmailBlock: AdminCanonicalEmailBlock = adminCanonicalEmailBlockMethods
            .blockCanonicalEmail(requestedEmailAddress)
            .execute()

        with(canonicalEmailBlock) {
            id shouldBeEqualTo "1"
            canonicalEmailHash shouldBeEqualTo "b344e55d11b3fc25d0d53194e0475838bf17e9be67ce3e6469956222d9a34f9c"
        }
        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.post(
                path = "api/v1/admin/canonical_email_blocks",
                body = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "email=${requestedEmailAddress.email}"
        }
    }

    @Test
    fun `Given client returning success, when blocking a canonical email by email hash, then call correct endpoint and parse response`() {
        val client = MockClient.mock("admin_canonical_email_blocks_block_success.json")
        val adminCanonicalEmailBlockMethods = AdminCanonicalEmailBlockMethods(client)
        val requestedEmailHash = BlockCanonicalEmailVariant.ByHash(
            canonicalEmailHash = "b344e55d11b3fc25d0d53194e0475838bf17e9be67ce3e6469956222d9a34f9c"
        )

        val canonicalEmailBlock: AdminCanonicalEmailBlock = adminCanonicalEmailBlockMethods
            .blockCanonicalEmail(requestedEmailHash)
            .execute()

        with(canonicalEmailBlock) {
            id shouldBeEqualTo "1"
            canonicalEmailHash shouldBeEqualTo "b344e55d11b3fc25d0d53194e0475838bf17e9be67ce3e6469956222d9a34f9c"
        }
        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.post(
                path = "api/v1/admin/canonical_email_blocks",
                body = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "canonical_email_hash=${requestedEmailHash.canonicalEmailHash}"
        }
    }

    @Test
    fun `Given client returning success, when removing a canonical email block, then call correct endpoint`() {
        val client = MockClient.mock("admin_canonical_email_blocks_delete_success.json")
        val adminCanonicalEmailBlockMethods = AdminCanonicalEmailBlockMethods(client)
        val requestedId = "foo"

        adminCanonicalEmailBlockMethods.removeCanonicalEmailBlock(requestedId)

        verify {
            client.performAction(
                endpoint = "api/v1/admin/canonical_email_blocks/$requestedId",
                method = MastodonClient.Method.DELETE,
                parameters = null
            )
        }
    }

    @Test
    fun `Given client returning success, when canonicalising an email address, then call correct endpoint and parse response`() {
        val client = MockClient.mock("admin_canonical_email_blocks_test_success.json")
        val adminCanonicalEmailBlockMethods = AdminCanonicalEmailBlockMethods(client)
        val requestedEmailAddress = "foo"

        val canonicalizedEmailAddress: List<AdminCanonicalEmailBlock> = adminCanonicalEmailBlockMethods
            .canonicalizeAndHashEmailAddress(requestedEmailAddress)
            .execute()

        with(canonicalizedEmailAddress) {
            shouldHaveSize(1)

            with(get(0)) {
                id shouldBeEqualTo "1"
                canonicalEmailHash shouldBeEqualTo "b344e55d11b3fc25d0d53194e0475838bf17e9be67ce3e6469956222d9a34f9c"
            }
        }
        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.post(
                path = "api/v1/admin/canonical_email_blocks/test",
                body = capture(parametersCapturingSlot),
                addIdempotencyKey = false
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "email=${requestedEmailAddress.urlEncode()}"
        }
    }
}
