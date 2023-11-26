package social.bigbone

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.spyk
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withCause
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneClientInstantiationException
import social.bigbone.api.exception.InstanceVersionRetrievalException
import social.bigbone.api.exception.ServerInfoRetrievalException
import social.bigbone.nodeinfo.NodeInfoClient
import social.bigbone.testtool.AssetsUtil
import java.net.UnknownHostException

class MastodonClientTest {

    @Test
    fun `Given server response with available v2 endpoint, when building MastodonClient, then return instance version of v2 endpoint`() {
        // given
        val clientBuilder = spyk(MastodonClient.Builder("foo.bar")) {
            // Mock internal NodeInfoClient so that we don't open the site in unit testing
            mockkObject(NodeInfoClient)
            every { NodeInfoClient.retrieveServerInfo("foo.bar") } throws ServerInfoRetrievalException(
                "just for testing",
                null
            )

            val responseV1Mock = mockk<Response>()
            every { versionedInstanceRequest(1) } answers { responseV1Mock }

            val responseBodyV2Mock = mockk<ResponseBody> {
                every { string() } answers {
                    AssetsUtil.readFromAssets("mastodon_client_v2_instance_response.json")
                }
                every { close() } returns Unit
            }
            val responseV2Mock = mockk<Response> {
                every { body } answers { responseBodyV2Mock }
                every { isSuccessful } answers { true }
                every { close() } returns Unit
            }
            every { versionedInstanceRequest(2) } answers { responseV2Mock }
        }

        // when
        val client = clientBuilder.build()

        // then
        client.getInstanceVersion() shouldBeEqualTo "4.0.0rc1"
    }

    @Test
    fun `Given server response with only v1 endpoint available, when building MastodonClient, then return instance version of v1 endpoint`() {
        // given
        val clientBuilder = spyk(MastodonClient.Builder("foo.bar")) {
            // Mock internal NodeInfoClient so that we don't open the site in unit testing
            mockkObject(NodeInfoClient)
            every { NodeInfoClient.retrieveServerInfo("foo.bar") } throws ServerInfoRetrievalException(
                "just for testing",
                null
            )

            val responseBodyV1Mock = mockk<ResponseBody> {
                every { string() } answers {
                    AssetsUtil.readFromAssets("mastodon_client_v1_instance_response.json")
                }
            }
            val responseV1Mock = mockk<Response> {
                every { isSuccessful } answers { true }
                every { body } answers { responseBodyV1Mock }
                every { close() } returns Unit
            }
            every { versionedInstanceRequest(1) } answers { responseV1Mock }

            val responseV2Mock = mockk<Response> {
                every { isSuccessful } answers { false }
                every { code } returns 404
                every { message } returns "Not Found"
                every { close() } returns Unit
            }
            every { versionedInstanceRequest(2) } answers { responseV2Mock }
        }

        // when
        val client = clientBuilder.build()

        // then
        client.getInstanceVersion() shouldBeEqualTo "4.0.0rc1"
    }

    @Test
    fun `Given response body without instance version, when building MastodonClient, then fail with InstanceVersionRetrievalException`() {
        val serverUrl = "foo.bar"
        val clientBuilder = spyk(MastodonClient.Builder(serverUrl)) {
            // Mock internal NodeInfoClient so that we don't open the site in unit testing
            mockkObject(NodeInfoClient)
            every { NodeInfoClient.retrieveServerInfo(serverUrl) } throws ServerInfoRetrievalException(
                "just for testing",
                null
            )

            val responseMock = mockk<Response> {
                val invalidResponseBody = "{ \"foo\": \"bar\" }"
                every { body } answers { invalidResponseBody.toResponseBody("application/json".toMediaType()) }
                every { isSuccessful } answers { true }
                every { close() } returns Unit
            }
            every { versionedInstanceRequest(any()) } answers { responseMock }
        }

        invoking(clientBuilder::build)
            .shouldThrow(BigBoneClientInstantiationException::class)
            .withCause(InstanceVersionRetrievalException::class)
            .withMessage("Failed to get instance version of $serverUrl")
    }

    @Test
    fun `Given a server that doesn't run Mastodon, when building MastodonClient, then fail with InstanceVersionRetrievalException`() {
        val testUrl = "pod.dapor.net"
        val clientBuilder: MastodonClient.Builder = spyk(MastodonClient.Builder(testUrl))

        invoking(clientBuilder::build)
            .shouldThrow(BigBoneClientInstantiationException::class)
            .withCause(InstanceVersionRetrievalException::class)
            .withMessage("Failed to get instance version of $testUrl")
    }

    @Test
    fun `Given a server that cannot be reached, when building MastodonClient, then propagate UnknownHostException`() {
        val clientBuilder: MastodonClient.Builder = spyk(MastodonClient.Builder("unreachabledomain"))

        invoking(clientBuilder::build) shouldThrow UnknownHostException::class
    }
}
