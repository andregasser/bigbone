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
        val measures: List<RequestMeasure> = listOf(
            RequestMeasure.ActiveUsers,
            RequestMeasure.NewUsers,
            RequestMeasure.Interactions,
            RequestMeasure.OpenedReports,
            RequestMeasure.ResolvedReports,
            RequestMeasure.TagAccounts(tagId = "123"),
            RequestMeasure.TagUses(tagId = "123"),
            RequestMeasure.TagServers(tagId = "123"),
            RequestMeasure.InstanceAccounts(remoteDomain = "mastodon.social"),
            RequestMeasure.InstanceMediaAttachments(remoteDomain = "mastodon.social"),
            RequestMeasure.InstanceReports(remoteDomain = "mastodon.social"),
            RequestMeasure.InstanceStatuses(remoteDomain = "mastodon.social"),
            RequestMeasure.InstanceFollows(remoteDomain = "mastodon.social"),
            RequestMeasure.InstanceFollowers(remoteDomain = "mastodon.social")
        )
        val startAt = Instant.now().minusSeconds(600)
        val endAt = Instant.now()

        val measurableData: List<Admin.Measure> = adminMeasuresMethods.getMeasurableData(
            measures = measures,
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
            val measuresString: String = "keys[]=active_users&" +
                "keys[]=new_users&" +
                "keys[]=interactions&" +
                "keys[]=opened_reports&" +
                "keys[]=resolved_reports&" +

                "keys[]=tag_accounts&" +
                "tag_accounts[id]=123&" +

                "keys[]=tag_uses&" +
                "tag_uses[id]=123&" +

                "keys[]=tag_servers&" +
                "tag_servers[id]=123&" +

                "keys[]=instance_accounts&" +
                "instance_accounts[domain]=mastodon.social&" +

                "keys[]=instance_media_attachments&" +
                "instance_media_attachments[domain]=mastodon.social&" +

                "keys[]=instance_reports&" +
                "instance_reports[domain]=mastodon.social&" +

                "keys[]=instance_statuses&" +
                "instance_statuses[domain]=mastodon.social&" +

                "keys[]=instance_follows&" +
                "instance_follows[domain]=mastodon.social&" +

                "keys[]=instance_followers&" +
                "instance_followers[domain]=mastodon.social"
            val startString = URLEncoder.encode(startAt.toString(), "utf-8")
            val endString = URLEncoder.encode(endAt.toString(), "utf-8")

            toQuery() shouldBeEqualTo "$measuresString&start_at=$startString&end_at=$endString"
        }
    }
}
