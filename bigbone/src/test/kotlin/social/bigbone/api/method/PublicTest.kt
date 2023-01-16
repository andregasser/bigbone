package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient
import social.bigbone.testtool.TestUtil

class PublicTest {
    @Test
    fun getInstance() {
        val client = MockClient.mock("instance.json")
        val publicMethod = Public(client)

        val instance = publicMethod.getInstance().execute()
        instance.uri shouldBeEqualTo "test.com"
        instance.title shouldBeEqualTo "test.com"
        instance.description shouldBeEqualTo "description"
        instance.email shouldBeEqualTo "owner@test.com"
        instance.version shouldBeEqualTo "1.3.2"
    }

    @Test
    fun getInstanceWithJson() {
        val client = MockClient.mock("instance.json")
        val publicMethod = Public(client)
        publicMethod.getInstance()
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
                assertEquals(TestUtil.normalizeLineBreaks(it), TestUtil.normalizeLineBreaks(expected))
            }
            .execute()
    }

    @Test
    fun getInstanceWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val publicMethod = Public(client)
            publicMethod.getInstance().execute()
        }
    }

    @Test
    fun search() {
        val client = MockClient.mock("search.json")

        val publicMethod = Public(client)
        val result = publicMethod.search("test").execute()
        result.accounts.size shouldBeEqualTo 6
        result.statuses.size shouldBeEqualTo 2
        result.hashtags.size shouldBeEqualTo 1
    }

    @Test
    fun searchWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val publicMethod = Public(client)
            publicMethod.search("test").execute()
        }
    }
}
