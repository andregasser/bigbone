package social.bigbone.rx

import org.junit.jupiter.api.Test
import social.bigbone.api.Pageable
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.rx.testtool.MockClient

class RxDomainBlockMethodsTest {

    @Test
    fun `Given a client returning success, when getting blocked domains, then emit list of blocked domains`() {
        val client = MockClient.mock(
            jsonName = "domain_blocks_view_success.json"
        )
        val domainBlockMethods = RxDomainBlockMethods(client)

        with(domainBlockMethods.getDomainBlocks().test()) {
            assertNoErrors()
            assertComplete()

            assertValueCount(1)
            assertValue { blockedDomains: Pageable<String> -> blockedDomains.part.size == 2 }

            dispose()
        }
    }

    @Test
    fun `Given a failing client, when getting blocked domains, then emit error`() {
        val client = MockClient.failWithResponse(
            responseJsonAssetPath = "error_401_unauthorized.json",
            responseCode = 401,
            message = "Unauthorized"
        )
        val domainBlockMethods = RxDomainBlockMethods(client)

        with(domainBlockMethods.getDomainBlocks().test()) {
            assertNoValues()
            assertNotComplete()

            assertError { error: Throwable ->
                error is BigBoneRequestException && error.message == "Unauthorized"
            }

            dispose()
        }
    }

    @Test
    fun `Given a client returning success, when blocking valid domain, then complete`() {
        val client = MockClient.mock(
            jsonName = "domain_blocks_block_success.json"
        )
        val domainBlockMethods = RxDomainBlockMethods(client)

        with(domainBlockMethods.blockDomain("nsfw.social").test()) {
            assertNoErrors()
            assertComplete()

            dispose()
        }
    }

    @Test
    fun `Given a failing client, when blocking invalid domain, then emit error`() {
        val client = MockClient.failWithResponse(
            responseJsonAssetPath = "domain_blocks_block_error_422.json",
            responseCode = 422,
            message = "Unprocessable Entity"
        )
        val domainBlockMethods = RxDomainBlockMethods(client)

        with(domainBlockMethods.blockDomain("invalidDomain").test()) {
            assertNoValues()
            assertNotComplete()

            assertError { error: Throwable ->
                error is BigBoneRequestException && error.message == "Unprocessable Entity"
            }

            dispose()
        }
    }
}
