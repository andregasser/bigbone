package social.bigbone.rx

import org.junit.jupiter.api.Test
import social.bigbone.api.method.TimelineMethods
import social.bigbone.rx.testtool.MockClient

class RxTimelineMethodsTest {

    @Test
    fun getPublicTimeline() {
        val client = MockClient.mock("public_timeline.json", "5", "40")
        val timelines = RxTimelineMethods(client)
        val subscriber = timelines.getPublicTimeline(statusOrigin = TimelineMethods.StatusOrigin.LOCAL).test()
        subscriber.assertNoErrors()
    }
}
