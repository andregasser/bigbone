package social.bigbone.api.entity.admin

import social.bigbone.api.method.admin.AdminAccountMethods
import java.util.Locale

/**
 * Defines the type of action to be taken against an account when using [AdminAccountMethods.performActionAgainstAccount].
 */
enum class ActionAgainstAccount {

    None,
    Sensitive,
    Disable,
    Silence,
    Suspend;

    fun apiName(): String = name.lowercase(Locale.ENGLISH)
}
