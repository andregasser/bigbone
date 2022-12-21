package social.bigbone.api.method

import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.MastodonClient
import social.bigbone.api.Scope
import social.bigbone.api.exception.Mastodon4jRequestException
import social.bigbone.testtool.MockClient

class AppsTest {
    @Test
    fun createApp() {
        val client: MastodonClient = MockClient.mock("app_registration.json")
        every { client.getInstanceName() } returns "mastodon.cloud"

        val apps = Apps(client)
        val registration = apps.createApp(
            clientName = "mastodon-android-sys1yagi",
            scope = Scope(Scope.Name.ALL)
        ).execute()

        registration.instanceName shouldBeEqualTo "mastodon.cloud"
        registration.clientId shouldBeEqualTo "client id"
        registration.clientSecret shouldBeEqualTo "client secret"
        registration.redirectUri shouldBeEqualTo "urn:ietf:wg:oauth:2.0:oob"
    }

    @Test
    fun createAppWithException() {
        Assertions.assertThrows(Mastodon4jRequestException::class.java) {
            val client = MockClient.ioException()
            val apps = Apps(client)
            apps.createApp(
                clientName = "mastodon-android-sys1yagi",
                scope = Scope(Scope.Name.ALL)
            ).execute()
        }
    }

    @Test
    fun getOAuthUrl() {
        val client: MastodonClient = mockk()
        every { client.getInstanceName() } returns "mastodon.cloud"

        val url = Apps(client).getOAuthUrl("client_id", Scope(Scope.Name.ALL))
        url shouldBeEqualTo "https://mastodon.cloud/oauth/authorize?client_id=client_id" +
                "&redirect_uri=urn:ietf:wg:oauth:2.0:oob&response_type=code&scope=read write follow"
    }

    @Test
    fun getAccessToken() {
        val client: MastodonClient = MockClient.mock("access_token.json")
        every { client.getInstanceName() } returns "mastodon.cloud"
        val apps = Apps(client)
        val accessToken = apps.getAccessToken("test", "test", code = "test").execute()
        accessToken.accessToken shouldBeEqualTo "test"
        accessToken.scope shouldBeEqualTo "read write follow"
        accessToken.tokenType shouldBeEqualTo "bearer"
        accessToken.createdAt shouldBeEqualTo 1_493_188_835
    }

    @Test
    fun getAccessTokenWithException() {
        Assertions.assertThrows(Mastodon4jRequestException::class.java) {
            val client: MastodonClient = MockClient.ioException()
            every { client.getInstanceName() } returns "mastodon.cloud"
            val apps = Apps(client)
            apps.getAccessToken("test", "test", code = "test").execute()
        }
    }

    @Test
    fun postUserNameAndPassword() {
        val client: MastodonClient = MockClient.mock("access_token.json")
        every { client.getInstanceName() } returns "mastodon.cloud"
        val apps = Apps(client)
        val accessToken = apps.postUserNameAndPassword("test", "test", Scope(Scope.Name.ALL), "test", "test").execute()
        accessToken.accessToken shouldBeEqualTo "test"
        accessToken.scope shouldBeEqualTo "read write follow"
        accessToken.tokenType shouldBeEqualTo "bearer"
        accessToken.createdAt shouldBeEqualTo 1_493_188_835
    }

    @Test
    fun postUserNameAndPasswordWithException() {
        Assertions.assertThrows(Mastodon4jRequestException::class.java) {
            val client: MastodonClient = MockClient.ioException()
            every { client.getInstanceName() } returns "mastodon.cloud"
            val apps = Apps(client)
            apps.postUserNameAndPassword("test", "test", Scope(Scope.Name.ALL), "test", "test").execute()
        }
    }
}
