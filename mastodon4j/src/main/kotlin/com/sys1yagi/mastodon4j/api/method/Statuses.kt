package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.entity.Card
import com.sys1yagi.mastodon4j.api.entity.Context
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#statuses
 */
class Statuses(private val client: MastodonClient) {

    //  GET /api/v1/statuses/:id
    @Throws(Mastodon4jRequestException::class)
    fun getStatus(statusId: Long): MastodonRequest<Status> {
        return MastodonRequest<Status>(
            {
                client.get("api/v1/statuses/$statusId")
            },
            {
                client.getSerializer().fromJson(it, Status::class.java)
            }
        )
    }

    //  GET /api/v1/statuses/:id/context
    @Throws(Mastodon4jRequestException::class)
    fun getContext(statusId: Long): MastodonRequest<Context> {
        return MastodonRequest<Context>(
            {
                client.get("api/v1/statuses/$statusId/context")
            },
            {
                client.getSerializer().fromJson(it, Context::class.java)
            }
        )
    }

    //  GET /api/v1/statuses/:id/card
    @Throws(Mastodon4jRequestException::class)
    fun getCard(statusId: Long): MastodonRequest<Card> {
        return MastodonRequest<Card>(
            {
                client.get("api/v1/statuses/$statusId/card")
            },
            {
                client.getSerializer().fromJson(it, Card::class.java)
            }
        )
    }

    //  GET /api/v1/reblogged_by
    @JvmOverloads
    @Throws(Mastodon4jRequestException::class)
    fun getRebloggedBy(statusId: Long, range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return MastodonRequest<Pageable<Account>>(
            {
                client.get(
                    "api/v1/statuses/$statusId/reblogged_by",
                    range.toParameter()
                )
            },
            {
                client.getSerializer().fromJson(it, Account::class.java)
            }
        ).toPageable()
    }

    //  GET /api/v1/favourited_by
    @JvmOverloads
    @Throws(Mastodon4jRequestException::class)
    fun getFavouritedBy(statusId: Long, range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return MastodonRequest<Pageable<Account>>(
            {
                client.get(
                    "api/v1/statuses/$statusId/favourited_by",
                    range.toParameter()
                )
            },
            {
                client.getSerializer().fromJson(it, Account::class.java)
            }
        ).toPageable()
    }

    /**
     * POST /api/v1/status
     * status: The text of the status
     * in_reply_to_id (optional): local ID of the status you want to reply to
     * media_ids (optional): array of media IDs to attach to the status (maximum 4)
     * sensitive (optional): set this to mark the media of the status as NSFW
     * spoiler_text (optional): text to be shown as a warning before the actual content
     * visibility (optional): either "direct", "private", "unlisted" or "public"
     */
    @JvmOverloads
    @Throws(Mastodon4jRequestException::class)
    fun postStatus(
        status: String,
        inReplyToId: Long?,
        mediaIds: List<Long>?,
        sensitive: Boolean,
        spoilerText: String?,
        visibility: Status.Visibility = Status.Visibility.Public
    ): MastodonRequest<Status> {
        val parameters = Parameter().apply {
            append("status", status)
            inReplyToId?.let {
                append("in_reply_to_id", it)
            }
            mediaIds?.let {
                append("media_ids", it)
            }
            append("sensitive", sensitive)
            spoilerText?.let {
                append("spoiler_text", it)
            }
            append("visibility", visibility.value)
        }

        return MastodonRequest<Status>(
            {
                client.post("api/v1/statuses", parameters)
            },
            {
                client.getSerializer().fromJson(it, Status::class.java)
            }
        )
    }

    //  DELETE /api/v1/statuses/:id
    @Throws(Mastodon4jRequestException::class)
    fun deleteStatus(statusId: Long) {
        val response = client.delete("api/v1/statuses/$statusId")
        if (!response.isSuccessful) {
            throw Mastodon4jRequestException(response)
        }
    }

    //  POST /api/v1/statuses/:id/reblog
    @Throws(Mastodon4jRequestException::class)
    fun postReblog(statusId: Long): MastodonRequest<Status> {
        return MastodonRequest<Status>(
            {
                client.post("api/v1/statuses/$statusId/reblog")
            },
            {
                client.getSerializer().fromJson(it, Status::class.java)
            }
        )
    }

    //  POST /api/v1/statuses/:id/unreblog
    @Throws(Mastodon4jRequestException::class)
    fun postUnreblog(statusId: Long): MastodonRequest<Status> {
        return MastodonRequest<Status>(
            {
                client.post("api/v1/statuses/$statusId/unreblog")
            },
            {
                client.getSerializer().fromJson(it, Status::class.java)
            }
        )
    }

    //  POST /api/v1/statuses/:id/favourite
    @Throws(Mastodon4jRequestException::class)
    fun postFavourite(statusId: Long): MastodonRequest<Status> {
        return MastodonRequest<Status>(
            {
                client.post("api/v1/statuses/$statusId/favourite")
            },
            {
                client.getSerializer().fromJson(it, Status::class.java)
            }
        )
    }

    //  POST /api/v1/statuses/:id/unfavourite
    @Throws(Mastodon4jRequestException::class)
    fun postUnfavourite(statusId: Long): MastodonRequest<Status> {
        return MastodonRequest<Status>(
            {
                client.post("api/v1/statuses/$statusId/unfavourite")
            },
            {
                client.getSerializer().fromJson(it, Status::class.java)
            }
        )
    }
}
