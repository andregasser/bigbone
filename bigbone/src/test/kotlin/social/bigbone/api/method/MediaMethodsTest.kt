package social.bigbone.api.method

import io.mockk.slot
import io.mockk.verify
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldContainIgnoringCase
import org.amshove.kluent.shouldNotBe
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.entity.MediaAttachment
import social.bigbone.api.entity.data.Focus
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient
import java.io.File

class MediaMethodsTest {

    @Test
    fun `Given client that succeeds, when getting media attachment, then call correct endpoint and parse response`() {
        val client = MockClient.mock("media_get_attachment_success.json")
        val mediaMethods = MediaMethods(client)
        val mediaId = "1357"

        val mediaAttachment: MediaAttachment = mediaMethods.getMediaAttachment(withId = mediaId).execute()

        with(mediaAttachment) {
            id shouldBeEqualTo "22348641"
            type shouldBeEqualTo MediaAttachment.MediaType.IMAGE
            url shouldBeEqualTo "https://files.mastodon.social/media_attachments/files/022/348/641/original/e96382f26c72a29c.jpeg"
            previewUrl shouldBeEqualTo "https://files.mastodon.social/media_attachments/files/022/348/641/small/e96382f26c72a29c.jpeg"
            remoteUrl.shouldBeNull()
            textUrl shouldBeEqualTo "https://mastodon.social/media/4Zj6ewxzzzDi0g8JnZQ"

            with(meta) {
                shouldNotBeNull()

                with(focus) {
                    shouldNotBeNull()
                    x shouldBeEqualTo -0.42f
                    y shouldBeEqualTo 0.69f
                }

                with(original) {
                    shouldNotBeNull()

                    width shouldBeEqualTo 640
                    height shouldBeEqualTo 480
                    size shouldBeEqualTo "640x480"
                    aspectRatio shouldBeEqualTo 1.3333333333333333
                }

                with(small) {
                    shouldNotBeNull()

                    width shouldBeEqualTo 461
                    height shouldBeEqualTo 346
                    size shouldBeEqualTo "461x346"
                    aspectRatio shouldBeEqualTo 1.3323699421965318
                }
            }

            description shouldBeEqualTo "test uploaded via api, but updated"
            blurhash shouldBeEqualTo "UFBWY:8_0Jxv4mx]t8t64.%M-:IUWGWAt6M}"
        }
        verify {
            client.get(
                path = "api/v1/media/1357",
                query = null
            )
        }
    }

    @Test
    fun `Given client returning success, when updating media attachment, then call correct endpoint`() {
        val client = MockClient.mock("media_update_attachment_success.json")
        val mediaMethods = MediaMethods(client)
        val customThumbnail = CustomThumbnail(File("foo.bar"), "image/foo")
        val mediaAttachmentId = "13579"
        val description = "test uploaded via api, but updated"
        val focus = Focus(0.5f, 0.5f)

        val updatedAttachment: MediaAttachment = mediaMethods.updateMediaAttachment(
            withId = mediaAttachmentId,
            customThumbnail = customThumbnail,
            description = description,
            focus = focus
        ).execute()

        updatedAttachment.description shouldBeEqualTo "test uploaded via api, but updated"
        val requestBodyCapturingSlot = slot<RequestBody>()
        verify {
            client.putRequestBody(
                path = "api/v1/media/13579",
                body = capture(requestBodyCapturingSlot)
            )
        }
        with(requestBodyCapturingSlot.captured) {
            with(contentType()) {
                shouldNotBeNull()
                type shouldBeEqualTo "multipart"
                subtype shouldBeEqualTo "form-data"
            }

            shouldBeInstanceOf(MultipartBody::class)
            with(this as MultipartBody) {
                val partHeaders: List<String> = parts.mapNotNull {
                    it.headers?.values("Content-Disposition")
                }.flatten()

                partHeaders.shouldContainIgnoringCase("""name="thumbnail"""")
                partHeaders.shouldContainIgnoringCase("""name="description"""")
                partHeaders.shouldContainIgnoringCase("""name="focus"""")
            }
        }
    }

    @Test
    fun uploadMedia() {
        val client = MockClient.mock("attachment.json")
        val mediaMethods = MediaMethods(client)
        val file = File("foo.bar")
        val mediaType = "image/foo"
        val description = "This is an image description"
        val focus = Focus(0.5f, 0.5f)
        val attachment = mediaMethods.uploadMedia(file, mediaType, description, focus).execute()
        attachment.id shouldBeEqualTo "10"
        attachment.type.name.lowercase() shouldBeEqualTo "video"
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
