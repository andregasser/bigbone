package social.bigbone.api.entity.admin

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents qualitative data about the server.
 * @see <a href="https://docs.joinmastodon.org/entities/Admin_Dimension/">Mastodon API Admin::Dimension documentation</a>
 */
@Serializable
data class AdminDimension(

    /**
     * The unique keystring for the requested dimension.
     */
    @SerialName("key")
    val key: Key? = null,

    /**
     * The data available for the requested dimension.
     */
    @SerialName("data")
    val data: List<Data>? = null
) {

    /**
     * The unique keystring for the requested dimension.
     */
    @Serializable
    enum class Key {

        /**
         * Most-used languages on this server.
         */
        @SerialName("languages")
        LANGUAGES,

        /**
         * Most-used client apps on this server.
         */
        @SerialName("sources")
        SOURCES,

        /**
         * Remote servers with the most statuses.
         */
        @SerialName("servers")
        SERVERS,

        /**
         * How much space is used by your software stack.
         */
        @SerialName("space_usage")
        SPACE_USAGE,

        /**
         * The version numbers for your software stack.
         */
        @SerialName("software_versions")
        SOFTWARE_VERSIONS,

        /**
         * Most-common servers for statuses including a trending tag.
         */
        @SerialName("tag_servers")
        TAG_SERVERS,

        /**
         * Most-used languages for statuses including a trending tag.
         */
        @SerialName("tag_languages")
        TAG_LANGUAGES,

        /**
         * Most-followed accounts from a remote server.
         */
        @SerialName("instance_accounts")
        INSTANCE_ACCOUNTS,

        /**
         * Most-used languages from a remote server.
         */
        @SerialName("instance_languages")
        INSTANCE_LANGUAGES;

        @OptIn(ExperimentalSerializationApi::class)
        val apiName: String get() = Key.serializer().descriptor.getElementName(ordinal)
    }

    /**
     * The data available for the requested dimension.
     */
    @Serializable
    data class Data(
        /**
         * The unique keystring for this data item.
         */
        @SerialName("key")
        val key: String? = null,

        /**
         * A human-readable key for this data item.
         */
        @SerialName("human_key")
        val humanKey: String? = null,

        /**
         * The value for this data item.
         */
        @SerialName("value")
        val value: String? = null,

        /**
         * The units associated with this data item’s value, if applicable.
         */
        @SerialName("unit")
        val unit: String? = null,

        /**
         * A human-readable formatted value for this data item.
         */
        @SerialName("human_value")
        val humanValue: String? = null
    )
}
