package social.bigbone.api.method

import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.AnyException
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldContainAll
import org.amshove.kluent.shouldNotBeNull
import org.amshove.kluent.shouldNotThrow
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test
import social.bigbone.JSON_SERIALIZER
import social.bigbone.MastodonClient
import social.bigbone.Parameters
import social.bigbone.PrecisionDateTime.ValidPrecisionDateTime.ExactTime
import social.bigbone.api.entity.Notification
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient
import java.time.Instant

class NotificationMethodsTest {
    @Test
    fun `Given a JSON response with invalid status, when deserialising, then default to null`() {
        val json = """
            {
                "type": "new_unknown_type"
            }
        """.trimIndent()

        val notification: Notification = JSON_SERIALIZER.decodeFromString(json)

        notification.type.shouldBeNull()
    }

    @Test
    fun getMentionNotification() {
        val client = MockClient.mock("notifications.json")
        val notificationMethods = NotificationMethods(client)

        val pageable = notificationMethods.getAllNotifications().execute()

        with(pageable.part.first()) {
            type.shouldNotBeNull()
            type!!.name.lowercase() shouldBeEqualTo "mention"
            createdAt shouldBeEqualTo ExactTime(Instant.parse("2019-11-23T07:49:02.064Z"))
            account.shouldNotBeNull()
        }
        with(pageable.part.first().status) {
            shouldNotBeNull()
            createdAt shouldBeEqualTo ExactTime(Instant.parse("2019-11-23T07:49:01.940Z"))
        }

        verify {
            client.get(
                path = "api/v1/notifications",
                query = any<Parameters>()
            )
        }
    }

    @Test
    fun `When getting all notifications with valid includeTypes, excludeTypes, and accountId, then call endpoint with correct parameters`() {
        val client = MockClient.mock("notifications.json")
        val notificationMethods = NotificationMethods(client)

        val includeTypes = listOf(
            Notification.NotificationType.FOLLOW,
            Notification.NotificationType.MENTION
        )
        val excludeTypes = listOf(
            Notification.NotificationType.FAVOURITE
        )
        notificationMethods.getAllNotifications(
            includeTypes = includeTypes,
            excludeTypes = excludeTypes,
            accountId = "1234567"
        ).execute()

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.get(
                path = "api/v1/notifications",
                query = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            parameters["types[]"]?.shouldContainAll(includeTypes.map(Notification.NotificationType::apiName))
            parameters["exclude_types[]"]?.shouldContainAll(excludeTypes.map(Notification.NotificationType::apiName))
            parameters["account_id"]?.shouldContainAll(listOf("1234567"))

            toQuery() shouldBeEqualTo "types[]=follow&types[]=mention&exclude_types[]=favourite&account_id=1234567"
        }
    }

    @Test
    fun `When getting all notifications with empty includeTypes and includeTypes, then call endpoint without types`() {
        val client = MockClient.mock("notifications.json")
        val notificationMethods = NotificationMethods(client)

        notificationMethods.getAllNotifications(
            includeTypes = emptyList(),
            excludeTypes = emptyList()
        ).execute()

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.get(
                path = "api/v1/notifications",
                query = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            parameters["types[]"].shouldBeNull()
            parameters["exclude_types[]"].shouldBeNull()
            parameters["account_id"].shouldBeNull()

            toQuery() shouldBeEqualTo ""
        }
    }

    @Test
    fun getFavouriteNotification() {
        val client = MockClient.mock("notifications.json")
        val notificationMethods = NotificationMethods(client)
        val pageable = notificationMethods.getAllNotifications().execute()

        with(pageable.part[1]) {
            type.shouldNotBeNull()
            type!!.name.lowercase() shouldBeEqualTo "favourite"
            createdAt shouldBeEqualTo ExactTime(Instant.parse("2019-11-23T07:29:18.903Z"))
        }
        with(pageable.part[1].status) {
            shouldNotBeNull()
            createdAt shouldBeEqualTo ExactTime(Instant.parse("2019-11-23T07:28:34.210Z"))
        }

        verify {
            client.get(
                path = "api/v1/notifications",
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
                path = "api/v1/notifications/1",
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
                endpoint = "api/v1/notifications/1/dismiss",
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
                endpoint = "api/v1/notifications/clear",
                method = MastodonClient.Method.POST
            )
        }
    }
}
