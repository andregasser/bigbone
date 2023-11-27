package social.bigbone.api.method.admin

import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldNotBeNull
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import social.bigbone.Parameters
import social.bigbone.api.entity.admin.AdminDimension
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient
import java.net.URLEncoder
import java.time.Instant

class AdminDimensionMethodsTest {

    @Test
    fun `Given client returning success, when calling getDimensionalData, then ensure proper deserialisation and correct endpoint and parameter usage`() {
        val client = MockClient.mock("admin_dimensions_get_dimensional_data_success.json")
        val adminDimensionMethods = AdminDimensionMethods(client)
        val dimensions: List<RequestDimension> = listOf(
            RequestDimension.Languages,
            RequestDimension.Sources,
            RequestDimension.Servers,
            RequestDimension.SpaceUsage,
            RequestDimension.SoftwareVersions,
            RequestDimension.TagServers(trendingTagId = "123"),
            RequestDimension.TagLanguages(trendingTagId = "123"),
            RequestDimension.InstanceAccounts(remoteDomain = "mastodon.social"),
            RequestDimension.InstanceLanguages(remoteDomain = "mastodon.social")
        )
        val startAt = Instant.now().minusSeconds(600)
        val endAt = Instant.now()

        val dimensionalData: List<AdminDimension> = adminDimensionMethods.getDimensionalData(
            dimensions = dimensions,
            startAt = startAt,
            endAt = endAt
        ).execute()
        with(dimensionalData) {
            shouldHaveSize(9)

            with(get(0)) {
                key shouldBeEqualTo AdminDimension.Key.LANGUAGES

                with(data) {
                    shouldNotBeNull()
                    shouldHaveSize(2)

                    get(0).key shouldBeEqualTo "en"
                    get(0).humanKey shouldBeEqualTo "English"
                    get(0).value shouldBeEqualTo "10"
                    get(0).humanValue.shouldBeNull()
                }
            }

            with(get(3)) {
                key shouldBeEqualTo AdminDimension.Key.SPACE_USAGE

                with(data) {
                    shouldNotBeNull()
                    shouldHaveSize(3)

                    get(0).key shouldBeEqualTo "postgresql"
                    get(0).humanKey shouldBeEqualTo "PostgreSQL"
                    get(0).value shouldBeEqualTo "49581359907"
                    get(0).unit shouldBeEqualTo "bytes"
                    get(0).humanValue shouldBeEqualTo "46.2 GB"
                }
            }
        }

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.post(
                path = "api/v1/admin/dimensions",
                body = capture(parametersCapturingSlot),
                addIdempotencyKey = false
            )
        }
        with(parametersCapturingSlot.captured) {
            val dimensionsString: String = "keys[]=languages&" +
                "keys[]=sources&" +
                "keys[]=servers&" +
                "keys[]=space_usage&" +
                "keys[]=software_versions&" +
                "keys[]=tag_servers&" +
                "keys[]=tag_languages&" +
                "keys[]=instance_accounts&" +
                "keys[]=instance_languages&" +
                "tag_servers[id]=123&" +
                "tag_languages[id]=123&" +
                "instance_accounts[domain]=mastodon.social&" +
                "instance_languages[domain]=mastodon.social"
            val startString = URLEncoder.encode(startAt.toString(), "utf-8")
            val endString = URLEncoder.encode(endAt.toString(), "utf-8")

            toQuery() shouldBeEqualTo "$dimensionsString&start_at=$startString&end_at=$endString"
        }
    }

    @Test
    fun `Given client returning success, when calling getDimensionalData for TAG_SERVERS, then ensure tag id is set`() {
        val client = MockClient.mock("admin_dimensions_get_dimensional_data_success.json")
        val adminDimensionMethods = AdminDimensionMethods(client)
        val dimensions: List<RequestDimension> = listOf(
            RequestDimension.TagServers(trendingTagId = "123")
        )
        val startAt = Instant.now().minusSeconds(600)
        val endAt = Instant.now()

        adminDimensionMethods.getDimensionalData(
            dimensions = dimensions,
            startAt = startAt,
            endAt = endAt
        ).execute()

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.post(
                path = "api/v1/admin/dimensions",
                body = capture(parametersCapturingSlot),
                addIdempotencyKey = false
            )
        }
        with(parametersCapturingSlot.captured) {
            val dimensionsString = "keys[]=tag_servers&tag_servers[id]=123"
            val startString = URLEncoder.encode(startAt.toString(), "utf-8")
            val endString = URLEncoder.encode(endAt.toString(), "utf-8")

            toQuery() shouldBeEqualTo "$dimensionsString&start_at=$startString&end_at=$endString"
        }
    }

    @Test
    fun `Given client returning success, when calling getDimensionalData for TAG_LANGUAGES with limit, then ensure tag id and limit are set`() {
        val client = MockClient.mock("admin_dimensions_get_dimensional_data_success.json")
        val adminDimensionMethods = AdminDimensionMethods(client)
        val dimensions: List<RequestDimension> = listOf(
            RequestDimension.TagLanguages(trendingTagId = "123", limit = 10)
        )
        val startAt = Instant.now().minusSeconds(600)
        val endAt = Instant.now()

        adminDimensionMethods.getDimensionalData(
            dimensions = dimensions,
            startAt = startAt,
            endAt = endAt
        ).execute()

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.post(
                path = "api/v1/admin/dimensions",
                body = capture(parametersCapturingSlot),
                addIdempotencyKey = false
            )
        }
        with(parametersCapturingSlot.captured) {
            val dimensionsString = "keys[]=tag_languages&tag_languages[id]=123&limit=10"
            val startString = URLEncoder.encode(startAt.toString(), "utf-8")
            val endString = URLEncoder.encode(endAt.toString(), "utf-8")

            toQuery() shouldBeEqualTo "$dimensionsString&start_at=$startString&end_at=$endString"
        }
    }

    @Test
    fun `Given a client returning forbidden, when getting dimensions data, then propagate error`() {
        val client = MockClient.failWithResponse(
            responseJsonAssetPath = "error_403_forbidden.json",
            responseCode = 403,
            message = "Forbidden"
        )

        invoking {
            AdminDimensionMethods(client).getDimensionalData(
                dimensions = listOf(RequestDimension.Languages),
                startAt = Instant.now().minusSeconds(600),
                endAt = Instant.now()
            ).execute()
        } shouldThrow BigBoneRequestException::class withMessage "Forbidden"
    }
}
