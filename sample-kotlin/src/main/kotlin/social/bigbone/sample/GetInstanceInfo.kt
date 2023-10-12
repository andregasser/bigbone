package social.bigbone.sample

import kotlinx.serialization.json.Json
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Instance

object GetInstanceInfo {

    private val JSON_SERIALIZER: Json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val instance = args[0]

        // Instantiate client
        val client = MastodonClient.Builder(instance)
            .build()

        // Get instance info and dump it to the console as JSON
        val instanceInfo = client.instances.getInstance().execute()
        println(JSON_SERIALIZER.encodeToString(Instance.serializer(), instanceInfo))
    }
}
