package social.bigbone.api.method

import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.AnyException
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotThrow
import org.junit.jupiter.api.Test
import social.bigbone.MastodonClient
import social.bigbone.Parameters
import social.bigbone.testtool.MockClient
import java.net.URLEncoder

class EmailMethodsTest {

    @Test
    fun `Given a client returning success, when resending confirmation mail to existing address, then verify correct method was called`() {
        val client = MockClient.mock(jsonName = "emails_resend_confirmation_mail_success.json")
        val emailMethods = EmailMethods(client)

        invoking {
            emailMethods.resendConfirmationEmail()
        } shouldNotThrow AnyException

        verify {
            client.performAction(
                endpoint = "api/v1/emails/confirmations",
                method = MastodonClient.Method.POST,
                parameters = null
            )
        }
    }

    @Test
    fun `Given a client returning success, when resending confirmation mail to a new address, then verify correct method was called`() {
        val client = MockClient.mock(jsonName = "emails_resend_confirmation_mail_success.json")
        val emailMethods = EmailMethods(client)
        val newMailAddress = "newAddress@example.com"

        invoking {
            emailMethods.resendConfirmationEmail(emailAddress = newMailAddress)
        } shouldNotThrow AnyException

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.performAction(
                endpoint = "api/v1/emails/confirmations",
                method = MastodonClient.Method.POST,
                parameters = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "email=${URLEncoder.encode(newMailAddress, "utf-8")}"
        }
    }
}
