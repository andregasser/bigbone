package social.bigbone

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException

@SuppressWarnings("FunctionMaxLength")
class MastodonClientTest {

    private val invalidResponseBody = "{ \"foo\": \"bar\" }"
    private val v2InstanceResponseBody = """{
    "domain": "mastodon.social",
    "title": "Mastodon",
    "version": "4.0.0rc1",
    "source_url": "https://github.com/mastodon/mastodon",
    "description": "The original server operated by the Mastodon gGmbH non-profit",
    "usage": {
        "users": {
            "active_month": 148401
        }
    },
    "thumbnail": {
        "url": "https://files.mastodon.social/site_uploads/files/000/000/001/@1x/57c12f441d083cde.png",
        "blurhash": "UeKUpFxuo~R%0nW;WCnhF6RjaJt757oJodS${'$'}",
        "versions": {
            "@1x": "https://files.mastodon.social/site_uploads/files/000/000/001/@1x/57c12f441d083cde.png",
            "@2x": "https://files.mastodon.social/site_uploads/files/000/000/001/@2x/57c12f441d083cde.png"
        }
    },
    "languages": [
        "en"
    ],
    "configuration": {
        "urls": {
            "streaming": "wss://mastodon.social"
        },
        "accounts": {
            "max_featured_tags": 10
        },
        "statuses": {
            "max_characters": 500,
            "max_media_attachments": 4,
            "characters_reserved_per_url": 23
        },
        "media_attachments": {
            "supported_mime_types": [
                "image/jpeg",
                "image/png",
                "image/gif",
                "image/heic",
                "image/heif",
                "image/webp",
                "image/avif",
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
            "image_size_limit": 10485760,
            "image_matrix_limit": 16777216,
            "video_size_limit": 41943040,
            "video_frame_rate_limit": 60,
            "video_matrix_limit": 2304000
        },
        "polls": {
            "max_options": 4,
            "max_characters_per_option": 50,
            "min_expiration": 300,
            "max_expiration": 2629746
        },
        "translation": {
            "enabled": true
        }
    },
    "registrations": {
        "enabled": false,
        "approval_required": false,
        "message": null
    },
    "contact": {
        "email": "staff@mastodon.social",
        "account": {
            "id": "1",
            "username": "Gargron",
            "acct": "Gargron",
            "display_name": "Eugen Rochko",
            "locked": false,
            "bot": false,
            "discoverable": true,
            "group": false,
            "created_at": "2016-03-16T00:00:00.000Z",
            "note": "\u003cp\u003eFounder, CEO and lead developer \u003cspan class=\"h-card\"\u003e\u003ca href=\"https://mastodon.social/@Mastodon\" class=\"u-url mention\"\u003e@\u003cspan\u003eMastodon\u003c/span\u003e\u003c/a\u003e\u003c/span\u003e, Germany.\u003c/p\u003e",
            "url": "https://mastodon.social/@Gargron",
            "avatar": "https://files.mastodon.social/accounts/avatars/000/000/001/original/dc4286ceb8fab734.jpg",
            "avatar_static": "https://files.mastodon.social/accounts/avatars/000/000/001/original/dc4286ceb8fab734.jpg",
            "header": "https://files.mastodon.social/accounts/headers/000/000/001/original/3b91c9965d00888b.jpeg",
            "header_static": "https://files.mastodon.social/accounts/headers/000/000/001/original/3b91c9965d00888b.jpeg",
            "followers_count": 288544,
            "following_count": 342,
            "statuses_count": 73066,
            "last_status_at": "2023-01-17",
            "noindex": false,
            "emojis": [],
            "fields": [
                {
                    "name": "Patreon",
                    "value": "\u003ca href=\"https://www.patreon.com/mastodon\" target=\"_blank\" rel=\"nofollow noopener noreferrer me\"\u003e\u003cspan class=\"invisible\"\u003ehttps://www.\u003c/span\u003e\u003cspan class=\"\"\u003epatreon.com/mastodon\u003c/span\u003e\u003cspan class=\"invisible\"\u003e\u003c/span\u003e\u003c/a\u003e",
                    "verified_at": null
                }
            ]
        }
    },
    "rules": [
        {
            "id": "1",
            "text": "Sexually explicit or violent media must be marked as sensitive when posting"
        },
        {
            "id": "2",
            "text": "No racism, sexism, homophobia, transphobia, xenophobia, or casteism"
        },
        {
            "id": "3",
            "text": "No incitement of violence or promotion of violent ideologies"
        },
        {
            "id": "4",
            "text": "No harassment, dogpiling or doxxing of other users"
        },
        {
            "id": "5",
            "text": "No content illegal in Germany"
        },
        {
            "id": "7",
            "text": "Do not share intentionally false or misleading information"
        }
    ]
}    
    """
    private val v1InstanceResponseBody = """{
    "uri": "mastodon.social",
    "title": "Mastodon",
    "short_description": "The original server operated by the Mastodon gGmbH non-profit",
    "description": "",
    "email": "staff@mastodon.social",
    "version": "4.0.0rc1",
    "urls": {
        "streaming_api": "wss://mastodon.social"
    },
    "stats": {
        "user_count": 921487,
        "status_count": 48629230,
        "domain_count": 48722
    },
    "thumbnail": "https://files.mastodon.social/site_uploads/files/000/000/001/@1x/57c12f441d083cde.png",
    "languages": [
        "en"
    ],
    "registrations": false,
    "approval_required": false,
    "invites_enabled": true,
    "configuration": {
        "accounts": {
            "max_featured_tags": 10
        },
        "statuses": {
            "max_characters": 500,
            "max_media_attachments": 4,
            "characters_reserved_per_url": 23
        },
        "media_attachments": {
            "supported_mime_types": [
                "image/jpeg",
                "image/png",
                "image/gif",
                "image/heic",
                "image/heif",
                "image/webp",
                "image/avif",
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
            "image_size_limit": 10485760,
            "image_matrix_limit": 16777216,
            "video_size_limit": 41943040,
            "video_frame_rate_limit": 60,
            "video_matrix_limit": 2304000
        },
        "polls": {
            "max_options": 4,
            "max_characters_per_option": 50,
            "min_expiration": 300,
            "max_expiration": 2629746
        }
    },
    "contact_account": {
        "id": "1",
        "username": "Gargron",
        "acct": "Gargron",
        "display_name": "Eugen Rochko",
        "locked": false,
        "bot": false,
        "discoverable": true,
        "group": false,
        "created_at": "2016-03-16T00:00:00.000Z",
        "note": "\u003cp\u003eFounder, CEO and lead developer \u003cspan class=\"h-card\"\u003e\u003ca href=\"https://mastodon.social/@Mastodon\" class=\"u-url mention\"\u003e@\u003cspan\u003eMastodon\u003c/span\u003e\u003c/a\u003e\u003c/span\u003e, Germany.\u003c/p\u003e",
        "url": "https://mastodon.social/@Gargron",
        "avatar": "https://files.mastodon.social/accounts/avatars/000/000/001/original/dc4286ceb8fab734.jpg",
        "avatar_static": "https://files.mastodon.social/accounts/avatars/000/000/001/original/dc4286ceb8fab734.jpg",
        "header": "https://files.mastodon.social/accounts/headers/000/000/001/original/3b91c9965d00888b.jpeg",
        "header_static": "https://files.mastodon.social/accounts/headers/000/000/001/original/3b91c9965d00888b.jpeg",
        "followers_count": 288547,
        "following_count": 342,
        "statuses_count": 73066,
        "last_status_at": "2023-01-17",
        "noindex": false,
        "emojis": [],
        "fields": [
            {
                "name": "Patreon",
                "value": "\u003ca href=\"https://www.patreon.com/mastodon\" target=\"_blank\" rel=\"nofollow noopener noreferrer me\"\u003e\u003cspan class=\"invisible\"\u003ehttps://www.\u003c/span\u003e\u003cspan class=\"\"\u003epatreon.com/mastodon\u003c/span\u003e\u003cspan class=\"invisible\"\u003e\u003c/span\u003e\u003c/a\u003e",
                "verified_at": null
            }
        ]
    },
    "rules": [
        {
            "id": "1",
            "text": "Sexually explicit or violent media must be marked as sensitive when posting"
        },
        {
            "id": "2",
            "text": "No racism, sexism, homophobia, transphobia, xenophobia, or casteism"
        },
        {
            "id": "3",
            "text": "No incitement of violence or promotion of violent ideologies"
        },
        {
            "id": "4",
            "text": "No harassment, dogpiling or doxxing of other users"
        },
        {
            "id": "5",
            "text": "No content illegal in Germany"
        },
        {
            "id": "7",
            "text": "Do not share intentionally false or misleading information"
        }
    ]
}       
    """

    @Test
    fun `should return version of v2 endpoint if available`() {
        // given
        val responseBodyV2Mock = mockk<ResponseBody>()
        every { responseBodyV2Mock.string() } answers { v2InstanceResponseBody }
        val responseV2Mock = mockk<Response>()
        every { responseV2Mock.body } answers { responseBodyV2Mock }
        every { responseV2Mock.isSuccessful } answers { true }

        val responseV1Mock = mockk<Response>()

        val clientBuilder = spyk(MastodonClient.Builder("foo.bar"))
        every { clientBuilder.versionedInstanceRequest(1) } answers { responseV1Mock }
        every { clientBuilder.versionedInstanceRequest(2) } answers { responseV2Mock }

        // when
        val client = clientBuilder.build()

        // then
        client.getInstanceVersion() shouldBeEqualTo "4.0.0rc1"
    }

    @Test
    fun `should return version of v1 endpoint if v2 endpoint is not available`() {
        // given
        val responseV2Mock = mockk<Response>()
        every { responseV2Mock.isSuccessful } answers { false }

        val responseBodyV1Mock = mockk<ResponseBody>()
        every { responseBodyV1Mock.string() } answers { v1InstanceResponseBody }
        val responseV1Mock = mockk<Response>()
        every { responseV1Mock.isSuccessful } answers { true }
        every { responseV1Mock.body } answers { responseBodyV1Mock }

        val clientBuilder = spyk(MastodonClient.Builder("foo.bar"))
        every { clientBuilder.versionedInstanceRequest(1) } answers { responseV1Mock }
        every { clientBuilder.versionedInstanceRequest(2) } answers { responseV2Mock }

        // when
        val client = clientBuilder.build()

        // then
        client.getInstanceVersion() shouldBeEqualTo "4.0.0rc1"
    }

    @Test
    fun `should throw exception when instance version cannot be found in response body`() {
        // given
        val clientBuilder = spyk(MastodonClient.Builder("foo.bar"))
        val responseMock = mockk<Response>()
        every { responseMock.body } answers { invalidResponseBody.toResponseBody("application/json".toMediaType()) }
        every { responseMock.isSuccessful } answers { true }
        every { clientBuilder.versionedInstanceRequest(any()) } answers { responseMock }

        // when / then
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            clientBuilder.build()
        }
    }
}
