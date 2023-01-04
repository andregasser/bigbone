package social.bigbone.sample

import kotlinx.coroutines.runBlocking
import social.bigbone.api.method.Timelines

object GetRawJson {
    @JvmStatic
    fun main(args: Array<String>) {
        val instanceName = args[0]
        val credentialFilePath = args[1]
        val client = Authenticator.appRegistrationIfNeeded(instanceName, credentialFilePath)
        runBlocking {
            val timelines = Timelines(client)
            timelines.getPublicTimeline(statusOrigin = Timelines.StatusOrigin.LOCAL).doOnJson {
                println(it)
            }.execute()
        }
    }
}
