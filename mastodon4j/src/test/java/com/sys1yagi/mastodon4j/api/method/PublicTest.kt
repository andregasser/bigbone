package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import com.sys1yagi.mastodon4j.testtool.TestUtil
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldEqualTo
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

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

    @Test(expected = Mastodon4jRequestException::class)
    fun getInstanceWithException() {
        val client = MockClient.ioException()
        val publicMethod = Public(client)
        publicMethod.getInstance().execute()
    }

    @Test
    fun getSearch() {
        val client = MockClient.mock("search.json")

        val publicMethod = Public(client)
        val result = publicMethod.getSearch("test").execute()
        result.statuses.size shouldBeEqualTo 0
        result.accounts.size shouldBeEqualTo 6
        result.hashtags.size shouldBeEqualTo 5
        result.hashtags.size shouldBeEqualTo 5
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getSearchWithException() {
        val client = MockClient.ioException()
        val publicMethod = Public(client)
        publicMethod.getSearch("test").execute()
    }

    @Test
    fun getLocalPublic() {
        val client = MockClient.mock("public_timeline.json", maxId = 3L, sinceId = 1L)
        val publicMethod = Public(client)
        val statuses = publicMethod.getLocalPublic().execute()
        statuses.part.size shouldBeEqualTo 20
        statuses.link?.let {
            it.nextPath shouldBeEqualTo "<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&max_id=3>"
            it.maxId shouldBeEqualTo 3L
            it.prevPath shouldBeEqualTo "<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&since_id=1>"
            it.sinceId shouldBeEqualTo 1L
        }
    }

    @Test
    fun getLocalPublicWithJson() {
        val atomicInt = AtomicInteger(0)
        val client = MockClient.mock("public_timeline.json", maxId = 3L, sinceId = 1L)
        val publicMethod = Public(client)
        publicMethod.getLocalPublic()
            .doOnJson {
                atomicInt.incrementAndGet()
            }
            .execute()
        atomicInt.get() shouldBeEqualTo 20
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getLocalPublicWithException() {
        val client = MockClient.ioException()
        val publicMethod = Public(client)
        publicMethod.getLocalPublic().execute()
    }

    @Test
    fun getLocalTag() {
        val client = MockClient.mock("tag.json", maxId = 3L, sinceId = 1L)
        val publicMethod = Public(client)
        val statuses = publicMethod.getLocalTag("mastodon").execute()
        statuses.part.size shouldBeEqualTo 20
    }

    @Test(expected = Mastodon4jRequestException::class)
    fun getLocalTagWithException() {
        val client = MockClient.ioException()
        val publicMethod = Public(client)
        publicMethod.getLocalTag("mastodon").execute()
    }

}
