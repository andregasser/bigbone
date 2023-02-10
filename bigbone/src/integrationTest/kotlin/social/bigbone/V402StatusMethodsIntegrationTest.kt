package social.bigbone

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import social.bigbone.api.Scope
import social.bigbone.api.entity.Application
import social.bigbone.api.entity.Token

class V402StatusMethodsIntegrationTest: V402BaseIntegrationTest() {
    private lateinit var client: MastodonClient
    private lateinit var token: Token

    @BeforeAll
    fun beforeAll() {
        val application = createApp()
        token = loginUser(application, "foobar", "foo@bar.ch")

//        // Create user account
//        val client1 = MastodonClient.Builder("localhost")
//            .withHttpsDisabled()
//            .withPort(mastodonWebContainer.getMappedPort(3000))
//            .accessToken(accessToken0.accessToken)
//            .build()
//        val accessToken = client1.accounts.registerAccount(
//            username = "andre",
//            email = "andre.gasser@protonmail.com",
//            password = "test",
//            agreement = true,
//            locale = "en",
//            reason = null).execute()
    }

    @Test
    fun postStatus() {
        val client = MastodonClient.Builder("localhost")
            .withHttpsDisabled()
            .withPort(mastodonWebContainer.getMappedPort(3000))
            .accessToken(token.accessToken)
            .build()
        val response = client.statuses.postStatus("This is my status", null, null, false, "Test").execute()
        assertEquals(response.text, "This is my status")
    }

    private fun createApp(): Application {
        val client = MastodonClient.Builder("localhost")
            .withHttpsDisabled()
            .withPort(mastodonWebContainer.getMappedPort(3000))
            .build()
        return client.apps.createApp("int-test-client").execute()
    }

    private fun loginUser(application: Application, username: String, password: String): Token {
        return client.oauth.getAccessTokenWithPasswordGrant(
            application.clientId!!,
            application.clientSecret!!,
            Scope(Scope.Name.ALL),
            username,
            password
        ).execute()
    }

}
