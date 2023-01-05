package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.api.Range
import social.bigbone.api.method.Timelines
import social.bigbone.api.method.Timelines.StatusOrigin

object GetPublicTimeline {
    @JvmStatic
    fun main(args: Array<String>) {
        val instance = "<YOUR INSTANCE>"

        // Instantiate client
        val client = MastodonClient.Builder(instance)
            .build()

        // Get statuses from public timeline
        val timelines = Timelines(client)
        val statuses = timelines.getPublicTimeline(Range(), StatusOrigin.LOCAL_AND_REMOTE).execute()
        statuses.part.forEach {
            println(it.content)
        }
    }
}
