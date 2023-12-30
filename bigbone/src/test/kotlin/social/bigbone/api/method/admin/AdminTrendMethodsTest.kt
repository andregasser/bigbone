package social.bigbone.api.method.admin

import io.mockk.verify
import org.amshove.kluent.shouldBeBlank
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeFalse
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldBeTrue
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldNotBeNull
import org.amshove.kluent.shouldNotBeNullOrBlank
import org.junit.jupiter.api.Test
import social.bigbone.PrecisionDateTime.ValidPrecisionDateTime.ExactTime
import social.bigbone.api.entity.PreviewCard
import social.bigbone.api.entity.Status
import social.bigbone.api.entity.TrendsLink
import social.bigbone.api.entity.admin.AdminTag
import social.bigbone.testtool.MockClient
import java.time.Instant

class AdminTrendMethodsTest {

    @Test
    fun `Given client returning success, when getting trending links, then ensure parsing and correct endpoint`() {
        val client = MockClient.mock("admin_trends_trending_links_success.json")
        val adminTrendMethods = AdminTrendMethods(client)

        val trendingLinks: List<TrendsLink> = adminTrendMethods.getTrendingLinks().execute()
        with(trendingLinks) {
            shouldHaveSize(1)

            with(get(0)) {
                url.shouldNotBeNullOrBlank()
                title shouldBeEqualTo "ARuFa on Twitter"
                description.shouldNotBeNullOrBlank()
                type shouldBeEqualTo PreviewCard.CardType.LINK
                authorName?.shouldBeBlank()
                authorUrl?.shouldBeBlank()
                providerName shouldBeEqualTo "Twitter"
                providerUrl?.shouldBeBlank()
                html?.shouldBeBlank()
                width shouldBeEqualTo 400
                height shouldBeEqualTo 225
                image.shouldNotBeNullOrBlank()
                embedUrl?.shouldBeBlank()
                blurhash shouldBeEqualTo "UNFiDM~o-oD%x[xtaxM|xaNHRkjsoft7ofWB"

                history?.shouldHaveSize(7)
                history?.get(0)?.day shouldBeEqualTo "1669507200"
                history?.get(0)?.accounts shouldBeEqualTo "9"
                history?.get(0)?.uses shouldBeEqualTo "9"
            }
        }

        verify {
            client.get(
                path = "api/v1/admin/trends/links",
                query = null
            )
        }
    }

    @Test
    fun `Given client returning success, when getting trending statuses, then ensure parsing and correct endpoint`() {
        val client = MockClient.mock("admin_trends_trending_statuses_success.json")
        val adminTrendMethods = AdminTrendMethods(client)

        val trendingStatuses: List<Status> = adminTrendMethods.getTrendingStatuses().execute()
        with(trendingStatuses) {
            shouldHaveSize(1)

            with(get(0)) {
                id shouldBeEqualTo "109415512969053017"
                createdAt shouldBeEqualTo ExactTime(Instant.parse("2022-11-27T11:23:52.000Z"))
                inReplyToId.shouldBeNull()
                inReplyToAccountId.shouldBeNull()
                account.shouldNotBeNull()
                account?.id shouldBeEqualTo "109332240210946752"
                mediaAttachments.shouldBeEmpty()
                mentions.shouldBeEmpty()
                tags.shouldBeEmpty()
                emojis.shouldBeEmpty()
                card.shouldBeNull()
                poll.shouldBeNull()
            }
        }

        verify {
            client.get(
                path = "api/v1/admin/trends/statuses",
                query = null
            )
        }
    }

    @Test
    fun `Given client returning success, when getting trending tags, then ensure parsing and correct endpoint`() {
        val client = MockClient.mock("admin_trends_trending_tags_success.json")
        val adminTrendMethods = AdminTrendMethods(client)

        val trendingTags: List<AdminTag> = adminTrendMethods.getTrendingTags().execute()
        with(trendingTags) {
            shouldHaveSize(1)

            with(get(0)) {
                name shouldBeEqualTo "caturday"
                url shouldBeEqualTo "https://mastodon.example/tags/caturday"

                history.shouldHaveSize(7)
                history[0].day shouldBeEqualTo "1669507200"
                history[0].accounts shouldBeEqualTo "53"
                history[0].uses shouldBeEqualTo "56"

                id shouldBeEqualTo "802"
                trendable.shouldBeTrue()
                usable.shouldBeTrue()
                requiresReview.shouldBeFalse()
            }
        }

        verify {
            client.get(
                path = "api/v1/admin/trends/tags",
                query = null
            )
        }
    }
}
