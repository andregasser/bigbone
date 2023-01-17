package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameter
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.entity.Relationship
import social.bigbone.api.entity.Status

class AccountsMethods(private val client: MastodonClient) {
    // GET /api/v1/accounts/:id
    fun getAccount(accountId: String): MastodonRequest<Account> {
        return client.getMastodonRequest(
            endpoint = "api/v1/accounts/$accountId",
            method = MastodonClient.Method.GET
        )
    }

    //  GET /api/v1/accounts/verify_credentials
    fun verifyCredentials(): MastodonRequest<Account> {
        return client.getMastodonRequest(
            endpoint = "api/v1/accounts/verify_credentials",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * PATCH /api/v1/accounts/update_credentials
     * display_name: The name to display in the user's profile
     * note: A new biography for the user
     * avatar: A base64 encoded image to display as the user's avatar (e.g. data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAUoAAADrCAYAAAA...)
     * header: A base64 encoded image to display as the user's header image (e.g. data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAUoAAADrCAYAAAA...)
     */
    fun updateCredentials(displayName: String?, note: String?, avatar: String?, header: String?): MastodonRequest<Account> {
        return client.getMastodonRequest(
            endpoint = "api/v1/accounts/update_credentials",
            method = MastodonClient.Method.PATCH,
            parameters = Parameter().apply {
                displayName?.let { append("display_name", it) }
                note?.let { append("note", it) }
                avatar?.let { append("avatar", it) }
                header?.let { append("header", it) }
            }
        )
    }

    //  GET /api/v1/accounts/:id/followers
    @JvmOverloads
    fun getFollowers(accountId: String, range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/accounts/$accountId/followers",
            method = MastodonClient.Method.GET,
            parameters = range.toParameter()
        )
    }

    //  GET /api/v1/accounts/:id/following
    @JvmOverloads
    fun getFollowing(accountId: String, range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/accounts/$accountId/following",
            method = MastodonClient.Method.GET,
            parameters = range.toParameter()
        )
    }

    //  GET /api/v1/accounts/:id/statuses
    @JvmOverloads
    fun getStatuses(
        accountId: String,
        onlyMedia: Boolean = false,
        excludeReplies: Boolean = false,
        pinned: Boolean = false,
        range: Range = Range()
    ): MastodonRequest<Pageable<Status>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/accounts/$accountId/statuses",
            method = MastodonClient.Method.GET,
            parameters = range.toParameter().apply {
                if (onlyMedia) { append("only_media", true) }
                if (pinned) { append("pinned", true) }
                if (excludeReplies) { append("exclude_replies", true) }
            }
        )
    }

    //  POST /api/v1/accounts/:id/follow
    fun followAccount(accountId: String): MastodonRequest<Relationship> {
        return client.getMastodonRequest(
            endpoint = "api/v1/accounts/$accountId/follow",
            method = MastodonClient.Method.POST,
        )
    }

    //  POST /api/v1/accounts/:id/unfollow
    fun unfollowAccount(accountId: String): MastodonRequest<Relationship> {
        return client.getMastodonRequest(
            endpoint = "api/v1/accounts/$accountId/unfollow",
            method = MastodonClient.Method.POST
        )
    }

    //  POST /api/v1/accounts/:id/block
    fun blockAccount(accountId: String): MastodonRequest<Relationship> {
        return client.getMastodonRequest(
            endpoint = "api/v1/accounts/$accountId/block",
            method = MastodonClient.Method.POST
        )
    }

    //  POST /api/v1/accounts/:id/unblock
    fun unblockAccount(accountId: String): MastodonRequest<Relationship> {
        return client.getMastodonRequest(
            endpoint = "api/v1/accounts/$accountId/unblock",
            method = MastodonClient.Method.POST
        )
    }

    //  POST /api/v1/accounts/:id/mute
    fun muteAccount(accountId: String): MastodonRequest<Relationship> {
        return client.getMastodonRequest(
            endpoint = "api/v1/accounts/$accountId/mute",
            method = MastodonClient.Method.POST
        )
    }

    //  POST /api/v1/accounts/:id/unmute
    fun unmuteAccount(accountId: String): MastodonRequest<Relationship> {
        return client.getMastodonRequest(
            endpoint = "api/v1/accounts/$accountId/unmute",
            method = MastodonClient.Method.POST
        )
    }

    //  GET /api/v1/accounts/relationships
    fun getRelationships(accountIds: List<String>): MastodonRequest<List<Relationship>> {
        return client.getMastodonRequestForList(
            endpoint = "api/v1/accounts/relationships",
            method = MastodonClient.Method.GET,
            parameters = Parameter().append("id", accountIds)
        )
    }

    // GET /api/v1/accounts/search
    /**
     * q: What to search for
     * limit: Maximum number of matching accounts to return (default: 40)
     */
    @JvmOverloads
    fun searchAccounts(query: String, limit: Int = 40): MastodonRequest<List<Account>> {
        return client.getMastodonRequestForList(
            endpoint = "api/v1/accounts/search",
            method = MastodonClient.Method.GET,
            parameters = Parameter()
                .append("q", query)
                .append("limit", limit)
        )
    }
}
