package social.bigbone.api.method.admin

import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.AnyException
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotThrow
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import social.bigbone.MastodonClient
import social.bigbone.Parameters
import social.bigbone.api.Range
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class AdminDomainAllowMethodsTest {

    private val adminDomainAllowEndpoint = "api/v1/admin/domain_allows"

    @Test
    fun `Given client returning success, when getting all allowed domains, then return list of allowed domains`() {
        val client = MockClient.mock("admin_domain_allow_list.json")
        val adminDomainAllowMethods = AdminDomainAllowMethods(client)
        val range = Range("1", "10", "1", 10)
        val allowedDomains = adminDomainAllowMethods.getAllAllowedDomains(range).execute()
        allowedDomains.part.size shouldBeEqualTo 2
        with(allowedDomains.part[1]) {
            id shouldBeEqualTo "1"
            domain shouldBeEqualTo "mastodon.social"
        }

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.get(
                path = adminDomainAllowEndpoint,
                query = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "max_id=${range.maxId}&min_id=${range.minId}&since_id=${range.sinceId}&limit=${range.limit}"
        }
    }

    @Test
    fun `Given client returning success, when getting specific allowed domain, then return expected data for single domain`() {
        val client = MockClient.mock("admin_domain_allow.json")
        val adminDomainAllowMethods = AdminDomainAllowMethods(client)
        val pathVariable = "1"
        val adminDomainAllowed = adminDomainAllowMethods.getAllowedDomain(pathVariable).execute()
        with(adminDomainAllowed) {
            id shouldBeEqualTo pathVariable
            domain shouldBeEqualTo "mastodon.social"
        }

        verify {
            client.get(
                path = "$adminDomainAllowEndpoint/$pathVariable"
            )
        }
    }

    @Test
    fun `Given client returning success, when posting a domain to be allowed to federate, then return expected data about the domain`() {
        val client = MockClient.mock("admin_domain_allow.json")
        val adminDomainAllowMethods = AdminDomainAllowMethods(client)

        invoking {
            adminDomainAllowMethods.allowDomainToFederate(domain = "mastodon.social").execute()
        } shouldNotThrow AnyException

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.post(
                path = adminDomainAllowEndpoint,
                body = capture(parametersCapturingSlot),
                addIdempotencyKey = false
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "domain=mastodon.social"
        }
    }

    @Test
    fun `Given client returning success, when removing specific domain from being allowd, then check data for single ip`() {
        val client = MockClient.mock("admin_domain_allow.json")
        val adminDomainAllowMethods = AdminDomainAllowMethods(client)
        val id = "1"

        invoking {
            adminDomainAllowMethods.removeAllowedDomain(id = id)
        } shouldNotThrow AnyException

        verify {
            client.performAction(
                endpoint = "$adminDomainAllowEndpoint/$id",
                method = MastodonClient.Method.DELETE
            )
        }
    }

    @Test
    fun `Given a client returning forbidden, when getting specific allowed domain, then propagate error`() {
        val client = MockClient.failWithResponse(
            responseJsonAssetPath = "error_403_forbidden.json",
            responseCode = 403,
            message = "Forbidden"
        )

        invoking {
            AdminDomainAllowMethods(client).getAllowedDomain(id = "1").execute()
        } shouldThrow BigBoneRequestException::class withMessage "Forbidden"
    }
}
