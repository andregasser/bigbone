package social.bigbone.api.method

import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.junit.jupiter.api.Test
import social.bigbone.Parameters
import social.bigbone.api.entity.OEmbedMetadata
import social.bigbone.testtool.MockClient

class OEmbedMethodsTest {

    @Test
    fun `Given a client returning successfully, when getting oEmbed info, then ensure correct parsing and correct method was called`() {
        val client = MockClient.mock("oembed_get_success.json")
        val oEmbedMethods = OEmbedMethods(client)
        val urlOfStatus: String = "https://mastodon.social/@trwnh/99664077509711321"
        val maxWidth: Int = 640
        val maxHeight: Int? = null

        val meta: OEmbedMetadata = oEmbedMethods
            .getOEmbedInfoAsJson(urlOfStatus = urlOfStatus, maxWidth = maxWidth, maxHeight = maxHeight)
            .execute()

        with(meta) {
            type shouldBeEqualTo "rich"
            version shouldBeEqualTo "1.0"
            title shouldBeEqualTo "New status by trwnh"
            authorName shouldBeEqualTo "infinite love ⴳ"
            authorUrl shouldBeEqualTo "https://mastodon.social/@trwnh"
            providerName shouldBeEqualTo "mastodon.social"
            providerUrl shouldBeEqualTo "https://mastodon.social/"
            cacheAge shouldBeEqualTo 86_400
            html shouldBeEqualTo "<iframe " +
                "src=\"https://mastodon.social/@trwnh/99664077509711321/embed\" " +
                "class=\"mastodon-embed\" style=\"max-width: 100%; border: 0\" width=\"400\" " +
                "allowfullscreen=\"allowfullscreen\">" +
                "</iframe>" +
                "<script src=\"https://mastodon.social/embed.js\" async=\"async\"></script>"
            width shouldBeEqualTo 400
            height.shouldBeNull()
        }

        verify {
            client.get(
                path = "api/oembed",
                query = any<Parameters>()
            )
        }
    }
}
