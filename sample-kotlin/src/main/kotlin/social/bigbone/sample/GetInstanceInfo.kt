package social.bigbone.sample

import social.bigbone.MastodonClient

object GetInstanceInfo {
    @JvmStatic
    fun main(args: Array<String>) {
        val instance = args[0]

        // Instantiate client
        val client = MastodonClient.Builder(instance)
            .build()

        // Get instance info and dump it to the console as JSON
        val instanceInfo = client.instances.getInstance().execute()
        println(instanceInfo.toString())
    }
}
