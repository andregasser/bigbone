package social.bigbone.testtool

import io.mockk.every
import io.mockk.mockk
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import social.bigbone.MastodonClient
import social.bigbone.api.exception.BigBoneRequestException
import java.net.SocketTimeoutException

object MockClient {

    fun mock(
        jsonName: String,
        maxId: String? = null,
        sinceId: String? = null,
        requestUrl: String = "https://example.com",
        responseBaseUrl: String = "https://mstdn.jp/api/v1/timelines/public"
    ): MastodonClient {
        val clientMock: MastodonClient = mockk()
        val response: Response = Response.Builder()
            .code(200)
            .message("OK")
            .request(Request.Builder().url(requestUrl).build())
            .protocol(Protocol.HTTP_1_1)
            .body(
                AssetsUtil
                    .readFromAssets(jsonName)
                    .toResponseBody("application/json; charset=utf-8".toMediaTypeOrNull())
            )
            .apply {
                val linkHeader = buildList {
                    maxId?.let {
                        add("""<$responseBaseUrl?limit=20&local=true&max_id=$it>; rel="next"""")
                    }
                    sinceId?.let {
                        add("""<$responseBaseUrl?limit=20&local=true&since_id=$it>; rel="prev"""")
                    }
                }
                if (linkHeader.isNotEmpty()) {
                    header(
                        name = "link",
                        value = linkHeader.joinToString(separator = ",")
                    )
                }
            }
            .build()

        every { clientMock.delete(ofType<String>(), any()) } returns response
        every { clientMock.get(ofType<String>(), isNull(inverse = false)) } returns response
        every { clientMock.get(ofType<String>(), any()) } returns response
        every { clientMock.patch(ofType<String>(), any()) } returns response
        every { clientMock.post(ofType<String>(), any()) } returns response
        every { clientMock.post(ofType<String>(), any(), any()) } returns response
        every { clientMock.postRequestBody(ofType<String>(), any()) } returns response
        every { clientMock.put(ofType<String>(), any()) } returns response
        return clientMock
    }

    fun ioException(
        requestUrl: String = "https://example.com"
    ): MastodonClient {
        val clientMock: MastodonClient = mockk()
        val responseBodyMock: ResponseBody = mockk()
        every { responseBodyMock.toString() } throws SocketTimeoutException()
        val response: Response = Response.Builder()
            .code(200)
            .message("OK")
            .request(Request.Builder().url(requestUrl).build())
            .protocol(Protocol.HTTP_1_1)
            .body(responseBodyMock)
            .build()

        every { clientMock.delete(ofType<String>(), any()) } returns response
        every { clientMock.get(ofType<String>(), isNull(inverse = false)) } returns response
        every { clientMock.get(ofType<String>(), any()) } returns response
        every { clientMock.patch(ofType<String>(), any()) } returns response
        every { clientMock.post(ofType<String>(), any()) } returns response
        every { clientMock.post(ofType<String>(), any(), any()) } returns response
        every { clientMock.postRequestBody(ofType<String>(), any()) } returns response
        every { clientMock.put(ofType<String>(), any()) } returns response
        every { clientMock.performAction(ofType<String>(), any()) } throws BigBoneRequestException("mock")
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

        every { clientMock.delete(ofType<String>(), any()) } returns response
        every { clientMock.get(ofType<String>(), isNull(inverse = false)) } returns response
        every { clientMock.get(ofType<String>(), any()) } returns response
        every { clientMock.patch(ofType<String>(), any()) } returns response
        every { clientMock.post(ofType<String>(), any()) } returns response
        every { clientMock.post(ofType<String>(), any(), any()) } returns response
        every { clientMock.postRequestBody(ofType<String>(), any()) } returns response
        every { clientMock.put(ofType<String>(), any()) } returns response
        every { clientMock.performAction(ofType<String>(), any()) } throws BigBoneRequestException("mock")
        return clientMock
    }
}
