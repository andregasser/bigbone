package social.bigbone.api.entity

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime
import social.bigbone.PrecisionDateTime.InvalidPrecisionDateTime

/**
 * Summary of a moderation or block event that caused follow relationships to be severed.
 * @see <a href="https://docs.joinmastodon.org/entities/RelationshipSeveranceEvent/">entities/RelationshipSeveranceEvent</a>
 * @since Mastodon 4.3.0
 */
@Serializable
data class RelationshipSeveranceEvent(
    /**
     * The ID of the relationship severance event in the database.
     * Type: String (cast from integer)
     * @since Mastodon 4.3.0
     */
    @SerialName("id")
    val id: String = "",

    /**
     * Type of event.
     * @since Mastodon 4.3.0
     */
    @SerialName("type")
    val type: Type? = null,

    /**
     * Whether the list of severed relationships is unavailable because the underlying issue has been purged.
     * @since Mastodon 4.3.0
     */
    @SerialName("purged")
    val purged: Boolean = false,

    /**
     * Name of the target of the moderation/block event.
     * This is either a domain name or a user handle, depending on the event type.
     * @since Mastodon 4.3.0
     */
    @SerialName("target_name")
    val targetName: String = "",

    /**
     * Number of follow relationships (in either direction) that were severed.
     * @since Mastodon 4.3.0
     */
    @SerialName("relationships_count")
    val relationshipsCount: Int? = null,

    /**
     * When the event took place.
     * @since Mastodon 4.3.0
     */
    @Serializable(with = DateTimeSerializer::class)
    @SerialName("created_at")
    val createdAt: PrecisionDateTime = InvalidPrecisionDateTime.Unavailable,
) {
    /**
     * Type of RelationshipSeveranceEvent.
     */
    @Serializable
    enum class Type {
        /**
         * A moderator suspended a whole domain.
         * @since Mastodon 4.3.0
         */
        @SerialName("domain_block")
        DOMAIN_BLOCK,

        /**
         * The user blocked a whole domain.
         * @since Mastodon 4.3.0
         */
        @SerialName("user_domain_block")
        USER_DOMAIN_BLOCK,

        /**
         * A moderator suspended a specific account.
         * @since Mastodon 4.3.0
         */
        @SerialName("account_suspension")
        ACCOUNT_SUSPENSION;

        @OptIn(ExperimentalSerializationApi::class)
        val apiName: String get() = serializer().descriptor.getElementName(ordinal)
    }
}
