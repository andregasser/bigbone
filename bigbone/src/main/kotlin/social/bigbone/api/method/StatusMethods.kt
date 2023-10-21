package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.entity.Context
import social.bigbone.api.entity.ScheduledStatus
import social.bigbone.api.entity.Status
import social.bigbone.api.entity.StatusEdit
import social.bigbone.api.entity.StatusSource
import social.bigbone.api.entity.Translation
import social.bigbone.api.entity.data.PollData
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
     * Translate the status content into some language.
     * @param statusId The ID of the Status in the database.
     * @param language ISO 639 language code. The status content will be translated into this language. Defaults to the user’s current locale.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#translate">Mastodon API documentation: methods/statuses/#translate</a>
     */
    @JvmOverloads
    @Throws(BigBoneRequestException::class)
    fun translateStatus(statusId: String, language: String? = null): MastodonRequest<Translation> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/translate",
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                language?.let { append("lang", it) }
            }
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
     * Publish a status with the given parameters. To publish a status containing a poll, use [postPoll].
     * To schedule a status, use [scheduleStatus].
     * @param status the text of the status
     * @param mediaIds the array of media ids to attach to the status (maximum 4)
     * @param visibility either "direct", "private", "unlisted" or "public"
     * @param inReplyToId the local id of the status you want to reply to
     * @param sensitive set this to mark the media of the status as NSFW
     * @param spoilerText text to be shown as a warning before the actual content
     * @param language ISO 639 language code for this status.
     * @param addIdempotencyKey If true, this will generate a unique hash value from all given parameters and add it to
     *         the request as its idempotency key value. To avoid duplicate submissions of the same status, if this
     *         value is reused within a short timeframe, another status will not be created. Defaults to true.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#create">Mastodon API documentation: methods/statuses/#create</a>
     */
    @JvmOverloads
    @Throws(BigBoneRequestException::class)
    fun postStatus(
        status: String,
        mediaIds: List<String>? = null,
        visibility: Status.Visibility = Status.Visibility.Public,
        inReplyToId: String? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null,
        addIdempotencyKey: Boolean = true
    ): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses",
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                append("status", status)
                append("visibility", visibility.value)
                inReplyToId?.let { append("in_reply_to_id", it) }
                mediaIds?.let { append("media_ids", it) }
                append("sensitive", sensitive)
                spoilerText?.let { append("spoiler_text", it) }
                language?.let { append("language", it) }
            },
            addIdempotencyKey = addIdempotencyKey
        )
    }

    /**
     * Publish a status containing a poll with the given parameters. To schedule a poll status, use [schedulePoll].
     * @param status the text of the status
     * @param pollData containing all necessary poll data
     * @param visibility either "direct", "private", "unlisted" or "public"
     * @param inReplyToId the local id of the status you want to reply to
     * @param sensitive set this to mark the media of the status as NSFW
     * @param spoilerText text to be shown as a warning before the actual content
     * @param language ISO 639 language code for this status.
     * @param addIdempotencyKey If true, this will generate a unique hash value from all given parameters and add it to
     *         the request as its idempotency key value. To avoid duplicate submissions of the same status, if this
     *         value is reused within a short timeframe, another status will not be created. Defaults to true.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#create">Mastodon API documentation: methods/statuses/#create</a>
     */
    @JvmOverloads
    @Throws(BigBoneRequestException::class)
    fun postPoll(
        status: String,
        pollData: PollData,
        visibility: Status.Visibility = Status.Visibility.Public,
        inReplyToId: String? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null,
        addIdempotencyKey: Boolean = true
    ): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses",
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                append("status", status)
                append("poll[options]", pollData.options)
                append("poll[expires_in]", pollData.expiresIn)
                append("visibility", visibility.value)
                append("poll[multiple]", pollData.multiple ?: false)
                append("poll[hide_totals", pollData.hideTotals ?: false)
                inReplyToId?.let { append("in_reply_to_id", it) }
                append("sensitive", sensitive)
                spoilerText?.let { append("spoiler_text", it) }
                language?.let { append("language", it) }
            },
            addIdempotencyKey = addIdempotencyKey
        )
    }

    /**
     * Schedule a status with the given parameters. To schedule a status containing a poll, use [schedulePoll].
     * To post a status immediately, use [postStatus].
     * @param status the text of the status
     * @param mediaIds the array of media ids to attach to the status (maximum 4)
     * @param scheduledAt ISO 8601 Datetime at which to schedule a status. Must be at least 5 minutes in the future.
     * @param visibility either "direct", "private", "unlisted" or "public"
     * @param inReplyToId the local id of the status you want to reply to
     * @param sensitive set this to mark the media of the status as NSFW
     * @param spoilerText text to be shown as a warning before the actual content
     * @param language ISO 639 language code for this status.
     * @param addIdempotencyKey If true, this will generate a unique hash value from all given parameters and add it to
     *         the request as its idempotency key value. To avoid duplicate submissions of the same status, if this
     *         value is reused within a short timeframe, another status will not be created. Defaults to true.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#create">Mastodon API documentation: methods/statuses/#create</a>
     */
    @JvmOverloads
    @Throws(BigBoneRequestException::class)
    fun scheduleStatus(
        status: String,
        mediaIds: List<String>? = null,
        scheduledAt: String,
        visibility: Status.Visibility = Status.Visibility.Public,
        inReplyToId: String? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null,
        addIdempotencyKey: Boolean = true
    ): MastodonRequest<ScheduledStatus> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses",
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                append("status", status)
                append("scheduled_at", scheduledAt)
                append("visibility", visibility.value)
                inReplyToId?.let { append("in_reply_to_id", it) }
                mediaIds?.let { append("media_ids", it) }
                append("sensitive", sensitive)
                spoilerText?.let { append("spoiler_text", it) }
                language?.let { append("language", it) }
            },
            addIdempotencyKey = addIdempotencyKey
        )
    }

    /**
     * Schedule a status containing a poll with the given parameters. To post immediately, use [postPoll].
     * @param status the text of the status
     * @param scheduledAt ISO 8601 Datetime at which to schedule a status. Must be at least 5 minutes in the future.
     * @param pollData containing all necessary poll data
     * @param visibility either "direct", "private", "unlisted" or "public"
     * @param inReplyToId the local id of the status you want to reply to
     * @param sensitive set this to mark the media of the status as NSFW
     * @param spoilerText text to be shown as a warning before the actual content
     * @param language ISO 639 language code for this status.
     * @param addIdempotencyKey If true, this will generate a unique hash value from all given parameters and add it to
     *         the request as its idempotency key value. To avoid duplicate submissions of the same status, if this
     *         value is reused within a short timeframe, another status will not be created. Defaults to true.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#create">Mastodon API documentation: methods/statuses/#create</a>
     */
    @JvmOverloads
    @Throws(BigBoneRequestException::class)
    fun schedulePoll(
        status: String,
        scheduledAt: String,
        pollData: PollData,
        visibility: Status.Visibility = Status.Visibility.Public,
        inReplyToId: String? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null,
        addIdempotencyKey: Boolean = true
    ): MastodonRequest<ScheduledStatus> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses",
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                append("status", status)
                append("scheduled_at", scheduledAt)
                append("poll[options]", pollData.options)
                append("poll[expires_in]", pollData.expiresIn)
                append("visibility", visibility.value)
                append("poll[multiple]", pollData.multiple ?: false)
                append("poll[hide_totals]", pollData.multiple ?: false)
                inReplyToId?.let { append("in_reply_to_id", it) }
                append("sensitive", sensitive)
                spoilerText?.let { append("spoiler_text", it) }
                language?.let { append("language", it) }
            },
            addIdempotencyKey = addIdempotencyKey
        )
    }

    /**
     * Delete one of your own statuses.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#delete">Mastodon API documentation: methods/statuses/#delete</a>
     */
    @Throws(BigBoneRequestException::class)
    fun deleteStatus(statusId: String): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId",
            method = MastodonClient.Method.DELETE
        )
    }

    /**
     * Reshare a status on your own profile.
     * @param statusId ID of the status.
     * @param visibility Any visibility except limited or direct (i.e. public, unlisted, private). Defaults to public.
     *  Currently unused in UI.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#boost">Mastodon API documentation: methods/statuses/#boost</a>
     */
    @JvmOverloads
    @Throws(BigBoneRequestException::class)
    fun reblogStatus(statusId: String, visibility: Status.Visibility = Status.Visibility.Public): MastodonRequest<Status> {
        if (visibility != Status.Visibility.Public &&
            visibility != Status.Visibility.Unlisted &&
            visibility != Status.Visibility.Private
        ) {
            throw BigBoneRequestException("Visibility must be one of: public, unlisted, private when reblogging.")
        }
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/reblog",
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                append("visibility", visibility.value)
            }
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

    /**
     * Privately bookmark a status.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#bookmark">Mastodon API documentation: methods/statuses/#bookmark</a>
     */
    @Throws(BigBoneRequestException::class)
    fun bookmarkStatus(statusId: String): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/bookmark",
            method = MastodonClient.Method.POST
        )
    }

    /**
     * Remove a status from your private bookmarks.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#unbookmark">Mastodon API documentation: methods/statuses/#unbookmark</a>
     */
    @Throws(BigBoneRequestException::class)
    fun unbookmarkStatus(statusId: String): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/unbookmark",
            method = MastodonClient.Method.POST
        )
    }

    /**
     * Do not receive notifications for the thread that this status is part of. Must be a thread in which you are a participant.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#mute">Mastodon API documentation: methods/statuses/#mute</a>
     */
    @Throws(BigBoneRequestException::class)
    fun muteConversation(statusId: String): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/mute",
            method = MastodonClient.Method.POST
        )
    }

    /**
     * Start receiving notifications again for the thread that this status is part of.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#unmute">Mastodon API documentation: methods/statuses/#unmute</a>
     */
    @Throws(BigBoneRequestException::class)
    fun unmuteConversation(statusId: String): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/unmute",
            method = MastodonClient.Method.POST
        )
    }

    /**
     * Feature one of your own public statuses at the top of your profile.
     * @param statusId The local ID of the Status in the database. The status should be authored by the authorized account.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#pin">Mastodon API documentation: methods/statuses/#pin</a>
     */
    @Throws(BigBoneRequestException::class)
    fun pinStatus(statusId: String): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/pin",
            method = MastodonClient.Method.POST
        )
    }

    /**
     * Unfeature a status from the top of your profile.
     * @param statusId The local ID of the Status in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#unpin">Mastodon API documentation: methods/statuses/#unpin</a>
     */
    @Throws(BigBoneRequestException::class)
    fun unpinStatus(statusId: String): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/unpin",
            method = MastodonClient.Method.POST
        )
    }

    /**
     * Edit a given status to change its text, sensitivity, media attachments. Use [getStatus] and/or [getStatusSource]
     * to get previous status data to use in this update. Editing a status containing a poll using this method will
     * remove the poll - use [editPoll] instead!
     * @param statusId the ID of the Status in the database
     * @param status the plain text content of the status
     * @param mediaIds the array of media ids to attach to the status (maximum 4)
     * @param sensitive whether the status should be marked as sensitive
     * @param spoilerText the plain text subject or content warning of the status
     * @param language ISO 639 language code for the status
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#edit">Mastodon API documentation: methods/statuses/#edit</a>
     */
    @JvmOverloads
    @Throws(BigBoneRequestException::class)
    fun editStatus(
        statusId: String,
        status: String,
        mediaIds: List<String>? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null
    ): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId",
            method = MastodonClient.Method.PUT,
            parameters = Parameters().apply {
                append("status", status)
                mediaIds?.let { append("media_ids", it) }
                append("sensitive", sensitive)
                spoilerText?.let { append("spoiler_text", it) }
                language?.let { append("language", it) }
            }
        )
    }

    /**
     * Edit a given status containing a poll to change its text, sensitivity, or poll. Use [getStatus] and/or
     * [getStatusSource] to get previous status data to use in this update, including all necessary poll data that
     * should not be changed. Note that changing a poll’s options will reset the votes.
     * @param statusId the ID of the Status in the database
     * @param status the plain text content of the status
     * @param pollData containing all necessary poll data
     * @param sensitive whether the status should be marked as sensitive
     * @param spoilerText the plain text subject or content warning of the status
     * @param language ISO 639 language code for this status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#edit">Mastodon API documentation: methods/statuses/#edit</a>
     */
    @JvmOverloads
    @Throws(BigBoneRequestException::class)
    fun editPoll(
        statusId: String,
        status: String,
        pollData: PollData,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null
    ): MastodonRequest<Status> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId",
            method = MastodonClient.Method.PUT,
            parameters = Parameters().apply {
                append("status", status)
                append("poll[options]", pollData.options)
                append("poll[expires_in]", pollData.expiresIn)
                append("poll[multiple]", pollData.multiple ?: false)
                append("poll[hide_totals]", pollData.hideTotals ?: false)
                append("sensitive", sensitive)
                spoilerText?.let { append("spoiler_text", it) }
                language?.let { append("language", it) }
            }
        )
    }

    /**
     * Get all known versions of a status, including the initial and current states.
     * @param statusId The local ID of the Status in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#history">Mastodon API documentation: methods/statuses/#history</a>
     */
    @Throws(BigBoneRequestException::class)
    fun getEditHistory(statusId: String): MastodonRequest<List<StatusEdit>> {
        return client.getMastodonRequestForList(
            endpoint = "api/v1/statuses/$statusId/history",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Obtain the source properties for a status so that it can be edited.
     * @param statusId The local ID of the Status in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#source">Mastodon API documentation: methods/statuses/#source</a>
     */
    @Throws(BigBoneRequestException::class)
    fun getStatusSource(statusId: String): MastodonRequest<StatusSource> {
        return client.getMastodonRequest(
            endpoint = "api/v1/statuses/$statusId/source",
            method = MastodonClient.Method.GET
        )
    }
}
