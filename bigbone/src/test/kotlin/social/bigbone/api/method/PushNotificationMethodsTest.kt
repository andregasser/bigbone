package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class PushNotificationMethodsTest {

    @Test
    fun subscribeToPushNotification() {
        val client = MockClient.mock("push_notification_subscription.json")
        val pushNotificationMethods = PushNotificationMethods(client)
        val subscription = pushNotificationMethods.subscribePushNotification(
            endpoint = "endpoint",
            userPublicKey = "userPublicKey",
            userAuthSecret = "userAuthSecret",
            follow = true,
            mention = true
        ).execute()
        subscription.serverKey shouldNotBeEqualTo ""
        subscription.alerts.follow shouldBeEqualTo true
        subscription.alerts.mention shouldBeEqualTo true
    }

    @Test
    fun updatePushNotificationSubscriptionWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val pushNotificationMethods = PushNotificationMethods(client)
            pushNotificationMethods.updatePushSubscription().execute()
        }
    }
}
