package social.bigbone.api.method.admin

import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.AnyException
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotThrow
import org.junit.jupiter.api.Test
import social.bigbone.MastodonClient
import social.bigbone.Parameters
import social.bigbone.api.Range
import social.bigbone.api.entity.admin.AdminIpBlock
import social.bigbone.testtool.MockClient
import java.net.URLEncoder

class AdminIpBlockMethodsTest {

    private val adminIpBlockEndpoint = "api/v1/admin/ip_blocks"

    @Test
    fun `Given client returning success, when getting all blocked ip addresses, then return list of ip addresses`() {
        val client = MockClient.mock("admin_ip_blocks_get_all.json")
        val adminIpBlockMethods = AdminIpBlockMethods(client)
        val range = Range("1", "10", "1", 10)
        val blockedIpAddresses = adminIpBlockMethods.getAllIpBlocks(range).execute()
        blockedIpAddresses.part.size shouldBeEqualTo 2
        with(blockedIpAddresses.part[0]) {
            id shouldBeEqualTo "1"
            ip shouldBeEqualTo "8.8.8.8/32"
            severity?.apiName shouldBeEqualTo "no_access"
        }

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.get(
                path = adminIpBlockEndpoint,
                query = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "max_id=${range.maxId}&min_id=${range.minId}&since_id=${range.sinceId}&limit=${range.limit}"
        }
    }

    @Test
    fun `Given client returning success, when getting specific blocked ip address, then return expected data for single ip`() {
        val client = MockClient.mock("admin_ip_blocks_single.json")
        val adminIpBlockMethods = AdminIpBlockMethods(client)
        val pathVariable = "1"
        val blockedIpAddress = adminIpBlockMethods.getBlockedIpRange(pathVariable).execute()
        with(blockedIpAddress) {
            id shouldBeEqualTo pathVariable
            ip shouldBeEqualTo "8.8.8.8/32"
            severity?.apiName shouldBeEqualTo "no_access"
        }

        verify {
            client.get(
                path = "$adminIpBlockEndpoint/$pathVariable"
            )
        }
    }

    @Test
    fun `Given client returning success, when posting an ip address to be blocked, then check expected data that has been posted`() {
        val client = MockClient.mock("admin_ip_blocks_single.json")
        val adminIpBlockMethods = AdminIpBlockMethods(client)
        val ipToBeBlocked = "8.8.8.8/32"
        val severity = AdminIpBlock.Severity.NO_ACCESS
        val comment = "new_comment"
        invoking {
            adminIpBlockMethods.blockIpRange(ipAddress = ipToBeBlocked, severity = severity, comment = comment).execute()
        } shouldNotThrow AnyException

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.post(
                path = adminIpBlockEndpoint,
                body = capture(parametersCapturingSlot),
                addIdempotencyKey = false
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "ip=${URLEncoder.encode(ipToBeBlocked, "utf-8")}&severity=${severity.apiName}&comment=$comment"
        }
    }

    @Test
    fun `Given client returning success, when updating a blocked ip address, then check expected data that has been sent`() {
        val client = MockClient.mock("admin_ip_blocks_single.json")
        val adminIpBlockMethods = AdminIpBlockMethods(client)
        val idOfIpBlocked = "1"
        val ipToBeBlocked = "8.8.8.8/32"
        val severity = AdminIpBlock.Severity.SIGN_UP_REQUIRES_APPROVAL
        val comment = "new_comment"
        invoking {
            adminIpBlockMethods.updateBlockedIpRange(id = idOfIpBlocked, ipAddress = ipToBeBlocked, severity = severity, comment = comment).execute()
        } shouldNotThrow AnyException

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.put(
                path = "$adminIpBlockEndpoint/$idOfIpBlocked",
                body = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "ip=${URLEncoder.encode(ipToBeBlocked, "utf-8")}&severity=${severity.apiName}&comment=$comment"
        }
    }

    @Test
    fun `Given client returning success, when removing specific blocked ip address, then check data for single ip`() {
        val client = MockClient.mock("admin_ip_blocks_single.json")
        val adminIpBlockMethods = AdminIpBlockMethods(client)
        val idOfIpBlocked = "1"

        invoking {
            adminIpBlockMethods.removeIpBlock(id = idOfIpBlocked)
        } shouldNotThrow AnyException

        verify {
            client.performAction(
                endpoint = "$adminIpBlockEndpoint/$idOfIpBlocked",
                method = MastodonClient.Method.DELETE
            )
        }
    }
}
