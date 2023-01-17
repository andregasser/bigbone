package social.bigbone.rx

import org.junit.jupiter.api.Test
import social.bigbone.api.method.TimelinesMethods
import social.bigbone.rx.testtool.MockClient

class RxTimelinesMethodsTest {

    @Test
    fun getPublicTimeline() {
        val client = MockClient.mock("public_timeline.json", "5", "40")
        val timelines = RxTimelinesMethods(client)
        val subscriber = timelines.getPublicTimeline(statusOrigin = TimelinesMethods.StatusOrigin.LOCAL).test()
        subscriber.assertNoErrors()
    }
}
