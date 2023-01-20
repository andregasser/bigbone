package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
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
class StatusMethods(private val client: MastodonClient) {

    /**
     * Obtain information about a status.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#get">Mastodon API documentation: methods/statuses/#get</a>
     */
    @Throws(BigBoneRequestException::class)
    fun getStatus(statusId: String): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * View statuses above and below this status in the thread.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#context">Mastodon API documentation: methods/statuses/#context</a>
     */
    @Throws(BigBoneRequestException::class)
    fun getContext(statusId: String): MastodonRequest<Context> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/context",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Fetch preview card.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#card">Mastodon API documentation: methods/statuses/#card</a>
     */
    @Throws(BigBoneRequestException::class)
    fun getCard(statusId: String): MastodonRequest<Card> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/card",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * View who boosted a given status.
     * @param statusId ID of the status.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#reblogged_by">Mastodon API documentation: methods/statuses/#reblogged_by</a>
     */
    @JvmOverloads
    @Throws(BigBoneRequestException::class)
    fun getRebloggedBy(statusId: String, range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/reblogged_by",
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }

    /**
     * View who favourited a given status.
     * @param statusId ID of the status.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#favourited_by">Mastodon API documentation: methods/statuses/#favourited_by</a>
     */
    @JvmOverloads
    @Throws(BigBoneRequestException::class)
    fun getFavouritedBy(statusId: String, range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/favourited_by",
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }

    /**
     * Publish a status with the given parameters.
     * @param status the text of the status
     * @param inReplyToId the local id of the status you want to reply to
     * @param mediaIds the array of media ids to attach to the status (maximum 4)
     * @param sensitive set this to mark the media of the status as NSFW
     * @param spoilerText text to be shown as a warning before the actual content
     * @param visibility either "direct", "private", "unlisted" or "public"
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#create">Mastodon API documentation: methods/statuses/#create</a>
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
            parameters = Parameters().apply {
                append("status", status)
                inReplyToId?.let { append("in_reply_to_id", it) }
                mediaIds?.let { append("media_ids", it) }
                append("sensitive", sensitive)
                spoilerText?.let { append("spoiler_text", it) }
                append("visibility", visibility.value)
            }
        )
    }

    /**
     * Delete one of your own statuses.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#delete">Mastodon API documentation: methods/statuses/#delete</a>
     */
    @Throws(BigBoneRequestException::class)
    fun deleteStatus(statusId: String) {
        client.performAction(
            endpoint = "api/v1/statuses/$statusId",
            method = MastodonClient.Method.DELETE
        )
    }

    /**
     * Reshare a status on your own profile.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#boost">Mastodon API documentation: methods/statuses/#boost</a>
     */
    @Throws(BigBoneRequestException::class)
    fun reblogStatus(statusId: String): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/reblog",
            method = MastodonClient.Method.POST
        )
    }

    /**
     * Undo a reshare of a status.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#unreblog">Mastodon API documentation: methods/statuses/#unreblog</a>
     */
    @Throws(BigBoneRequestException::class)
    fun unreblogStatus(statusId: String): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/unreblog",
            method = MastodonClient.Method.POST
        )
    }

    /**
     * Favourite a status.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#favourite">Mastodon API documentation: methods/statuses/#favourite</a>
     */
    @Throws(BigBoneRequestException::class)
    fun favouriteStatus(statusId: String): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/favourite",
            method = MastodonClient.Method.POST
        )
    }

    /**
     * Remove a status from your favourites list.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#unfavourite">Mastodon API documentation: methods/statuses/#unfavourite</a>
     */
    @Throws(BigBoneRequestException::class)
    fun unfavouriteStatus(statusId: String): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/unfavourite",
            method = MastodonClient.Method.POST
        )
    }
}
