package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class TagMethodsTest {

    @Test
    fun getTag() {
        val client = MockClient.mock("tag.json")
        val tagMethods = TagMethods(client)
        val tag = tagMethods.getTag("nowplaying").execute()
        val historyElement = tag.history.first()
        tag.name shouldBeEqualTo "nowplaying"
        tag.following shouldBeEqualTo false
        tag.url shouldBeEqualTo "https://mastodon.social/tags/nowplaying"
        historyElement.day shouldBeEqualTo "1574553600"
        historyElement.uses shouldBeEqualTo "200"
        historyElement.accounts shouldBeEqualTo "31"
    }

    @Test
    fun getTagWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val tagMethods = TagMethods(client)
            tagMethods.getTag("nowplaying").execute()
        }
    }

    @Test
    fun followTag() {
        val client = MockClient.mock("tag.json")
        val tagMethods = TagMethods(client)
        val tag = tagMethods.followTag("nowplaying").execute()
        tag.name shouldBeEqualTo "nowplaying"
        tag.following shouldBeEqualTo false
        tag.url shouldBeEqualTo "https://mastodon.social/tags/nowplaying"
    }

    @Test
    fun followTagWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val tagMethods = TagMethods(client)
            tagMethods.followTag("nowplaying").execute()
        }
    }

    @Test
    fun unfollowTag() {
        val client = MockClient.mock("tag.json")
        val tagMethods = TagMethods(client)
        val tag = tagMethods.unfollowTag("nowplaying").execute()
        tag.name shouldBeEqualTo "nowplaying"
        tag.following shouldBeEqualTo false
        tag.url shouldBeEqualTo "https://mastodon.social/tags/nowplaying"
    }

    @Test
    fun unfollowTagWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val tagMethods = TagMethods(client)
            tagMethods.unfollowTag("nowplaying").execute()
        }
    }
}
