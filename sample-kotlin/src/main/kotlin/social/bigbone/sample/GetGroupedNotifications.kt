package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.GroupedNotificationsResults
import social.bigbone.api.entity.NotificationType

object GetGroupedNotifications {

    /**
     * Get all grouped notifications for the account having the access token supplied via [args].
     *
     * Example call: "mastodon.social" "$TOKEN" "favourite,mention" "poll,reblog" "$ACCOUNT_ID"
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val instance: String = args[0]
        val accessToken: String = args[1]
        val includeTypes: String = args.getOrElse(2) { "" } // Comma-separated
        val excludeTypes: String = args.getOrElse(3) { "" } // Comma-separated
        val accountId: String? = args.getOrNull(4)

        // Instantiate client
        val client = MastodonClient.Builder(instance)
            .accessToken(accessToken)
            .build()

        // Get grouped notifications
        val firstPart: Pageable<GroupedNotificationsResults> = client.groupedNotifications.getAllGroupedNotifications(
            includeTypes = includeTypes.explodeToNotificationTypes(),
            excludeTypes = excludeTypes.explodeToNotificationTypes(),
            accountId = accountId,
            range = Range(limit = 2)
        ).execute()

        println("Part 1: ${firstPart.part.first()}")

        val secondPart: Pageable<GroupedNotificationsResults> = client.groupedNotifications.getAllGroupedNotifications(
            includeTypes = includeTypes.explodeToNotificationTypes(),
            excludeTypes = excludeTypes.explodeToNotificationTypes(),
            accountId = accountId,
            range = firstPart.nextRange(limit = 2)
        ).execute()

        println("Part 2: ${secondPart.part.first()}")
    }

    private fun String.explodeToNotificationTypes(): List<NotificationType>? {
        return split(",")
            .mapNotNull { it.toNotificationType() }
            .takeIf { it.isNotEmpty() }
    }

    private fun String.toNotificationType(): NotificationType? {
        for (notificationType in NotificationType.entries) {
            if (notificationType.apiName.equals(this.trim(), ignoreCase = true)) return notificationType
        }
        return null
    }
}
