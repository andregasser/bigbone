package social.bigbone.api.method.admin

import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test
import social.bigbone.Parameters
import social.bigbone.PrecisionDateTime.ValidPrecisionDateTime.ExactTime
import social.bigbone.api.entity.Admin
import social.bigbone.api.entity.Admin.Measure.Key
import social.bigbone.testtool.MockClient
import java.net.URLEncoder
import java.time.Instant

class AdminMeasuresMethodsTest {

    @Test
    fun `Given client returning success, when calling getMeasurableDate, then ensure proper deserialisation and correct endpoint and parameter usage`() {
        val client = MockClient.mock("admin_measures_get_measurable_data_success.json")
        val adminMeasuresMethods = AdminMeasuresMethods(client)
        val keys = listOf(
            Key.ACTIVE_USERS,
            Key.NEW_USERS,
            Key.INTERACTIONS,
            Key.OPENED_REPORTS,
            Key.RESOLVED_REPORTS,
            Key.TAG_ACCOUNTS,
            Key.TAG_USES,
            Key.TAG_SERVERS,
            Key.INSTANCE_ACCOUNTS,
            Key.INSTANCE_MEDIA_ATTACHMENTS,
            Key.INSTANCE_REPORTS,
            Key.INSTANCE_STATUSES,
            Key.INSTANCE_FOLLOWS,
            Key.INSTANCE_FOLLOWERS
        )
        val startAt = Instant.now().minusSeconds(600)
        val endAt = Instant.now()

        val measurableData: List<Admin.Measure> = adminMeasuresMethods.getMeasurableData(
            keys = keys,
            startAt = startAt,
            endAt = endAt
        ).execute()
        with(measurableData) {
            shouldHaveSize(14)

            with(get(0)) {
                key shouldBeEqualTo Key.ACTIVE_USERS
                unit.shouldBeNull()
                total shouldBeEqualTo "2"
                previousTotal shouldBeEqualTo "0"

                with(data) {
                    shouldNotBeNull()
                    shouldHaveSize(1)

                    get(0).date shouldBeEqualTo ExactTime(Instant.parse("2022-09-14T00:00:00Z"))
                    get(0).value shouldBeEqualTo "0"
                }
            }
        }

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.post(
                path = "api/v1/admin/measures",
                body = capture(parametersCapturingSlot),
                addIdempotencyKey = false
            )
        }
        with(parametersCapturingSlot.captured) {
            val keysString = keys.joinToString(separator = "&") {
                "keys[]=${URLEncoder.encode(it.apiName, "utf-8")}"
            }
            val startString = URLEncoder.encode(startAt.toString(), "utf-8")
            val endString = URLEncoder.encode(endAt.toString(), "utf-8")

            toQuery() shouldBeEqualTo "$keysString&start_at=$startString&end_at=$endString"
        }
    }
}
