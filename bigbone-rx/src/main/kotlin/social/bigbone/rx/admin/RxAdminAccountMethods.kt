package social.bigbone.rx.admin

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.admin.AccountOrigin
import social.bigbone.api.entity.admin.AccountStatus
import social.bigbone.api.entity.admin.ActionAgainstAccount
import social.bigbone.api.entity.admin.AdminAccount
import social.bigbone.api.method.admin.AdminAccountMethods

/**
 * Reactive implementation of [AdminAccountMethods].
 *
 * Perform moderation actions with accounts.
 *
 * @see <a href="https://docs.joinmastodon.org/methods/admin/accounts/">Mastodon admin/accounts API methods</a>
 */
class RxAdminAccountMethods(client: MastodonClient) {

    private val adminAccountMethods = AdminAccountMethods(client)

    /**
     * View all accounts, optionally matching certain criteria for filtering, up to 100 at a time.
     *
     * @param range optional Range for the pageable return value
     * @param origin Filter for [AccountOrigin.Local] or [AccountOrigin.Remote]
     * @param status Filter for [AccountStatus] accounts
     * @param permissions Filter for accounts with <code>staff</code> permissions (users that can manage reports)
     * @param roleIds Filter for users with these roles
     * @param invitedById Lookup users invited by the account with this ID
     * @param username Search for the given username
     * @param displayName Search for the given display name
     * @param byDomain Filter by the given domain
     * @param emailAddress Lookup a user with this email
     * @param ipAddress Lookup users with this IP address
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/accounts/#v2">Mastodon API documentation: admin/accounts/#v2</a>
     */
    @JvmOverloads
    fun viewAccounts(
        range: Range = Range(),
        origin: AccountOrigin? = null,
        status: AccountStatus? = null,
        permissions: String? = null,
        roleIds: List<String>? = null,
        invitedById: String? = null,
        username: String? = null,
        displayName: String? = null,
        byDomain: String? = null,
        emailAddress: String? = null,
        ipAddress: String? = null
    ): Single<Pageable<AdminAccount>> = Single.fromCallable {
        adminAccountMethods.viewAccounts(
            range = range,
            origin = origin,
            status = status,
            permissions = permissions,
            roleIds = roleIds,
            invitedById = invitedById,
            username = username,
            displayName = displayName,
            byDomain = byDomain,
            emailAddress = emailAddress,
            ipAddress = ipAddress
        ).execute()
    }

    /**
     * View admin-level information about the given account.
     *
     * @param withId The ID of the account in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/accounts/#get-one">Mastodon API documentation: admin/accounts/#get-one</a>
     */
    fun viewAccount(withId: String): Single<AdminAccount> = Single.fromCallable {
        adminAccountMethods.viewAccount(withId = withId).execute()
    }

    /**
     * Approve the given local account if it is currently pending approval.
     *
     * @param withId The ID of the account in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/accounts/#approve">Mastodon API documentation: admin/accounts/#approve</a>
     */
    fun approvePendingAccount(withId: String): Single<AdminAccount> = Single.fromCallable {
        adminAccountMethods.approvePendingAccount(withId = withId).execute()
    }

    /**
     * Reject the given local account if it is currently pending approval.
     *
     * @param withId The ID of the account in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/accounts/#reject">Mastodon API documentation: admin/accounts/#reject</a>
     */
    fun rejectPendingAccount(withId: String): Single<AdminAccount> = Single.fromCallable {
        adminAccountMethods.rejectPendingAccount(withId = withId).execute()
    }

    /**
     * Permanently delete data for a suspended account.
     *
     * @param withId The ID of the account in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/accounts/#delete">Mastodon API documentation: admin/accounts/#delete</a>
     */
    fun deleteAccount(withId: String): Single<AdminAccount> = Single.fromCallable {
        adminAccountMethods.deleteAccount(withId = withId).execute()
    }

    /**
     * Perform an action against an account and log this action in the moderation history.
     * Also resolves any open reports against this account.
     *
     * @param withId The ID of the account in the database.
     * @param type The type of action to be taken. One of [ActionAgainstAccount].
     * @param text Additional clarification for why this action was taken.
     * @param reportId The ID of an associated report that caused this action to be taken.
     * @param warningPresetId The ID of a preset warning.
     * @param sendEmailNotification Whether an email should be sent to the user with the above information.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/accounts/#action">Mastodon API documentation: admin/accounts/#action</a>
     */
    @JvmOverloads
    fun performActionAgainstAccount(
        withId: String,
        type: ActionAgainstAccount,
        text: String? = null,
        reportId: String? = null,
        warningPresetId: String? = null,
        sendEmailNotification: Boolean? = null
    ): Completable {
        return Completable.fromAction {
            adminAccountMethods.performActionAgainstAccount(
                withId = withId,
                type = type,
                text = text,
                reportId = reportId,
                warningPresetId = warningPresetId,
                sendEmailNotification = sendEmailNotification
            )
        }
    }

    /**
     * Re-enable a local account whose login is currently disabled.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/accounts/#enable">Mastodon API documentation: admin/accounts/#enable</a>
     */
    fun enableDisabledAccount(withId: String): Single<AdminAccount> = Single.fromCallable {
        adminAccountMethods.enableDisabledAccount(withId = withId).execute()
    }

    /**
     * Unsilence an account if it is currently silenced.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/accounts/#unsilence">Mastodon API documentation: admin/accounts/#unsilence</a>
     */
    fun unsilenceAccount(withId: String): Single<AdminAccount> = Single.fromCallable {
        adminAccountMethods.unsilenceAccount(withId = withId).execute()
    }

    /**
     * Unsuspend a currently suspended account.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/accounts/#unsuspend">Mastodon API documentation: admin/accounts/#unsuspend</a>
     */
    fun unsuspendAccount(withId: String): Single<AdminAccount> = Single.fromCallable {
        adminAccountMethods.unsuspendAccount(withId = withId).execute()
    }

    /**
     * Stops marking an account's posts as sensitive, if it was previously flagged as sensitive.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/accounts/#unsensitive">Mastodon API documentation: admin/accounts/#unsensitive</a>
     */
    fun unmarkAccountAsSensitive(withId: String): Single<AdminAccount> = Single.fromCallable {
        adminAccountMethods.unmarkAccountAsSensitive(withId = withId).execute()
    }
}
