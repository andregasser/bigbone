package social.bigbone

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import social.bigbone.TestConstants.Companion.REST_API_HOSTNAME

/**
 * Integration tests for InstanceMethods running on Mastodon 4.1.0.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class V410InstanceMethodsIntegrationTest {
    private lateinit var client: MastodonClient

    @BeforeAll
    fun beforeAll() {
        client = MastodonClient.Builder(REST_API_HOSTNAME)
            .withTrustAllCerts()
            .build()
    }

    @Test
    fun getInstance() {
        val instance = client.instances.getInstance().execute()
        println(instance)
    }
}
