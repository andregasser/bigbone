package social.bigbone.api.method

import okhttp3.MultipartBody
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigboneRequestException
import social.bigbone.extension.emptyRequestBody
import social.bigbone.testtool.MockClient

class MediaTest {
    @Test
    fun postMedia() {
        val client = MockClient.mock("attachment.json")
        val media = Media(client)
        val attachment = media.postMedia(MultipartBody.Part.create(emptyRequestBody())).execute()
        attachment.id shouldBeEqualTo "22348641"
        attachment.type shouldBeEqualTo "image"
        attachment.url shouldBeEqualTo "https://files.example.org/media_attachments/files/022/348/641/original/cebc6d51be03e509.jpeg"
        attachment.remoteUrl shouldBeEqualTo null
        attachment.previewUrl shouldBeEqualTo "https://files.example.org/media_attachments/files/022/348/641/small/cebc6d51be03e509.jpeg"
        attachment.textUrl shouldBeEqualTo "https://example.org/media/4Zj6ewxzzzDi0g8JnZQ"
    }

    @Test
    fun postMediaWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val media = Media(client)
            media.postMedia(MultipartBody.Part.create(emptyRequestBody())).execute()
        }
    }
}
