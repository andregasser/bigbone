package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class NotificationMethodsTest {
    @Test
    fun getFavoriteNotification() {
        val client = MockClient.mock("notifications.json")
        val notificationMethods = NotificationMethods(client)
        val pageable = notificationMethods.getNotifications().execute()
        val favoriteNotification = pageable.part.first()
        favoriteNotification.type shouldBeEqualTo "favourite"
        favoriteNotification.account shouldNotBe null
        favoriteNotification.status shouldNotBe null
    }

    @Test
    fun getReportNotification() {
        val client = MockClient.mock("notifications.json")
        val notificationMethods = NotificationMethods(client)
        val pageable = notificationMethods.getNotifications().execute()

        val reportNotification = pageable.part[1]
        reportNotification.type shouldBeEqualTo "admin.report"
        reportNotification.report shouldNotBe null
        reportNotification.report?.id shouldBeEqualTo "48914"
        reportNotification.report?.actionTaken shouldBeEqualTo "false"
    }

    @Test
    fun getNotificationsWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val notificationMethods = NotificationMethods(client)
            notificationMethods.getNotifications().execute()
        }
    }

    @Test
    fun getNotificationWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val notificationMethods = NotificationMethods(client)
            notificationMethods.getNotification("1").execute()
        }
    }
}
