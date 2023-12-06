package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.entity.Notification

object GetNotifications {
    /**
     * Get all notifications for the account having the access token supplied via [args].
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

        // Get notifications
        val notifications: Pageable<Notification> = client.notifications.getAllNotifications(
            includeTypes = includeTypes.explodeToNotificationTypes(),
            excludeTypes = excludeTypes.explodeToNotificationTypes(),
            accountId = accountId
        ).execute()

        notifications.part.forEach(::println)
    }

    private fun String.explodeToNotificationTypes(): List<Notification.NotificationType>? {
        return split(",")
            .mapNotNull { it.toNotificationType() }
            .takeIf { it.isNotEmpty() }
    }

    private fun String.toNotificationType(): Notification.NotificationType? {
        for (notificationType in Notification.NotificationType.entries) {
            if (notificationType.apiName.equals(this.trim(), ignoreCase = true)) return notificationType
        }
        return null
    }
}
