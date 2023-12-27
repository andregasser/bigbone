package social.bigbone.api.method

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import social.bigbone.JSON_SERIALIZER
import social.bigbone.MastodonClient
import social.bigbone.MastodonClient.Method
import social.bigbone.MastodonRequest
import social.bigbone.api.entity.MediaAttachment
import social.bigbone.api.entity.data.Focus
import java.io.File

/**
 * Allows access to API methods with endpoints having an "api/vX/media" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/media/">Mastodon media API methods</a>
 */
class MediaMethods(private val client: MastodonClient) {

    val endpoint: String = "api/v1/media"

    /**
     * Get a media attachment, before it is attached to a status and posted, but after it is accepted for processing.
     * Use this method to check that the full-size media has finished processing.
     *
     * @param withId The ID of the [MediaAttachment] in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/media/#get">Mastodon API documentation: methods/media/#get</a>
     */
    fun getMediaAttachment(withId: String): MastodonRequest<MediaAttachment> {
        return client.getMastodonRequest(
            endpoint = "$endpoint/$withId",
            method = Method.GET
        )
    }

    /**
     * Update a [MediaAttachment]’s parameters before it’s attached to a status and posted.
     *
     * @param withId The ID of the [MediaAttachment] in the database.
     * @param customThumbnail The custom thumbnail of the media to be attached.
     * @param description A plain-text description of the media, for accessibility purposes.
     * @param focus a [Focus] instance which specifies the x- and y-coordinate of the focal point. Valid range for x and y is -1.0 to 1.0.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/media/#get">Mastodon API documentation: methods/media/#get</a>
     */
    @JvmOverloads
    fun updateMediaAttachment(
        withId: String,
        customThumbnail: CustomThumbnail? = null,
        description: String? = null,
        focus: Focus? = null
    ): MastodonRequest<MediaAttachment> {
        val requestBodyBuilder: MultipartBody.Builder = MultipartBody.Builder().setType(MultipartBody.FORM)

        if (customThumbnail != null) {
            val (file, mediaType) = customThumbnail
            val part = MultipartBody.Part.createFormData(
                name = "thumbnail",
                filename = file.name,
                body = file.asRequestBody(mediaType.toMediaTypeOrNull())
            )
            requestBodyBuilder.addPart(part)
        }

        description?.let { requestBodyBuilder.addFormDataPart("description", description) }
        focus?.let { requestBodyBuilder.addFormDataPart("focus", focus.toString()) }

        return MastodonRequest(
            executor = { client.putRequestBody(path = "$endpoint/$withId", body = requestBodyBuilder.build()) },
            mapper = { JSON_SERIALIZER.decodeFromString<MediaAttachment>(it) }
        )
    }

    /**
     * Creates an attachment to be used with a new status. This method will return after the full sized media is done processing.
     *
     * @param file the file that should be uploaded
     * @param mediaType media type of the file as a string, e.g. "image/png"
     * @param description a plain-text description of the media, for accessibility purposes.
     * @param focus a [Focus] instance which specifies the x- and y- coordinate of the focal point. Valid range for x and y is -1.0 to 1.0.
     * @param customThumbnail The custom thumbnail of the media to be attached.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/media/#v1">Mastodon API documentation: methods/media/#v1</a>
     */
    @JvmOverloads
    fun uploadMedia(
        file: File,
        mediaType: String,
        description: String? = null,
        focus: Focus? = null,
        customThumbnail: CustomThumbnail? = null,
    ): MastodonRequest<MediaAttachment> {
        val body = file.asRequestBody(mediaType.toMediaTypeOrNull())
        val part = MultipartBody.Part.createFormData("file", file.name, body)

        val requestBodyBuilder = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addPart(part)

        if (customThumbnail != null) {
            val (thumbnailFile, thumbnailMediaType) = customThumbnail
            val thumbnailPart: MultipartBody.Part = MultipartBody.Part.createFormData(
                name = "thumbnail",
                filename = thumbnailFile.name,
                body = thumbnailFile.asRequestBody(thumbnailMediaType.toMediaTypeOrNull())
            )
            requestBodyBuilder.addPart(thumbnailPart)
        }

        description?.let { requestBodyBuilder.addFormDataPart("description", description) }
        focus?.let { requestBodyBuilder.addFormDataPart("focus", focus.toString()) }

        val requestBody = requestBodyBuilder.build()

        return MastodonRequest(
            executor = { client.postRequestBody(endpoint, requestBody) },
            mapper = { JSON_SERIALIZER.decodeFromString<MediaAttachment>(it) }
        )
    }
}

/**
 * Wrapper that can be used to update a [MediaAttachment] via [MediaMethods.updateMediaAttachment].
 *
 * @property file [File] representation of the thumbnail that should be used.
 * @property mediaType [String] representation of [file]’s media type. Defaults to image/jpeg.
 */
data class CustomThumbnail(
    val file: File,
    val mediaType: String = "image/jpeg"
)
