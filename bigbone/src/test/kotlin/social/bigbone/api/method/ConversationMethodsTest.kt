package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class ConversationMethodsTest {
    @Test
    fun getConversations() {
        val client = MockClient.mock("conversations.json")
        val conversationMethods = ConversationMethods(client)
        val pageable = conversationMethods.getConversations().execute()
        val conversation = pageable.part.first()
        conversation.id shouldBeEqualTo "418450"
        conversation.unread shouldBeEqualTo true
        conversation.lastStatus?.id shouldBeEqualTo "11111"
    }

    @Test
    fun getConversationsWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val conversationMethods = ConversationMethods(client)
            conversationMethods.getConversations().execute()
        }
    }

    @Test
    fun deleteConversationWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val conversationMethods = ConversationMethods(client)
            conversationMethods.deleteConversation("12345")
        }
    }

    @Test
    fun markConversationAsRead() {
        val client = MockClient.mock("conversations_after_edit.json")
        val conversationMethods = ConversationMethods(client)
        val conversation = conversationMethods.markConversationAsRead("418450").execute()
        conversation.id shouldBeEqualTo "418450"
        conversation.unread shouldBeEqualTo false
    }

    @Test
    fun markConversationAsReadWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val conversationMethods = ConversationMethods(client)
            conversationMethods.markConversationAsRead("12345").execute()
        }
    }
}
