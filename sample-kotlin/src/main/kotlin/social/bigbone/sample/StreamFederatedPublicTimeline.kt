package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.api.Handler
import social.bigbone.api.entity.Notification
import social.bigbone.api.entity.Status

object StreamFederatedPublicTimeline {
    @JvmStatic
    fun main(args: Array<String>) {
        val instance = args[0]

        // Instantiate client
        val client = MastodonClient.Builder(instance)
            .useStreamingApi()
            .build()

        // Configure status handler
        val handler: Handler = object : Handler {
            override fun onStatus(status: Status) {
                println(status.content)
            }

            override fun onNotification(notification: Notification) {
                // No op
            }

            override fun onDelete(id: String) {
                // No op
            }
        }

        // Start federated timeline streaming and stop after 20 seconds
        val shutdownable = client.streaming.federatedPublic(handler)
        Thread.sleep(20_000L)
        shutdownable.shutdown()
    }
}
