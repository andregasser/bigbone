package social.bigbone.api.method

import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.MastodonClient
import social.bigbone.api.Scope
import social.bigbone.api.exception.BigboneRequestException
import social.bigbone.testtool.MockClient

class AppsTest {
    @Test
    fun createApp() {
        val client: MastodonClient = MockClient.mock("app_registration.json")
        every { client.getInstanceName() } returns "mastodon.social"

        val apps = Apps(client)
        val registration = apps.createApp(
            clientName = "mastodon-android-sys1yagi", scope = Scope(Scope.Name.ALL)
        ).execute()

        registration.instanceName shouldBeEqualTo "mastodon.social"
        registration.clientId shouldBeEqualTo "TWhM-tNSuncnqN7DBJmoyeLnk6K3iJJ71KKXxgL1hPM"
        registration.clientSecret shouldBeEqualTo "ZEaFUFmF0umgBX1qKJDjaU99Q31lDkOU8NutzTOoliw"
        registration.redirectUri shouldBeEqualTo "urn:ietf:wg:oauth:2.0:oob"
    }

    @Test
    fun createAppWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val apps = Apps(client)
            apps.createApp(
                clientName = "mastodon-android-sys1yagi", scope = Scope(Scope.Name.ALL)
            ).execute()
        }
    }

    @Test
    fun getOAuthUrl() {
        val client: MastodonClient = mockk()
        every { client.getInstanceName() } returns "mastodon.cloud"

        val url = Apps(client).getOAuthUrl("client_id", Scope(Scope.Name.ALL))
        url shouldBeEqualTo "https://mastodon.cloud/oauth/authorize?client_id=client_id" +
            "&redirect_uri=urn%3Aietf%3Awg%3Aoauth%3A2.0%3Aoob&response_type=code&scope=read+write+follow"
    }

    @Test
    fun getAccessToken() {
        val client: MastodonClient = MockClient.mock("access_token.json")
        every { client.getInstanceName() } returns "mastodon.cloud"
        val apps = Apps(client)
        val accessToken = apps.getAccessToken("test", "test", code = "test").execute()
        accessToken.accessToken shouldBeEqualTo "ZA-Yj3aBD8U8Cm7lKUp-lm9O9BmDgdhHzDeqsY8tlL0"
        accessToken.scope shouldBeEqualTo "read write follow push"
        accessToken.tokenType shouldBeEqualTo "Bearer"
        accessToken.createdAt shouldBeEqualTo 1_573_979_017L
    }

    @Test
    fun getAccessTokenWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client: MastodonClient = MockClient.ioException()
            every { client.getInstanceName() } returns "mastodon.cloud"
            val apps = Apps(client)
            apps.getAccessToken("test", "test", code = "test").execute()
        }
    }
}
