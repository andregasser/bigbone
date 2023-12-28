package social.bigbone.api.entity.admin

import social.bigbone.api.method.admin.AdminAccountMethods
import java.util.Locale

/**
 * Filter that can be used when viewing all accounts via [AdminAccountMethods.viewAccounts].
 * Filters for accounts that have one of the available status types of this class.
 */
enum class AccountStatus {

    Active,
    Pending,
    Disabled,
    Silenced,
    Suspended;

    fun apiName(): String = name.lowercase(Locale.ENGLISH)
}
