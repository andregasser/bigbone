package social.bigbone

import java.time.Instant
import java.time.format.DateTimeParseException

/**
 * Sealed interface with implementing classes that represent a moment in UTC with varying degrees of precision.
 * Depending on API endpoint and entity we get from the Mastodon API, dates can vary between (sub-)second precision to
 * day precision. On some, we might even be unable to parse a date at all.
 */
sealed interface PrecisionDateTime {

    fun asJsonString(): String

    /**
     * Type used when a date property specifies both date and time.
     */
    @JvmInline
    value class ExactTime(val instant: Instant) : PrecisionDateTime {
        override fun asJsonString(): String = instant.toString()
    }

    /**
     * Type used when a date property only specifies a date, but no time.
     */
    @JvmInline
    value class StartOfDay(val instant: Instant) : PrecisionDateTime {
        override fun asJsonString(): String = instant.toString()
    }

    /**
     * Type used when we encountered an error when attempting to parse a date.
     * This differs from [Unavailable] as [Invalid] will only be returned if a date
     * property was null or otherwise unavailable.
     */
    @JvmInline
    value class Invalid(val parseException: DateTimeParseException) : PrecisionDateTime {
        override fun asJsonString(): String = ""
    }

    /**
     * Type used when a date property is null or otherwise unavailable.
     */
    data object Unavailable : PrecisionDateTime {
        override fun asJsonString(): String = ""
    }
}
