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
import social.bigbone.api.exception.BigBoneRequestException

/**
 * Allows access to API methods with endpoints having an "api/vX/statuses" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/statuses/">Mastodon statuses API methods</a>
 */
class StatusesMethods(private val client: MastodonClient) {

    //  GET /api/v1/statuses/:id
    @Throws(BigBoneRequestException::class)
    fun getStatus(statusId: String): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId",
            method = MastodonClient.Method.GET
        )
    }

    //  GET /api/v1/statuses/:id/context
    @Throws(BigBoneRequestException::class)
    fun getContext(statusId: String): MastodonRequest<Context> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/context",
            method = MastodonClient.Method.GET
        )
    }

    //  GET /api/v1/statuses/:id/card
    @Throws(BigBoneRequestException::class)
    fun getCard(statusId: String): MastodonRequest<Card> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/card",
            method = MastodonClient.Method.GET
        )
    }

    //  GET /api/v1/reblogged_by
    @JvmOverloads
    @Throws(BigBoneRequestException::class)
    fun getRebloggedBy(statusId: String, range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/reblogged_by",
            method = MastodonClient.Method.GET,
            parameters = range.toParameter()
        )
    }

    //  GET /api/v1/favourited_by
    @JvmOverloads
    @Throws(BigBoneRequestException::class)
    fun getFavouritedBy(statusId: String, range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/favourited_by",
            method = MastodonClient.Method.GET,
            parameters = range.toParameter()
        )
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
    @Throws(BigBoneRequestException::class)
    fun postStatus(
        status: String,
        inReplyToId: String?,
        mediaIds: List<String>?,
        sensitive: Boolean,
        spoilerText: String?,
        visibility: Status.Visibility = Status.Visibility.Public
    ): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses",
            method = MastodonClient.Method.POST,
            parameters = Parameter().apply {
                append("status", status)
                inReplyToId?.let { append("in_reply_to_id", it) }
                mediaIds?.let { append("media_ids", it) }
                append("sensitive", sensitive)
                spoilerText?.let { append("spoiler_text", it) }
                append("visibility", visibility.value)
            }
        )
    }

    //  DELETE /api/v1/statuses/:id
    @Throws(BigBoneRequestException::class)
    fun deleteStatus(statusId: String) {
        client.performAction(
            endpoint = "api/v1/statuses/$statusId",
            method = MastodonClient.Method.DELETE
        )
    }

    //  POST /api/v1/statuses/:id/reblog
    @Throws(BigBoneRequestException::class)
    fun reblogStatus(statusId: String): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/reblog",
            method = MastodonClient.Method.POST
        )
    }

    //  POST /api/v1/statuses/:id/unreblog
    @Throws(BigBoneRequestException::class)
    fun unreblogStatus(statusId: String): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/unreblog",
            method = MastodonClient.Method.POST
        )
    }

    //  POST /api/v1/statuses/:id/favourite
    @Throws(BigBoneRequestException::class)
    fun favouriteStatus(statusId: String): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/favourite",
            method = MastodonClient.Method.POST
        )
    }

    //  POST /api/v1/statuses/:id/unfavourite
    @Throws(BigBoneRequestException::class)
    fun unfavouriteStatus(statusId: String): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/unfavourite",
            method = MastodonClient.Method.POST
        )
    }
}
