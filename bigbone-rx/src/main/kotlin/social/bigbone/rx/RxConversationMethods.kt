package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Conversation
import social.bigbone.api.method.ConversationMethods

/**
 * Reactive implementation of [ConversationMethods].
 * Direct conversations with other participants. (Currently, just threads containing a post with "direct" visibility.)
 * @see <a href="https://docs.joinmastodon.org/methods/conversations/">Mastodon conversations API methods</a>
 */
class RxConversationMethods(client: MastodonClient) {
    private val conversationMethods = ConversationMethods(client)

    /**
     * View your direct conversations with other participants.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/conversations/#get">Mastodon API documentation: methods/conversations/#get</a>
     */
    @JvmOverloads
    fun getConversations(range: Range = Range()): Single<Pageable<Conversation>> = Single.fromCallable { conversationMethods.getConversations(range).execute() }

    /**
     * Removes a conversation from your list of conversations.
     * @param conversationId ID of the conversation.
     * @see <a href="https://docs.joinmastodon.org/methods/conversations/#delete">Mastodon API documentation: methods/conversations/#delete</a>
     */
    fun deleteConversation(conversationId: String): Completable = Completable.fromAction { conversationMethods.deleteConversation(conversationId) }

    /**
     * Marks a conversation as read.
     * @param conversationId ID of the conversation.
     * @see <a href="https://docs.joinmastodon.org/methods/conversations/#read">Mastodon API documentation: methods/conversations/#read</a>
     */
    fun markConversationAsRead(conversationId: String): Single<Conversation> =
        Single.fromCallable { conversationMethods.markConversationAsRead(conversationId).execute() }
}
