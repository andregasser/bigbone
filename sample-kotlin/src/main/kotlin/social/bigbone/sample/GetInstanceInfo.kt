package social.bigbone.sample

import com.google.gson.Gson
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
        val gson = Gson()
        println(gson.toJson(instanceInfo))
    }
}
