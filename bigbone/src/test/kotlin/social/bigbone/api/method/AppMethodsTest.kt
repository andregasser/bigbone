package social.bigbone.api.method

import io.mockk.every
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.MastodonClient
import social.bigbone.api.Scope
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class AppMethodsTest {
    @Test
    fun createApp() {
        val client: MastodonClient = MockClient.mock("app_registration.json")
        every { client.getInstanceName() } returns "mastodon.cloud"

        val appMethods = AppMethods(client)
        val registration = appMethods.createApp(
            clientName = "bigbone-sample-app", scope = Scope(Scope.Name.ALL)
        ).execute()

        registration.clientId shouldBeEqualTo "client id"
        registration.clientSecret shouldBeEqualTo "client secret"
        registration.redirectUri shouldBeEqualTo "urn:ietf:wg:oauth:2.0:oob"
    }

    @Test
    fun createAppWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val appMethods = AppMethods(client)
            appMethods.createApp(
                clientName = "bigbone-sample-app", scope = Scope(Scope.Name.ALL)
            ).execute()
        }
    }
}
