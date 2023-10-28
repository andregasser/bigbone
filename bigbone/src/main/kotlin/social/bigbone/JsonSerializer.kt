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
 * Custom [KSerializer] to serialize and deserialize ISO 8601 datetime or date strings into an [Instant] and vice versa.
 */
object InstantSerializer : KSerializer<Instant> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("java.time.Instant", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: Instant) = encoder.encodeString(value.toString())
    override fun deserialize(decoder: Decoder): Instant {
        val decodedString = decoder.decodeString()
        return try {
            // Attempt to deserialize ISO 8601 date time strings first
            Instant.parse(decodedString)
        } catch (exception: DateTimeParseException) {
            // If that didn't work, try again with a LocalDate string only. Some properties return ISO 8601 date time
            // strings for one HTTP endpoint, but an ISO 8601 date only string for other HTTP endpoints with the same
            // type. Should this fail again, this will implicitly throw a new DateTimeParseException again.
            val localDate = LocalDate.parse(decodedString)
            return localDate.atStartOfDay().toInstant(ZoneOffset.UTC)
        }
    }
}
