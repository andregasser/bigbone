package social.bigbone

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ParametersTest {
    @Test
    fun parameterEmpty() {
        Parameters().toQuery() shouldBeEqualTo ""
    }

    @Test
    fun parameterOne() {
        Parameters()
            .append("test", "value")
            .toQuery() shouldBeEqualTo "test=value"
    }

    @Test
    fun parametersOne() {
        Parameters()
            .append("test", "value")
            .append("id", 3L)
            .append("max", 10)
            .append("is_staff", false)
            .toQuery() shouldBeEqualTo "test=value&id=3&max=10&is_staff=false"
    }

    @Test
    fun parameterArray() {
        Parameters()
            .append("media_ids", listOf(1, 3, 4))
            .toQuery() shouldBeEqualTo "media_ids[]=1&media_ids[]=3&media_ids[]=4"
    }
}
