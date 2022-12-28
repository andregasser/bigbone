package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameter
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.entity.Card
import social.bigbone.api.entity.Context
import social.bigbone.api.entity.Status
import social.bigbone.api.exception.BigboneRequestException

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#statuses
 */
class Statuses(private val client: MastodonClient) {

    //  GET /api/v1/statuses/:id
    @Throws(BigboneRequestException::class)
    fun getStatus(statusId: String): MastodonRequest<Status> {
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
    @Throws(BigboneRequestException::class)
    fun getContext(statusId: String): MastodonRequest<Context> {
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
    @Throws(BigboneRequestException::class)
    fun getCard(statusId: String): MastodonRequest<Card> {
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
    @Throws(BigboneRequestException::class)
    fun getRebloggedBy(statusId: String, range: Range = Range()): MastodonRequest<Pageable<Account>> {
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
    @Throws(BigboneRequestException::class)
    fun getFavouritedBy(statusId: String, range: Range = Range()): MastodonRequest<Pageable<Account>> {
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
    @Throws(BigboneRequestException::class)
    fun postStatus(
        status: String,
        inReplyToId: String?,
        mediaIds: List<String>?,
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
    @Throws(BigboneRequestException::class)
    fun deleteStatus(statusId: String) {
        val response = client.delete("api/v1/statuses/$statusId")
        if (!response.isSuccessful) {
            throw BigboneRequestException(response)
        }
    }

    //  POST /api/v1/statuses/:id/reblog
    @Throws(BigboneRequestException::class)
    fun postReblog(statusId: String): MastodonRequest<Status> {
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
    @Throws(BigboneRequestException::class)
    fun postUnreblog(statusId: String): MastodonRequest<Status> {
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
    @Throws(BigboneRequestException::class)
    fun postFavourite(statusId: String): MastodonRequest<Status> {
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
    @Throws(BigboneRequestException::class)
    fun postUnfavourite(statusId: String): MastodonRequest<Status> {
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
