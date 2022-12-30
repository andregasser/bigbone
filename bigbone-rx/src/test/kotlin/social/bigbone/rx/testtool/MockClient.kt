package social.bigbone.rx.testtool

import com.google.gson.Gson
import io.mockk.every
import io.mockk.mockk
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import social.bigbone.MastodonClient

object MockClient {
    fun mock(jsonName: String, maxId: String? = null, sinceId: String? = null): MastodonClient {
        val client: MastodonClient = mockk()
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
        every { client.get(ofType<String>(), any()) } returns response
        every { client.getSerializer() } returns Gson()
        return client
    }
}
