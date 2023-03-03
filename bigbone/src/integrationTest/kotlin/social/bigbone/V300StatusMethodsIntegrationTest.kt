package social.bigbone

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import social.bigbone.TestConstants.Companion.REDIRECT_URI
import social.bigbone.TestConstants.Companion.REST_API_HOSTNAME
import social.bigbone.TestConstants.Companion.USER2_APP_NAME
import social.bigbone.TestConstants.Companion.USER2_EMAIL
import social.bigbone.TestConstants.Companion.USER2_PASSWORD
import social.bigbone.api.Scope
import social.bigbone.api.entity.Application
import social.bigbone.api.entity.Token

/**
 * Integration tests for StatusMethods running on Mastodon 3.0.0.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class V300StatusMethodsIntegrationTest {
    private lateinit var appToken: Token
    private lateinit var user2Token: Token

    @BeforeAll
    fun beforeAll() {
        val application = createApp()
        appToken = getAppToken(application)
        user2Token = getUserToken(application, USER2_EMAIL, USER2_PASSWORD)
    }

    @Test
    fun postStatus() {
        val client = MastodonClient.Builder(REST_API_HOSTNAME)
            .withTrustAllCerts()
            .accessToken(user2Token.accessToken)
            .build()
        val response = client.statuses.postStatus(status = "This is my status", spoilerText = "Test").execute()
        Assertions.assertEquals("<p>This is my status</p>", response.content)
        Assertions.assertEquals("Test", response.spoilerText)
    }

    private fun createApp(): Application {
        val client = MastodonClient.Builder(REST_API_HOSTNAME)
            .withTrustAllCerts()
            .build()
        return client.apps.createApp(USER2_APP_NAME, REDIRECT_URI, Scope(Scope.Name.ALL)).execute()
    }

    private fun getAppToken(application: Application): Token {
        val client = MastodonClient.Builder(REST_API_HOSTNAME)
            .withTrustAllCerts()
            .build()
        return client.oauth.getAccessTokenWithClientCredentialsGrant(
            clientId = application.clientId!!,
            clientSecret = application.clientSecret!!,
            scope = Scope(Scope.Name.ALL)
        ).execute()
    }

    private fun getUserToken(application: Application, username: String, password: String): Token {
        val client = MastodonClient.Builder(REST_API_HOSTNAME)
            .withTrustAllCerts()
            .build()
        return client.oauth.getUserAccessTokenWithPasswordGrant(
            clientId = application.clientId!!,
            clientSecret = application.clientSecret!!,
            scope = Scope(Scope.Name.ALL),
            redirectUri = REDIRECT_URI,
            username = username,
            password = password
        ).execute()
    }
}
