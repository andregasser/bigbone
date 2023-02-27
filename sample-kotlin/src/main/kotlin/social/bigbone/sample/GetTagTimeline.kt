package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.api.Range
import social.bigbone.api.method.TimelineMethods

object GetTagTimeline {
    @JvmStatic
    fun main(args: Array<String>) {
        val instance = args[0]
        val hashtag = args[1]

        // Instantiate client
        val client = MastodonClient.Builder(instance)
            .build()

        // Get statuses from public timeline
        val statuses = client.timelines.getTagTimeline(hashtag, Range(), TimelineMethods.StatusOrigin.LOCAL_AND_REMOTE).execute()
        statuses.part.forEach {
            println(it.content)
        }
    }
}
