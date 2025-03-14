package social.bigbone.api.entity

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBe
import org.junit.jupiter.api.Test
import social.bigbone.JSON_SERIALIZER
import social.bigbone.testtool.AssetsUtil

class MediaAttachmentTest {
    @Test
    fun deserialize() {
        val json = AssetsUtil.readFromAssets("attachment.json")
        val mediaAttachment: MediaAttachment = JSON_SERIALIZER.decodeFromString(json)
        mediaAttachment.id shouldBeEqualTo "10"
        mediaAttachment.url shouldBeEqualTo "youtube"
        mediaAttachment.remoteUrl shouldNotBe null
        mediaAttachment.previewUrl shouldBeEqualTo "preview"
        mediaAttachment.type shouldBeEqualTo MediaAttachment.MediaType.VIDEO
    }
}
