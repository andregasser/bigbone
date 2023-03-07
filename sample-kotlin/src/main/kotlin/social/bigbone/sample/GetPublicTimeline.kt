package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.api.method.TimelineMethods.StatusOrigin

object GetPublicTimeline {
    @JvmStatic
    fun main(args: Array<String>) {
        val instance = args[0]

        // Instantiate client
        val client = MastodonClient.Builder(instance)
            .build()

        // Get statuses from public timeline
        val statuses = client.timelines.getPublicTimeline(StatusOrigin.LOCAL_AND_REMOTE).execute()
        statuses.part.forEach {
            println(it.content)
        }
    }
}
