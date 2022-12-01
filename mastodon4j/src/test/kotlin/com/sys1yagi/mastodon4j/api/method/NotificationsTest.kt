package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NotificationsTest {
    @Test
    fun getNotifications() {
        val client = MockClient.mock("notifications.json")
        val notifications = Notifications(client)
        val pageable = notifications.getNotifications().execute()
        val notification = pageable.part.first()
        notification.type shouldBeEqualTo "favourite"
        notification.account shouldNotBe null
        notification.status shouldNotBe null
    }

    @Test
    fun getNotificationsWithException() {
        Assertions.assertThrows(Mastodon4jRequestException::class.java) {
            val client = MockClient.ioException()
            val notifications = Notifications(client)
            notifications.getNotifications().execute()
        }
    }

    @Test
    fun getNotificationWithException() {
        Assertions.assertThrows(Mastodon4jRequestException::class.java) {
            val client = MockClient.ioException()
            val notifications = Notifications(client)
            notifications.getNotification(1L).execute()
        }
    }
}
