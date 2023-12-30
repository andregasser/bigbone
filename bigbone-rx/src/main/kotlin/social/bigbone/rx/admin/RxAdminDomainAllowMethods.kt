package social.bigbone.rx.admin

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.admin.AdminDomainAllow
import social.bigbone.api.method.admin.AdminDomainAllowMethods

/**
 * Reactive implementation of [AdminDomainAllowMethods].
 *
 * Allow certain domains to federate.
 * @see <a href="https://docs.joinmastodon.org/methods/admin/domain_allows/">Mastodon admin/domain_allows API methods</a>
 */
class RxAdminDomainAllowMethods(client: MastodonClient) {

    private val adminAllowMethods = AdminDomainAllowMethods(client)

    /**
     * Show information about all allowed domains.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/domain_allows/#get">Mastodon API documentation: methods/domain_allows/#get</a>
     */
    @JvmOverloads
    fun getAllAllowedDomains(range: Range = Range()): Single<Pageable<AdminDomainAllow>> = Single.fromCallable {
        adminAllowMethods.getAllAllowedDomains(range = range).execute()
    }

    /**
     * Show information about a single allowed domain.
     * @param id The ID of the DomainAllow in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/domain_allows/#get-one">Mastodon API documentation: methods/domain_allows/#get-one</a>
     */
    fun getAllowedDomain(id: String): Single<AdminDomainAllow> = Single.fromCallable {
        adminAllowMethods.getAllowedDomain(id = id).execute()
    }

    /**
     * Add a domain to the list of domains allowed to federate, to be used when the instance is in allow-list federation mode.
     * @param domain The domain to allow federation with.
     * @see <a href="https://docs.joinmastodon.org/methods/admin/domain_allows/#create">Mastodon API documentation: admin/domain_allows/#create</a>
     */
    fun allowDomainToFederate(domain: String): Single<AdminDomainAllow> = Single.fromCallable {
        adminAllowMethods.allowDomainToFederate(domain = domain).execute()
    }

    /**
     * Delete a domain from the allowed domains list.
     * @param id The ID of the DomainAllow in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/admin/domain_allows/#delete">Mastodon API documentation: admin/domain_allows/#delete</a>
     */
    fun removeAllowedDomain(id: String): Completable = Completable.fromAction {
        adminAllowMethods.removeAllowedDomain(id = id)
    }
}
