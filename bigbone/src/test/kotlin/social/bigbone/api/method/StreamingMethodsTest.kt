package social.bigbone.api.method

import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import okio.IOException
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContainSame
import org.amshove.kluent.shouldNotThrow
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.MastodonClient
import social.bigbone.Parameters
import social.bigbone.api.entity.streaming.MastodonApiEvent
import social.bigbone.api.entity.streaming.ParsedStreamEvent
import social.bigbone.api.entity.streaming.StreamType
import social.bigbone.api.entity.streaming.TechnicalEvent
import social.bigbone.api.entity.streaming.WebSocketCallback
import social.bigbone.api.entity.streaming.WebSocketEvent
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient
import social.bigbone.testtool.TestUtil.urlEncode

class StreamingMethodsTest {

    @Test
    fun `Given a client returning success, when checking server health, then expect no errors`() {
        val client = MockClient.mockClearText(clearTextResponse = "OK")
        val streamingMethods = StreamingMethods(client)

        invoking { streamingMethods.checkServerHealth() } shouldNotThrow BigBoneRequestException::class

        verify {
            client.performAction(
                endpoint = "api/v1/streaming/health",
                method = MastodonClient.Method.GET,
                parameters = null
            )
        }
    }

    @Test
    fun `Given websocket with 6 events lined up, when streaming federated public timeline, then expect emissions and no errors`() {
        val sentEvents = listOf(
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
        val client = MockClient.mockWebSocket(sentEvents)
        val streamingMethods = StreamingMethods(client)

        val receivedEvents: MutableList<WebSocketEvent> = mutableListOf()
        val callback = WebSocketCallback(receivedEvents::add)
        val closeable = streamingMethods.federatedPublic(
            onlyMedia = false,
            callback = callback
        )
        closeable.close()

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.stream(
                parameters = capture(parametersCapturingSlot),
                callback = callback
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "stream=${StreamType.PUBLIC.apiName.urlEncode()}"
        }

        sentEvents shouldContainSame receivedEvents
    }

    @Test
    fun `Given working websocket, when streaming local public timeline with media only, then verify client is called correctly`() {
        val client = MockClient.mockWebSocket(listOf(TechnicalEvent.Open))
        val streamingMethods = StreamingMethods(client)

        val callback = WebSocketCallback {}
        val closeable = streamingMethods.localPublic(
            onlyMedia = true,
            callback = callback
        )
        closeable.close()

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.stream(
                parameters = capture(parametersCapturingSlot),
                callback = callback
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "stream=${StreamType.PUBLIC_LOCAL_MEDIA.apiName.urlEncode()}"
        }
    }

    @Test
    fun `Given working websocket, when streaming private hashtag timeline, then verify client is called correctly`() {
        val client = MockClient.mockWebSocket(listOf(TechnicalEvent.Open))
        val streamingMethods = StreamingMethods(client)
        val hashTagName = "bigbone"

        val callback = WebSocketCallback {}
        val closeable = streamingMethods.hashtag(
            tagName = hashTagName,
            onlyFromThisServer = true,
            callback = callback
        )
        closeable.close()

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.stream(
                parameters = capture(parametersCapturingSlot),
                callback = callback
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "stream=${StreamType.HASHTAG_LOCAL.apiName.urlEncode()}" +
                "&tag=$hashTagName"
        }
    }

    @Test
    fun `Given working websocket, when streaming list, then verify client is called correctly`() {
        val client = MockClient.mockWebSocket(listOf(TechnicalEvent.Open))
        val streamingMethods = StreamingMethods(client)
        val listName = "bigbone-developers"

        val callback = WebSocketCallback {}
        val closeable = streamingMethods.list(
            listId = listName,
            callback = callback
        )
        closeable.close()

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.stream(
                parameters = capture(parametersCapturingSlot),
                callback = callback
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "stream=${StreamType.LIST.apiName.urlEncode()}" +
                "&list=${listName.urlEncode()}"
        }
    }

    @Test
    fun `Given websocket with failure, when streaming federated public timeline, then expect error is propagated`() {
        val expectedError = IOException("expected")
        val sentEvents = listOf(
            TechnicalEvent.Open,
            TechnicalEvent.Failure(expectedError)
        )
        val client = MockClient.mockWebSocket(sentEvents)
        val streamingMethods = StreamingMethods(client)

        val receivedEvents: MutableList<WebSocketEvent> = mutableListOf()
        val callback = WebSocketCallback(receivedEvents::add)
        val closeable = streamingMethods.federatedPublic(
            onlyMedia = false,
            callback = callback
        )
        closeable.close()

        Assertions.assertIterableEquals(sentEvents, receivedEvents)
    }
}
