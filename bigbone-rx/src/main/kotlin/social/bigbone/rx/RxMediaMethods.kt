package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.MediaAttachment
import social.bigbone.api.entity.data.Focus
import social.bigbone.api.method.FileAsMediaAttachment
import social.bigbone.api.method.MediaMethods
import java.io.File

/**
 * Reactive implementation of [MediaMethods].
 * Allows access to API methods with endpoints having an "api/vX/media" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/media/">Mastodon media API methods</a>
 */
class RxMediaMethods(client: MastodonClient) {

    private val mediaMethods = MediaMethods(client)

    /**
     * Get a media attachment, before it is attached to a status and posted, but after it is accepted for processing.
     * Use this method to check that the full-size media has finished processing.
     *
     * @param withId The ID of the [MediaAttachment] in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/media/#get">Mastodon API documentation: methods/media/#get</a>
     */
    fun getMediaAttachment(withId: String): Single<MediaAttachment> = Single.fromCallable {
        mediaMethods.getMediaAttachment(withId).execute()
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
        customThumbnail: FileAsMediaAttachment? = null,
        description: String? = null,
        focus: Focus? = null
    ): Single<MediaAttachment> = Single.fromCallable {
        mediaMethods.updateMediaAttachment(withId, customThumbnail, description, focus).execute()
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
    @Deprecated(
        message = "Use variant with combined MediaForUpload parameter instead.",
        replaceWith = ReplaceWith("uploadMedia(FileAsMediaAttachment(file, mediaType), description, focus, customThumbnail)")
    )
    fun uploadMedia(
        file: File,
        mediaType: String,
        description: String? = null,
        focus: Focus? = null,
        customThumbnail: FileAsMediaAttachment? = null
    ): Single<MediaAttachment> = Single.fromCallable {
        mediaMethods.uploadMedia(file, mediaType, description, focus, customThumbnail).execute()
    }

    /**
     * Creates an attachment to be used with a new status. This method will return after the full sized media is done processing.
     *
     * @param mediaAttachment The file with media type that should be attached
     * @param description a plain-text description of the media, for accessibility purposes.
     * @param focus a [Focus] instance which specifies the x- and y- coordinate of the focal point. Valid range for x and y is -1.0 to 1.0.
     * @param customThumbnail The custom thumbnail of the media to be attached.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/media/#v1">Mastodon API documentation: methods/media/#v1</a>
     */
    @JvmOverloads
    fun uploadMedia(
        mediaAttachment: FileAsMediaAttachment,
        description: String? = null,
        focus: Focus? = null,
        customThumbnail: FileAsMediaAttachment? = null
    ): Single<MediaAttachment> = Single.fromCallable {
        mediaMethods.uploadMedia(mediaAttachment, description, focus, customThumbnail).execute()
    }
}
