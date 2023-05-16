package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Filter
import social.bigbone.api.entity.FilterKeyword
import social.bigbone.api.entity.FilterStatus
import social.bigbone.api.method.FilterMethods
import social.bigbone.rx.extensions.onErrorIfNotDisposed

/**
 * Reactive implementation of [FilterMethods].
 * Allows access to API methods with endpoints having an "api/vX/filters" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/filters/">Mastodon filters API methods</a>
 */
class RxFilterMethods(client: MastodonClient) {
    private val filterMethods = FilterMethods(client)

    fun listFilters(): Single<List<Filter>> {
        return Single.create {
            try {
                val filters = filterMethods.listFilters()
                it.onSuccess(filters.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun viewFilter(filterId: String): Single<Filter> {
        return Single.create {
            try {
                val filter = filterMethods.viewFilter(filterId)
                it.onSuccess(filter.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    @JvmOverloads
    fun createFilter(
        title: String,
        context: List<Filter.Context>,
        filterKeywords: List<FilterKeyword>,
        expiresIn: Int? = null,
        filterAction: Filter.Action = Filter.Action.Warn
    ): Single<Filter> {
        return Single.create {
            try {
                val filter = filterMethods.createFilter(title, context, filterKeywords, expiresIn, filterAction)
                it.onSuccess(filter.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    @JvmOverloads
    fun updateFilter(
        filterId: String,
        title: String? = null,
        context: List<Filter.Context>? = null,
        filterAction: Filter.Action? = null,
        expiresIn: Int? = null,
        keywordsToAdd: List<FilterKeyword>? = null,
        keywordsToUpdate: List<FilterKeyword>? = null,
        keywordsToRemove: List<FilterKeyword>? = null
    ): Single<Filter> {
        return Single.create {
            try {
                val filter = filterMethods.updateFilter(filterId, title, context, filterAction, expiresIn, keywordsToAdd, keywordsToUpdate, keywordsToRemove)
                it.onSuccess(filter.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun deleteFilter(filterId: String): Completable {
        return Completable.create {
            try {
                filterMethods.deleteFilter(filterId)
                it.onComplete()
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun listKeywords(filterId: String): Single<List<FilterKeyword>> {
        return Single.create {
            try {
                val filter = filterMethods.listKeywords(filterId)
                it.onSuccess(filter.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun addKeyword(filterId: String, filterKeyword: FilterKeyword): Single<FilterKeyword> {
        return Single.create {
            try {
                val filter = filterMethods.addKeyword(filterId, filterKeyword)
                it.onSuccess(filter.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun viewKeyword(keywordId: String): Single<FilterKeyword> {
        return Single.create {
            try {
                val filter = filterMethods.viewKeyword(keywordId)
                it.onSuccess(filter.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun updateKeyword(filterKeyword: FilterKeyword): Single<FilterKeyword> {
        return Single.create {
            try {
                val filter = filterMethods.updateKeyword(filterKeyword)
                it.onSuccess(filter.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun deleteKeyword(keywordId: String): Completable {
        return Completable.create {
            try {
                filterMethods.deleteKeyword(keywordId)
                it.onComplete()
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun listStatusFilters(filterId: String): Single<List<FilterStatus>> {
        return Single.create {
            try {
                val filter = filterMethods.listStatusFilters(filterId)
                it.onSuccess(filter.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun addStatusToFilter(filterId: String, statusId: String): Single<FilterStatus> {
        return Single.create {
            try {
                val filter = filterMethods.addStatusToFilter(filterId, statusId)
                it.onSuccess(filter.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun viewStatusFilter(filterStatusId: String): Single<FilterStatus> {
        return Single.create {
            try {
                val filter = filterMethods.viewStatusFilter(filterStatusId)
                it.onSuccess(filter.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun removeStatusFromFilter(filterStatusId: String): Single<FilterStatus> {
        return Single.create {
            try {
                val filter = filterMethods.removeStatusFromFilter(filterStatusId)
                it.onSuccess(filter.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
