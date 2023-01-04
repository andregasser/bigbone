package social.bigbone.api.method

import okhttp3.MultipartBody
import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.entity.Attachment

class Media(private val client: MastodonClient) {
    //  POST /api/v1/media
    fun postMedia(file: MultipartBody.Part): MastodonRequest<Attachment> {
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addPart(file)
            .build()
        return MastodonRequest<Attachment>(
            {
                client.postRequestBody("api/v1/media", requestBody)
            },
            {
                client.getSerializer().fromJson(it, Attachment::class.java)
            }
        )
    }
}
