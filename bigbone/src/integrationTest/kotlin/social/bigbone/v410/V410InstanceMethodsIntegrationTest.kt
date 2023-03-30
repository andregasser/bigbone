package social.bigbone.v410

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import social.bigbone.MastodonClient
import social.bigbone.TestHelpers

/**
 * Integration tests for InstanceMethods running on Mastodon 4.1.0.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class V410InstanceMethodsIntegrationTest {
    private lateinit var client: MastodonClient

    @BeforeAll
    fun beforeAll() {
        client = TestHelpers.getTrustAllClient()
    }

    @Test
    fun getInstance() {
        val instance = client.instances.getInstance().execute()
        println(instance)
    }
}
