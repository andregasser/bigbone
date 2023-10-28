package social.bigbone.api.method

import org.amshove.kluent.`should not be`
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import social.bigbone.testtool.MockClient
import java.time.Instant

class MarkerMethodsTest {
    @Test
    fun getAllMarkers() {
        val client = MockClient.mock("markers.json")
        val markerMethods = MarkerMethods(client)
        val marker = markerMethods.getMarkers().execute()
        marker.home `should not be` null
        marker.home?.let {
            it.lastReadId shouldBeEqualTo "103206604258487607"
            it.version shouldBeEqualTo 468
            it.updatedAt shouldBeEqualTo Instant.parse("2019-11-26T22:37:25.235Z")
        }
        marker.notifications `should not be` null
        marker.notifications?.let {
            it.lastReadId shouldBeEqualTo "35098814"
            it.version shouldBeEqualTo 361
            it.updatedAt shouldBeEqualTo Instant.parse("2019-11-26T22:37:25.239Z")
        }
    }

    @Test
    fun getSpecificMarker() {
        val client = MockClient.mock("marker_home.json")
        val markerMethods = MarkerMethods(client)
        val marker = markerMethods.getMarkers(Timeline.HOME).execute()
        marker.home `should not be` null
        marker.notifications shouldBe null
        marker.home?.let {
            it.lastReadId shouldBeEqualTo "103206604258487607"
            it.version shouldBeEqualTo 468
            it.updatedAt shouldBeEqualTo Instant.parse("2019-11-26T22:37:25.235Z")
        }
    }
}
