package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.entity.MastodonList

/**
 * Allows access to API methods with endpoints having an "api/vX/lists" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/lists/">Mastodon lists API methods</a>
 */
class ListMethods(private val client: MastodonClient) {

    /**
     * Fetch all lists that the user owns.
     * @see <a href="https://docs.joinmastodon.org/methods/lists/#get">Mastodon API documentation: methods/lists/#get</a>
     */
    fun getLists(): MastodonRequest<List<MastodonList>> {
        return client.getMastodonRequestForList(
            endpoint = "api/v1/lists",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Fetch the list with the given ID. Used for verifying the title of a list, and which replies to show within that list.
     * @param listId The ID of the List in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/lists/#get-one">Mastodon API documentation: methods/lists/#get-one</a>
     */
    fun getList(listId: String): MastodonRequest<MastodonList> {
        return client.getMastodonRequest(
            endpoint = "api/v1/lists/$listId",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Create a new list.
     * @param title The title of the list to be created.
     * @param repliesPolicy One of [MastodonList.RepliesPolicy], defaults to [MastodonList.RepliesPolicy.List].
     * @see <a href="https://docs.joinmastodon.org/methods/lists/#create">Mastodon API documentation: methods/lists/#create</a>
     */
    @JvmOverloads
    fun createList(
        title: String,
        repliesPolicy: MastodonList.RepliesPolicy = MastodonList.RepliesPolicy.List
    ): MastodonRequest<MastodonList> {
        return client.getMastodonRequest(
            endpoint = "api/v1/lists",
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                append("title", title)
                append("replies_policy", repliesPolicy.value)
            }
        )
    }

    /**
     * Change the title of a list, or which replies to show.
     * @param listId The ID of the List in the database.
     * @param title The new title of the list to be updated.
     * @param repliesPolicy One of [MastodonList.RepliesPolicy].
     * @see <a href="https://docs.joinmastodon.org/methods/lists/#update">Mastodon API documentation: methods/lists/#update</a>
     */
    fun updateList(
        listId: String,
        title: String,
        repliesPolicy: MastodonList.RepliesPolicy
    ): MastodonRequest<MastodonList> {
        return client.getMastodonRequest(
            endpoint = "api/v1/lists/$listId",
            method = MastodonClient.Method.PUT,
            parameters = Parameters().apply {
                append("title", title)
                append("replies_policy", repliesPolicy.value)
            }
        )
    }

    /**
     * Delete a list.
     * @param listId The ID of the List in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/lists/#delete">Mastodon API documentation: methods/lists/#delete</a>
     */
    fun deleteList(listId: String) {
        client.performAction(
            endpoint = "api/v1/lists/$listId",
            method = MastodonClient.Method.DELETE
        )
    }

    /**
     * View accounts in a list.
     * @param listId The ID of the List in the database.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/lists/#accounts">Mastodon API documentation: methods/lists/#accounts</a>
     */
    @JvmOverloads
    fun getAccountsInList(listId: String, range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/lists/$listId/accounts",
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }

    /**
     * Add accounts to the given list. Note that the user must be following these accounts.
     * @param listId The ID of the List in the database.
     * @param accountIds The accounts that should be added to the list.
     * @see <a href="https://docs.joinmastodon.org/methods/lists/#accounts-add">Mastodon API documentation: methods/lists/#accounts-add</a>
     */
    fun addAccountsToList(listId: String, accountIds: List<String>) {
        client.performAction(
            endpoint = "api/v1/lists/$listId/accounts",
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                append("account_ids", accountIds)
            }
        )
    }

    /**
     * Remove accounts from the given list.
     * @param listId The ID of the List in the database.
     * @param accountIds The accounts that should be removed from the list.
     * @see <a href="https://docs.joinmastodon.org/methods/lists/#accounts-remove">Mastodon API documentation: methods/lists/#accounts-remove</a>
     */
    fun deleteAccountsFromList(listId: String, accountIds: List<String>) {
        client.performAction(
            endpoint = "api/v1/lists/$listId/accounts",
            method = MastodonClient.Method.DELETE,
            parameters = Parameters().apply {
                append("account_ids", accountIds)
            }
        )
    }
}
