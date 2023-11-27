@file:Suppress("MaxLineLength")

package social.bigbone.api.method

import io.mockk.Called
import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeFalse
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldBeNullOrEmpty
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldNotBeNull
import org.amshove.kluent.shouldNotBeNullOrEmpty
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import social.bigbone.Parameters
import social.bigbone.PrecisionDateTime.ValidPrecisionDateTime.ExactTime
import social.bigbone.api.entity.PreviewCard
import social.bigbone.api.entity.Status
import social.bigbone.api.entity.Tag
import social.bigbone.api.entity.TrendsLink
import social.bigbone.testtool.MockClient
import java.time.Instant

class TrendMethodsTest {

    @Test
    fun `Given a client returning success, when getting trending tags with default limit, then request correct endpoint and serialise response correctly`() {
        val client = MockClient.mock(jsonName = "trends_view_trending_tags_success.json")
        val trendMethods = TrendMethods(client)

        val tags: List<Tag> = trendMethods.getTrendingTags().execute()

        with(tags) {
            shouldHaveSize(3)

            get(0).name shouldBeEqualTo "hola"
            get(1).name shouldBeEqualTo "SaveDotOrg"
            get(2).name shouldBeEqualTo "introduction"

            get(0).url shouldBeEqualTo "https://mastodon.social/tags/hola"
            get(1).url shouldBeEqualTo "https://mastodon.social/tags/SaveDotOrg"
            get(2).url shouldBeEqualTo "https://mastodon.social/tags/introduction"

            get(0).history shouldHaveSize 1
            get(0).history[0].day shouldBeEqualTo "1574726400"
            get(0).history[0].uses shouldBeEqualTo "13"
            get(0).history[0].accounts shouldBeEqualTo "10"

            get(1).history shouldHaveSize 1
            get(1).history[0].day shouldBeEqualTo "1574726400"
            get(1).history[0].uses shouldBeEqualTo "9"
            get(1).history[0].accounts shouldBeEqualTo "9"

            get(2).history shouldHaveSize 1
            get(2).history[0].day shouldBeEqualTo "1574726400"
            get(2).history[0].uses shouldBeEqualTo "15"
            get(2).history[0].accounts shouldBeEqualTo "14"
        }

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.get(
                path = "api/v1/trends/tags",
                query = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "limit=10"
        }
    }

    @Test
    fun `Given a client returning success, when getting trending tags with too high a limit, then fail with IllegalArgumentException and don't call endpoint`() {
        val client = MockClient.mock(jsonName = "trends_view_trending_tags_success.json")
        val trendMethods = TrendMethods(client)
        val requestedLimit = 42

        invoking {
            trendMethods.getTrendingTags(limit = requestedLimit).execute()
        } shouldThrow IllegalArgumentException::class withMessage "Limit must not be larger than 20 but was $requestedLimit"

        verify { client wasNot Called }
    }

    @Test
    fun `Given a client returning success, when getting trending statuses with default limit, then request correct endpoint and serialise response correctly`() {
        val client = MockClient.mock(jsonName = "trends_view_trending_statuses_success.json")
        val trendMethods = TrendMethods(client)

        val statuses: List<Status> = trendMethods.getTrendingStatuses().execute()

        with(statuses) {
            shouldHaveSize(1)

            with(get(0)) {
                id shouldBeEqualTo "108910940413327534"
                createdAt shouldBeEqualTo ExactTime(Instant.parse("2022-08-30T08:44:26.366Z"))
                inReplyToId.shouldBeNull()
                inReplyToAccountId.shouldBeNull()
                isSensitive.shouldBeFalse()
                content.shouldNotBeNullOrEmpty()
            }
        }

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.get(
                path = "api/v1/trends/statuses",
                query = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "limit=20"
        }
    }

    @Test
    fun `Given a client returning success, when getting trending statuses with too high a limit, then fail with IllegalArgumentException and don't call endpoint`() {
        val client = MockClient.mock(jsonName = "trends_view_trending_statuses_success.json")
        val trendMethods = TrendMethods(client)
        val requestedLimit = 42

        invoking {
            trendMethods.getTrendingStatuses(limit = requestedLimit).execute()
        } shouldThrow IllegalArgumentException::class withMessage "Limit must not be larger than 40 but was $requestedLimit"

        verify { client wasNot Called }
    }

    @Test
    fun `Given a client returning success, when getting trending links with default limit, then request correct endpoint and serialise response correctly`() {
        val client = MockClient.mock(jsonName = "trends_view_trending_links_success.json")
        val trendMethods = TrendMethods(client)

        val trendsLinks: List<TrendsLink> = trendMethods.getTrendingLinks().execute()

        with(trendsLinks) {
            shouldHaveSize(1)

            with(get(0)) {
                url.shouldNotBeNullOrEmpty()
                title shouldBeEqualTo "Plan Your Vote: 2022 Elections"
                description.shouldNotBeNullOrEmpty()
                type shouldBeEqualTo PreviewCard.CardType.LINK
                authorName shouldBeEqualTo "NBC News"
                authorUrl.shouldBeNullOrEmpty()
                providerName shouldBeEqualTo "NBC News"
                providerUrl.shouldBeNullOrEmpty()
                html.shouldBeNullOrEmpty()
                width shouldBeEqualTo 400
                height shouldBeEqualTo 225
                image.shouldNotBeNullOrEmpty()
                embedUrl.shouldBeNullOrEmpty()
                blurhash shouldBeEqualTo "UcQmF#ay~qofj[WBj[j[~qof9Fayofofayay"

                with(history) {
                    shouldNotBeNull()
                    shouldHaveSize(7)

                    get(0).day shouldBeEqualTo "1661817600"
                    get(0).accounts shouldBeEqualTo "7"
                    get(0).uses shouldBeEqualTo "7"

                    get(6).day shouldBeEqualTo "1661299200"
                    get(6).accounts shouldBeEqualTo "0"
                    get(6).uses shouldBeEqualTo "0"
                }
            }
        }

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.get(
                path = "api/v1/trends/links",
                query = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "limit=10"
        }
    }

    @Test
    fun `Given a client returning success, when getting trending links with too high a limit, then fail with IllegalArgumentException and don't call endpoint`() {
        val client = MockClient.mock(jsonName = "trends_view_trending_links_success.json")
        val trendMethods = TrendMethods(client)
        val requestedLimit = 42

        invoking {
            trendMethods.getTrendingLinks(limit = requestedLimit).execute()
        } shouldThrow IllegalArgumentException::class withMessage "Limit must not be larger than 20 but was $requestedLimit"

        verify { client wasNot Called }
    }
}
