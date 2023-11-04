package social.bigbone.api.entity.admin

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime

/**
 * Represents a retention metric.
 * @see <a href="https://docs.joinmastodon.org/entities/Admin_Cohort/">Mastodon documentation Admin::Cohort</a>
 *
 */
@Serializable
data class AdminCohort(

    /**
     * The timestamp for the start of the period, at midnight.
     */
    @SerialName("period")
    @Serializable(with = DateTimeSerializer::class)
    val period: PrecisionDateTime = PrecisionDateTime.InvalidPrecisionDateTime.Unavailable,

    /**
     * The size of the bucket for the returned data.
     */
    @SerialName("frequency")
    val frequency: FrequencyOneOf? = null,

    /**
     * Retention data for users who registered during the given period.
     */
    @SerialName("data")
    val data: List<CohortData>? = null
) {
    /**
     * The size of the bucket for the returned data.
     */
    @Serializable
    enum class FrequencyOneOf {
        /**
         * Daily buckets.
         */
        @SerialName("day")
        DAY,

        /**
         * Monthly buckets.
         */
        @SerialName("month")
        MONTH;

        @OptIn(ExperimentalSerializationApi::class)
        val apiName: String get() = serializer().descriptor.getElementName(ordinal)
    }

    /**
     * Retention data for users who registered during the given period.
     */
    @Serializable
    data class CohortData(
        /**
         * The timestamp for the start of the bucket, at midnight.
         */
        @SerialName("date")
        @Serializable(with = DateTimeSerializer::class)
        val date: PrecisionDateTime = PrecisionDateTime.InvalidPrecisionDateTime.Unavailable,

        /**
         * The percentage rate of users who registered in the specified period and were active for the given date bucket.
         */
        @SerialName("rate")
        val rate: Double? = null,

        /**
         * How many users registered in the specified period and were active for the given date bucket.
         */
        @SerialName("value")
        val value: String? = null
    )
}
