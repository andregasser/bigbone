package social.bigbone.api.entity

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Appeal against a moderation action.
 * @see <a href="https://docs.joinmastodon.org/entities/Appeal/">entities/Appeal</a>
 * @since Mastodon 4.3.0
 */
@Serializable
data class Appeal(
    /**
     * Text of the appeal from the moderated account to the moderators.
     * @since Mastodon 4.3.0
     */
    @SerialName("text")
    val text: String = "",

    /**
     * State of the appeal.
     * @since Mastodon 4.3.0
     */
    @SerialName("state")
    val state: State = State.PENDING
) {
    /**
     * State an appeal can have.
     */
    @Serializable
    enum class State {
        /**
         * The appeal has been approved by a moderator.
         */
        @SerialName("APPROVED")
        APPROVED,

        /**
         * The appeal has been rejected by a moderator.
         */
        @SerialName("REJECTED")
        REJECTED,

        /**
         * The appeal has been submitted, but neither approved nor rejected yet.
         */
        @SerialName("PENDING")
        PENDING;

        @OptIn(ExperimentalSerializationApi::class)
        val apiName: String get() = serializer().descriptor.getElementName(ordinal)
    }
}
