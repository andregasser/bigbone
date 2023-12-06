package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.api.entity.OEmbedMetadata

object GetOEmbedMetadata {
    @JvmStatic
    fun main(args: Array<String>) {
        val instance = args[0]
        val statusUrl = args[1]

        // Instantiate client
        val client = MastodonClient.Builder(instance).build()

        // Get oEmbed metadata for [statusUrl]
        val oEmbedMetadata: OEmbedMetadata = client.oembed.getOEmbedInfoAsJson(urlOfStatus = statusUrl).execute()
        println(oEmbedMetadata)
    }
}
