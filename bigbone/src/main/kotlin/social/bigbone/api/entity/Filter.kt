package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a user-defined filter for determining which statuses should not be shown to the user.
 * @see <a href="https://docs.joinmastodon.org/entities/Filter/">Mastodon API Filter</a>
 */
@Serializable
data class Filter(
    /**
     * The ID of the Filter in the database.
     */
    @SerialName("id")
    val id: String = "0",

    /**
     * A title given by the user to name the filter.
     */
    @SerialName("title")
    val title: String = "",

    /**
     * The contexts in which the filter should be applied.
     * @see Context
     */
    @SerialName("context")
    val context: List<String> = emptyList(),

    /**
     * When the filter should no longer be applied.
     */
    @SerialName("expires_at")
    val expiresAt: String? = null,

    /**
     * The action to be taken when a status matches this filter.
     * @see Action
     */
    @SerialName("filter_action")
    val filterAction: String = Action.Warn.value,

    /**
     * The keywords grouped under this filter.
     */
    @SerialName("keywords")
    val keywords: List<FilterKeyword> = emptyList(),

    /**
     * The statuses grouped under this filter.
     */
    @SerialName("statuses")
    val statuses: List<FilterStatus> = emptyList()
) {
    /**
     * The action to be taken when a status matches this filter.
     */
    enum class Action(val value: String) {
        /**
         * Do not show this status if it is received.
         */
        Hide("hide"),
        /**
         * Show a warning that identifies the matching filter by title, and allow the user to expand the filtered status.
         * This is the default (and unknown values should be treated as equivalent to warn).
         */
        Warn("warn")
    }

    /**
     * The context(s) in which the filter should be applied.
     */
    enum class Context(val value: String) {
        /**
         * Apply filter when viewing a profile.
         */
        Account("account"),

        /**
         * Apply filter when viewing home timeline and lists.
         */
        Home("home"),

        /**
         * Apply filter when viewing notifications timeline.
         */
        Notifications("notifications"),

        /**
         * Apply filter when viewing public timelines.
         */
        Public("public"),

        /**
         * Apply filter when viewing expanded thread of a detailed status.
         */
        Thread("thread")
    }
}
