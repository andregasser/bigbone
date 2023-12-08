package social.bigbone.api.method

import io.mockk.every
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.MastodonClient
import social.bigbone.TestConstants
import social.bigbone.api.Scope
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class AppMethodsTest {
    @Test
    fun createApp() {
        val client: MastodonClient = MockClient.mock("application.json")
        every { client.getInstanceName() } returns "mastodon.cloud"

        val appMethods = AppMethods(client)
        val application = appMethods.createApp(
            clientName = "bigbone-sample-app",
            redirectUris = TestConstants.REDIRECT_URI,
            scope = Scope(Scope.Name.READ, Scope.Name.WRITE, Scope.Name.PUSH)
        ).execute()

        application.clientId shouldBeEqualTo "client id"
        application.clientSecret shouldBeEqualTo "client secret"
    }

    @Test
    fun createAppWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val appMethods = AppMethods(client)
            appMethods.createApp(
                clientName = "bigbone-sample-app",
                redirectUris = TestConstants.REDIRECT_URI,
                scope = Scope(Scope.Name.READ, Scope.Name.WRITE, Scope.Name.PUSH)
            ).execute()
        }
    }

    @Test
    fun verifyCredentials() {
        val client: MastodonClient = MockClient.mock("application_no_client_data.json")
        every { client.getInstanceName() } returns "mastodon.cloud"

        val appMethods = AppMethods(client)
        val application = appMethods.verifyCredentials().execute()

        application.name shouldBeEqualTo "bigbone-sample-app"
    }

    @Test
    fun verifyCredentialsWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val appMethods = AppMethods(client)
            appMethods.verifyCredentials().execute()
        }
    }
}
