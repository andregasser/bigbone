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

    /**
     * Obtain a list of all filter groups for the current user.
     * @see <a href="https://docs.joinmastodon.org/methods/filters/#get">Mastodon API documentation: methods/filters/#get</a>
     */
    fun listFilters(): Single<List<Filter>> = Single.fromCallable {
        filterMethods.listFilters().execute()
    }

    /**
     * Obtain a single filter group owned by the current user.
     * @param filterId the ID of the Filter in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/filters/#get-one">Mastodon API documentation: methods/filters/#get-one</a>
     */
    fun viewFilter(filterId: String): Single<Filter> = Single.fromCallable {
        filterMethods.viewFilter(filterId).execute()
    }

    /**
     * Create a filter group with the given parameters.
     * @param title the name of the filter group
     * @param context where the filter should be applied. Specify at least one of home, notifications, public, thread, account.
     * @param filterKeywords list of [FilterKeyword] to be added to the newly-created filter group
     * @param expiresIn how many seconds from now should the filter expire?
     * @param filterAction the policy to be applied when the filter is matched. Specify warn or hide.
     * @see <a href="https://docs.joinmastodon.org/methods/filters/#create">Mastodon API documentation: methods/filters/#create</a>
     */
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

    /**
     * Update a filter group with the given parameters.
     * @param filterId the ID of the Filter in the database.
     * @param title the name of the filter group
     * @param context where the filter should be applied. Specify at least one of home, notifications, public, thread, account.
     * @param filterAction the policy to be applied when the filter is matched. Specify warn or hide.
     * @param expiresIn how many seconds from now should the filter expire?
     * @param keywordsToAdd list of [FilterKeyword] that will be added to this filter group;
     *   ID values of these FilterKeywords will be ignored
     * @param keywordsToUpdate list of [FilterKeyword] that will be updated in this filter group;
     *   ID values of these FilterKeywords should already exist in the filter group
     * @param keywordsToRemove list of [FilterKeyword] that will be removed from this filter group;
     *   only the ID values of these FilterKeywords will be used, and should already exist in the filter group
     * @see <a href="https://docs.joinmastodon.org/methods/filters/#update">Mastodon API documentation: methods/filters/#update</a>
     */
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

    /**
     * Delete a filter group with the given id.
     * @param filterId the ID of the Filter in the database
     * @see <a href="https://docs.joinmastodon.org/methods/filters/#delete">Mastodon API documentation: methods/filters/#delete</a>
     */
    fun deleteFilter(filterId: String): Completable = Completable.fromAction {
        filterMethods.deleteFilter(filterId)
    }

    /**
     * List all keywords attached to the current filter group.
     * @param filterId the ID of the Filter in the database
     * @see <a href="https://docs.joinmastodon.org/methods/filters/#keywords-get">Mastodon API documentation: methods/filters/#keywords-get</a>
     */
    fun listKeywords(filterId: String): Single<List<FilterKeyword>> = Single.fromCallable {
        filterMethods.listKeywords(filterId).execute()
    }

    /**
     * Add the given keyword to the specified filter group.
     * @param filterId The ID of the Filter in the database.
     * @param filterKeyword The keyword to be added to the filter group.
     * @see <a href="https://docs.joinmastodon.org/methods/filters/#keywords-create">Mastodon API documentation: methods/filters/#keywords-create</a>
     */
    fun addKeyword(filterId: String, filterKeyword: FilterKeyword): Single<FilterKeyword> = Single.fromCallable {
        filterMethods.addKeyword(filterId, filterKeyword).execute()
    }

    /**
     * Get one filter keyword by the given ID.
     * @param keywordId The ID of the FilterKeyword in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/filters/#keywords-get-one">Mastodon API documentation: methods/filters/#keywords-get-one</a>
     */
    fun viewKeyword(keywordId: String): Single<FilterKeyword> = Single.fromCallable {
        filterMethods.viewKeyword(keywordId).execute()
    }

    /**
     * Update the given filter keyword.
     * @param filterKeyword The keyword to be updated, including a valid ID.
     * @see <a href="https://docs.joinmastodon.org/methods/filters/#keywords-update">Mastodon API documentation: methods/filters/#keywords-update</a>
     */
    fun updateKeyword(filterKeyword: FilterKeyword): Single<FilterKeyword> = Single.fromCallable {
        filterMethods.updateKeyword(filterKeyword).execute()
    }

    /**
     * Deletes the given filter keyword.
     * @param keywordId The ID of the FilterKeyword in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/filters/#keywords-delete">Mastodon API documentation: methods/filters/#keywords-delete</a>
     */
    fun deleteKeyword(keywordId: String): Completable = Completable.fromAction {
        filterMethods.deleteKeyword(keywordId)
    }

    /**
     * Obtain a list of all status filters within this filter group.
     * @param filterId The ID of the Filter in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/filters/#statuses-get">Mastodon API documentation: methods/filters/#statuses-get</a>
     */
    fun listStatusFilters(filterId: String): Single<List<FilterStatus>> = Single.fromCallable {
        filterMethods.listStatusFilters(filterId).execute()
    }

    /**
     * Add a status filter to the current filter group.
     * @see <a href="https://docs.joinmastodon.org/methods/filters/#statuses-add">Mastodon API documentation: methods/filters/#statuses-add</a>
     */
    fun addStatusToFilter(filterId: String, statusId: String): Single<FilterStatus> = Single.fromCallable {
        filterMethods.addStatusToFilter(filterId, statusId).execute()
    }

    /**
     * Obtain a single status filter.
     * @param filterStatusId The ID of the FilterStatus in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/filters/#statuses-get-one">Mastodon API documentation: methods/filters/#statuses-get-one</a>
     */
    fun viewStatusFilter(filterStatusId: String): Single<FilterStatus> = Single.fromCallable {
        filterMethods.viewStatusFilter(filterStatusId).execute()
    }

    /**
     * Remove a status from a filter group.
     * @param filterStatusId The ID of the FilterStatus in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/filters/#statuses-remove">Mastodon API documentation: methods/filters/#statuses-remove</a>
     */
    fun removeStatusFromFilter(filterStatusId: String): Single<FilterStatus> = Single.fromCallable {
        filterMethods.removeStatusFromFilter(filterStatusId).execute()
    }
}
