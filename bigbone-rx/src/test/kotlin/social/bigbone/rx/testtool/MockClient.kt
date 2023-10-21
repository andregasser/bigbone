package social.bigbone.rx.testtool

import io.mockk.every
import io.mockk.mockk
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import social.bigbone.MastodonClient

object MockClient {

    fun mock(
        jsonName: String,
        maxId: String? = null,
        sinceId: String? = null,
        requestUrl: String = "https://example.com",
        responseBaseUrl: String = "https://mstdn.jp/api/v1/timelines/public"
    ): MastodonClient {
        val client: MastodonClient = mockk()
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
        every { client.get(ofType<String>(), any()) } returns response

        return client
    }
}
