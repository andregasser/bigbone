package social.bigbone

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.AssetsUtil

class MastodonClientTest {

    @Test
    fun `Given server response with available v2 endpoint, when building MastodonClient, then return instance version of v2 endpoint`() {
        // given
        val clientBuilder = spyk(MastodonClient.Builder("foo.bar")) {
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
    fun `Given response body without instance version, when building MastodonClient, then fail with exception`() {
        val clientBuilder = spyk(MastodonClient.Builder("foo.bar")) {
            val responseMock = mockk<Response> {
                val invalidResponseBody = "{ \"foo\": \"bar\" }"
                every { body } answers { invalidResponseBody.toResponseBody("application/json".toMediaType()) }
                every { isSuccessful } answers { true }
                every { close() } returns Unit
            }
            every { versionedInstanceRequest(any()) } answers { responseMock }
        }

        invoking(clientBuilder::build) shouldThrow BigBoneRequestException::class
    }
}
