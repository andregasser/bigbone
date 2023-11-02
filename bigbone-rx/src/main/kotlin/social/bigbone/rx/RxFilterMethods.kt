package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Filter
import social.bigbone.api.entity.FilterKeyword
import social.bigbone.api.entity.FilterStatus
import social.bigbone.api.method.FilterMethods

/**
 * Reactive implementation of [FilterMethods].
 * Allows access to API methods with endpoints having an "api/vX/filters" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/filters/">Mastodon filters API methods</a>
 */
class RxFilterMethods(client: MastodonClient) {

    private val filterMethods = FilterMethods(client)

    fun listFilters(): Single<List<Filter>> = Single.fromCallable {
        filterMethods.listFilters().execute()
    }

    fun viewFilter(filterId: String): Single<Filter> = Single.fromCallable {
        filterMethods.viewFilter(filterId).execute()
    }

    @JvmOverloads
    fun createFilter(
        title: String,
        context: List<Filter.FilterContext>,
        filterKeywords: List<FilterKeyword>,
        expiresIn: Int? = null,
        filterAction: Filter.FilterAction = Filter.FilterAction.WARN
    ): Single<Filter> = Single.fromCallable {
        filterMethods.createFilter(
            title,
            context,
            filterKeywords,
            expiresIn,
            filterAction
        ).execute()
    }

    @JvmOverloads
    fun updateFilter(
        filterId: String,
        title: String? = null,
        context: List<Filter.FilterContext>? = null,
        filterAction: Filter.FilterAction? = null,
        expiresIn: Int? = null,
        keywordsToAdd: List<FilterKeyword>? = null,
        keywordsToUpdate: List<FilterKeyword>? = null,
        keywordsToRemove: List<FilterKeyword>? = null
    ): Single<Filter> = Single.fromCallable {
        filterMethods.updateFilter(
            filterId,
            title,
            context,
            filterAction,
            expiresIn,
            keywordsToAdd,
            keywordsToUpdate,
            keywordsToRemove
        ).execute()
    }

    fun deleteFilter(filterId: String): Completable = Completable.fromAction {
        filterMethods.deleteFilter(filterId)
    }

    fun listKeywords(filterId: String): Single<List<FilterKeyword>> = Single.fromCallable {
        filterMethods.listKeywords(filterId).execute()
    }

    fun addKeyword(filterId: String, filterKeyword: FilterKeyword): Single<FilterKeyword> = Single.fromCallable {
        filterMethods.addKeyword(filterId, filterKeyword).execute()
    }

    fun viewKeyword(keywordId: String): Single<FilterKeyword> = Single.fromCallable {
        filterMethods.viewKeyword(keywordId).execute()
    }

    fun updateKeyword(filterKeyword: FilterKeyword): Single<FilterKeyword> = Single.fromCallable {
        filterMethods.updateKeyword(filterKeyword).execute()
    }

    fun deleteKeyword(keywordId: String): Completable = Completable.fromAction {
        filterMethods.deleteKeyword(keywordId)
    }

    fun listStatusFilters(filterId: String): Single<List<FilterStatus>> = Single.fromCallable {
        filterMethods.listStatusFilters(filterId).execute()
    }

    fun addStatusToFilter(filterId: String, statusId: String): Single<FilterStatus> = Single.fromCallable {
        filterMethods.addStatusToFilter(filterId, statusId).execute()
    }

    fun viewStatusFilter(filterStatusId: String): Single<FilterStatus> = Single.fromCallable {
        filterMethods.viewStatusFilter(filterStatusId).execute()
    }

    fun removeStatusFromFilter(filterStatusId: String): Single<FilterStatus> = Single.fromCallable {
        filterMethods.removeStatusFromFilter(filterStatusId).execute()
    }
}
