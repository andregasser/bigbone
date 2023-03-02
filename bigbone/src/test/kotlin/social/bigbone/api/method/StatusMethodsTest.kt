package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.entity.Status
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class StatusMethodsTest {
    @Test
    fun getStatus() {
        val client = MockClient.mock("status.json")
        val statusMethods = StatusMethods(client)
        val status = statusMethods.getStatus("1").execute()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun getStatusWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            statusMethods.getStatus("1").execute()
        }
    }

    @Test
    fun getContext() {
        val client = MockClient.mock("context.json")
        val statusMethods = StatusMethods(client)
        val context = statusMethods.getContext("1").execute()
        context.ancestors.size shouldBeEqualTo 2
        context.descendants.size shouldBeEqualTo 1
    }

    @Test
    fun getContextWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            statusMethods.getContext("1").execute()
        }
    }

    @Test
    fun translateStatus() {
        val client = MockClient.mock("translation.json")
        val statusMethods = StatusMethods(client)
        val translation = statusMethods.translateStatus("statusId", "es").execute()
        translation.content shouldBeEqualTo "<p>Hola mundo</p>"
        translation.detectedSourceLanguage shouldBeEqualTo "en"
        translation.provider shouldBeEqualTo "DeepL.com"
    }

    @Test
    fun translateStatusWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            statusMethods.translateStatus("statusId", "es").execute()
        }
    }

    @Test
    fun getRebloggedBy() {
        val client = MockClient.mock("reblogged_by.json")
        val statusMethods = StatusMethods(client)
        val pageable = statusMethods.getRebloggedBy("1").execute()
        val account = pageable.part.first()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.username shouldBeEqualTo "test"
    }

    @Test
    fun getRebloggedByWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            statusMethods.getRebloggedBy("1").execute()
        }
    }

    @Test
    fun getFavouritedBy() {
        val client = MockClient.mock("reblogged_by.json")
        val statusMethods = StatusMethods(client)
        val pageable = statusMethods.getFavouritedBy("1").execute()
        val account = pageable.part.first()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.username shouldBeEqualTo "test"
    }

    @Test
    fun getFavouritedByWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            statusMethods.getFavouritedBy("1").execute()
        }
    }

    @Test
    fun postStatus() {
        val client = MockClient.mock("status.json")
        val statusMethods = StatusMethods(client)
        val status = statusMethods.postStatus(
            status = "a",
            inReplyToId = null,
            mediaIds = null,
            sensitive = false,
            spoilerText = null,
            visibility = Status.Visibility.Unlisted,
            language = "en"
        ).execute()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun postStatusWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            statusMethods.postStatus(
                status = "a",
                inReplyToId = null,
                mediaIds = null,
                sensitive = false,
                spoilerText = null,
                visibility = Status.Visibility.Unlisted,
                language = "en"
            ).execute()
        }
    }

    @Test
    fun postPoll() {
        val client = MockClient.mock("status_with_poll.json")
        val statusMethods = StatusMethods(client)
        val pollOptions = listOf("foo", "bar", "baz")
        val status = statusMethods.postPoll(
            status = "a",
            pollOptions = pollOptions,
            pollExpiresIn = 3600,
            pollMultiple = false,
            pollHideTotals = false,
            inReplyToId = null,
            sensitive = false,
            spoilerText = null,
            visibility = Status.Visibility.Unlisted,
            language = "en"
        ).execute()
        status.poll?.id shouldBeEqualTo "34830"
        status.poll?.expired shouldBeEqualTo true
        status.poll?.votesCount shouldBeEqualTo 10
        status.poll?.options?.get(0)?.votesCount shouldBeEqualTo 6
    }

    @Test
    fun postPollWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            val pollOptions = listOf("foo", "bar", "baz")
            statusMethods.postPoll(
                status = "a",
                pollOptions = pollOptions,
                pollExpiresIn = 3600,
                pollMultiple = false,
                pollHideTotals = false,
                inReplyToId = null,
                sensitive = false,
                spoilerText = null,
                visibility = Status.Visibility.Unlisted,
                language = "en"
            ).execute()
        }
    }

    @Test
    fun scheduleStatus() {
        val client = MockClient.mock("scheduled_status.json")
        val statusMethods = StatusMethods(client)
        val scheduledStatus = statusMethods.scheduleStatus(
            status = "a",
            inReplyToId = null,
            mediaIds = null,
            sensitive = false,
            spoilerText = null,
            visibility = Status.Visibility.Unlisted,
            language = "en",
            scheduledAt = "2023-12-31T12:34:56.789Z"
        ).execute()
        scheduledStatus.id shouldBeEqualTo "12345"
        scheduledStatus.scheduledAt shouldBeEqualTo "2023-12-31T12:34:56.789Z"
        scheduledStatus.params.text shouldBeEqualTo "test post"
    }

    @Test
    fun scheduleStatusWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            statusMethods.scheduleStatus(
                status = "a",
                inReplyToId = null,
                mediaIds = null,
                sensitive = false,
                spoilerText = null,
                visibility = Status.Visibility.Unlisted,
                language = "en",
                scheduledAt = "2023-12-31T12:34:56.789Z"
            ).execute()
        }
    }

    @Test
    fun schedulePoll() {
        val client = MockClient.mock("scheduled_status_with_poll.json")
        val statusMethods = StatusMethods(client)
        val scheduledStatus = statusMethods.schedulePoll(
            status = "a",
            pollOptions = listOf("foo", "bar", "baz"),
            pollExpiresIn = 3600,
            pollMultiple = true,
            pollHideTotals = false,
            inReplyToId = null,
            sensitive = false,
            spoilerText = null,
            visibility = Status.Visibility.Unlisted,
            language = "en",
            scheduledAt = "2023-12-31T12:34:56.789Z"
        ).execute()
        scheduledStatus.id shouldBeEqualTo "12345"
        scheduledStatus.params.poll?.options?.get(0) shouldBeEqualTo "foo"
        scheduledStatus.params.poll?.options?.get(1) shouldBeEqualTo "bar"
        scheduledStatus.params.poll?.multiple shouldBeEqualTo true
    }

    @Test
    fun schedulePollWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            statusMethods.schedulePoll(
                status = "a",
                pollOptions = listOf("foo", "bar", "baz"),
                pollExpiresIn = 3600,
                pollMultiple = true,
                pollHideTotals = false,
                inReplyToId = null,
                sensitive = false,
                spoilerText = null,
                visibility = Status.Visibility.Unlisted,
                language = "en",
                scheduledAt = "2023-12-31T12:34:56.789Z"
            ).execute()
        }
    }

    @Test
    fun reblogStatus() {
        val client = MockClient.mock("status.json")
        val statusMethods = StatusMethods(client)
        val status = statusMethods.reblogStatus("1", visibility = Status.Visibility.Unlisted).execute()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun reblogStatusWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            statusMethods.reblogStatus("1").execute()
        }
    }

    @Test
    fun deleteStatus() {
        val client = MockClient.mock("status_after_deleting.json")
        val statusMethods = StatusMethods(client)
        val status = statusMethods.deleteStatus("statusId").execute()
        status.text shouldBeEqualTo "status text after deletion"
        status.mediaAttachments[0].id shouldBeEqualTo "22345792"
    }

    @Test
    fun deleteStatusWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            statusMethods.deleteStatus("1").execute()
        }
    }

    @Test
    fun unreblogStatus() {
        val client = MockClient.mock("status.json")
        val statusMethods = StatusMethods(client)
        val status = statusMethods.unreblogStatus("1").execute()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun unreblogStatusWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            statusMethods.unreblogStatus("1").execute()
        }
    }

    @Test
    fun favouriteStatus() {
        val client = MockClient.mock("status.json")
        val statusMethods = StatusMethods(client)
        val status = statusMethods.favouriteStatus("1").execute()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun favouriteStatusWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            statusMethods.favouriteStatus("1").execute()
        }
    }

    @Test
    fun unfavouriteStatus() {
        val client = MockClient.mock("status.json")
        val statusMethods = StatusMethods(client)
        val status = statusMethods.unfavouriteStatus("1").execute()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun unfavouriteStatusWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            statusMethods.unfavouriteStatus("1").execute()
        }
    }

    @Test
    fun bookmarkStatus() {
        val client = MockClient.mock("status.json")
        val statusMethods = StatusMethods(client)
        val status = statusMethods.bookmarkStatus("1").execute()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun bookmarkStatusWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            statusMethods.bookmarkStatus("1").execute()
        }
    }

    @Test
    fun unbookmarkStatus() {
        val client = MockClient.mock("status.json")
        val statusMethods = StatusMethods(client)
        val status = statusMethods.unbookmarkStatus("1").execute()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun unbookmarkStatusWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            statusMethods.unbookmarkStatus("1").execute()
        }
    }

    @Test
    fun muteConversation() {
        val client = MockClient.mock("status.json")
        val statusMethods = StatusMethods(client)
        val status = statusMethods.muteConversation("1").execute()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun muteConversationWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            statusMethods.muteConversation("1").execute()
        }
    }

    @Test
    fun unmuteConversation() {
        val client = MockClient.mock("status.json")
        val statusMethods = StatusMethods(client)
        val status = statusMethods.unmuteConversation("1").execute()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun unmuteConversationWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            statusMethods.unmuteConversation("1").execute()
        }
    }

    @Test
    fun pinStatus() {
        val client = MockClient.mock("status.json")
        val statusMethods = StatusMethods(client)
        val status = statusMethods.pinStatus("1").execute()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun pinStatusWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            statusMethods.pinStatus("1").execute()
        }
    }

    @Test
    fun unpinStatus() {
        val client = MockClient.mock("status.json")
        val statusMethods = StatusMethods(client)
        val status = statusMethods.unpinStatus("1").execute()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun unpinStatusWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val statusMethods = StatusMethods(client)
            statusMethods.unpinStatus("1").execute()
        }
    }
}
