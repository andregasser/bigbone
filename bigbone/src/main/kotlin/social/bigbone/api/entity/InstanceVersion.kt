package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * A minimalistic version of the Instance entity just holding the version field.
 * This class is not exposed to the library user and only used during client instantiation.
 * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
 */
internal data class InstanceVersion(
    /**
     * The version of Mastodon installed on the instance.
     */
    @SerializedName("version")
    val version: String? = null
)
