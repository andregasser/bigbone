package social.bigbone.api.entity.data

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.api.entity.Report
import social.bigbone.api.entity.admin.AdminReport

/**
 * The category under which a [Report] (or [AdminReport]) is classified.
 */
@Serializable
enum class ReportCategory {
    @SerialName("spam")
    SPAM,

    @SerialName("violation")
    VIOLATION,

    @SerialName("other")
    OTHER;

    @OptIn(ExperimentalSerializationApi::class)
    val apiName: String get() = serializer().descriptor.getElementName(ordinal)
}
