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

    @JvmOverloads
    fun getConversations(range: Range = Range()): Single<Pageable<Conversation>> = Single.fromCallable {
        conversationMethods.getConversations(range).execute()
    }

    fun deleteConversation(conversationId: String): Completable = Completable.fromAction {
        conversationMethods.deleteConversation(conversationId)
    }

    fun markConversationAsRead(conversationId: String): Single<Conversation> = Single.fromCallable {
        conversationMethods.markConversationAsRead(conversationId).execute()
    }
}
