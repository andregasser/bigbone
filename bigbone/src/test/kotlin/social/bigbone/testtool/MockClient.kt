package social.bigbone.testtool

import com.google.gson.Gson
import io.mockk.every
import io.mockk.mockk
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.asResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.BufferedSource
import social.bigbone.MastodonClient
import java.net.SocketTimeoutException

object MockClient {

    fun mock(jsonName: String, maxId: Long? = null, sinceId: Long? = null): MastodonClient {
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

        every { clientMock.get(ofType<String>(), isNull(inverse = false)) } returns response
        every { clientMock.get(ofType<String>(), any()) } returns response
        every { clientMock.post(ofType<String>(), any()) } returns response
        every { clientMock.postUrl(ofType<String>(), any()) } returns response
        every { clientMock.patch(ofType<String>(), any()) } returns response
        every { clientMock.getSerializer() } returns Gson()
        return clientMock
    }

    fun ioException(): MastodonClient {
        val clientMock: MastodonClient = mockk()
        val source: BufferedSource = mockk()
        every { source.readString(any()) } throws SocketTimeoutException()
        val response: Response = Response.Builder()
                .code(200)
                .message("OK")
                .request(Request.Builder().url("https://test.com/").build())
                .protocol(Protocol.HTTP_1_1)
                .body(
                    source
                        .asResponseBody(
                            "application/json; charset=utf-8".toMediaTypeOrNull(),
                            1024
                        )
                )
                .build()

        every { clientMock.get(ofType<String>(), isNull(inverse = false)) } returns response
        every { clientMock.get(ofType<String>(), any()) } returns response
        every { clientMock.post(ofType<String>(), any()) } returns response
        every { clientMock.postUrl(ofType<String>(), any()) } returns response
        every { clientMock.patch(ofType<String>(), any()) } returns response
        every { clientMock.getSerializer() } returns Gson()
        return clientMock
    }
}
