package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Attachment
import social.bigbone.api.method.Media
import java.io.File

class RxMedia(client: MastodonClient) {
    val media = Media(client)

    fun postMedia(file: File, mediaType: String): Single<Attachment> {
        return Single.create {
            try {
                val result = media.postMedia(file, mediaType)
                it.onSuccess(result.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
