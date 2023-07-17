package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Conversation

/**
 * Direct conversations with other participants. (Currently, just threads containing a post with "direct" visibility.)
 * @see <a href="https://docs.joinmastodon.org/methods/conversations/">Mastodon conversations API methods</a>
 */
class ConversationMethods(private val client: MastodonClient) {
    /**
     * View your direct conversations with other participants.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/conversations/#get">Mastodon API documentation: methods/conversations/#get</a>
     */
    @JvmOverloads
    fun getConversations(range: Range = Range()): MastodonRequest<Pageable<Conversation>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/conversations",
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }

    /**
     * Removes a conversation from your list of conversations.
     * @param conversationId ID of the conversation.
     * @see <a href="https://docs.joinmastodon.org/methods/conversations/#delete">Mastodon API documentation: methods/conversations/#delete</a>
     */
    fun deleteConversation(conversationId: String) {
        client.performAction(
            endpoint = "api/v1/conversations/$conversationId",
            method = MastodonClient.Method.DELETE
        )
    }

    /**
     * Marks a conversation as read.
     * @param conversationId ID of the conversation.
     * @see <a href="https://docs.joinmastodon.org/methods/conversations/#read">Mastodon API documentation: methods/conversations/#read</a>
     */
    fun markConversationAsRead(conversationId: String): MastodonRequest<Conversation> {
        return client.getMastodonRequest(
            endpoint = "api/v1/conversations/$conversationId/read",
            method = MastodonClient.Method.POST
        )
    }
}
