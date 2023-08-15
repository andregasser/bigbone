package social.bigbone.sample

import social.bigbone.MastodonClient

object GetMarkers {
    @JvmStatic
    fun main(args: Array<String>) {
        val instance = args[0]
        val accessToken = args[1]

        // Instantiate client
        val client = MastodonClient.Builder(instance)
            .accessToken(accessToken)
            .build()

        // Get statuses from public timeline
        val markers = client.markers.getMarkers().execute()
        println(markers)
    }
}
