package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
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
import social.bigbone.api.entity.data.Visibility
import social.bigbone.api.method.StatusMethods
import java.time.Instant

/**
 * Reactive implementation of [StatusMethods].
 * Allows access to API methods with endpoints having an "api/vX/statuses" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/statuses/">Mastodon statuses API methods</a>
 */
class RxStatusMethods(client: MastodonClient) {
    private val statusMethods = StatusMethods(client)

    /**
     * Obtain information about a status.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#get">Mastodon API documentation: methods/statuses/#get</a>
     */
    fun getStatus(statusId: String): Single<Status> = Single.fromCallable { statusMethods.getStatus(statusId).execute() }

    /**
     * View statuses above and below this status in the thread.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#context">Mastodon API documentation: methods/statuses/#context</a>
     */
    fun getContext(statusId: String): Single<Context> = Single.fromCallable { statusMethods.getContext(statusId).execute() }

    /**
     * Translate the status content into some language.
     * @param statusId The ID of the Status in the database.
     * @param language ISO 639 language code. The status content will be translated into this language. Defaults to the user’s current locale.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#translate">Mastodon API documentation: methods/statuses/#translate</a>
     */
    @JvmOverloads
    fun translateStatus(
        statusId: String,
        language: String? = null
    ): Single<Translation> = Single.fromCallable { statusMethods.translateStatus(statusId, language).execute() }

    /**
     * View who boosted a given status.
     * @param statusId ID of the status.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#reblogged_by">Mastodon API documentation: methods/statuses/#reblogged_by</a>
     */
    @JvmOverloads
    fun getRebloggedBy(
        statusId: String,
        range: Range = Range()
    ): Single<Pageable<Account>> = Single.fromCallable { statusMethods.getRebloggedBy(statusId, range).execute() }

    /**
     * View who favourited a given status.
     * @param statusId ID of the status.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#favourited_by">Mastodon API documentation: methods/statuses/#favourited_by</a>
     */
    @JvmOverloads
    fun getFavouritedBy(
        statusId: String,
        range: Range = Range()
    ): Single<Pageable<Account>> = Single.fromCallable { statusMethods.getFavouritedBy(statusId, range).execute() }

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
    fun postStatus(
        status: String,
        mediaIds: List<String>? = null,
        visibility: Visibility = Visibility.PUBLIC,
        inReplyToId: String? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null,
        addIdempotencyKey: Boolean = true
    ): Single<Status> =
        Single.fromCallable {
            statusMethods.postStatus(status, mediaIds, visibility, inReplyToId, sensitive, spoilerText, language, addIdempotencyKey).execute()
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
    fun postPoll(
        status: String,
        pollData: PollData,
        visibility: Visibility = Visibility.PUBLIC,
        inReplyToId: String? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null,
        addIdempotencyKey: Boolean = true
    ): Single<Status> =
        Single.fromCallable { statusMethods.postPoll(status, pollData, visibility, inReplyToId, sensitive, spoilerText, language, addIdempotencyKey).execute() }

    /**
     * Schedule a status with the given parameters. To schedule a status containing a poll, use [schedulePoll].
     * To post a status immediately, use [postStatus].
     * @param status the text of the status
     * @param mediaIds the array of media ids to attach to the status (maximum 4)
     * @param scheduledAt [Instant] at which to schedule a status. Must be at least 5 minutes in the future.
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
    fun scheduleStatus(
        status: String,
        mediaIds: List<String>? = null,
        scheduledAt: Instant,
        visibility: Visibility = Visibility.PUBLIC,
        inReplyToId: String? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null,
        addIdempotencyKey: Boolean = true
    ): Single<ScheduledStatus> =
        Single.fromCallable {
            statusMethods.scheduleStatus(status, mediaIds, scheduledAt, visibility, inReplyToId, sensitive, spoilerText, language, addIdempotencyKey).execute()
        }

    /**
     * Schedule a status containing a poll with the given parameters. To post immediately, use [postPoll].
     * @param status the text of the status
     * @param scheduledAt [Instant] at which to schedule a status. Must be at least 5 minutes in the future.
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
    fun schedulePoll(
        status: String,
        scheduledAt: Instant,
        pollData: PollData,
        visibility: Visibility = Visibility.PUBLIC,
        inReplyToId: String? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null,
        addIdempotencyKey: Boolean = true
    ): Single<ScheduledStatus> =
        Single.fromCallable {
            statusMethods.schedulePoll(status, scheduledAt, pollData, visibility, inReplyToId, sensitive, spoilerText, language, addIdempotencyKey).execute()
        }

    /**
     * Delete one of your own statuses.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#delete">Mastodon API documentation: methods/statuses/#delete</a>
     */
    fun deleteStatus(statusId: String): Single<Status> = Single.fromCallable { statusMethods.deleteStatus(statusId).execute() }

    /**
     * Reshare a status on your own profile.
     * @param statusId ID of the status.
     * @param visibility Any visibility except limited or direct (i.e. public, unlisted, private). Defaults to public.
     *  Currently unused in UI.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#boost">Mastodon API documentation: methods/statuses/#boost</a>
     */
    @JvmOverloads
    fun reblogStatus(
        statusId: String,
        visibility: Visibility = Visibility.PUBLIC
    ): Single<Status> = Single.fromCallable { statusMethods.reblogStatus(statusId, visibility).execute() }

    /**
     * Undo a reshare of a status.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#unreblog">Mastodon API documentation: methods/statuses/#unreblog</a>
     */
    fun unreblogStatus(statusId: String): Single<Status> = Single.fromCallable { statusMethods.unreblogStatus(statusId).execute() }

    /**
     * Favourite a status.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#favourite">Mastodon API documentation: methods/statuses/#favourite</a>
     */
    fun favouriteStatus(statusId: String): Single<Status> = Single.fromCallable { statusMethods.favouriteStatus(statusId).execute() }

    /**
     * Remove a status from your favourites list.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#unfavourite">Mastodon API documentation: methods/statuses/#unfavourite</a>
     */
    fun unfavouriteStatus(statusId: String): Single<Status> = Single.fromCallable { statusMethods.unfavouriteStatus(statusId).execute() }

    /**
     * Privately bookmark a status.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#bookmark">Mastodon API documentation: methods/statuses/#bookmark</a>
     */
    fun bookmarkStatus(statusId: String): Single<Status> = Single.fromCallable { statusMethods.bookmarkStatus(statusId).execute() }

    /**
     * Remove a status from your private bookmarks.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#unbookmark">Mastodon API documentation: methods/statuses/#unbookmark</a>
     */
    fun unbookmarkStatus(statusId: String): Single<Status> = Single.fromCallable { statusMethods.unbookmarkStatus(statusId).execute() }

    /**
     * Do not receive notifications for the thread that this status is part of. Must be a thread in which you are a participant.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#mute">Mastodon API documentation: methods/statuses/#mute</a>
     */
    fun muteConversation(statusId: String): Single<Status> = Single.fromCallable { statusMethods.muteConversation(statusId).execute() }

    /**
     * Start receiving notifications again for the thread that this status is part of.
     * @param statusId ID of the status.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#unmute">Mastodon API documentation: methods/statuses/#unmute</a>
     */
    fun unmuteConversation(statusId: String): Single<Status> = Single.fromCallable { statusMethods.unmuteConversation(statusId).execute() }

    /**
     * Feature one of your own public statuses at the top of your profile.
     * @param statusId The local ID of the Status in the database. The status should be authored by the authorized account.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#pin">Mastodon API documentation: methods/statuses/#pin</a>
     */
    fun pinStatus(statusId: String): Single<Status> = Single.fromCallable { statusMethods.pinStatus(statusId).execute() }

    /**
     * Unfeature a status from the top of your profile.
     * @param statusId The local ID of the Status in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#unpin">Mastodon API documentation: methods/statuses/#unpin</a>
     */
    fun unpinStatus(statusId: String): Single<Status> = Single.fromCallable { statusMethods.unpinStatus(statusId).execute() }

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
    fun editStatus(
        statusId: String,
        status: String,
        mediaIds: List<String>? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null
    ): Single<Status> = Single.fromCallable { statusMethods.editStatus(statusId, status, mediaIds, sensitive, spoilerText, language).execute() }

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
    fun editPoll(
        statusId: String,
        status: String,
        pollData: PollData,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null
    ): Single<Status> = Single.fromCallable { statusMethods.editPoll(statusId, status, pollData, sensitive, spoilerText, language).execute() }

    /**
     * Get all known versions of a status, including the initial and current states.
     * @param statusId The local ID of the Status in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#history">Mastodon API documentation: methods/statuses/#history</a>
     */
    fun getEditHistory(statusId: String): Single<List<StatusEdit>> = Single.fromCallable { statusMethods.getEditHistory(statusId).execute() }

    /**
     * Obtain the source properties for a status so that it can be edited.
     * @param statusId The local ID of the Status in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/statuses/#source">Mastodon API documentation: methods/statuses/#source</a>
     */
    fun getStatusSource(statusId: String): Single<StatusSource> = Single.fromCallable { statusMethods.getStatusSource(statusId).execute() }
}
