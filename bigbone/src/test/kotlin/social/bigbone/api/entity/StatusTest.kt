package social.bigbone.api.entity

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import social.bigbone.JSON_SERIALIZER
import social.bigbone.testtool.AssetsUtil

class StatusTest {

    @Test
    fun deserialize() {
        val json = AssetsUtil.readFromAssets("status.json")
        val status: Status = JSON_SERIALIZER.decodeFromString(json)
        status.id shouldBeEqualTo "11111"
        status.visibility shouldBeEqualTo Status.Visibility.Public.value
        status.content shouldBeEqualTo "Test Status"
        val account = status.account
        requireNotNull(account)
        account.id shouldBeEqualTo "14476"
        status.isReblogged shouldBeEqualTo false
    }

    @Test
    fun constructor() {
        val status = Status(id = "123", visibility = Status.Visibility.Private.value)
        status.id shouldBeEqualTo "123"
        status.visibility shouldBeEqualTo Status.Visibility.Private.value
        status.content shouldBeEqualTo ""
    }
}
