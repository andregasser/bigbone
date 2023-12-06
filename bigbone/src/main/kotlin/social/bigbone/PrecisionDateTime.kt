package social.bigbone

import social.bigbone.PrecisionDateTime.InvalidPrecisionDateTime.Invalid
import social.bigbone.PrecisionDateTime.InvalidPrecisionDateTime.Unavailable
import social.bigbone.PrecisionDateTime.ValidPrecisionDateTime.ExactTime
import social.bigbone.PrecisionDateTime.ValidPrecisionDateTime.StartOfDay
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeParseException

/**
 * Sealed interface with implementing classes that represent a moment in UTC with varying degrees of precision.
 * Depending on API endpoint and entity we get from the Mastodon API, dates can vary between (sub-)second precision to
 * day precision. On some, we might even be unable to parse a date at all.
 */
sealed interface PrecisionDateTime {
    fun asJsonString(): String

    /**
     * Gets the most precise [Instant] possible for this subtype of [PrecisionDateTime] or null in case it represents
     * an invalid or unavailable instant in time.
     *
     * This can be useful for cases where you may want to sort multiple entities that have a [PrecisionDateTime] property
     * but want to make sure that you only get those variants that are "valid". You wouldn't want to sort, e.g., by
     * oldest account and then get all the accounts with an invalid creation date at the start just because we decided
     * for you that a good fallback would be [Instant.EPOCH] or any other date time.
     * If you do want that, choose [mostPreciseOrFallback] instead.
     */
    fun mostPreciseInstantOrNull(): Instant?

    /**
     * Convenience method to find out if this [PrecisionDateTime] represents an instance in time.
     */
    fun isValid() = this is ValidPrecisionDateTime

    /**
     * Gets the most precise [Instant] possible for this subtype of [PrecisionDateTime] or falls back to [fallback] in
     * case it represents an invalid or unavailable instant in time.
     *
     * This can be useful for cases where you may want to e.g. show the creation date and use your own fallback. It's
     * merely a convenience function on top of [mostPreciseInstantOrNull].
     */
    fun mostPreciseOrFallback(fallback: Instant): Instant = mostPreciseInstantOrNull() ?: fallback

    /**
     * Represents a "valid" date or date and time, meaning: Subtypes of this class have an [Instant] set by the Mastodon
     * instance this was retrieved from as they are either [ExactTime] or [StartOfDay].
     */
    sealed class ValidPrecisionDateTime(open val instant: Instant) :
        PrecisionDateTime,
        Comparable<ValidPrecisionDateTime> {
        override fun mostPreciseInstantOrNull(): Instant = instant

        override fun compareTo(other: ValidPrecisionDateTime): Int = this.instant.compareTo(other.instant)

        /**
         * Type used when a date property specifies both date and time.
         */
        data class ExactTime(override val instant: Instant) : ValidPrecisionDateTime(instant) {
            override fun asJsonString(): String = instant.toString()
        }

        /**
         * Type used when a date property only specifies a date, but no time.
         */
        data class StartOfDay(override val instant: Instant) : ValidPrecisionDateTime(instant) {
            override fun asJsonString(): String = instant.atZone(ZoneOffset.UTC).toLocalDate().toString()
        }
    }

    /**
     * Represents an "invalid" datetime, meaning: Subtypes of this class do _not_ have an [Instant] set by the Mastodon
     * instance this was retrieved from as they are either [Unavailable] or [Invalid].
     */
    sealed interface InvalidPrecisionDateTime : PrecisionDateTime, Comparable<InvalidPrecisionDateTime> {
        override fun asJsonString(): String = ""

        override fun mostPreciseInstantOrNull(): Instant? = null

        override fun compareTo(other: InvalidPrecisionDateTime): Int = 0

        /**
         * Type used when we encountered an error when attempting to parse a date.
         */
        @JvmInline
        value class Invalid(val parseException: DateTimeParseException) : InvalidPrecisionDateTime

        /**
         * Type used when a date property is null or otherwise unavailable.
         */
        data object Unavailable : InvalidPrecisionDateTime
    }
}
