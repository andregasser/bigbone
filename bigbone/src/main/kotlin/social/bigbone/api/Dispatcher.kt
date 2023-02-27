package social.bigbone.api

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * Dispatcher is used by the streaming methods (e.g. [social.bigbone.api.method.StreamingMethods.federatedPublic].
 * It maintains an executor service that is used to process incoming data.
 */
class Dispatcher {
    private val executorService: ExecutorService = Executors.newFixedThreadPool(1) { r ->
        val thread = Thread(r)
        thread.isDaemon = true
        return@newFixedThreadPool thread
    }

    private val lock = ReentrantLock()
    private val shutdownTime = 1000L

    fun invokeLater(task: Runnable) = executorService.execute(task)

    fun shutdown() {
        lock.withLock {
            executorService.shutdown()
            if (!executorService.awaitTermination(shutdownTime, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow()
            }
        }
    }
}
