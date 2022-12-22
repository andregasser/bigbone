package social.bigbone.rx

import io.reactivex.Single
import okhttp3.MultipartBody
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Attachment
import social.bigbone.api.method.Media

class RxMedia(client: MastodonClient) {
    val media = Media(client)

    fun postMedia(part: MultipartBody.Part): Single<Attachment> {
        return Single.create {
            try {
                val result = media.postMedia(part)
                it.onSuccess(result.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
