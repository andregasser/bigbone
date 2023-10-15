package social.bigbone.api.entity.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A minimalistic version of the Instance entity just holding the version field.
 * This class is not exposed to the library user and only used during client instantiation.
 * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
 */
@Serializable
internal data class InstanceVersion(
    /**
     * The version of Mastodon installed on the instance.
     */
    @SerialName("version")
    val version: String? = null
)
