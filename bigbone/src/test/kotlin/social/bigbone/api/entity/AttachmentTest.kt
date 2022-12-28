package social.bigbone.api.entity

import com.google.gson.Gson
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBe
import org.junit.jupiter.api.Test
import social.bigbone.testtool.AssetsUtil

class AttachmentTest {
    @Test
    fun deserialize() {
        val json = AssetsUtil.readFromAssets("attachment.json")
        val status: Attachment = Gson().fromJson(json, Attachment::class.java)
        status.id shouldBeEqualTo "10"
        status.url shouldBeEqualTo "youtube"
        status.remoteUrl shouldNotBe null
        status.previewUrl shouldBeEqualTo "preview"
        status.textUrl shouldNotBe null
        status.type shouldBeEqualTo Attachment.Type.Video.value
    }
}
