package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.Mastodon4jRequestException
import social.bigbone.testtool.MockClient

class TimelinesTest {

    @Test
    fun getHome() {
        val client = MockClient.mock("timelines.json")
        val timelines = Timelines(client)
        val pageable = timelines.getHome().execute()
        val status = pageable.part.first()
        status.id shouldBeEqualTo 11_111L
    }

    @Test
    fun homeWithException() {
        Assertions.assertThrows(Mastodon4jRequestException::class.java) {
            val client = MockClient.ioException()
            val timelines = Timelines(client)
            timelines.getHome().execute()
        }
    }

    // TODO 401
}
