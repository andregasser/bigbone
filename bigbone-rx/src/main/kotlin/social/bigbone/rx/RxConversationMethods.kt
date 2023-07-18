package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Conversation
import social.bigbone.api.method.ConversationMethods
import social.bigbone.rx.extensions.onErrorIfNotDisposed

/**
 * Reactive implementation of [ConversationMethods].
 * Direct conversations with other participants. (Currently, just threads containing a post with "direct" visibility.)
 * @see <a href="https://docs.joinmastodon.org/methods/conversations/">Mastodon conversations API methods</a>
 */
class RxConversationMethods(client: MastodonClient) {
    private val conversationMethods = ConversationMethods(client)

    @JvmOverloads
    fun getConversations(range: Range = Range()): Single<Pageable<Conversation>> {
        return Single.create {
            try {
                val conversations = conversationMethods.getConversations(range)
                it.onSuccess(conversations.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun deleteConversation(conversationId: String): Completable {
        return Completable.create {
            try {
                conversationMethods.deleteConversation(conversationId)
                it.onComplete()
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun markConversationAsRead(conversationId: String): Single<Conversation> {
        return Single.create {
            try {
                val conversation = conversationMethods.markConversationAsRead(conversationId)
                it.onSuccess(conversation.execute())
            } catch (throwable: Throwable) {
                it.onError(throwable)
            }
        }
    }
}
