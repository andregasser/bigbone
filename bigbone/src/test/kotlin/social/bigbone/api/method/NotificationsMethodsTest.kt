package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class NotificationsMethodsTest {
    @Test
    fun getNotifications() {
        val client = MockClient.mock("notifications.json")
        val notificationsMethods = NotificationsMethods(client)
        val pageable = notificationsMethods.getNotifications().execute()
        val notification = pageable.part.first()
        notification.type shouldBeEqualTo "favourite"
        notification.account shouldNotBe null
        notification.status shouldNotBe null
    }

    @Test
    fun getNotificationsWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val notificationsMethods = NotificationsMethods(client)
            notificationsMethods.getNotifications().execute()
        }
    }

    @Test
    fun getNotificationWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val notificationsMethods = NotificationsMethods(client)
            notificationsMethods.getNotification("1").execute()
        }
    }
}
