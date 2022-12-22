package social.bigbone.rx

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import social.bigbone.MastodonClient
import social.bigbone.api.Handler
import social.bigbone.api.Shutdownable
import social.bigbone.api.entity.Notification
import social.bigbone.api.entity.Status
import social.bigbone.api.method.Streaming

class RxStreaming(client: MastodonClient) {

    val streaming = Streaming(client)

    private fun stream(f: (Handler) -> Shutdownable): Flowable<Status> {
        return Flowable.create<Status>({ emmiter ->
            val shutdownable = f(object : Handler {
                override fun onStatus(status: Status) {
                    emmiter.onNext(status)
                }

                override fun onNotification(notification: Notification) {
                    // no op
                }

                override fun onDelete(id: Long) {
                    // no op
                }
            })
            emmiter.setCancellable {
                shutdownable.shutdown()
            }
        }, BackpressureStrategy.LATEST)
    }

    private fun statusStream(f: (Handler) -> Shutdownable): Flowable<Status> {
        return stream { handler ->
            f(handler)
        }
    }

    private fun tagStream(tag: String, f: (String, Handler) -> Shutdownable): Flowable<Status> {
        return stream { handler ->
            f(tag, handler)
        }
    }

    fun localPublic(): Flowable<Status> = statusStream(streaming::localPublic)

    fun federatedPublic(): Flowable<Status> = statusStream(streaming::federatedPublic)

    fun localHashtag(tag: String): Flowable<Status> = tagStream(tag, streaming::localHashtag)

    fun federatedHashtag(tag: String): Flowable<Status> = tagStream(tag, streaming::federatedHashtag)

    // TODO user streaming
}
