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
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class DomainBlockMethodsTest {
    @Test
    fun `Given a client returning success, when getting blocked domains, then expect values of response`() {
        val client = MockClient.mock("domain_blocks_view_success.json")

        val domainBlockMethods = DomainBlockMethods(client)
        val blockedDomains: Pageable<String> = domainBlockMethods.getDomainBlocks().execute()

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
    fun `Given a client returning unauthorized, when getting blocked domains, then propagate error`() {
        val client = MockClient.failWithResponse(
            responseJsonAssetPath = "error_401_unauthorized.json",
            responseCode = 401,
            message = "Unauthorized"
        )

        invoking {
            DomainBlockMethods(client).getDomainBlocks().execute()
        } shouldThrow BigBoneRequestException::class withMessage "Unauthorized"
    }

    @Test
    fun `Given a client returning success, when blocking a valid domain, then expect no errors`() {
        val client = MockClient.mock("domain_blocks_block_success.json")

        val domainBlockMethods = DomainBlockMethods(client)
        invoking { domainBlockMethods.blockDomain("nsfw.social") } shouldNotThrow BigBoneRequestException::class

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

        val domainBlockMethods = DomainBlockMethods(client)
        invoking {
            domainBlockMethods.blockDomain("")
        } shouldThrow IllegalArgumentException::class withMessage "domain must not be blank"

        invoking {
            domainBlockMethods.blockDomain("    ")
        } shouldThrow IllegalArgumentException::class withMessage "domain must not be blank"

        invoking {
            domainBlockMethods.blockDomain("example . com")
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
            DomainBlockMethods(client).blockDomain("nsfw.social")
        } shouldThrow BigBoneRequestException::class withMessage "Unprocessable entity"
    }

    @Test
    fun `Given a client returning success, when unblocking a valid domain, then expect no errors`() {
        val client = spyk(MockClient.mock("domain_blocks_unblock_success.json"))

        val domainBlockMethods = DomainBlockMethods(client)
        invoking { domainBlockMethods.unblockDomain("nsfw.social") } shouldNotThrow BigBoneRequestException::class

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

        val domainBlockMethods = DomainBlockMethods(client)
        invoking {
            domainBlockMethods.unblockDomain("")
        } shouldThrow IllegalArgumentException::class withMessage "domain must not be blank"

        invoking {
            domainBlockMethods.unblockDomain("    ")
        } shouldThrow IllegalArgumentException::class withMessage "domain must not be blank"

        invoking {
            domainBlockMethods.unblockDomain("example . com")
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
            DomainBlockMethods(client).unblockDomain("nsfw.social")
        } shouldThrow BigBoneRequestException::class withMessage "Unprocessable entity"
    }
}
