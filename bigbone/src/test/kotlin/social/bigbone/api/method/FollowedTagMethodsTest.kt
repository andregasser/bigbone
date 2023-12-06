package social.bigbone.api.method

import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeTrue
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test
import social.bigbone.Parameters
import social.bigbone.api.Pageable
import social.bigbone.api.entity.Tag
import social.bigbone.testtool.MockClient

class FollowedTagMethodsTest {
    @Test
    fun `Given a client returning success, when viewing all followed tags, then verify correct method was called and response can be parsed`() {
        val client = MockClient.mock("followed_tags_view_all_followed_tags_success.json")
        val followedTagMethods = FollowedTagMethods(client)

        val pageable: Pageable<Tag> = followedTagMethods.viewAllFollowedTags().execute()

        with(pageable.part[0]) {
            name shouldBeEqualTo "Test"
            url shouldBeEqualTo "http://mastodon.example/tags/test"

            with(history) {
                shouldHaveSize(7)
                get(0).day shouldBeEqualTo "1668556800"
                get(0).accounts shouldBeEqualTo "0"
                get(0).uses shouldBeEqualTo "0"
            }

            with(following) {
                shouldNotBeNull()
                shouldBeTrue()
            }
        }

        verify {
            client.get(
                path = "api/v1/followed_tags",
                query = any<Parameters>()
            )
        }
    }
}
