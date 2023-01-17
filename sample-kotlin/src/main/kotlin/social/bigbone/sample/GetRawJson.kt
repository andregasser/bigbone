package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.api.method.TimelinesMethods.StatusOrigin.LOCAL_AND_REMOTE

object GetRawJson {
    @JvmStatic
    fun main(args: Array<String>) {
        val instance = args[0]

        // Instantiate client
        val client = MastodonClient.Builder(instance)
            .build()

        // Print timeline statuses
        client.timelines.getPublicTimeline(statusOrigin = LOCAL_AND_REMOTE).doOnJson {
            println(it)
        }.execute()
    }
}
