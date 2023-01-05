package social.bigbone.api.method

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.entity.Attachment
import java.io.File

class Media(private val client: MastodonClient) {
    /**
     * Creates an attachment to be used with a new status.
     * @param file the file that should be uploaded
     * @param mediaType media type of the file as a string, e.g. "image/png"
     * @see <a href="https://docs.joinmastodon.org/methods/media/#v1">Mastodon API documentation: methods/media/#v1</a>
     */
    fun postMedia(file: File, mediaType: String): MastodonRequest<Attachment> {
        val body = file.asRequestBody(mediaType.toMediaTypeOrNull())
        val part = MultipartBody.Part.createFormData("file", file.name, body)
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addPart(part)
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
