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
            mention = true,
            favourite = true
        ).execute()
        subscription.serverKey shouldNotBeEqualTo ""
        subscription.alerts.follow shouldBeEqualTo true
        subscription.alerts.mention shouldBeEqualTo true
        subscription.alerts.poll shouldBeEqualTo false
        subscription.alerts.reblog shouldBeEqualTo false
        subscription.alerts.favourite shouldBeEqualTo true
    }

    @Test
    fun updatePushNotification() {
        val client = MockClient.mock("push_notification_subscription.json")
        val pushNotificationMethods = PushNotificationMethods(client)
        val subscription = pushNotificationMethods.updatePushSubscription(
            follow = true,
            mention = true,
            favourite = true
        ).execute()
        subscription.serverKey shouldNotBeEqualTo ""
        subscription.alerts.follow shouldBeEqualTo true
        subscription.alerts.mention shouldBeEqualTo true
        subscription.alerts.poll shouldBeEqualTo false
        subscription.alerts.reblog shouldBeEqualTo false
        subscription.alerts.favourite shouldBeEqualTo true
    }

    @Test
    fun deletePushSubscriptionWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val pushNotificationMethods = PushNotificationMethods(client)
            pushNotificationMethods.removePushSubscription()
        }
    }

    @Test
    fun getPushNotification() {
        val client = MockClient.mock("push_notification_subscription.json")
        val pushNotificationMethods = PushNotificationMethods(client)
        val response = pushNotificationMethods.getPushNotification().execute()
        response.serverKey shouldNotBeEqualTo ""
        response.alerts.follow shouldBeEqualTo true
        response.alerts.mention shouldBeEqualTo true
        response.alerts.poll shouldBeEqualTo false
        response.alerts.reblog shouldBeEqualTo false
        response.alerts.favourite shouldBeEqualTo true
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
