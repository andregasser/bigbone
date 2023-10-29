package social.bigbone

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeParseException

internal val JSON_SERIALIZER: Json = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
    coerceInputValues = true
}

/**
 * Custom [KSerializer] to serialize and deserialize ISO 8601 datetime
 * or date strings into a [PrecisionDateTime] and vice versa.
 *
 * This will attempt to retrieve an [PrecisionDateTime.ExactTime] first.
 * If that fails, it will try with [PrecisionDateTime.StartOfDay].
 * If that _also_ fails, it will return [PrecisionDateTime.Invalid],
 * swallowing any [DateTimeParseException]s in the process.
 */
object DateTimeSerializer : KSerializer<PrecisionDateTime> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("java.time.Instant", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: PrecisionDateTime) = encoder.encodeString(value.asJsonString())

    override fun deserialize(decoder: Decoder): PrecisionDateTime {
        val decodedString = decoder.decodeString()

        return try {
            try {
                parseExactDateTime(decodedString)
            } catch (dateTimeParseException: DateTimeParseException) {
                parseStartOfDay(decodedString)
            }
        } catch (dateTimeParseException: DateTimeParseException) {
            PrecisionDateTime.Invalid(dateTimeParseException)
        }
    }

    /**
     * Attempts to parse an ISO 8601 string as an exact point in time.
     * @param decodedString ISO 8601 string retrieved from JSON
     */
    private fun parseExactDateTime(decodedString: String): PrecisionDateTime.ExactTime =
        PrecisionDateTime.ExactTime(Instant.parse(decodedString))

    /**
     * Attempts to parse an ISO 8601 string into a [LocalDate] and returning an [Instant] at the start of that day in UTC.
     * @param decodedString ISO 8601 string retrieved from JSON
     */
    private fun parseStartOfDay(decodedString: String): PrecisionDateTime.StartOfDay =
        PrecisionDateTime.StartOfDay(LocalDate.parse(decodedString).atStartOfDay().toInstant(ZoneOffset.UTC))
}
