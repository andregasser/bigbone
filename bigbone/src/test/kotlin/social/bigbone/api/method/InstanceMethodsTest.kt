package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient
import social.bigbone.testtool.TestUtil

class InstanceMethodsTest {
    @Test
    fun getInstance() {
        val client = MockClient.mock("instance.json")
        val instanceMethods = InstanceMethods(client)

        val instance = instanceMethods.getInstance().execute()
        instance.uri shouldBeEqualTo "test.com"
        instance.title shouldBeEqualTo "test.com"
        instance.description shouldBeEqualTo "description"
        instance.email shouldBeEqualTo "owner@test.com"
        instance.version shouldBeEqualTo "1.3.2"
    }

    @Test
    fun getInstanceExtended() {
        val client = MockClient.mock("instance_extended.json")
        val instanceMethods = InstanceMethods(client)

        val instance = instanceMethods.getInstance().execute()
        instance.uri shouldBeEqualTo "mastodon.social"
        instance.title shouldBeEqualTo "Mastodon"
        instance.description shouldBeEqualTo ""
        instance.email shouldBeEqualTo "staff@mastodon.social"
        instance.version shouldBeEqualTo "3.5.3"
        val config = instance.configuration!!
        config.statuses!!.maxCharacters shouldBeEqualTo 500
        config.statuses!!.maxMediaAttachments shouldBeEqualTo 4
        config.statuses!!.charactersReservedPerUrl shouldBeEqualTo 23
        config.mediaAttachments!!.supportedMimeTypes.size shouldBeEqualTo 25
        config.mediaAttachments!!.imageSizeLimit shouldBeEqualTo 10_485_760
        config.mediaAttachments!!.imageMatrixLimit shouldBeEqualTo 16_777_216
        config.mediaAttachments!!.videoSizeLimit shouldBeEqualTo 41_943_040
        config.mediaAttachments!!.videoFrameRateLimit shouldBeEqualTo 60
        config.mediaAttachments!!.videoMatrixLimit shouldBeEqualTo 2_304_000
        config.polls!!.maxOptions shouldBeEqualTo 4
        config.polls!!.maxCharactersPerOption shouldBeEqualTo 50
        config.polls!!.minExpiration shouldBeEqualTo 300
        config.polls!!.maxExpiration shouldBeEqualTo 2_629_746
        val rules = instance.rules!!
        rules.size shouldBeEqualTo 6
        rules[0].id shouldBeEqualTo "1"
        rules[0].text shouldBeEqualTo "Sexually explicit or violent media must be marked as sensitive when posting"
    }

    @Test
    fun getInstanceWithJson() {
        val client = MockClient.mock("instance.json")
        val instanceMethods = InstanceMethods(client)
        instanceMethods.getInstance()
            .doOnJson {
                val expected = """{
  "uri": "test.com",
  "title": "test.com",
  "short_description": "short description",
  "description": "description",
  "email": "owner@test.com",
  "version": "1.3.2",
  "stats": {
    "user_count": 123456,
    "status_count": 512023,
    "domain_count": 13002
  },
  "thumbnail": "https://www.server.com/testimage.svg",
  "registrations": true,
  "approval_required": false,
  "invites_enabled": true
}
"""
                Assertions.assertEquals(TestUtil.normalizeLineBreaks(it), TestUtil.normalizeLineBreaks(expected))
            }
            .execute()
    }

    @Test
    fun getInstanceWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val instanceMethods = InstanceMethods(client)
            instanceMethods.getInstance().execute()
        }
    }
}
