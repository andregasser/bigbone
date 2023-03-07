package social.bigbone

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import social.bigbone.TestConstants.Companion.USER1_APP_NAME
import social.bigbone.TestConstants.Companion.USER1_EMAIL
import social.bigbone.TestConstants.Companion.USER1_PASSWORD
import social.bigbone.TestConstants.Companion.USER2_APP_NAME
import social.bigbone.TestConstants.Companion.USER2_EMAIL
import social.bigbone.TestConstants.Companion.USER2_PASSWORD
import social.bigbone.api.Scope
import social.bigbone.api.entity.Application
import social.bigbone.api.entity.Token

/**
 * Integration tests for StatusMethods running on Mastodon 4.1.0.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class V410StatusMethodsIntegrationTest {
    private lateinit var user1AppToken: Token
    private lateinit var user2AppToken: Token
    private lateinit var user1UserToken: Token
    private lateinit var user2UserToken: Token

    @BeforeAll
    fun beforeAll() {
        val user1Application = createApp(USER1_APP_NAME)
        user1AppToken = getAppToken(user1Application)
        user1UserToken = getUserToken(user1Application, USER1_EMAIL, USER1_PASSWORD)

        val user2Application = createApp(USER2_APP_NAME)
        user2AppToken = getAppToken(user2Application)
        user2UserToken = getUserToken(user2Application, USER2_EMAIL, USER2_PASSWORD)
    }

    @Test
    fun `retrieve previously posted status by id`() {
        val user1Client = getTrustAllClient(user1UserToken.accessToken)
        val status = user1Client.statuses.postStatus(status = "This is my status", spoilerText = "Test").execute()
        val retrievedStatus = user1Client.statuses.getStatus(status.id).execute()
        Assertions.assertEquals("<p>This is my status</p>", retrievedStatus.content)
        Assertions.assertEquals("Test", retrievedStatus.spoilerText)
    }

    @Test
    fun `user1 sees status as boosted after user2 has boosted it`() {
        val user1Client = getTrustAllClient(user1UserToken.accessToken)
        val user2Client = getTrustAllClient(user2UserToken.accessToken)

        // User1 posts status
        val user1Status = user1Client.statuses.postStatus(status = "Status to be boosted").execute()

        // User2 boosts it
        user2Client.statuses.reblogStatus(user1Status.id).execute()

        // User1 should see it as boosted
        val user1StatusAfterBoost = user1Client.statuses.getStatus(user1Status.id).execute()

        Assertions.assertFalse(user1StatusAfterBoost.isReblogged)
        Assertions.assertEquals(1, user1StatusAfterBoost.reblogsCount)
    }

    @Test
    fun `user1 sees status as unboosted after user2 has boosted and unboosted it`() {
        val user1Client = getTrustAllClient(user1UserToken.accessToken)
        val user2Client = getTrustAllClient(user2UserToken.accessToken)

        // User1 posts status
        val user1Status = user1Client.statuses.postStatus(status = "This status is boosted, then unboosted").execute()

        // User2 boosts it, then unboosts it
        user2Client.statuses.reblogStatus(user1Status.id).execute()
        user2Client.statuses.unreblogStatus(user1Status.id).execute()
        Thread.sleep(1000)

        // User1 should see it as unboosted
        val user1StatusAfterBoost = user1Client.statuses.getStatus(user1Status.id).execute()

        Assertions.assertFalse(user1StatusAfterBoost.isReblogged)
        Assertions.assertEquals(0, user1StatusAfterBoost.reblogsCount)
    }

    private fun createApp(appName: String): Application {
        val client = getTrustAllClient()
        return client.apps.createApp(appName, TestConstants.REDIRECT_URI, Scope(Scope.Name.ALL)).execute()
    }

    private fun getAppToken(application: Application): Token {
        val client = getTrustAllClient()
        return client.oauth.getAccessTokenWithClientCredentialsGrant(
            clientId = application.clientId!!,
            clientSecret = application.clientSecret!!,
            scope = Scope(Scope.Name.ALL)
        ).execute()
    }

    private fun getUserToken(application: Application, username: String, password: String): Token {
        val client = getTrustAllClient()
        return client.oauth.getUserAccessTokenWithPasswordGrant(
            clientId = application.clientId!!,
            clientSecret = application.clientSecret!!,
            scope = Scope(Scope.Name.ALL),
            redirectUri = TestConstants.REDIRECT_URI,
            username = username,
            password = password
        ).execute()
    }

    private fun getTrustAllClient() =
        MastodonClient.Builder(TestConstants.REST_API_HOSTNAME)
            .withTrustAllCerts()
            .build()

    private fun getTrustAllClient(accessToken: String) =
        MastodonClient.Builder(TestConstants.REST_API_HOSTNAME)
            .withTrustAllCerts()
            .accessToken(accessToken)
            .build()
}
