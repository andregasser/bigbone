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
    //private lateinit var client: MastodonClient
    private lateinit var token: Token

    @Test
    fun postStatus() {
        val username = "user1"
        val password = "123456"
        val email = "user1@mastodon.internal"
        val application = createApp()
        val appToken = getAppToken(application)
        val userToken = createUser(application, username, password, email)
        //token = loginUser(application, "foobar", "foo@bar.ch")

        val client = MastodonClient.Builder("localhost")
            .withHttpsDisabled()
            .withPort(mastodonWebContainer.getMappedPort(3000))
            .accessToken(userToken.accessToken)
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

    private fun getAppToken(application: Application) {
        val client = MastodonClient.Builder("localhost")
            .withHttpsDisabled()
            .withPort(mastodonWebContainer.getMappedPort(3000))
            .build()
        return client.oauth.get     //accounts.registerAccount(username, email, password, true, "en", null).execute()
    }

    private fun createUser(application: Application, username: String, password: String, email: String): Token {
        val client = MastodonClient.Builder("localhost")
            .withHttpsDisabled()
            .withPort(mastodonWebContainer.getMappedPort(3000))
            .build()
        return client.accounts.registerAccount(username, email, password, true, "en", null).execute()
    }

    private fun loginUser(application: Application, username: String, password: String): Token {
        val client = MastodonClient.Builder("localhost")
            .withHttpsDisabled()
            .withPort(mastodonWebContainer.getMappedPort(3000))
            .build()
        return client.oauth.getAccessTokenWithPasswordGrant(
            application.clientId!!,
            application.clientSecret!!,
            Scope(Scope.Name.ALL),
            username,
            password
        ).execute()
    }

}
