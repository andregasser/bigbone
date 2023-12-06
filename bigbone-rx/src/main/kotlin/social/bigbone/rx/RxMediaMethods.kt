package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.MediaAttachment
import social.bigbone.api.entity.data.Focus
import social.bigbone.api.method.MediaMethods
import java.io.File

/**
 * Reactive implementation of [MediaMethods].
 * Allows access to API methods with endpoints having an "api/vX/media" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/media/">Mastodon media API methods</a>
 */
class RxMediaMethods(client: MastodonClient) {
    private val mediaMethods = MediaMethods(client)

    @JvmOverloads
    fun uploadMedia(
        file: File,
        mediaType: String,
        description: String? = null,
        focus: Focus? = null
    ): Single<MediaAttachment> =
        Single.fromCallable { mediaMethods.uploadMedia(file, mediaType, description, focus).execute() }
}
