package social.bigbone

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigboneRequestException

@SuppressWarnings("FunctionMaxLength")
class MastodonClientTest {

    private val invalidResponseBody = "{ \"foo\": \"bar\" }"
    private val v2InstanceResponseBody = "{ \"version\": \"4.0.0rc1\" }"
    private val v1InstanceResponseBody = "{ \"version\": \"4.0.0rc1\" }"

    @Test
    fun `should return version of v2 endpoint if available`() {
        // given
        val responseBodyV2Mock = mockk<ResponseBody>()
        every { responseBodyV2Mock.toString() } answers { v2InstanceResponseBody }
        val responseV2Mock = mockk<Response>()
        every { responseV2Mock.body } answers { responseBodyV2Mock }
        every { responseV2Mock.isSuccessful } answers { true }

        val responseV1Mock = mockk<Response>()

        val clientBuilder = spyk(MastodonClient.Builder("foo.bar"))
        every { clientBuilder.v2InstanceRequest() } answers { responseV2Mock }
        every { clientBuilder.v1InstanceRequest() } answers { responseV1Mock }

        // when
        val client = clientBuilder.build()

        // then
        client.getInstanceVersion() shouldBeEqualTo "4.0.0rc1"
    }

    @Test
    fun `should return version of v1 endpoint if v2 endpoint is not available`() {
        // given
        val responseV2Mock = mockk<Response>()
        every { responseV2Mock.isSuccessful } answers { false }

        val responseBodyV1Mock = mockk<ResponseBody>()
        every { responseBodyV1Mock.toString() } answers { v1InstanceResponseBody }
        val responseV1Mock = mockk<Response>()
        every { responseV1Mock.isSuccessful } answers { true }
        every { responseV1Mock.body } answers { responseBodyV1Mock }

        val clientBuilder = spyk(MastodonClient.Builder("foo.bar"))
        every { clientBuilder.v2InstanceRequest() } answers { responseV2Mock }
        every { clientBuilder.v1InstanceRequest() } answers { responseV1Mock }

        // when
        val client = clientBuilder.build()

        // then
        client.getInstanceVersion() shouldBeEqualTo "4.0.0rc1"
    }

    @Test
    fun `should throw exception when instance version cannot be found in response body`() {
        // given
        val clientBuilder = spyk(MastodonClient.Builder("foo.bar"))
        val responseMock = mockk<Response>()
        every { responseMock.body } answers { invalidResponseBody.toResponseBody("application/json".toMediaType()) }
        every { responseMock.isSuccessful } answers { true }
        every { clientBuilder.v2InstanceRequest() } answers { responseMock }
        every { clientBuilder.v1InstanceRequest() } answers { responseMock }

        // when / then
        Assertions.assertThrows(BigboneRequestException::class.java) {
            clientBuilder.build()
        }
    }
}
