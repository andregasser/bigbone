package social.bigbone.api.entity

import com.google.gson.Gson
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import social.bigbone.testtool.AssetsUtil

class AttachmentTest {
    @Test
    fun deserialize() {
        val json = AssetsUtil.readFromAssets("attachment.json")
        val status: Attachment = Gson().fromJson(json, Attachment::class.java)
        status.id shouldBeEqualTo "22348641"
        status.url shouldBeEqualTo "https://files.example.org/media_attachments/files/022/348/641/original/cebc6d51be03e509.jpeg"
        status.remoteUrl shouldBeEqualTo null
        status.previewUrl shouldBeEqualTo "https://files.example.org/media_attachments/files/022/348/641/small/cebc6d51be03e509.jpeg"
        status.textUrl shouldBeEqualTo "https://example.org/media/4Zj6ewxzzzDi0g8JnZQ"
        status.type shouldBeEqualTo Attachment.Type.Image.value
    }
}
