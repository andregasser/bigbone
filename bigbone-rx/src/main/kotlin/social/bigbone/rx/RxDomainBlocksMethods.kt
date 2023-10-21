package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.method.DomainBlocksMethods

/**
 * Reactive implementation of [DomainBlocksMethods]
 * Manage a user's blocked domains.
 * @see <a href="https://docs.joinmastodon.org/methods/domain_blocks/">Mastodon domain_blocks API methods</a>
 */
class RxDomainBlocksMethods(client: MastodonClient) {

    private val domainBlocksMethods = DomainBlocksMethods(client)

    /**
     * View domains the user has blocked.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/domain_blocks/#get">Mastodon API documentation: methods/domain_blocks/#get</a>
     * @return
     */
    @JvmOverloads
    fun getDomainBlocks(range: Range = Range()): Single<Pageable<String>> =
        Single.fromCallable { domainBlocksMethods.getDomainBlocks(range).execute() }

    /**
     * Block a domain to hide all public posts from it, hide all notifications from it,
     * remove all followers from it, prevent following new users from it (but does not remove existing follows).
     *
     * @param domain Domain to block
     * @see <a href="https://docs.joinmastodon.org/methods/domain_blocks/#block">Mastodon API documentation: methods/domain_blocks/#block</a>
     */
    fun blockDomain(domain: String): Completable = Completable.fromAction { domainBlocksMethods.blockDomain(domain) }

    /**
     * Remove a domain block, if it exists in the user’s array of blocked domains.
     *
     * @param domain Domain to block
     * @see <a href="https://docs.joinmastodon.org/methods/domain_blocks/#block">Mastodon API documentation: methods/domain_blocks/#block</a>
     */
    fun unblockDomain(domain: String): Completable =
        Completable.fromAction { domainBlocksMethods.unblockDomain(domain) }

}
