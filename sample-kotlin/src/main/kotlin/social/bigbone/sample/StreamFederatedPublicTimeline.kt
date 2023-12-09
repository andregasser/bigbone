package social.bigbone.sample

import social.bigbone.MastodonClient

object StreamFederatedPublicTimeline {
    @JvmStatic
    fun main(args: Array<String>) {
        val instance = args[0]
        val accessToken = args[1]

        // Instantiate client
        val client = MastodonClient.Builder(instance)
            .accessToken(accessToken)
            .useStreamingApi()
            .build()

        client.streaming.federatedPublic(
            accessToken = accessToken,
            onlyMedia = false,
            callback = ::println
        ).use {
            Thread.sleep(15_000L)
        }
    }
}
