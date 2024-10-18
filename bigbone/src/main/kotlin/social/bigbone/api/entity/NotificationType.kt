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
     * @since Mastodon 4.0.0
     */
    @SerialName("admin.report")
    ADMIN_REPORT,

    /**
     * @since Mastodon 3.5.0
     */
    @SerialName("admin.sign_up")
    ADMIN_SIGN_UP,

    /**
     * @since Mastodon 0.9.9
     */
    @SerialName("favourite")
    FAVOURITE,

    /**
     * @since Mastodon 0.9.9
     */
    @SerialName("follow")
    FOLLOW,

    /**
     * @since Mastodon 3.1.0
     */
    @SerialName("follow_request")
    FOLLOW_REQUEST,

    /**
     * @since Mastodon 0.9.9
     */
    @SerialName("mention")
    MENTION,

    /**
     * @since Mastodon 4.3.0
     */
    @SerialName("moderation_warning")
    MODERATION_WARNING,

    /**
     * @since Mastodon 2.8.0
     */
    @SerialName("poll")
    POLL,

    /**
     * @since Mastodon 0.9.9
     */
    @SerialName("reblog")
    REBLOG,

    /**
     * @since Mastodon 4.3.0
     */
    @SerialName("severed_relationships")
    SEVERED_RELATIONSHIPS,

    /**
     * @since Mastodon 3.3.0
     */
    @SerialName("status")
    STATUS,

    /**
     * @since Mastodon 3.5.0
     */
    @SerialName("update")
    UPDATE;

    @OptIn(ExperimentalSerializationApi::class)
    val apiName: String get() = serializer().descriptor.getElementName(ordinal)
}
