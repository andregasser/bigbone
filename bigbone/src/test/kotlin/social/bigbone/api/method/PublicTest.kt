package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigboneRequestException
import social.bigbone.testtool.MockClient
import social.bigbone.testtool.TestUtil
import java.util.concurrent.atomic.AtomicInteger

class PublicTest {
    @Test
    fun getInstance() {
        val client = MockClient.mock("instance.json")
        val publicMethod = Public(client)

        val instance = publicMethod.getInstance().execute()
        instance.uri shouldBeEqualTo "mastodon.social"
        instance.title shouldBeEqualTo "Mastodon"
        instance.description shouldBeEqualTo ""
        instance.email shouldBeEqualTo "staff@mastodon.social"
        instance.version shouldBeEqualTo "3.5.3"
    }

    @Test
    fun getInstanceWithJson() {
        val client = MockClient.mock("instance.json")
        val publicMethod = Public(client)
        publicMethod.getInstance()
            .doOnJson {
                val expected = """{
  "uri":"mastodon.social",
  "title":"Mastodon",
  "short_description":"The original server operated by the Mastodon gGmbH non-profit",
  "description":"",
  "email":"staff@mastodon.social",
  "version":"3.5.3",
  "urls":{
    "streaming_api":"wss://mastodon.social"
  },
  "stats":{
    "user_count":812303,
    "status_count":38151616,
    "domain_count":25255
  },
  "thumbnail":"https://files.mastodon.social/site_uploads/files/000/000/001/original/vlcsnap-2018-08-27-16h43m11s127.png",
  "languages":[
    "en"
  ],
  "registrations":false,
  "approval_required":false,
  "invites_enabled":true,
  "configuration":{
    "statuses":{
      "max_characters":500,
      "max_media_attachments":4,
      "characters_reserved_per_url":23
    },
    "media_attachments":{
      "supported_mime_types":[
        "image/jpeg",
        "image/png",
        "image/gif",
        "image/webp",
        "video/webm",
        "video/mp4",
        "video/quicktime",
        "video/ogg",
        "audio/wave",
        "audio/wav",
        "audio/x-wav",
        "audio/x-pn-wave",
        "audio/vnd.wave",
        "audio/ogg",
        "audio/vorbis",
        "audio/mpeg",
        "audio/mp3",
        "audio/webm",
        "audio/flac",
        "audio/aac",
        "audio/m4a",
        "audio/x-m4a",
        "audio/mp4",
        "audio/3gpp",
        "video/x-ms-asf"
      ],
      "image_size_limit":10485760,
      "image_matrix_limit":16777216,
      "video_size_limit":41943040,
      "video_frame_rate_limit":60,
      "video_matrix_limit":2304000
    },
    "polls":{
      "max_options":4,
      "max_characters_per_option":50,
      "min_expiration":300,
      "max_expiration":2629746
    }
  },
  "contact_account":{
    "id":"1",
    "username":"Gargron",
    "acct":"Gargron",
    "display_name":"Eugen",
    "locked":false,
    "bot":false,
    "discoverable":true,
    "group":false,
    "created_at":"2016-03-16T00:00:00.000Z",
    "note":"\u003cp\u003eFounder, CEO and lead developer \u003cspan class=\"h-card\"\u003e\u003ca href=\"https://mastodon.social/@Mastodon\" class=\"u-url mention\"\u003e@\u003cspan\u003eMastodon\u003c/span\u003e\u003c/a\u003e\u003c/span\u003e, Germany.\u003c/p\u003e",
    "url":"https://mastodon.social/@Gargron",
    "avatar":"https://files.mastodon.social/accounts/avatars/000/000/001/original/dc4286ceb8fab734.jpg",
    "avatar_static":"https://files.mastodon.social/accounts/avatars/000/000/001/original/dc4286ceb8fab734.jpg",
    "header":"https://files.mastodon.social/accounts/headers/000/000/001/original/3b91c9965d00888b.jpeg",
    "header_static":"https://files.mastodon.social/accounts/headers/000/000/001/original/3b91c9965d00888b.jpeg",
    "followers_count":118944,
    "following_count":305,
    "statuses_count":72309,
    "last_status_at":"2022-08-24",
    "emojis":[

    ],
    "fields":[
      {
        "name":"Patreon",
        "value":"\u003ca href=\"https://www.patreon.com/mastodon\" target=\"_blank\" rel=\"nofollow noopener noreferrer me\"\u003e\u003cspan class=\"invisible\"\u003ehttps://www.\u003c/span\u003e\u003cspan class=\"\"\u003epatreon.com/mastodon\u003c/span\u003e\u003cspan class=\"invisible\"\u003e\u003c/span\u003e\u003c/a\u003e",
        "verified_at":null
      }
    ]
  },
  "rules":[
    {
      "id":"1",
      "text":"Sexually explicit or violent media must be marked as sensitive when posting"
    },
    {
      "id":"2",
      "text":"No racism, sexism, homophobia, transphobia, xenophobia, or casteism"
    },
    {
      "id":"3",
      "text":"No incitement of violence or promotion of violent ideologies"
    },
    {
      "id":"4",
      "text":"No harassment, dogpiling or doxxing of other users"
    },
    {
      "id":"5",
      "text":"No content illegal in Germany"
    },
    {
      "id":"7",
      "text":"Do not share intentionally false or misleading information"
    }
  ]
}"""
                assertEquals(TestUtil.normalizeLineBreaks(it), TestUtil.normalizeLineBreaks(expected))
            }
            .execute()
    }

    @Test
    fun getInstanceWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val publicMethod = Public(client)
            publicMethod.getInstance().execute()
        }
    }

    @Test
    fun getSearch() {
        val client = MockClient.mock("search.json")

        val publicMethod = Public(client)
        val result = publicMethod.getSearch("test").execute()
        result.statuses.size shouldBeEqualTo 0
        result.accounts.size shouldBeEqualTo 6
        result.hashtags.size shouldBeEqualTo 5
        result.hashtags.size shouldBeEqualTo 5
    }

    @Test
    fun getSearchWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val publicMethod = Public(client)
            publicMethod.getSearch("test").execute()
        }
    }

    @Test
    fun getLocalPublic() {
        val client = MockClient.mock("public_timeline.json", maxId = "3", sinceId = "1")
        val publicMethod = Public(client)
        val statuses = publicMethod.getLocalPublic().execute()
        statuses.part.size shouldBeEqualTo 20
        statuses.link?.let {
            it.nextPath shouldBeEqualTo "<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&max_id=3>"
            it.maxId shouldBeEqualTo "3"
            it.prevPath shouldBeEqualTo "<https://mstdn.jp/api/v1/timelines/public?limit=20&local=true&since_id=1>"
            it.sinceId shouldBeEqualTo "1"
        }
    }

    @Test
    fun getLocalPublicWithJson() {
        val atomicInt = AtomicInteger(0)
        val client = MockClient.mock("public_timeline.json", maxId = "3", sinceId = "1")
        val publicMethod = Public(client)
        publicMethod.getLocalPublic()
            .doOnJson {
                atomicInt.incrementAndGet()
            }
            .execute()
        atomicInt.get() shouldBeEqualTo 20
    }

    @Test
    fun getLocalPublicWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val publicMethod = Public(client)
            publicMethod.getLocalPublic().execute()
        }
    }

    @Test
    fun getLocalTag() {
        val client = MockClient.mock("tag.json", maxId = "3", sinceId = "1")
        val publicMethod = Public(client)
        val statuses = publicMethod.getLocalTag("mastodon").execute()
        statuses.part.size shouldBeEqualTo 2
    }

    @Test
    fun getLocalTagWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val publicMethod = Public(client)
            publicMethod.getLocalTag("mastodon").execute()
        }
    }
}
