package social.bigbone.api.entity

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Specifies the notification type.
 */
@Serializable
enum class NotificationType {

    /**
     * A new report has been filed.
     * @since Mastodon 4.0.0
     */
    @SerialName("admin.report")
    ADMIN_REPORT,

    /**
     * Someone signed up (optionally sent to admins).
     * @since Mastodon 3.5.0
     */
    @SerialName("admin.sign_up")
    ADMIN_SIGN_UP,

    /**
     * Someone favourited one of your statuses.
     * @since Mastodon 0.9.9
     */
    @SerialName("favourite")
    FAVOURITE,

    /**
     * Someone followed you.
     * @since Mastodon 0.9.9
     */
    @SerialName("follow")
    FOLLOW,

    /**
     * Someone requested to follow you.
     * @since Mastodon 3.1.0
     */
    @SerialName("follow_request")
    FOLLOW_REQUEST,

    /**
     * Someone mentioned you in their status.
     * @since Mastodon 0.9.9
     */
    @SerialName("mention")
    MENTION,

    /**
     * A moderator has taken action against your account or has sent you a warning.
     * @since Mastodon 4.3.0
     */
    @SerialName("moderation_warning")
    MODERATION_WARNING,

    /**
     * A poll you have voted in or created has ended.
     * @since Mastodon 2.8.0
     */
    @SerialName("poll")
    POLL,

    /**
     * Someone boosted one of your statuses.
     * @since Mastodon 0.9.9
     */
    @SerialName("reblog")
    REBLOG,

    /**
     * Some of your follow relationships have been severed as a result of a moderation or block event.
     * @since Mastodon 4.3.0
     */
    @SerialName("severed_relationships")
    SEVERED_RELATIONSHIPS,

    /**
     * Someone you enabled notifications for has posted a status.
     * @since Mastodon 3.3.0
     */
    @SerialName("status")
    STATUS,

    /**
     * A status you interacted with has been edited.
     * @since Mastodon 3.5.0
     */
    @SerialName("update")
    UPDATE;

    @OptIn(ExperimentalSerializationApi::class)
    val apiName: String get() = serializer().descriptor.getElementName(ordinal)
}
