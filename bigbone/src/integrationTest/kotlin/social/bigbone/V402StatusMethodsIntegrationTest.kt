package social.bigbone

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import social.bigbone.api.Scope
import social.bigbone.api.entity.Application
import social.bigbone.api.entity.Token

private const val MASTODON_REST_API_HOSTNAME = "localhost"
private const val MASTODON_REST_API_PORT = 3000
private const val APP_NAME = "bigbone-int-test-client"
private const val REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob"

private const val USER1_USERNAME = "user1"
private const val USER1_EMAIL = "user1@bigbone.social"
private const val USER1_PASSWORD = "user1abcdef"

private const val USER2_USERNAME = "user2"
private const val USER2_EMAIL = "user2@bigbone.social"
private const val USER2_PASSWORD = "user2abcdef"

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class V402StatusMethodsIntegrationTest {
    private lateinit var appToken: Token
    private lateinit var user2Token: Token

    @BeforeAll
    fun beforeAll() {
        val application = createApp()
        appToken = getAppToken(application)
        user2Token = getUserToken(application, USER2_EMAIL, USER2_PASSWORD)
        //verifyAppToken(appToken)
    }

    @Test
    fun postStatus() {
        val client = MastodonClient.Builder(MASTODON_REST_API_HOSTNAME)
            .withHttpsDisabled()
            .withPort(MASTODON_REST_API_PORT)
            .accessToken(user2Token.accessToken)
            .build()
        val response = client.statuses.postStatus("This is my status", null, null, false, "Test").execute()
        assertEquals("<p>This is my status</p>", response.content)
        assertEquals("Test", response.spoilerText)
    }

    private fun createApp(): Application {
        val client = MastodonClient.Builder(MASTODON_REST_API_HOSTNAME)
            .withHttpsDisabled()
            .withPort(MASTODON_REST_API_PORT)
            .build()
        return client.apps.createApp(APP_NAME, REDIRECT_URI, Scope(Scope.Name.ALL)).execute()
    }

    private fun getAppToken(application: Application): Token {
        val client = MastodonClient.Builder(MASTODON_REST_API_HOSTNAME)
            .withHttpsDisabled()
            .withPort(MASTODON_REST_API_PORT)
            .build()
        return client.oauth.getClientAccessToken(application.clientId!!, application.clientSecret!!, Scope(Scope.Name.ALL)).execute()
    }

    private fun getUserToken(application: Application, username: String, password: String): Token {
        val client = MastodonClient.Builder(MASTODON_REST_API_HOSTNAME)
            .withHttpsDisabled()
            .withPort(MASTODON_REST_API_PORT)
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

//    private fun verifyAppToken(token: Token) {
//        val client = MastodonClient.Builder("localhost")
//            .withHttpsDisabled()
//            .withPort(mastodonWebContainer.getMappedPort(3000))
//            .build()
//        return client.apps //.oauth.getClientAccessToken(application.clientId!!, application.clientSecret!!).execute()
//    }

//    private fun registerAccount(application: Application, username: String, password: String, email: String): Token {
//        val client = MastodonClient.Builder(MASTODON_REST_API_HOSTNAME)
//            .withHttpsDisabled()
//            .accessToken(appToken.accessToken)
//            .withPort(MASTODON_REST_API_PORT)
//            .build()
//        return client.accounts.registerAccount(username, email, password, true, "en", null)
//            .doOnJson {
//                println(it)
//            }.execute()
//    }

//    private fun loginUser(application: Application, username: String, password: String): Token {
//        val client = MastodonClient.Builder(MASTODON_REST_API_HOSTNAME)
//            .withHttpsDisabled()
//            .withPort(MASTODON_REST_API_PORT)
//            .build()
//        return client.oauth.getUserAccessTokenWithPasswordGrant(
//            application.clientId!!,
//            application.clientSecret!!,
//            Scope(Scope.Name.ALL),
//            REDIRECT_URI,
//            username,
//            password
//        ).execute()
//    }

}
