package social.bigbone.api.method

import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import social.bigbone.api.entity.FeaturedTag
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class FeaturedTagsMethodsTest {

    @Test
    fun `Given a client returning success, when getting featured_tags, then expect values of response`() {
        val client = MockClient.mock("featured_tags_view_success.json")

        val featuredTagsMethods = FeaturedTagsMethods(client)
        val featuredTags: List<FeaturedTag> = featuredTagsMethods.getFeaturedTags().execute()

        featuredTags.size shouldBeEqualTo 1
        val (id, name, url, statusesCount, lastStatusAt) = featuredTags.first()
        id shouldBeEqualTo "627"
        name shouldBeEqualTo "nowplaying"
        url shouldBeEqualTo "https://mastodon.example/@user/tagged/nowplaying"
        statusesCount shouldBeEqualTo 70
        lastStatusAt shouldBeEqualTo "2022-08-29T12:03:35.061Z"
    }

    @Test
    fun `Given a client returning unauthorized, when getting featured_tags, then propagate error`() {
        val client = MockClient.failWithResponse(
            responseJsonAssetPath = "error_401_unauthorized.json",
            responseCode = 401,
            message = "Unauthorized"
        )

        invoking {
            FeaturedTagsMethods(client).getFeaturedTags().execute()
        } shouldThrow BigBoneRequestException::class withMessage "Unauthorized"
    }


}