package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.emptyRequestBody
import com.sys1yagi.mastodon4j.testtool.MockClient
import okhttp3.MultipartBody
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MediaTest {
    @Test
    fun postMedia() {
        val client = MockClient.mock("attachment.json")
        val media = Media(client)
        val attachment = media.postMedia(MultipartBody.Part.create(emptyRequestBody())).execute()
        attachment.id shouldBeEqualTo 10
        attachment.type shouldBeEqualTo "video"
        attachment.url shouldBeEqualTo "youtube"
        attachment.remoteUrl shouldNotBe null
        attachment.previewUrl shouldBeEqualTo "preview"
        attachment.textUrl shouldNotBe null
    }

    @Test
    fun postMediaWithException() {
        Assertions.assertThrows(Mastodon4jRequestException::class.java) {
            val client = MockClient.ioException()
            val media = Media(client)
            media.postMedia(MultipartBody.Part.create(emptyRequestBody())).execute()
        }
    }
}
