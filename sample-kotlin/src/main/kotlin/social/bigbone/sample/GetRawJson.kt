package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.api.method.Timelines
import social.bigbone.api.method.Timelines.StatusOrigin.LOCAL_AND_REMOTE

object GetRawJson {
    @JvmStatic
    fun main(args: Array<String>) {
        val instance = args[0]

        // Instantiate client
        val client = MastodonClient.Builder(instance)
            .build()

        // Print timeline statuses
        val timelines = Timelines(client)
        timelines.getPublicTimeline(statusOrigin = LOCAL_AND_REMOTE).doOnJson {
            println(it)
        }.execute()
    }
}
