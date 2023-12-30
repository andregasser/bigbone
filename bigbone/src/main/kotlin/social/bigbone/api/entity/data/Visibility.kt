package social.bigbone.api.entity.data

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Specifies the visibility of the status post.
 */
@Serializable
enum class Visibility {
    @SerialName("public")
    PUBLIC,

    @SerialName("unlisted")
    UNLISTED,

    @SerialName("private")
    PRIVATE,

    @SerialName("direct")
    DIRECT;

    @OptIn(ExperimentalSerializationApi::class)
    val apiName: String get() = serializer().descriptor.getElementName(ordinal)
}
