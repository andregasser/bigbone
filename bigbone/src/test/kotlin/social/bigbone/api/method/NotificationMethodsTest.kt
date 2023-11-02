package social.bigbone.api.method

import io.mockk.verify
import org.amshove.kluent.AnyException
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeNull
import org.amshove.kluent.shouldNotThrow
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test
import social.bigbone.MastodonClient
import social.bigbone.Parameters
import social.bigbone.PrecisionDateTime.ValidPrecisionDateTime.ExactTime
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient
import java.time.Instant

class NotificationMethodsTest {
    @Test
    fun getMentionNotification() {
        val client = MockClient.mock("notifications.json")
        val notificationMethods = NotificationMethods(client)

        val pageable = notificationMethods.getAllNotifications().execute()

        with(pageable.part.first()) {
            type.name.lowercase() shouldBeEqualTo "mention"
            createdAt shouldBeEqualTo ExactTime(Instant.parse("2019-11-23T07:49:02.064Z"))
            account.shouldNotBeNull()
        }
        with(pageable.part.first().status) {
            shouldNotBeNull()
            createdAt shouldBeEqualTo ExactTime(Instant.parse("2019-11-23T07:49:01.940Z"))
        }

        verify {
            client.get(
                path = "/api/v1/notifications",
                query = any<Parameters>()
            )
        }
    }

    @Test
    fun getFavouriteNotification() {
        val client = MockClient.mock("notifications.json")
        val notificationMethods = NotificationMethods(client)
        val pageable = notificationMethods.getAllNotifications().execute()

        with(pageable.part[1]) {
            type.name.lowercase() shouldBeEqualTo "favourite"
            createdAt shouldBeEqualTo ExactTime(Instant.parse("2019-11-23T07:29:18.903Z"))
        }
        with(pageable.part[1].status) {
            shouldNotBeNull()
            createdAt shouldBeEqualTo ExactTime(Instant.parse("2019-11-23T07:28:34.210Z"))
        }

        verify {
            client.get(
                path = "/api/v1/notifications",
                query = any<Parameters>()
            )
        }
    }

    @Test
    fun getNotificationsWithException() {
        val client = MockClient.ioException()
        val notificationMethods = NotificationMethods(client)

        invoking {
            notificationMethods.getAllNotifications().execute()
        } shouldThrow BigBoneRequestException::class
    }

    @Test
    fun getNotificationWithException() {
        val client = MockClient.ioException()
        val notificationMethods = NotificationMethods(client)

        invoking {
            notificationMethods.getNotification("1").execute()
        } shouldThrow BigBoneRequestException::class

        verify {
            client.get(
                path = "/api/v1/notifications/1",
                query = any<Parameters>()
            )
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
