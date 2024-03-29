package social.bigbone.rx

import io.mockk.mockk
import io.reactivex.rxjava3.schedulers.TestScheduler
import io.reactivex.rxjava3.subscribers.TestSubscriber
import okio.IOException
import org.junit.jupiter.api.Test
import social.bigbone.api.entity.streaming.MastodonApiEvent
import social.bigbone.api.entity.streaming.ParsedStreamEvent
import social.bigbone.api.entity.streaming.StreamType
import social.bigbone.api.entity.streaming.TechnicalEvent
import social.bigbone.api.entity.streaming.WebSocketEvent
import social.bigbone.rx.testtool.MockClient

class RxStreamingMethodsTest {

    private val testScheduler = TestScheduler()

    @Test
    fun `Given client returning OK, when checking server health, then complete without errors`() {
        val client = MockClient.mockClearText(clearTextResponse = "OK")
        val streamingMethods = RxStreamingMethods(client)

        val serverHealth = streamingMethods.checkServerHealth().test()

        with(serverHealth) {
            assertComplete()
            assertNoErrors()
        }
    }

    @Test
    fun `Given websocket with 6 events lined up, when streaming federated public timeline, then expect emissions and no errors`() {
        val mockedEvents: List<WebSocketEvent> = listOf(
            TechnicalEvent.Open,
            MastodonApiEvent.StreamEvent(
                ParsedStreamEvent.FiltersChanged,
                listOf(StreamType.PUBLIC)
            ),
            MastodonApiEvent.StreamEvent(
                ParsedStreamEvent.StatusCreated(mockk()),
                listOf(StreamType.PUBLIC)
            ),
            MastodonApiEvent.StreamEvent(
                ParsedStreamEvent.StatusDeleted(deletedStatusId = "12345"),
                listOf(StreamType.PUBLIC)
            ),
            MastodonApiEvent.StreamEvent(
                ParsedStreamEvent.AnnouncementDeleted(deletedAnnouncementId = "54321"),
                listOf(StreamType.PUBLIC)
            ),
            MastodonApiEvent.StreamEvent(
                ParsedStreamEvent.StatusCreated(createdStatus = mockk()),
                listOf(StreamType.PUBLIC)
            )
        )
        val client = MockClient.mockWebSocket(events = mockedEvents)
        val streamingMethods = RxStreamingMethods(client)

        val testSubscriber: TestSubscriber<WebSocketEvent> = streamingMethods
            .federatedPublic(onlyMedia = false)
            .subscribeOn(testScheduler)
            .observeOn(testScheduler)
            .test()
        testScheduler.triggerActions()

        with(testSubscriber) {
            assertValueCount(mockedEvents.size)
            assertValueSequence(mockedEvents)
            assertNotComplete()
            assertNoErrors()
            cancel()
        }
    }

    @Test
    fun `Given websocket with failure, when streaming federated public timeline, then expect error is propagated`() {
        val expectedError = IOException("Expected")
        val mockedEvents: List<WebSocketEvent> = listOf(
            TechnicalEvent.Open,
            TechnicalEvent.Failure(expectedError)
        )
        val client = MockClient.mockWebSocket(events = mockedEvents)
        val streamingMethods = RxStreamingMethods(client)

        val testSubscriber: TestSubscriber<WebSocketEvent> = streamingMethods
            .federatedPublic(onlyMedia = false)
            .subscribeOn(testScheduler)
            .observeOn(testScheduler)
            .test()
        testScheduler.triggerActions()

        with(testSubscriber) {
            assertNotComplete()
            assertError(expectedError)
            cancel()
        }
    }
}
