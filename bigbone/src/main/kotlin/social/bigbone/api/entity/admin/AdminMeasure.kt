package social.bigbone.api.entity.admin

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime
import social.bigbone.PrecisionDateTime.InvalidPrecisionDateTime

/**
 * Represents quantitative data about the server.
 * @see <a href="https://docs.joinmastodon.org/entities/Admin_Measure/>Mastodon API Admin::Measure documentation</a>
 */
@Serializable
data class AdminMeasure(
    /**
     * The unique keystring for the requested measure.
     */
    @SerialName("key")
    val key: Key? = null,

    /**
     * The units associated with this data item’s value, if applicable.
     */
    @SerialName("unit")
    val unit: String? = null,

    /**
     * The numeric total associated with the requested measure.
     */
    @SerialName("total")
    val total: String = "",

    /**
     * A human-readable formatted value for this data item.
     */
    @SerialName("human_value")
    val humanValue: String? = null,

    /**
     * The numeric total associated with the requested measure, in the previous period.
     *
     * Previous period is calculated by subtracting the start_at and end_at dates,
     * then offsetting both start and end dates backwards by the length of the time period.
     */
    @SerialName("previous_total")
    val previousTotal: String? = null,

    /**
     * The data available for the requested measure, split into daily buckets.
     */
    @SerialName("data")
    val data: List<Data>? = null
) {

    /**
     * The unique keystring for the requested measure.
     */
    @Serializable
    enum class Key {
        /**
         * Total active users on your instance within the time period.
         */
        @SerialName("active_users")
        ACTIVE_USERS,

        /**
         * Users who joined your instance within the time period.
         */
        @SerialName("new_users")
        NEW_USERS,

        /**
         * Total interactions (favourites, boosts, replies) on local statuses within the time period.
         */
        @SerialName("interactions")
        INTERACTIONS,

        /**
         * Total reports filed within the time period.
         */
        @SerialName("opened_reports")
        OPENED_REPORTS,

        /**
         * Total reports resolved within the time period.
         */
        @SerialName("resolved_reports")
        RESOLVED_REPORTS,

        /**
         * Total accounts who used a tag in at least one status within the time period.
         */
        @SerialName("tag_accounts")
        TAG_ACCOUNTS,

        /**
         * Total statuses which used a tag within the time period.
         */
        @SerialName("tag_uses")
        TAG_USES,

        /**
         * Total remote origin servers for statuses which used a tag within the time period.
         */
        @SerialName("tag_servers")
        TAG_SERVERS,

        /**
         * Total accounts originating from a remote domain within the time period.
         */
        @SerialName("instance_accounts")
        INSTANCE_ACCOUNTS,

        /**
         * Total space used by media attachments from a remote domain within the time period.
         */
        @SerialName("instance_media_attachments")
        INSTANCE_MEDIA_ATTACHMENTS,

        /**
         * Total reports filed against accounts from a remote domain within the time period.
         */
        @SerialName("instance_reports")
        INSTANCE_REPORTS,

        /**
         * Total statuses originating from a remote domain within the time period.
         */
        @SerialName("instance_statuses")
        INSTANCE_STATUSES,

        /**
         * Total accounts from a remote domain followed by a local user within the time period.
         */
        @SerialName("instance_follows")
        INSTANCE_FOLLOWS,

        /**
         * Total local accounts followed by accounts from a remote domain within the time period.
         */
        @SerialName("instance_followers")
        INSTANCE_FOLLOWERS;

        @OptIn(ExperimentalSerializationApi::class)
        val apiName: String get() = serializer().descriptor.getElementName(ordinal)
    }

    /**
     * The data available for the requested measure, split into daily buckets.
     */
    @Serializable
    data class Data(
        /**
         * Midnight on the requested day in the time period.
         */
        @SerialName("date")
        @Serializable(with = DateTimeSerializer::class)
        val date: PrecisionDateTime = InvalidPrecisionDateTime.Unavailable,

        /**
         * The numeric value for the requested measure.
         */
        @SerialName("value")
        val value: String? = null
    )
}
