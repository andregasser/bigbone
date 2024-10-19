package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.GroupedNotificationsResults
import social.bigbone.api.entity.NotificationType

/**
 * Allows access to API methods with endpoints having an "api/v2/notifications" prefix.
 *
 * This differs from [NotificationMethods] in that it offers grouping.
 *
 * Grouped notifications were implemented server-side so that:
 * 1. grouping is consistent across clients
 * 2. clients do not run into the issue of going through entire pages that do not
 * contribute to any new group; instead, notifications are already deduplicated server-side
 *
 * The API shape is a bit different from the non-grouped notifications,
 * because large notification groups usually tend to involve the same accounts,
 * and moving accounts to a root key can avoid a lot of duplication,
 * resulting in less server-side work and smaller network payloads.
 *
 * @see <a href="https://docs.joinmastodon.org/methods/grouped_notifications/">Mastodon grouped notifications API methods</a>
 * @since Mastodon 4.3.0
 */
class GroupedNotificationMethods(private val client: MastodonClient) {

    private val endpoint = "api/v2/notifications"

    /**
     * ExpandAccounts type that can be passed for [getAllGroupedNotifications].
     * @see getAllGroupedNotifications
     */
    enum class ExpandAccounts {
        FULL,
        PARTIAL_AVATARS;

        val apiName = this.name.lowercase()
    }

    /**
     * Return grouped notifications concerning the user.
     *
     * Notifications of type favourite, follow or reblog with the same type and the same target
     * made in a similar timeframe are given a same group_key by the server, and querying this
     * endpoint will return aggregated notifications, with only one object per group_key.
     *
     * Other notification types may be grouped in the future.
     * The grouped_types parameter should be used by the client to explicitly list the types it
     * supports showing grouped notifications for.
     *
     * @param includeTypes Types to include in the results.
     * @param excludeTypes Types to exclude from the results.
     * @param accountId Return only notifications received from the specified account.
     * @param expandAccounts One of [ExpandAccounts.FULL] or [ExpandAccounts.PARTIAL_AVATARS].
     * When set to [ExpandAccounts.PARTIAL_AVATARS], some accounts will not be rendered in full
     * in the returned accounts list but will be instead returned in stripped-down form in the
     * partial_accounts list.
     * The most recent account in a notification group is always rendered in full in the accounts attribute.
     * @param groupedTypes Restricts which [NotificationType]s can be grouped.
     * Use this if there are notification types for which your client does not support grouping.
     * If omitted, the server will group notifications of all types it supports
     * (4.3.0: [NotificationType.FAVOURITE], [NotificationType.FOLLOW], [NotificationType.REBLOG]).
     * If you do not want any notification grouping, use [NotificationMethods.getAllNotifications] instead.
     * Notifications that would be grouped if not for this parameter will instead be returned as individual
     * single-notification groups with a unique group_key that can be assumed to be of the form "ungrouped-{notification_id}".
     * @param includeFiltered Whether to include notifications filtered by the user’s NotificationPolicy. Defaults to false.
     * @param range optional Range for the pageable return value.
     * @see <a href="https://docs.joinmastodon.org/methods/grouped_notifications/#get-grouped">
     *     Mastodon API documentation: methods/grouped_notifications/#get-grouped</a>
     * @since Mastodon 4.3.0
     */
    @JvmOverloads
    fun getAllGroupedNotifications(
        includeTypes: List<NotificationType>? = null,
        excludeTypes: List<NotificationType>? = null,
        accountId: String? = null,
        expandAccounts: ExpandAccounts? = null,
        groupedTypes: List<NotificationType>? = null,
        includeFiltered: Boolean = false,
        range: Range = Range()
    ): MastodonRequest<Pageable<GroupedNotificationsResults>> {
        return client.getPageableMastodonRequest(
            endpoint = endpoint,
            method = MastodonClient.Method.GET,
            parameters = range.toParameters().apply {
                includeTypes?.let {
                    append("types", includeTypes.map(NotificationType::apiName))
                }
                excludeTypes?.let {
                    append("exclude_types", excludeTypes.map(NotificationType::apiName))
                }
                accountId?.let { append("account_id", accountId) }
                expandAccounts?.let { append("expand_accounts", expandAccounts.apiName) }
                groupedTypes?.let {
                    append("grouped_types", groupedTypes.map(NotificationType::apiName))
                }
                append("include_filtered", includeFiltered)
            }
        )
    }

    /**
     * View information about a specific notification group with a given group key.
     * @param groupKey The group key of the notification group
     * @see <a href="https://docs.joinmastodon.org/methods/grouped_notifications/#get-notification-group">
     *     Mastodon API documentation: methods/grouped_notifications/#get-notification-group</a>
     * @since Mastodon 4.3.0
     */
    fun getGroupedNotification(groupKey: String): MastodonRequest<GroupedNotificationsResults> {
        return client.getMastodonRequest(
            endpoint = "$endpoint/$groupKey",
            method = MastodonClient.Method.GET
        )
    }
}
