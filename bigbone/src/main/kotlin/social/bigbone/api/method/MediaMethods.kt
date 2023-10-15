package social.bigbone.api.method

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import social.bigbone.JSON_SERIALIZER
import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.entity.MediaAttachment
import social.bigbone.api.entity.data.Focus
import java.io.File

/**
 * Allows access to API methods with endpoints having an "api/vX/media" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/media/">Mastodon media API methods</a>
 */
class MediaMethods(private val client: MastodonClient) {
    /**
     * Creates an attachment to be used with a new status. This method will return after the full sized media is done processing.
     * @param file the file that should be uploaded
     * @param mediaType media type of the file as a string, e.g. "image/png"
     * @param description a plain-text description of the media, for accessibility purposes.
     * @param focus a [Focus] instance which specifies the x- and y- coordinate of the focal point. Valid range for x and y is -1.0 to 1.0.
     * @see <a href="https://docs.joinmastodon.org/methods/media/#v1">Mastodon API documentation: methods/media/#v1</a>
     */
    @JvmOverloads
    fun uploadMedia(file: File, mediaType: String, description: String? = null, focus: Focus? = null): MastodonRequest<MediaAttachment> {
        val body = file.asRequestBody(mediaType.toMediaTypeOrNull())
        val part = MultipartBody.Part.createFormData("file", file.name, body)
        val requestBodyBuilder = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addPart(part)
        description?.let {
            requestBodyBuilder.addFormDataPart("description", it)
        }
        focus?.let {
            requestBodyBuilder.addFormDataPart("focus", it.toString())
        }
        val requestBody = requestBodyBuilder.build()
        return MastodonRequest(
            executor = { client.postRequestBody("api/v1/media", requestBody) },
            mapper = { JSON_SERIALIZER.decodeFromString<MediaAttachment>(it) }
        )
    }
}
