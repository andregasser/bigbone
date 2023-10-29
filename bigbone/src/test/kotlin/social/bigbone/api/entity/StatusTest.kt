package social.bigbone.api.entity

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import social.bigbone.JSON_SERIALIZER
import social.bigbone.PrecisionDateTime
import social.bigbone.api.entity.data.Visibility
import social.bigbone.testtool.AssetsUtil
import java.time.Instant

class StatusTest {

    @Test
    fun deserialize() {
        val json = AssetsUtil.readFromAssets("status.json")
        val status: Status = JSON_SERIALIZER.decodeFromString(json)
        status.id shouldBeEqualTo "11111"
        status.visibility shouldBeEqualTo Visibility.PUBLIC
        status.content shouldBeEqualTo "Test Status"
        val account = status.account
        requireNotNull(account)
        account.id shouldBeEqualTo "14476"
        status.isReblogged shouldBeEqualTo false
    }

    @Test
    fun constructor() {
        val status = Status(
            id = "123",
            createdAt = PrecisionDateTime.ExactTime(Instant.now()),
            visibility = Visibility.PRIVATE
        )
        status.id shouldBeEqualTo "123"
        status.visibility shouldBeEqualTo Visibility.PRIVATE
        status.content shouldBeEqualTo ""
    }
}
