package social.bigbone.testtool

import io.mockk.every
import io.mockk.mockk
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import social.bigbone.MastodonClient
import social.bigbone.Parameters
import social.bigbone.api.exception.BigBoneRequestException
import java.net.SocketTimeoutException

object MockClient {

    fun mock(jsonName: String, maxId: String? = null, sinceId: String? = null): MastodonClient {
        val clientMock: MastodonClient = mockk()
        val response: Response = Response.Builder()
            .code(200)
            .message("OK")
            .request(Request.Builder().url("https://test.com/").build())
            .protocol(Protocol.HTTP_1_1)
            .body(
                AssetsUtil.readFromAssets(jsonName)
                    .toResponseBody("application/json; charset=utf-8".toMediaTypeOrNull())
            )
            .apply {
                val linkHeader = arrayListOf<String>().apply {
                    maxId?.let {
                        add("""<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&max_id=$it>; rel="next"""")
                    }
                    sinceId?.let {
                        add("""<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&since_id=$it>; rel="prev"""")
                    }
                }.joinToString(separator = ",")
                if (linkHeader.isNotEmpty()) {
                    header("link", linkHeader)
                }
            }
            .build()

        every { clientMock.delete(any<String>(), any<Parameters>()) } returns response
        every { clientMock.get(any<String>(), any<Parameters>()) } returns response
        every { clientMock.patch(any<String>(), any<Parameters>()) } returns response
        every { clientMock.post(any<String>(), any<Parameters>(), any<Boolean>()) } returns response
        every { clientMock.postRequestBody(any<String>(), any<RequestBody>()) } returns response
        every { clientMock.put(any<String>(), any<Parameters>()) } returns response
        return clientMock
    }

    fun ioException(): MastodonClient {
        val clientMock: MastodonClient = mockk()
        val responseBodyMock: ResponseBody = mockk()
        every { responseBodyMock.toString() } throws SocketTimeoutException()
        val response: Response = Response.Builder()
            .code(200)
            .message("OK")
            .request(Request.Builder().url("https://test.com/").build())
            .protocol(Protocol.HTTP_1_1)
            .body(responseBodyMock)
            .build()

        every { clientMock.delete(any<String>(), any<Parameters>()) } returns response
        every { clientMock.get(any<String>(), any<Parameters>()) } returns response
        every { clientMock.patch(any<String>(), any<Parameters>()) } returns response
        every { clientMock.post(any<String>(), any<Parameters>(), any<Boolean>()) } returns response
        every { clientMock.postRequestBody(any<String>(), any<RequestBody>()) } returns response
        every { clientMock.put(any<String>(), any<Parameters>()) } returns response
        every {
            clientMock.performAction(
                any<String>(),
                any<MastodonClient.Method>(),
                any<Parameters>()
            )
        } throws BigBoneRequestException("mock")
        return clientMock
    }

    fun failWithResponse(
        responseJsonAssetPath: String,
        responseCode: Int,
        message: String
    ): MastodonClient {
        val clientMock: MastodonClient = mockk()
        val responseBodyMock: ResponseBody = mockk()
        every { responseBodyMock.toString() } throws SocketTimeoutException()
        val response: Response = Response.Builder()
            .code(responseCode)
            .message(message)
            .request(Request.Builder().url("https://test.com/").build())
            .protocol(Protocol.HTTP_1_1)
            .body(
                AssetsUtil.readFromAssets(responseJsonAssetPath)
                    .toResponseBody("application/json; charset=utf-8".toMediaTypeOrNull())
            )
            .build()

        every { clientMock.delete(any<String>(), any<Parameters>()) } returns response
        every { clientMock.get(any<String>(), any<Parameters>()) } returns response
        every { clientMock.patch(any<String>(), any<Parameters>()) } returns response
        every { clientMock.post(any<String>(), any<Parameters>(), any<Boolean>()) } returns response
        every { clientMock.postRequestBody(any<String>(), any<RequestBody>()) } returns response
        every { clientMock.put(any<String>(), any<Parameters>()) } returns response
        every {
            clientMock.performAction(
                any<String>(),
                any<MastodonClient.Method>(),
                any<Parameters>()
            )
        } throws BigBoneRequestException(response)
        return clientMock
    }
}
