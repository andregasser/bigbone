package social.bigbone

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class V402StatusMethodsIntegrationTest: V402BaseIntegrationTest() {
    private lateinit var client: MastodonClient

    @BeforeEach
    fun setUp() {

    }

    @Test
    fun postStatus() {
        // Create app
        val client0 = MastodonClient.Builder("localhost")
            .withHttpsDisabled()
            .withPort(mastodonWebContainer.getMappedPort(3000))
            .build()
        val appRegistration = client0.apps.createApp("int-test-client").execute()
        val clientId = appRegistration.clientId
        val clientSecret = appRegistration.clientSecret
        client0.oauth.getAccessToken(clientId, clientSecret,)

        // Create user account
        val client1 = MastodonClient.Builder("localhost")
            .withHttpsDisabled()
            .withPort(mastodonWebContainer.getMappedPort(3000))
            .accessToken(appToken)
            .build()
        val accessToken = client1.accounts.registerAccount(
            username = "andre",
            email = "andre.gasser@protonmail.com",
            password = "test",
            agreement = true,
            locale = "en",
            reason = null).execute()

        assertNotNull(accessToken.accessToken)

        // Post status using user's access token
        val client2 = MastodonClient.Builder("localhost")
            .withHttpsDisabled()
            .withPort(mastodonWebContainer.getMappedPort(3000))
            .accessToken(accessToken.accessToken)
            .build()
        val response = client2.statuses.postStatus("This is my status", null, null, false, "Test").execute()

    }

}
