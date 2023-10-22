package social.bigbone.api.method

import io.mockk.verify
import org.amshove.kluent.AnyException
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBe
import org.amshove.kluent.shouldNotThrow
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.MastodonClient
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class NotificationMethodsTest {
    @Test
    fun getFavoriteNotification() {
        val client = MockClient.mock("notifications.json")
        val notificationMethods = NotificationMethods(client)
        val pageable = notificationMethods.getAllNotifications().execute()
        val favoriteNotification = pageable.part.first()
        favoriteNotification.type shouldBeEqualTo "favourite"
        favoriteNotification.account shouldNotBe null
        favoriteNotification.status shouldNotBe null
    }

    @Test
    fun getReportNotification() {
        val client = MockClient.mock("notifications.json")
        val notificationMethods = NotificationMethods(client)
        val pageable = notificationMethods.getAllNotifications().execute()

        val reportNotification = pageable.part[1]
        reportNotification.type shouldBeEqualTo "admin.report"
        reportNotification.report shouldNotBe null
        reportNotification.report?.id shouldBeEqualTo "48914"
        reportNotification.report?.actionTaken shouldBeEqualTo false
    }

    @Test
    fun getNotificationsWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val notificationMethods = NotificationMethods(client)
            notificationMethods.getAllNotifications().execute()
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

    @Test
    fun `Given a client returning success, when dismissing a specific notification, then expect no exceptions and verify correct method was called`() {
        val client = MockClient.mock(jsonName = "notifications_dismiss_success.json")
        val notificationMethods = NotificationMethods(client)

        invoking { notificationMethods.dismissNotification("1") } shouldNotThrow AnyException

        verify {
            client.performAction(
                endpoint = "/api/v1/notifications/1/dismiss",
                method = MastodonClient.Method.POST
            )
        }
    }

    @Test
    fun `Given a client returning success, when dismissing all notifications, then expect no exceptions and verify correct method was called`() {
        val client = MockClient.mock(jsonName = "notifications_dismiss_success.json")
        val notificationMethods = NotificationMethods(client)

        invoking { notificationMethods.dismissAllNotifications() } shouldNotThrow AnyException

        verify {
            client.performAction(
                endpoint = "/api/v1/notifications/clear",
                method = MastodonClient.Method.POST
            )
        }
    }
}
