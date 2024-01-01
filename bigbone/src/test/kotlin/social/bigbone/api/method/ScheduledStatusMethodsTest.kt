package social.bigbone.api.method

import io.mockk.Called
import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.AnyException
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeBlank
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldNotThrow
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import social.bigbone.MastodonClient.Method
import social.bigbone.Parameters
import social.bigbone.PrecisionDateTime.InvalidPrecisionDateTime
import social.bigbone.PrecisionDateTime.ValidPrecisionDateTime.ExactTime
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.ScheduledStatus
import social.bigbone.testtool.MockClient
import social.bigbone.testtool.TestUtil.urlEncode
import java.time.Duration
import java.time.Instant

class ScheduledStatusMethodsTest {

    @Test
    fun `Given a client returning success, when getting scheduled statuses without explicit Range, then call correct endpoint and parse response`() {
        val client = MockClient.mock("scheduled_statuses_get_statuses_success.json")
        val scheduledStatusMethods = ScheduledStatusMethods(client)

        val scheduledStatuses: Pageable<ScheduledStatus> = scheduledStatusMethods.getScheduledStatuses().execute()

        with(scheduledStatuses.part) {
            shouldHaveSize(1)

            with(get(0)) {
                id shouldBeEqualTo "3221"
                scheduledAt shouldBeEqualTo ExactTime(Instant.parse("2019-12-05T12:33:01.000Z"))

                with(params) {
                    poll.shouldBeNull()
                    text shouldBeEqualTo "test content"
                    mediaIds.shouldBeNull()
                    sensitive.shouldBeNull()
                    visibility.shouldBeNull()
                    idempotency.shouldBeNull()
                    scheduledAt shouldBeEqualTo InvalidPrecisionDateTime.Unavailable
                    spoilerText.shouldBeNull()
                    applicationId shouldBeEqualTo 596_551
                    inReplyToId.shouldBeNull()
                }

                mediaAttachments.shouldBeEmpty()
            }
        }

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.get(
                path = "api/v1/scheduled_statuses",
                query = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery().shouldBeBlank()
        }
    }

    @Test
    fun `Given a client returning success, when getting scheduled statuses with explicit Range, then call correct endpoint and parse response`() {
        val client = MockClient.mock("scheduled_statuses_get_statuses_success.json")
        val scheduledStatusMethods = ScheduledStatusMethods(client)
        val range = Range(limit = 56)

        val scheduledStatuses: Pageable<ScheduledStatus> = scheduledStatusMethods.getScheduledStatuses(range).execute()

        with(scheduledStatuses.part) {
            shouldHaveSize(1)

            with(get(0)) {
                id shouldBeEqualTo "3221"
                scheduledAt shouldBeEqualTo ExactTime(Instant.parse("2019-12-05T12:33:01.000Z"))

                with(params) {
                    poll.shouldBeNull()
                    text shouldBeEqualTo "test content"
                    mediaIds.shouldBeNull()
                    sensitive.shouldBeNull()
                    visibility.shouldBeNull()
                    idempotency.shouldBeNull()
                    scheduledAt shouldBeEqualTo InvalidPrecisionDateTime.Unavailable
                    spoilerText.shouldBeNull()
                    applicationId shouldBeEqualTo 596_551
                    inReplyToId.shouldBeNull()
                }

                mediaAttachments.shouldBeEmpty()
            }
        }

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.get(
                path = "api/v1/scheduled_statuses",
                query = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "limit=56"
        }
    }

    @Test
    fun `Given a client returning success, when getting scheduled status, then call correct endpoint and parse response`() {
        val client = MockClient.mock("scheduled_statuses_get_status_success.json")
        val scheduledStatusMethods = ScheduledStatusMethods(client)
        val scheduledStatusId = "3221"

        val scheduledStatus: ScheduledStatus = scheduledStatusMethods
            .getScheduledStatus(withId = scheduledStatusId)
            .execute()

        with(scheduledStatus) {
            id shouldBeEqualTo "3221"
            scheduledAt shouldBeEqualTo ExactTime(Instant.parse("2019-12-05T12:33:01.000Z"))

            with(params) {
                poll.shouldBeNull()
                text shouldBeEqualTo "test content"
                mediaIds.shouldBeNull()
                sensitive.shouldBeNull()
                visibility.shouldBeNull()
                idempotency.shouldBeNull()
                scheduledAt shouldBeEqualTo InvalidPrecisionDateTime.Unavailable
                spoilerText.shouldBeNull()
                applicationId shouldBeEqualTo 596_551
                inReplyToId.shouldBeNull()
            }

            mediaAttachments.shouldBeEmpty()
        }
        verify {
            client.get(
                path = "api/v1/scheduled_statuses/3221",
                query = null
            )
        }
    }

    @Test
    fun `Given a client that would return success, when updating a status less than 5min ahead, then throw Exception`() {
        val client = MockClient.mock("scheduled_statuses_update_success.json")
        val scheduledStatusMethods = ScheduledStatusMethods(client)
        val scheduledDate: Instant = Instant.now().plus(Duration.ofMinutes(2))

        invoking {
            scheduledStatusMethods.updatePublishingDate(
                ofId = "12345",
                newPublishingDate = scheduledDate
            ).execute()
        }
            .shouldThrow(IllegalArgumentException::class)
            .withMessage("New publishing date must lie ahead at least 5 minutes")

        verify { client wasNot Called }
    }

    @Test
    fun `Given a client returning success, when updating a status more than 5min ahead, then call correct endpoint and parse response`() {
        val client = MockClient.mock("scheduled_statuses_update_success.json")
        val scheduledStatusMethods = ScheduledStatusMethods(client)
        val scheduledDate: Instant = Instant.now().plus(Duration.ofMinutes(42))

        val scheduledStatus: ScheduledStatus = scheduledStatusMethods.updatePublishingDate(
            ofId = "3221",
            newPublishingDate = scheduledDate
        ).execute()

        with(scheduledStatus) {
            id shouldBeEqualTo "3221"
            scheduledAt shouldBeEqualTo ExactTime(Instant.parse("2019-12-05T13:33:01.000Z"))

            with(params) {
                poll.shouldBeNull()
                text shouldBeEqualTo "test content"
                mediaIds.shouldBeNull()
                sensitive.shouldBeNull()
                visibility.shouldBeNull()
                idempotency.shouldBeNull()
                scheduledAt shouldBeEqualTo InvalidPrecisionDateTime.Unavailable
                spoilerText.shouldBeNull()
                applicationId shouldBeEqualTo 596_551
                inReplyToId.shouldBeNull()
            }

            mediaAttachments.shouldBeEmpty()
        }

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.put(
                path = "api/v1/scheduled_statuses/3221",
                body = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "scheduled_at=${scheduledDate.toString().urlEncode()}"
        }
    }

    @Test
    fun `Given a client that would return success, when cancelling a scheduled status, then call correct endpoint`() {
        val client = MockClient.mockClearText("")
        val scheduledStatusMethods = ScheduledStatusMethods(client)

        invoking {
            scheduledStatusMethods.cancelScheduledStatus(withId = "12345")
        }.shouldNotThrow(AnyException)

        verify {
            client.performAction(
                endpoint = "api/v1/scheduled_statuses/12345",
                method = Method.DELETE,
                parameters = null
            )
        }
    }
}
