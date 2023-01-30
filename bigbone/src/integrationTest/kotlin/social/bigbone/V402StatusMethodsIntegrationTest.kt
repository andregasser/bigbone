package social.bigbone

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class V402StatusMethodsIntegrationTest: V402BaseIntegrationTest() {
    private lateinit var client: MastodonClient

    @BeforeEach
    fun setUp() {
        client = MastodonClient.Builder(mastodonWebContainer.host)
            .withHttpsDisabled()
            .withPort(mastodonWebContainer.getMappedPort(3000))
            .build()
    }

    @Test
    fun postStatus() {
        val accessToken = client.accounts.registerAccount(
            username = "andre",
            email = "andre.gasser@protonmail.com",
            password = "test",
            agreement = true,
            locale = "en",
            reason = null).execute()
        val response = client.statuses.postStatus("This is my status", null, null, false, "Test").execute()

    }

}
