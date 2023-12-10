package social.bigbone.api.method.admin

import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test
import social.bigbone.PrecisionDateTime
import social.bigbone.api.Range
import social.bigbone.api.entity.admin.AdminCohort
import social.bigbone.testtool.MockClient
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

class AdminIpBlocksMethodsTest {

    @Test
    fun `When calling to get all blocked ip addresses, given client returning success, returns list of ip addresses`() {
        val client = MockClient.mock("admin_ip_blocks_get_all.json")
        val adminIpBlocksMethods = AdminIpBlocksMethods(client)
        val range = Range("1", "10", "1", 10)
        val blockedIpAddresses = adminIpBlocksMethods.getAllIpBlocks(range).execute()
        blockedIpAddresses.part.size shouldBeEqualTo 2
        with(blockedIpAddresses.part[0]) {
            id shouldBeEqualTo "1"
            ip shouldBeEqualTo "8.8.8.8/32"
            severity?.apiName shouldBeEqualTo "no_access"
        }
    }

    @Test
    fun `When calling to get specific blocked ip address, given client returning success, returns expected data for single ip`() {
        val client = MockClient.mock("admin_ip_blocks_single.json")
        val adminIpBlocksMethods = AdminIpBlocksMethods(client)
        val blockedIpAddress = adminIpBlocksMethods.getSingleIpBlocked("1").execute()
        with(blockedIpAddress) {
            id shouldBeEqualTo "1"
            ip shouldBeEqualTo "8.8.8.8/32"
            severity?.apiName shouldBeEqualTo "no_access"
        }
    }


}