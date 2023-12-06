package social.bigbone.api.method.admin

import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test
import social.bigbone.Parameters
import social.bigbone.PrecisionDateTime.ValidPrecisionDateTime.ExactTime
import social.bigbone.api.entity.admin.AdminCohort
import social.bigbone.testtool.MockClient
import java.net.URLEncoder
import java.time.LocalDate
import java.time.ZoneOffset

@Suppress("ktlint:standard:max-line-length")
class AdminRetentionMethodsTest {
    @Test
    fun `Given client returning success, when getting calculated retention data with daily frequency, then call expected endpoint and return expected data`() {
        val client = MockClient.mock("admin_retention_calculate_retention_data_daily_success.json")
        val adminRetentionMethods = AdminRetentionMethods(client)
        val startAt = LocalDate.of(2023, 7, 2).atStartOfDay(ZoneOffset.UTC).toInstant()
        val endAt = LocalDate.of(2023, 7, 19).atStartOfDay(ZoneOffset.UTC).toInstant()

        val calculatedRetentionData = adminRetentionMethods.calculateRetentionData(
            startAt = startAt,
            endAt = endAt,
            frequency = AdminCohort.FrequencyOneOf.DAY
        ).execute()
        with(calculatedRetentionData) {
            shouldHaveSize(4)

            with(get(0)) {
                period shouldBeEqualTo ExactTime(
                    LocalDate.of(2022, 9, 8).atStartOfDay(ZoneOffset.UTC).toInstant()
                )
                frequency shouldBeEqualTo AdminCohort.FrequencyOneOf.DAY

                with(data) {
                    shouldNotBeNull()
                    shouldHaveSize(4)

                    get(0).rate shouldBeEqualTo 1.0f
                    get(0).value shouldBeEqualTo "2"
                }
            }
        }

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.post(
                path = "api/v1/admin/retention",
                body = capture(parametersCapturingSlot),
                addIdempotencyKey = false
            )
        }
        with(parametersCapturingSlot.captured) {
            val startString = URLEncoder.encode(startAt.toString(), "utf-8")
            val endString = URLEncoder.encode(endAt.toString(), "utf-8")
            toQuery() shouldBeEqualTo "start_at=$startString&end_at=$endString&frequency=day"
        }
    }

    @Test
    fun `Given client returning success, when getting retention data with monthly frequency, then call expected endpoint and return expected data`() {
        val client = MockClient.mock("admin_retention_calculate_retention_data_monthly_success.json")
        val adminRetentionMethods = AdminRetentionMethods(client)
        val startAt = LocalDate.of(2022, 8, 1).atStartOfDay(ZoneOffset.UTC).toInstant()
        val endAt = LocalDate.of(2023, 10, 1).atStartOfDay(ZoneOffset.UTC).toInstant()

        val calculatedRetentionData = adminRetentionMethods.calculateRetentionData(
            startAt = startAt,
            endAt = endAt,
            frequency = AdminCohort.FrequencyOneOf.MONTH
        ).execute()
        with(calculatedRetentionData) {
            shouldHaveSize(1)

            with(get(0)) {
                period shouldBeEqualTo ExactTime(
                    LocalDate.of(2022, 9, 1).atStartOfDay(ZoneOffset.UTC).toInstant()
                )
                frequency shouldBeEqualTo AdminCohort.FrequencyOneOf.MONTH

                with(data) {
                    shouldNotBeNull()
                    shouldHaveSize(1)

                    get(0).rate shouldBeEqualTo 1.0f
                    get(0).value shouldBeEqualTo "2"
                }
            }
        }

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.post(
                path = "api/v1/admin/retention",
                body = capture(parametersCapturingSlot),
                addIdempotencyKey = false
            )
        }
        with(parametersCapturingSlot.captured) {
            val startString = URLEncoder.encode(startAt.toString(), "utf-8")
            val endString = URLEncoder.encode(endAt.toString(), "utf-8")
            toQuery() shouldBeEqualTo "start_at=$startString&end_at=$endString&frequency=month"
        }
    }
}
