package social.bigbone

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ParameterListTest {
    @Test
    fun parameterEmpty() {
        ParameterList().build() shouldBeEqualTo ""
    }

    @Test
    fun parameterOne() {
        ParameterList()
            .append("test", "value")
            .build() shouldBeEqualTo "test=value"
    }

    @Test
    fun parametersOne() {
        ParameterList()
            .append("test", "value")
            .append("id", 3L)
            .append("max", 10)
            .append("is_staff", false)
            .build() shouldBeEqualTo "test=value&id=3&max=10&is_staff=false"
    }

    @Test
    fun parameterArray() {
        ParameterList()
            .append("media_ids", listOf(1, 3, 4))
            .build() shouldBeEqualTo "media_ids[]=1&media_ids[]=3&media_ids[]=4"
    }
}
