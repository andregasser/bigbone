package social.bigbone.api.entity.admin

import social.bigbone.api.method.admin.AdminAccountMethods
import java.util.Locale

/**
 * Filter that can be used when viewing all accounts via [AdminAccountMethods.viewAccounts].
 * Filters for accounts that are either only local or only remote.
 */
enum class AccountOrigin {

    Local,
    Remote;

    fun apiName(): String = name.lowercase(Locale.ENGLISH)
}
