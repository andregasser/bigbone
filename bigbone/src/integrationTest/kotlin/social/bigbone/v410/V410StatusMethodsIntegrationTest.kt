package social.bigbone.v410

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import social.bigbone.TestConstants.Companion.USER1_APP_NAME
import social.bigbone.TestConstants.Companion.USER1_EMAIL
import social.bigbone.TestConstants.Companion.USER1_PASSWORD
import social.bigbone.TestConstants.Companion.USER2_APP_NAME
import social.bigbone.TestConstants.Companion.USER2_EMAIL
import social.bigbone.TestConstants.Companion.USER2_PASSWORD
import social.bigbone.TestHelpers
import social.bigbone.api.entity.Status
import social.bigbone.api.entity.Token
import social.bigbone.api.exception.BigBoneRequestException

/**
 * Integration tests for StatusMethods running on Mastodon 4.1.0.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class V410StatusMethodsIntegrationTest {
    private lateinit var user1AppToken: Token
    private lateinit var user2AppToken: Token
    private lateinit var user1UserToken: Token
    private lateinit var user2UserToken: Token

    @BeforeAll
    fun beforeAll() {
        val user1Application = TestHelpers.createApp(USER1_APP_NAME)
        user1AppToken = TestHelpers.getAppToken(user1Application)
        user1UserToken = TestHelpers.getUserToken(user1Application, USER1_EMAIL, USER1_PASSWORD)

        val user2Application = TestHelpers.createApp(USER2_APP_NAME)
        user2AppToken = TestHelpers.getAppToken(user2Application)
        user2UserToken = TestHelpers.getUserToken(user2Application, USER2_EMAIL, USER2_PASSWORD)
    }

    @Nested
    @DisplayName("getStatus tests")
    internal inner class GetStatusTests {
        @Test
        fun `should return status when retrieved by id`() {
            val user1Client = TestHelpers.getTrustAllClient(user1UserToken.accessToken)
            val status = user1Client.statuses.postStatus(status = "This is my status", spoilerText = "Test").execute()
            val retrievedStatus = user1Client.statuses.getStatus(status.id).execute()
            assertEquals("<p>This is my status</p>", retrievedStatus.content)
            assertEquals("Test", retrievedStatus.spoilerText)
        }

        @Test
        fun `should throw BigBoneRequestException when called with an invalid id`() {
            val user1Client = TestHelpers.getTrustAllClient(user1UserToken.accessToken)
            assertThrows(BigBoneRequestException::class.java) {
                user1Client.statuses.getStatus("non-existing status id").execute()
            }
        }
    }

    @Nested
    @DisplayName("postStatus tests")
    internal inner class PostStatusTests {
        @Test
        fun `should post status when status set`() {
            val user1Client = TestHelpers.getTrustAllClient(user1UserToken.accessToken)
            val statusId = user1Client.statuses.postStatus(status = "Status to be posted").execute().id
            val status = user1Client.statuses.getStatus(statusId).execute()
            assertEquals(statusId, status.id)
            assertEquals("<p>Status to be posted</p>", status.content)
            assertEquals(Status.Visibility.Public.value, status.visibility)
            assertNull(status.inReplyToId)
            assertEquals(0, status.mediaAttachments.size)
            assertFalse(status.isSensitive)
            assertEquals("", status.spoilerText)
            assertEquals("en", status.language)
        }

        @Test
        fun `should post status when all params set`() {
            val user1Client = TestHelpers.getTrustAllClient(user1UserToken.accessToken)
            val statusId = user1Client.statuses.postStatus(status = "Status to be posted").execute().id
            val uploadedMediaId1 = TestHelpers.uploadMediaFromResourcesFolder("castle-1280x853.jpg", "image/jpg", user1Client).id
            val uploadedMediaId2 = TestHelpers.uploadMediaFromResourcesFolder("castle-1280x853.jpg", "image/jpg", user1Client).id
            val replyStatusId = user1Client.statuses.postStatus(
                status = "This is a reply to the previous status",
                visibility = Status.Visibility.Private,
                inReplyToId = statusId,
                mediaIds = listOf(uploadedMediaId1, uploadedMediaId2),
                sensitive = true,
                spoilerText = "<p>This is a spoiler text</p>",
                language = "en"
            ).execute().id
            val replyStatus = user1Client.statuses.getStatus(replyStatusId).execute()
            assertEquals(replyStatusId, replyStatus.id)
            assertEquals("<p>This is a reply to the previous status</p>", replyStatus.content)
            assertEquals(Status.Visibility.Private.value, replyStatus.visibility)
            assertEquals(statusId, replyStatus.inReplyToId)
            assertEquals(2, replyStatus.mediaAttachments.size)
            assertTrue(replyStatus.isSensitive)
            assertEquals("<p>This is a spoiler text</p>", replyStatus.spoilerText)
            assertEquals("en", replyStatus.language)
        }
    }
}
