package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.api.Range
import social.bigbone.api.method.Timelines

object GetTagTimeline {
    @JvmStatic
    fun main(args: Array<String>) {
        val instance = args[0]
        val hashtag = args[1]

        // Instantiate client
        val client = MastodonClient.Builder(instance)
            .build()

        // Get statuses from public timeline
        val timelines = Timelines(client)
        val statuses = timelines.getTagTimeline(hashtag, Range(), Timelines.StatusOrigin.LOCAL_AND_REMOTE).execute()
        statuses.part.forEach {
            println(it.content)
        }
    }
}
