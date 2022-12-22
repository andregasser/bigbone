package social.bigbone.sample

import social.bigbone.api.Handler
import social.bigbone.api.entity.Notification
import social.bigbone.api.entity.Status
import social.bigbone.api.exception.BigboneRequestException
import social.bigbone.api.method.Streaming

object StreamPublicTimeline {

    @JvmStatic
    fun main(args: Array<String>) {
        val instanceName = args[0]
        val credentialFilePath = args[1]

        // require authentication even if public streaming
        val client = Authenticator.appRegistrationIfNeeded(instanceName, credentialFilePath, true)
        val handler = object : Handler {
            override fun onStatus(status: Status) {
                println(status.content)
            }

            override fun onNotification(notification: Notification) { }

            override fun onDelete(id: Long) { }
        }
        val streaming = Streaming(client)
        try {
            val shutdownable = streaming.localPublic(handler)
            Thread.sleep(10_000L)
            shutdownable.shutdown()
        } catch (e: BigboneRequestException) {
            println("error")
            println(e.response?.code)
            println(e.response?.message)
            println(e.response?.body?.string())
            return
        }
    }
}
