package social.bigbone.api.method

import io.mockk.spyk
import io.mockk.verify
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotThrow
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import social.bigbone.MastodonClient
import social.bigbone.Parameters
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class DomainBlocksMethodsTest {

    @Test
    fun `Given a client returning success, when getting blocked domains, then expect values of response`() {
        val client = MockClient.mock("domain_blocks_view_success.json")

        val domainBlocksMethods = DomainBlocksMethods(client)
        val blockedDomains: Pageable<String> = domainBlocksMethods.getDomainBlocks().execute()

        blockedDomains.part.size shouldBeEqualTo 2
        blockedDomains.part[0] shouldBeEqualTo "nsfw.social"
        blockedDomains.part[1] shouldBeEqualTo "artalley.social"

        verify {
            client.get(
                path = "/api/v1/domain_blocks",
                query = any<Parameters>()
            )
        }
    }

    @Test
    fun `Given a client returning success, when getting blocked domains with a limit of 210, then throw IllegalArgumentException`() {
        val client = MockClient.mock("domain_blocks_view_success.json")

        invoking {
            DomainBlocksMethods(client).getDomainBlocks(
                range = Range(limit = 210)
            ).execute()
        } shouldThrow IllegalArgumentException::class withMessage "limit defined in Range must not be higher than 200 but was 210"
    }

    @Test
    fun `Given a client returning unauthorized, when getting blocked domains, then propagate error`() {
        val client = MockClient.failWithResponse(
            responseJsonAssetPath = "error_401_unauthorized.json",
            responseCode = 401,
            message = "Unauthorized"
        )

        invoking {
            DomainBlocksMethods(client).getDomainBlocks().execute()
        } shouldThrow BigBoneRequestException::class withMessage "Unauthorized"
    }

    @Test
    fun `Given a client returning success, when blocking a valid domain, then expect no errors`() {
        val client = MockClient.mock("domain_blocks_block_success.json")

        val domainBlocksMethods = DomainBlocksMethods(client)
        invoking { domainBlocksMethods.blockDomain("nsfw.social") } shouldNotThrow BigBoneRequestException::class

        verify {
            client.performAction(
                endpoint = "/api/v1/domain_blocks",
                method = MastodonClient.Method.POST
            )
        }
    }

    @Test
    fun `Given a client returning success, when blocking an invalid domain, then throw IllegalArgumentException`() {
        val client = MockClient.mock("domain_blocks_block_success.json")

        val domainBlocksMethods = DomainBlocksMethods(client)
        invoking {
            domainBlocksMethods.blockDomain("")
        } shouldThrow IllegalArgumentException::class withMessage "domain must not be blank"

        invoking {
            domainBlocksMethods.blockDomain("    ")
        } shouldThrow IllegalArgumentException::class withMessage "domain must not be blank"

        invoking {
            domainBlocksMethods.blockDomain("example . com")
        } shouldThrow IllegalArgumentException::class withMessage "domain must not contain spaces"
    }

    @Test
    fun `Given a client returning unprocessable entity, when blocking a domain, then propagate error`() {
        val client = MockClient.failWithResponse(
            responseJsonAssetPath = "domain_blocks_block_error_422.json",
            responseCode = 422,
            message = "Unprocessable entity"
        )

        invoking {
            DomainBlocksMethods(client).blockDomain("nsfw.social")
        } shouldThrow BigBoneRequestException::class withMessage "Unprocessable entity"
    }

    @Test
    fun `Given a client returning success, when unblocking a valid domain, then expect no errors`() {
        val client = spyk(MockClient.mock("domain_blocks_unblock_success.json"))

        val domainBlocksMethods = DomainBlocksMethods(client)
        invoking { domainBlocksMethods.unblockDomain("nsfw.social") } shouldNotThrow BigBoneRequestException::class

        verify {
            client.performAction(
                endpoint = "/api/v1/domain_blocks",
                method = MastodonClient.Method.DELETE
            )
        }
    }

    @Test
    fun `Given a client returning success, when unblocking an invalid domain, then throw IllegalArgumentException`() {
        val client = MockClient.mock("domain_blocks_unblock_success.json")

        val domainBlocksMethods = DomainBlocksMethods(client)
        invoking {
            domainBlocksMethods.unblockDomain("")
        } shouldThrow IllegalArgumentException::class withMessage "domain must not be blank"

        invoking {
            domainBlocksMethods.unblockDomain("    ")
        } shouldThrow IllegalArgumentException::class withMessage "domain must not be blank"

        invoking {
            domainBlocksMethods.unblockDomain("example . com")
        } shouldThrow IllegalArgumentException::class withMessage "domain must not contain spaces"
    }

    @Test
    fun `Given a client returning unprocessable entity, when unblocking a domain, then propagate error`() {
        val client = MockClient.failWithResponse(
            responseJsonAssetPath = "domain_blocks_unblock_error_422.json",
            responseCode = 422,
            message = "Unprocessable entity"
        )

        invoking {
            DomainBlocksMethods(client).unblockDomain("nsfw.social")
        } shouldThrow BigBoneRequestException::class withMessage "Unprocessable entity"
    }
}
