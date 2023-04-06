package social.bigbone.v300

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import social.bigbone.MastodonClient
import social.bigbone.TestConstants
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
        user2Token = getUserToken(application, TestConstants.USER2_EMAIL, TestConstants.USER2_PASSWORD)
    }

    @Test
    fun postStatus() {
        val client = MastodonClient.Builder(TestConstants.REST_API_HOSTNAME)
            .withTrustAllCerts()
            .accessToken(user2Token.accessToken)
            .build()
        val response = client.statuses.postStatus(status = "This is my status", spoilerText = "Test").execute()
        Assertions.assertEquals("<p>This is my status</p>", response.content)
        Assertions.assertEquals("Test", response.spoilerText)
    }

    private fun createApp(): Application {
        val client = MastodonClient.Builder(TestConstants.REST_API_HOSTNAME)
            .withTrustAllCerts()
            .build()
        return client.apps.createApp(TestConstants.USER2_APP_NAME, TestConstants.REDIRECT_URI, Scope(Scope.Name.ALL)).execute()
    }

    private fun getAppToken(application: Application): Token {
        val client = MastodonClient.Builder(TestConstants.REST_API_HOSTNAME)
            .withTrustAllCerts()
            .build()
        return client.oauth.getAccessTokenWithClientCredentialsGrant(
            clientId = application.clientId!!,
            clientSecret = application.clientSecret!!,
            redirectUri = TestConstants.REDIRECT_URI,
            scope = Scope(Scope.Name.ALL)
        ).execute()
    }

    private fun getUserToken(application: Application, username: String, password: String): Token {
        val client = MastodonClient.Builder(TestConstants.REST_API_HOSTNAME)
            .withTrustAllCerts()
            .build()
        return client.oauth.getUserAccessTokenWithPasswordGrant(
            clientId = application.clientId!!,
            clientSecret = application.clientSecret!!,
            scope = Scope(Scope.Name.ALL),
            redirectUri = TestConstants.REDIRECT_URI,
            username = username,
            password = password
        ).execute()
    }
}
