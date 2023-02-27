package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient
import java.io.File

class MediaMethodsTest {
    @Test
    fun uploadMedia() {
        val client = MockClient.mock("attachment.json")
        val mediaMethods = MediaMethods(client)
        val file = File("foo.bar")
        val mediaType = "image/foo"
        val attachment = mediaMethods.uploadMedia(file, mediaType).execute()
        attachment.id shouldBeEqualTo "10"
        attachment.type shouldBeEqualTo "video"
        attachment.url shouldBeEqualTo "youtube"
        attachment.remoteUrl shouldNotBe null
        attachment.previewUrl shouldBeEqualTo "preview"
        attachment.textUrl shouldNotBe null
    }

    @Test
    fun uploadMediaWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val mediaMethods = MediaMethods(client)
            val file = File("foo.bar")
            val mediaType = "image/foo"
            mediaMethods.uploadMedia(file, mediaType).execute()
        }
    }
}
