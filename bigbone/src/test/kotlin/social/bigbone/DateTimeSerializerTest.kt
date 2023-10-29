package social.bigbone

import kotlinx.serialization.encodeToString
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import social.bigbone.api.entity.Account
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeParseException

class DateTimeSerializerTest {

    @Test
    fun `Given a LocalDate string, when decoding from string, then return Instant at start of day for that date in UTC`() {
        val jsonString = "\"2022-05-03\""

        val decodedDateTime: PrecisionDateTime = JSON_SERIALIZER.decodeFromString(DateTimeSerializer, jsonString)

        decodedDateTime shouldBeInstanceOf PrecisionDateTime.ValidPrecisionDateTime.StartOfDay::class
        (decodedDateTime as PrecisionDateTime.ValidPrecisionDateTime.StartOfDay).instant shouldBeEqualTo LocalDateTime.of(
            LocalDate.of(2022, 5, 3),
            LocalTime.of(0, 0, 0, 0)
        ).toInstant(ZoneOffset.UTC)
    }

    @Test
    fun `Given an ISO 8601 date time string, when decoding from string, then return date parsed as Instant`() {
        val jsonString = "\"2022-05-03T13:37:42Z\""

        val decodedDateTime: PrecisionDateTime = JSON_SERIALIZER.decodeFromString(DateTimeSerializer, jsonString)

        decodedDateTime shouldBeInstanceOf PrecisionDateTime.ValidPrecisionDateTime.ExactTime::class
        (decodedDateTime as PrecisionDateTime.ValidPrecisionDateTime.ExactTime).instant shouldBeEqualTo LocalDateTime.of(
            LocalDate.of(2022, 5, 3),
            LocalTime.of(13, 37, 42, 0)
        ).toInstant(ZoneOffset.UTC)
    }

    @Test
    fun `Given an ISO 8601 date time string with milliseconds, when decoding from string, then return date parsed as Instant`() {
        val jsonString = "\"2019-06-12T18:55:12.053Z\""

        val decodedDateTime: PrecisionDateTime = JSON_SERIALIZER.decodeFromString(DateTimeSerializer, jsonString)

        decodedDateTime shouldBeInstanceOf PrecisionDateTime.ValidPrecisionDateTime.ExactTime::class
        (decodedDateTime as PrecisionDateTime.ValidPrecisionDateTime.ExactTime).instant shouldBeEqualTo LocalDateTime.of(
            LocalDate.of(2019, 6, 12),
            LocalTime.of(18, 55, 12, 53_000_000)
        ).toInstant(ZoneOffset.UTC)
    }

    @Test
    fun `Given an invalid string containing only a time, when decoding from string, then throw exception`() {
        val jsonString = "\"18:55:12.053Z\""

        val decodedDateTime: PrecisionDateTime = JSON_SERIALIZER.decodeFromString(DateTimeSerializer, jsonString)

        decodedDateTime shouldBeInstanceOf PrecisionDateTime.InvalidPrecisionDateTime.Invalid::class
        (decodedDateTime as PrecisionDateTime.InvalidPrecisionDateTime.Invalid).parseException shouldBeInstanceOf DateTimeParseException::class
    }

    @Test
    fun `Given an ExactTime, when encoding into JSON string then return expected JSON string, when decoding back to Instant then expect input Instant`() {
        val instant = ZonedDateTime.of(
            LocalDateTime.of(2023, 10, 31, 13, 37, 42),
            ZoneOffset.UTC
        ).toInstant()
        val exactTime = PrecisionDateTime.ValidPrecisionDateTime.ExactTime(instant)

        val encodedInstant = JSON_SERIALIZER.encodeToString(DateTimeSerializer, exactTime)

        encodedInstant shouldBeEqualTo "\"2023-10-31T13:37:42Z\""

        val decodedDateTime = JSON_SERIALIZER.decodeFromString(DateTimeSerializer, encodedInstant)

        decodedDateTime shouldBeEqualTo exactTime
    }

    @Test
    fun `Given a StartOfDay, when encoding into JSON string then return expected JSON string, when decoding back to Instant then expect input Instant`() {
        val instant = LocalDate.of(2023, 10, 31).atStartOfDay().toInstant(ZoneOffset.UTC)
        val exactTime = PrecisionDateTime.ValidPrecisionDateTime.StartOfDay(instant)

        val encodedInstant = JSON_SERIALIZER.encodeToString(DateTimeSerializer, exactTime)

        encodedInstant shouldBeEqualTo "\"2023-10-31\""

        val decodedDateTime = JSON_SERIALIZER.decodeFromString(DateTimeSerializer, encodedInstant)

        decodedDateTime shouldBeEqualTo exactTime
    }

    @Test
    fun `Given an Account with Unavailable createdAt, when encoding into JSON string then return expected JSON string with created_at as null`() {
        val account = Account(createdAt = PrecisionDateTime.InvalidPrecisionDateTime.Unavailable)

        val encodedInstant = JSON_SERIALIZER.encodeToString(account)

        encodedInstant shouldBeEqualTo """
            {"id":"0","username":"","acct":"","url":"","display_name":"","note":"","avatar":"","avatar_static":"","header":"","header_static":"","locked":false,"fields":[],"emojis":[],"bot":false,"group":false,"discoverable":null,"noindex":null,"moved":null,"suspended":null,"limited":null,"created_at":null,"last_status_at":null,"statuses_count":0,"followers_count":0,"following_count":0}
        """.trimIndent()
    }
}
