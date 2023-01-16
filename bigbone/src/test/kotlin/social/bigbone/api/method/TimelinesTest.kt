package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient
import java.util.concurrent.atomic.AtomicInteger

class TimelinesTest {

    @Test
    fun getHomeTimeline() {
        val client = MockClient.mock("timelines.json")
        val timelines = Timelines(client)
        val pageable = timelines.getHomeTimeline().execute()
        val status = pageable.part.first()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun getHomeTimelineWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val timelines = Timelines(client)
            timelines.getHomeTimeline().execute()
        }
    }

    @Test
    fun getLocalPublicTimeline() {
        val client = MockClient.mock("public_timeline.json", maxId = "3", sinceId = "1")
        val timelines = Timelines(client)
        val statuses = timelines.getPublicTimeline(statusOrigin = Timelines.StatusOrigin.LOCAL).execute()
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
        val timelines = Timelines(client)
        timelines.getPublicTimeline(statusOrigin = Timelines.StatusOrigin.LOCAL)
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
            val timelines = Timelines(client)
            timelines.getPublicTimeline(statusOrigin = Timelines.StatusOrigin.LOCAL).execute()
        }
    }

    @Test
    fun getLocalTagTimeline() {
        val client = MockClient.mock("tag.json", maxId = "3", sinceId = "1")
        val timelines = Timelines(client)
        val statuses = timelines.getTagTimeline(tag = "mastodon", statusOrigin = Timelines.StatusOrigin.LOCAL).execute()
        statuses.part.size shouldBeEqualTo 20
    }

    @Test
    fun getLocalTagTimelineWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val timelines = Timelines(client)
            timelines.getTagTimeline(tag = "mastodon", statusOrigin = Timelines.StatusOrigin.LOCAL).execute()
        }
    }

    // TODO 401
}
