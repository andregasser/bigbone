package social.bigbone.api

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class RangeTest {
    @Test
    fun toParameter() {
        run {
            val range = Range()
            range.toParameters().toQuery() shouldBeEqualTo ""
        }

        run {
            val range = Range(maxId = "10")
            range.toParameters().toQuery() shouldBeEqualTo "max_id=10"
        }

        run {
            val range = Range(maxId = "5", sinceId = "3", limit = 25)
            range.toParameters().toQuery() shouldBeEqualTo "max_id=5&since_id=3&limit=25"
        }
    }
}
