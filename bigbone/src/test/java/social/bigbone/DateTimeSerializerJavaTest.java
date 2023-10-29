package social.bigbone;

import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import social.bigbone.PrecisionDateTime.ValidPrecisionDateTime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static social.bigbone.JsonSerializerKt.getJSON_SERIALIZER;

public class DateTimeSerializerJavaTest {

    @Test
    void localDateStringToStartOfDay() {
        @Language(value = "json", prefix = "", suffix = "") String jsonString = "\"2022-05-03\"";

        PrecisionDateTime decodedDateTime = getJSON_SERIALIZER().decodeFromString(DateTimeSerializer.INSTANCE, jsonString);
        Assertions.assertInstanceOf(ValidPrecisionDateTime.ValidPrecisionDateTime.StartOfDay.class, decodedDateTime);
        Instant instant = ((ValidPrecisionDateTime.ValidPrecisionDateTime.StartOfDay) decodedDateTime).getInstant();
        Assertions
                .assertEquals(
                        LocalDateTime.of(2022, 5, 3, 0, 0, 0, 0).toInstant(ZoneOffset.UTC),
                        instant);
    }

    @Test
    void localDateStringToExactTime() {
        @Language(value = "json", prefix = "", suffix = "") String jsonString = "\"2022-05-03T13:37:42Z\"";

        PrecisionDateTime decodedDateTime = getJSON_SERIALIZER().decodeFromString(DateTimeSerializer.INSTANCE, jsonString);
        Assertions.assertInstanceOf(ValidPrecisionDateTime.ValidPrecisionDateTime.ExactTime.class, decodedDateTime);
        Instant instant = ((ValidPrecisionDateTime.ValidPrecisionDateTime.ExactTime) decodedDateTime).getInstant();
        Assertions
                .assertEquals(
                        LocalDateTime.of(2022, 5, 3, 13, 37, 42, 0).toInstant(ZoneOffset.UTC),
                        instant);
    }
}
