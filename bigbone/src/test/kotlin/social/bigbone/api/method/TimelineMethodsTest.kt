package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient
import java.util.concurrent.atomic.AtomicInteger

class TimelineMethodsTest {

    @Test
    fun getHomeTimeline() {
        val client = MockClient.mock("timelines.json")
        val timelineMethods = TimelineMethods(client)
        val pageable = timelineMethods.getHomeTimeline().execute()
        val status = pageable.part.first()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun getHomeTimelineWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val timelineMethods = TimelineMethods(client)
            timelineMethods.getHomeTimeline().execute()
        }
    }

    @Test
    fun getLocalPublicTimeline() {
        val client = MockClient.mock("public_timeline.json", maxId = "3", sinceId = "1")
        val timelineMethods = TimelineMethods(client)
        val statuses = timelineMethods.getPublicTimeline(statusOrigin = TimelineMethods.StatusOrigin.LOCAL).execute()
        statuses.part.size shouldBeEqualTo 20
        statuses.link?.let {
            it.nextPath shouldBeEqualTo "<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&max_id=3>"
            it.maxId shouldBeEqualTo "3"
            it.prevPath shouldBeEqualTo "<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&since_id=1>"
            it.sinceId shouldBeEqualTo "1"
        }
    }

    @Test
    fun getLocalPublicTimelineWithJson() {
        val atomicInt = AtomicInteger(0)
        val client = MockClient.mock("public_timeline.json", maxId = "3", sinceId = "1")
        val timelineMethods = TimelineMethods(client)
        timelineMethods.getPublicTimeline(statusOrigin = TimelineMethods.StatusOrigin.LOCAL)
            .doOnJson {
                atomicInt.incrementAndGet()
            }
            .execute()
        atomicInt.get() shouldBeEqualTo 20
    }

    @Test
    fun getLocalPublicTimelineWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val timelineMethods = TimelineMethods(client)
            timelineMethods.getPublicTimeline(statusOrigin = TimelineMethods.StatusOrigin.LOCAL).execute()
        }
    }

    @Test
    fun getLocalTagTimeline() {
        val client = MockClient.mock("tag.json", maxId = "3", sinceId = "1")
        val timelineMethods = TimelineMethods(client)
        val statuses = timelineMethods.getTagTimeline(tag = "mastodon", statusOrigin = TimelineMethods.StatusOrigin.LOCAL).execute()
        statuses.part.size shouldBeEqualTo 20
    }

    @Test
    fun getLocalTagTimelineWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val timelineMethods = TimelineMethods(client)
            timelineMethods.getTagTimeline(tag = "mastodon", statusOrigin = TimelineMethods.StatusOrigin.LOCAL).execute()
        }
    }

    // TODO 401
}
