package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigboneRequestException
import social.bigbone.testtool.MockClient
import java.io.File

class MediaTest {
    @Test
    fun postMedia() {
        val client = MockClient.mock("attachment.json")
        val media = Media(client)
        val file = File("foo.bar")
        val mediaType = "image/foo"
        val attachment = media.postMedia(file, mediaType).execute()
        attachment.id shouldBeEqualTo "10"
        attachment.type shouldBeEqualTo "video"
        attachment.url shouldBeEqualTo "youtube"
        attachment.remoteUrl shouldNotBe null
        attachment.previewUrl shouldBeEqualTo "preview"
        attachment.textUrl shouldNotBe null
    }

    @Test
    fun postMediaWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val media = Media(client)
            val file = File("foo.bar")
            val mediaType = "image/foo"
            media.postMedia(file, mediaType).execute()
        }
    }
}
