package social.bigbone.api.method.admin

import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import social.bigbone.Parameters
import social.bigbone.testtool.MockClient
import java.net.URLEncoder
import java.time.Instant

class AdminMeasuresMethodsTest {

    @Test
    fun `Given client returning success, when calling getMeasurableDate with valid start and end date, then ensure correct endpoint is called with correct parameters`() {
        val client = MockClient.mock("admin_measures_get_measurable_data_success.json")
        val adminMeasuresMethods = AdminMeasuresMethods(client)
        val startAt = Instant.now().minusSeconds(600)
        val endAt = Instant.now()

        adminMeasuresMethods.getMeasurableDate(
            startAt = startAt,
            endAt = endAt
        ).execute()

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.post(
                path = "api/v1/admin/measures",
                body = capture(parametersCapturingSlot),
                addIdempotencyKey = false
            )
        }
        with(parametersCapturingSlot.captured) {
            val startString = URLEncoder.encode(startAt.toString(), "utf-8")
            val endString = URLEncoder.encode(endAt.toString(), "utf-8")

            toQuery() shouldBeEqualTo "start_at=$startString&end_at=$endString"
        }
    }
}
