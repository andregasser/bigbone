package social.bigbone.api.method

import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldNotBeNull
import org.amshove.kluent.shouldThrow
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

        instance.domain shouldBeEqualTo "test.com"
        instance.title shouldBeEqualTo "test.com"
        instance.description shouldBeEqualTo "description"
        instance.version shouldBeEqualTo "1.3.2"
    }

    @Test
    fun getInstanceExtended() {
        val client = MockClient.mock("instance_extended.json")
        val instanceMethods = InstanceMethods(client)

        val instance = instanceMethods.getInstance().execute()
        instance.domain shouldBeEqualTo "mastodon.social"
        instance.title shouldBeEqualTo "Mastodon"
        instance.version shouldBeEqualTo "4.0.0rc1"
        instance.sourceUrl shouldBeEqualTo "https://github.com/mastodon/mastodon"
        instance.description shouldBeEqualTo "The original server operated by the Mastodon gGmbH non-profit"
        instance.usage.users.activeMonth shouldBeEqualTo 123122

        with(instance.thumbnail) {
            url shouldBeEqualTo "https://files.mastodon.social/site_uploads/files/000/000/001/@1x/57c12f441d083cde.png"
            blurHash.shouldNotBeNull()
            with(versions) {
                this.shouldNotBeNull()
                resolution1x.shouldNotBeNull()
                resolution2x.shouldNotBeNull()
            }
        }

        with(instance.languages) {
            size shouldBeEqualTo 1
            get(0) shouldBeEqualTo "en"
        }

        val config = instance.configuration
        config.urls.streaming shouldBeEqualTo "wss://mastodon.social"
        config.accounts.maxFeaturedTags shouldBeEqualTo 10
        with(config.statuses) {
            maxCharacters shouldBeEqualTo 500
            maxMediaAttachments shouldBeEqualTo 4
            charactersReservedPerUrl shouldBeEqualTo 23
        }
        with(config.mediaAttachments) {
            supportedMimeTypes.size shouldBeEqualTo 27
            imageSizeLimit shouldBeEqualTo 10_485_760
            imageMatrixLimit shouldBeEqualTo 16_777_216
            videoSizeLimit shouldBeEqualTo 41_943_040
            videoFrameRateLimit shouldBeEqualTo 60
            videoMatrixLimit shouldBeEqualTo 2_304_000
        }
        with(config.polls) {
            maxOptions shouldBeEqualTo 4
            maxCharactersPerOption shouldBeEqualTo 50
            minExpiration shouldBeEqualTo 300
            maxExpiration shouldBeEqualTo 2_629_746
        }
        config.translation.enabled shouldBeEqualTo true

        with(instance.registrations) {
            enabled shouldBeEqualTo false
            approvalRequired shouldBeEqualTo false
            message.shouldBeNull()
        }

        with(instance.contact) {
            email shouldBeEqualTo "staff@mastodon.social"
            with(account) {
                id shouldBeEqualTo "1"
                emojis.isEmpty() shouldBeEqualTo true
            }
        }

        with(instance.rules) {
            size shouldBeEqualTo 6
            get(0).id shouldBeEqualTo "1"
            get(0).text shouldBeEqualTo "Sexually explicit or violent media must be marked as sensitive when posting"
        }
    }

    @Test
    fun getInstanceWithJson() {
        val client = MockClient.mock("instance.json")
        val instanceMethods = InstanceMethods(client)
        instanceMethods.getInstance()
            .doOnJson {
                val expected = """{
  "domain": "test.com",
  "title": "test.com",
  "version": "1.3.2",
  "source_url": "https://github.com/mastodon/mastodon",
  "description": "description",
  "contact": {
    "email": "owner@test.com"
  },
  "usage": {
    "users": {
      "active_month": 123456
    }
  },
  "thumbnail": {
    "url": "https://www.server.com/testimage.svg"
  },
  "registrations": {
    "enabled": true,
    "approval_required": false
  }
}
"""
                TestUtil.normalizeLineBreaks(it) shouldBeEqualTo TestUtil.normalizeLineBreaks(expected)
            }
            .execute()
    }

    @Test
    fun getInstanceWithException() {
        val client = MockClient.ioException()
        val instanceMethods = InstanceMethods(client)

        invoking {
            instanceMethods.getInstance().execute()
        } shouldThrow BigBoneRequestException::class
    }
}
