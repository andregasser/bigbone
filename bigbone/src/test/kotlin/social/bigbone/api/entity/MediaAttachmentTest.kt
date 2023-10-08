package social.bigbone.api.entity

import kotlinx.serialization.json.Json
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBe
import org.junit.jupiter.api.Test
import social.bigbone.testtool.AssetsUtil

class MediaAttachmentTest {

    private val jsonSerializer: Json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Test
    fun deserialize() {
        val json = AssetsUtil.readFromAssets("attachment.json")
        val status: MediaAttachment = jsonSerializer.decodeFromString(json)
        status.id shouldBeEqualTo "10"
        status.url shouldBeEqualTo "youtube"
        status.remoteUrl shouldNotBe null
        status.previewUrl shouldBeEqualTo "preview"
        status.textUrl shouldNotBe null
        status.type shouldBeEqualTo MediaAttachment.Type.Video.value
    }
}
