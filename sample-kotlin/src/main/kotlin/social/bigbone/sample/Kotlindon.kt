package social.bigbone.sample

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Status
import social.bigbone.api.method.Timelines

object Kotlindon {
    private const val FIVE_SECONDS = 5000L

    @JvmStatic
    fun main(args: Array<String>) {
        val instanceName = args[0]
        val credentialFilePath = args[1]
        val client = Authenticator.appRegistrationIfNeeded(instanceName, credentialFilePath)
        listenHome(client)
    }

    fun listenHome(client: MastodonClient) {
        runBlocking {
            val timelines = Timelines(client)
            var pageable: Pageable<Status>? = null
            while (true) {
                val result = pageable?.let {
                    timelines.getHome(it.prevRange(limit = 5)).execute()
                } ?: timelines.getHome(Range(limit = 5)).execute()

                result.part.sortedBy { it.createdAt }.forEach {
                    println(it.account?.displayName)
                    println(it.content)
                    println(it.createdAt)
                    println("------------------------")
                }
                if (result.part.isNotEmpty()) {
                    pageable = result
                }
                println("wait next load...")
                delay(FIVE_SECONDS)
            }
        }
    }
}
