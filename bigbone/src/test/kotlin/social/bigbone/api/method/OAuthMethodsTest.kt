package social.bigbone.api.method

import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.MastodonClient
import social.bigbone.api.Scope
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

@Suppress("FunctionMaxLength")
class OAuthMethodsTest {

    @Test
    fun getOAuthUrl() {
        val client: MastodonClient = mockk()
        every { client.getInstanceName() } returns "mastodon.cloud"

        val url = OAuthMethods(client).getOAuthUrl("client_id", Scope(Scope.Name.ALL))
        url shouldBeEqualTo "https://mastodon.cloud/oauth/authorize?client_id=client_id" +
            "&redirect_uri=urn%3Aietf%3Awg%3Aoauth%3A2.0%3Aoob&response_type=code&scope=read+write+follow"
    }

    @Test
    fun getAccessTokenWithAuthorizationCodeGrant() {
        val client: MastodonClient = MockClient.mock("access_token.json")
        every { client.getInstanceName() } returns "mastodon.cloud"
        val oauth = OAuthMethods(client)
        val accessToken = oauth.getAccessTokenWithAuthorizationCodeGrant("test", "test", code = "test").execute()
        accessToken.accessToken shouldBeEqualTo "test"
        accessToken.scope shouldBeEqualTo "read write follow"
        accessToken.tokenType shouldBeEqualTo "bearer"
        accessToken.createdAt shouldBeEqualTo 1_493_188_835
    }

    @Test
    fun getAccessTokenWithAuthorizationCodeGrantWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client: MastodonClient = MockClient.ioException()
            every { client.getInstanceName() } returns "mastodon.cloud"
            val oauth = OAuthMethods(client)
            oauth.getAccessTokenWithAuthorizationCodeGrant("test", "test", code = "test").execute()
        }
    }

    @Test
    fun getAccessTokenWithClientCredentialsGrant() {
        val client: MastodonClient = MockClient.mock("access_token.json")
        every { client.getInstanceName() } returns "mastodon.cloud"
        val oauth = OAuthMethods(client)
        val accessToken = oauth.getAccessTokenWithClientCredentialsGrant("test", "test").execute()
        accessToken.accessToken shouldBeEqualTo "test"
        accessToken.scope shouldBeEqualTo "read write follow"
        accessToken.tokenType shouldBeEqualTo "bearer"
        accessToken.createdAt shouldBeEqualTo 1_493_188_835
    }

    @Test
    fun getAccessTokenWithClientCredentialsGrantWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client: MastodonClient = MockClient.ioException()
            every { client.getInstanceName() } returns "mastodon.cloud"
            val oauth = OAuthMethods(client)
            oauth.getAccessTokenWithClientCredentialsGrant("test", "test").execute()
        }
    }

    @Test
    fun getAccessTokenWithPasswordGrant() {
        val client: MastodonClient = MockClient.mock("access_token.json")
        every { client.getInstanceName() } returns "mastodon.cloud"
        val oauth = OAuthMethods(client)
        val accessToken = oauth.getAccessTokenWithPasswordGrant("test", "test", Scope(Scope.Name.ALL), "test", "test").execute()
        accessToken.accessToken shouldBeEqualTo "test"
        accessToken.scope shouldBeEqualTo "read write follow"
        accessToken.tokenType shouldBeEqualTo "bearer"
        accessToken.createdAt shouldBeEqualTo 1_493_188_835
    }

    @Test
    fun getAccessTokenWithPasswordGrantWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client: MastodonClient = MockClient.ioException()
            every { client.getInstanceName() } returns "mastodon.cloud"
            val oauth = OAuthMethods(client)
            oauth.getAccessTokenWithPasswordGrant("test", "test", Scope(Scope.Name.ALL), "test", "test").execute()
        }
    }
}
