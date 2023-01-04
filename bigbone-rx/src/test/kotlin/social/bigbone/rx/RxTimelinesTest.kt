package social.bigbone.rx

import org.junit.jupiter.api.Test
import social.bigbone.api.method.Timelines
import social.bigbone.rx.testtool.MockClient

class RxTimelinesTest {

    @Test
    fun getPublicTimeline() {
        val client = MockClient.mock("public_timeline.json", "5", "40")
        val timelines = RxTimelines(client)
        val subscriber = timelines.getPublicTimeline(statusOrigin = Timelines.StatusOrigin.LOCAL).test()
        subscriber.assertNoErrors()
    }
}
