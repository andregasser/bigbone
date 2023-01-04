package social.bigbone.api.entity

import com.google.gson.Gson
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import social.bigbone.testtool.AssetsUtil

class StatusTest {

    @Test
    fun deserialize() {
        val json = AssetsUtil.readFromAssets("status.json")
        val status: Status = Gson().fromJson(json, Status::class.java)
        status.id shouldBeEqualTo "103254962155278888"
        status.visibility shouldBeEqualTo Status.Visibility.Public.value
        status.content shouldBeEqualTo "<p>test content</p>"
        val account = status.account
        requireNotNull(account)
        account.id shouldBeEqualTo "239857457"
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
