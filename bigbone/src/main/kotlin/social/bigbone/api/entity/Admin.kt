package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime
import social.bigbone.PrecisionDateTime.InvalidPrecisionDateTime

class Admin {

    @Serializable
    data class Measure(
        /**
         * The unique keystring for the requested measure.
         */
        @SerialName("key")
        val key: String = "",

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
        val data: Set<Data>? = null
    ) {
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
}
