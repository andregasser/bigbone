package social.bigbone.api.method

import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldNotThrow
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import social.bigbone.PrecisionDateTime.ValidPrecisionDateTime.ExactTime
import social.bigbone.api.entity.FeaturedTag
import social.bigbone.api.entity.Tag
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient
import java.time.Instant

class FeaturedTagMethodsTest {

    @Test
    fun `Given a client returning success, when getting featured_tags, then expect values of response`() {
        val client = MockClient.mock("featured_tags_view_success.json")

        val featuredTagMethods = FeaturedTagMethods(client)
        val featuredTags: List<FeaturedTag> = featuredTagMethods.getFeaturedTags().execute()

        featuredTags.size shouldBeEqualTo 1
        val (id, name, url, statusesCount, lastStatusAt) = featuredTags.first()
        id shouldBeEqualTo "627"
        name shouldBeEqualTo "nowplaying"
        url shouldBeEqualTo "https://mastodon.example/@user/tagged/nowplaying"
        statusesCount shouldBeEqualTo 70
        lastStatusAt shouldBeEqualTo ExactTime(Instant.parse("2022-08-29T12:03:35.061Z"))
    }

    @Test
    fun `Given a client returning unauthorized, when getting featured_tags, then propagate error`() {
        val client = MockClient.failWithResponse(
            responseJsonAssetPath = "error_401_unauthorized.json",
            responseCode = 401,
            message = "Unauthorized"
        )

        invoking {
            FeaturedTagMethods(client).getFeaturedTags().execute()
        } shouldThrow BigBoneRequestException::class withMessage "Unauthorized"
    }

    @Test
    fun `Given a client returning success, when featuring a tag, then expect values of response`() {
        val client = MockClient.mock("featured_tags_post_success.json")

        val featuredTagMethods = FeaturedTagMethods(client)
        val (id, name, url, statusesCount, lastStatusAt) = featuredTagMethods
            .featureTag("nowplaying")
            .execute()

        id shouldBeEqualTo "13174"
        name shouldBeEqualTo "nowplaying"
        url shouldBeEqualTo "https://mastodon.example/@user/tagged/nowplaying"
        statusesCount shouldBeEqualTo 23
        lastStatusAt shouldBeEqualTo ExactTime(Instant.parse("2021-10-22T14:47:35.357Z"))
    }

    @Test
    fun `Given a client returning success, when attempting to feature a tag starting with #, then throw IllegalArgumentException`() {
        val client = MockClient.mock("featured_tags_post_success.json")

        val featuredTagMethods = FeaturedTagMethods(client)
        invoking {
            featuredTagMethods.featureTag("#nowplaying").execute()
        } shouldThrow IllegalArgumentException::class withMessage "Tag name to be featured must not contain '#'"
    }

    @Test
    fun `Given a client returning unprocessable entity, when featuring a tag, then propagate error`() {
        val client = MockClient.failWithResponse(
            responseJsonAssetPath = "featured_tags_post_error_422.json",
            responseCode = 422,
            message = "Unprocessable entity"
        )

        invoking {
            FeaturedTagMethods(client).featureTag("nowplaying").execute()
        } shouldThrow BigBoneRequestException::class withMessage "Unprocessable entity"
    }

    @Test
    fun `Given a client returning success, when unfeaturing a tag, then expect no errors`() {
        val client = MockClient.mock("featured_tags_delete_success.json")

        val featuredTagMethods = FeaturedTagMethods(client)
        invoking { featuredTagMethods.unfeatureTag("12345") } shouldNotThrow BigBoneRequestException::class
    }

    @Test
    fun `Given a client returning success, when attempting to unfeature a tag with blank tag id, then throw IllegalArgumentException`() {
        val client = MockClient.mock("featured_tags_delete_success.json")

        val featuredTagMethods = FeaturedTagMethods(client)
        invoking {
            featuredTagMethods.unfeatureTag("")
        } shouldThrow IllegalArgumentException::class withMessage "Tag ID must not be blank"

        invoking {
            featuredTagMethods.unfeatureTag("    ")
        } shouldThrow IllegalArgumentException::class withMessage "Tag ID must not be blank"
    }

    @Test
    fun `Given a client returning Not found, when unfeaturing a tag, then propagate error`() {
        val client = MockClient.failWithResponse(
            responseJsonAssetPath = "error_404.json",
            responseCode = 404,
            message = "Not found"
        )

        invoking {
            FeaturedTagMethods(client).featureTag("12345").execute()
        } shouldThrow BigBoneRequestException::class withMessage "Not found"
    }

    @Test
    fun `Given a client returning success, when getting suggested featured_tags, then expect values of response`() {
        val client = MockClient.mock("featured_tags_view_suggested_success.json")

        val featuredTagMethods = FeaturedTagMethods(client)
        val suggestions: List<Tag> = featuredTagMethods.getSuggestedTags().execute()

        suggestions.size shouldBeEqualTo 2

        val (name0, url0, history0, following0) = suggestions[0]
        name0 shouldBeEqualTo "nowplaying"
        url0 shouldBeEqualTo "https://mastodon.example/tags/nowplaying"
        history0.size shouldBeEqualTo 7
        history0[0].day shouldBeEqualTo "1574553600"
        history0[0].accounts shouldBeEqualTo "31"
        history0[0].uses shouldBeEqualTo "200"
        following0.shouldBeNull()

        val (name1, url1, history1, following1) = suggestions[1]
        name1 shouldBeEqualTo "mastothemes"
        url1 shouldBeEqualTo "https://mastodon.example/tags/mastothemes"
        history1.size shouldBeEqualTo 5
        history1[0].day shouldBeEqualTo "1574553600"
        history1[0].accounts shouldBeEqualTo "0"
        history1[0].uses shouldBeEqualTo "0"
        following1.shouldBeNull()
    }
}
