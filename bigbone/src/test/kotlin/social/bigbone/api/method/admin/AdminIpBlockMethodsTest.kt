package social.bigbone.api.method.admin

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import social.bigbone.api.Range
import social.bigbone.testtool.MockClient

class AdminIpBlockMethodsTest {

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
    }

    @Test
    fun `Given client returning success, when getting specific blocked ip address, then return expected data for single ip`() {
        val client = MockClient.mock("admin_ip_blocks_single.json")
        val adminIpBlockMethods = AdminIpBlockMethods(client)
        val blockedIpAddress = adminIpBlockMethods.getBlockedIpRange("1").execute()
        with(blockedIpAddress) {
            id shouldBeEqualTo "1"
            ip shouldBeEqualTo "8.8.8.8/32"
            severity?.apiName shouldBeEqualTo "no_access"
        }
    }
}
