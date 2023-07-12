package social.bigbone

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
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

    @Test
    fun sameUuidWithDifferentParameterOrder() {
        val params1 = Parameters()
            .append("One", "1")
            .append("Two", "2")
            .append("Three", "3")

        val params2 = Parameters()
            .append("Three", "3")
            .append("Two", "2")
            .append("One", "1")

        params1.uuid() shouldBeEqualTo params2.uuid()
    }

    @Test
    fun differentUuidWithDifferentKey() {
        val params1 = Parameters()
            .append("Key", "foo")

        val params2 = Parameters()
            .append("OtherKey", "foo")

        params1.uuid() shouldNotBeEqualTo params2.uuid()
    }

    @Test
    fun differentUuidWithDifferentValue() {
        val params1 = Parameters()
            .append("Key", "foo")

        val params2 = Parameters()
            .append("Key", "bar")

        params1.uuid() shouldNotBeEqualTo params2.uuid()
    }
}
