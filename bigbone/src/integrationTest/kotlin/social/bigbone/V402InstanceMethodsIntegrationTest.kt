package social.bigbone

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Integration tests for InstanceMethods running on Mastodon 4.0.2.
 */
class V402InstanceMethodsIntegrationTest : V402BaseIntegrationTest() {
    private lateinit var client: MastodonClient

    @BeforeEach
    fun setUp() {
        client = MastodonClient.Builder(mastodonWebContainer.host)
            .withHttpsDisabled()
            .withPort(mastodonWebContainer.getMappedPort(3000))
            .build()
    }

    @Test
    fun getInstance() {
        val instance = client.instances.getInstance().execute()
        println(instance)
    }
}
