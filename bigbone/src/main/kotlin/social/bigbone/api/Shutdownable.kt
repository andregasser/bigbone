package social.bigbone.api

import okhttp3.Response
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * Shutdownable is used by streaming endpoints, such as [social.bigbone.api.method.StreamingMethods.federatedPublic]. It
 * gives the caller a way of closing the stream, by calling the [shutdown] method.
 */
class Shutdownable(private val dispatcher: Dispatcher, private val response: Response) {
    private val lock = ReentrantLock()

    /**
     * Close the current stream as well as the response body the stream is based on.
     */
    fun shutdown() {
        lock.withLock {
            dispatcher.shutdown()
        }
        response.close()
    }
}
