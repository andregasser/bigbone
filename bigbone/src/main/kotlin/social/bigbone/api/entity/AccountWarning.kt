package social.bigbone.api.entity

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime
import social.bigbone.PrecisionDateTime.InvalidPrecisionDateTime
import social.bigbone.api.entity.AccountWarning.Action

/**
 * Moderation warning against a particular account.
 * @see <a href="https://docs.joinmastodon.org/entities/AccountWarning/">entities/AccountWarning</a>
 * @since Mastodon 4.3.0
 */
@Serializable
data class AccountWarning(
    /**
     * The ID of the account warning in the database.
     * Type: String (cast from integer)
     * @since Mastodon 4.3.0
     */
    @SerialName("id")
    val id: String = "",

    /**
     * Action taken against the account.
     * @since Mastodon 4.3.0
     */
    @SerialName("action")
    val action: Action? = null,

    /**
     * Message from the moderator to the target account.
     * @since Mastodon 4.3.0
     */
    @SerialName("text")
    val text: String = "",

    /**
     * List of status IDs that are relevant to the warning.
     * When [action] is
     * * [Action.MARK_STATUSES_AS_SENSITIVE] OR
     * * [Action.DELETE_STATUSES],
     * those are the affected statuses.
     * @since Mastodon 4.3.0
     */
    @SerialName("status_ids")
    val statusIds: List<String>? = null,

    /**
     * Account against which a moderation decision has been taken.
     * @since Mastodon 4.3.0
     */
    @SerialName("target_account")
    val targetAccount: Account? = null,

    /**
     * Appeal submitted by the target account, if any.
     * @since Mastodon 4.3.0
     */
    @SerialName("appeal")
    val appeal: Appeal? = null,

    /**
     * When the event took place.
     * @since Mastodon 4.3.0
     */
    @Serializable(with = DateTimeSerializer::class)
    @SerialName("created_at")
    val createdAt: PrecisionDateTime = InvalidPrecisionDateTime.Unavailable,
) {
    /**
     * Action that can be taken against an account.
     */
    @Serializable
    enum class Action {
        /**
         * No action was taken, this is simply a warning.
         * @since Mastodon 4.3.0
         */
        @SerialName("none")
        NONE,

        /**
         * The account has been disabled.
         * @since Mastodon 4.3.0
         */
        @SerialName("disable")
        DISABLE,

        /**
         * Specific posts from the target account have been marked as sensitive.
         * @since Mastodon 4.3.0
         */
        @SerialName("mark_statuses_as_sensitive")
        MARK_STATUSES_AS_SENSITIVE,

        /**
         * Specific statuses from the target account have been deleted.
         * @since Mastodon 4.3.0
         */
        @SerialName("delete_statuses")
        DELETE_STATUSES,

        /**
         * All posts from the target account are marked as sensitive.
         * @since Mastodon 4.3.0
         */
        @SerialName("sensitive")
        SENSITIVE,

        /**
         * The target account has been limited.
         * @since Mastodon 4.3.0
         */
        @SerialName("silence")
        SILENCE,

        /**
         * The target account has been suspended.
         * @since Mastodon 4.3.0
         */
        @SerialName("suspend")
        SUSPEND;

        @OptIn(ExperimentalSerializationApi::class)
        val apiName: String get() = serializer().descriptor.getElementName(ordinal)
    }
}
