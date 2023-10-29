package social.bigbone

import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeParseException

class InstantSerializerTest {

    @Test
    fun `Given a LocalDate string, when decoding from string, then return Instant at start of day for that date in UTC`() {
        val jsonString = "\"2022-05-03\""

        val decodedInstant: Instant = JSON_SERIALIZER.decodeFromString(InstantSerializer, jsonString)

        decodedInstant shouldBeEqualTo LocalDateTime.of(
            LocalDate.of(2022, 5, 3),
            LocalTime.of(0, 0, 0, 0)
        ).toInstant(ZoneOffset.UTC)
    }

    @Test
    fun `Given an ISO 8601 date time string, when decoding from string, then return date parsed as Instant`() {
        val jsonString = "\"2022-05-03T13:37:42Z\""

        val decodedInstant: Instant = JSON_SERIALIZER.decodeFromString(InstantSerializer, jsonString)

        decodedInstant shouldBeEqualTo LocalDateTime.of(
            LocalDate.of(2022, 5, 3),
            LocalTime.of(13, 37, 42, 0)
        ).toInstant(ZoneOffset.UTC)
    }

    @Test
    fun `Given an ISO 8601 date time string with milliseconds, when decoding from string, then return date parsed as Instant`() {
        val jsonString = "\"2019-06-12T18:55:12.053Z\""

        val decodedInstant: Instant = JSON_SERIALIZER.decodeFromString(InstantSerializer, jsonString)

        decodedInstant shouldBeEqualTo LocalDateTime.of(
            LocalDate.of(2019, 6, 12),
            LocalTime.of(18, 55, 12, 53_000_000)
        ).toInstant(ZoneOffset.UTC)
    }

    @Test
    fun `Given an invalid string containing only a time, when decoding from string, then throw exception`() {
        val jsonString = "\"18:55:12.053Z\""

        invoking {
            JSON_SERIALIZER.decodeFromString(InstantSerializer, jsonString)
        } shouldThrow DateTimeParseException::class
    }

    @Test
    fun `Given an Instant, when encoding into JSON string then return expected JSON string, when decoding back to Instant then expect input Instant`() {
        val instant = ZonedDateTime.of(
            LocalDateTime.of(2023, 10, 31, 13, 37, 42),
            ZoneOffset.UTC
        ).toInstant()

        val encodedInstant = JSON_SERIALIZER.encodeToString(InstantSerializer, instant)

        encodedInstant shouldBeEqualTo "\"2023-10-31T13:37:42Z\""

        val decodedInstant = JSON_SERIALIZER.decodeFromString(InstantSerializer, encodedInstant)

        decodedInstant shouldBeEqualTo instant
    }
}
