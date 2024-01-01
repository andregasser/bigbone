package social.bigbone.rx.admin

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.ScheduledStatus
import social.bigbone.api.method.ScheduledStatusMethods
import java.time.Instant

/**
 * Reactive implementation of [ScheduledStatusMethods].
 *
 * Manage statuses that were scheduled to be published at a future date.
 *
 * @see <a href="https://docs.joinmastodon.org/methods/scheduled_statuses/">Mastodon scheduled_statuses API methods</a>
 */
class RxScheduledStatusMethods(client: MastodonClient) {

    private val scheduledStatusMethods = ScheduledStatusMethods(client)

    /**
     * View scheduled statuses.
     *
     * @param range optional Range for the pageable return value.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/scheduled_statuses/#get">Mastodon API documentation: methods/scheduled_statuses/#get</a>
     */
    @JvmOverloads
    fun getScheduledStatuses(range: Range = Range()): Single<Pageable<ScheduledStatus>> = Single.fromCallable {
        scheduledStatusMethods.getScheduledStatuses(range).execute()
    }

    /**
     * View a single scheduled status.
     *
     * @param withId The ID of the [ScheduledStatus] in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/scheduled_statuses/#get-one">Mastodon API documentation: methods/scheduled_statuses/#get-one</a>
     */
    fun getScheduledStatus(withId: String): Single<ScheduledStatus> = Single.fromCallable {
        scheduledStatusMethods.getScheduledStatus(withId).execute()
    }

    /**
     * Update a scheduled status’ publishing date.
     *
     * @param ofId The ID of the [ScheduledStatus] in the database.
     * @param newPublishingDate Datetime at which the status will be published. Must lie at least 5 minutes ahead.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/scheduled_statuses/#update">Mastodon API documentation: methods/scheduled_statuses/#update</a>
     * @throws IllegalArgumentException if [newPublishingDate] is not at least 5 minutes ahead.
     */
    fun updatePublishingDate(
        ofId: String,
        newPublishingDate: Instant
    ): Single<ScheduledStatus> = Single.fromCallable {
        scheduledStatusMethods.updatePublishingDate(ofId, newPublishingDate).execute()
    }

    /**
     * Cancel a scheduled status.
     *
     * @param withId The ID of the [ScheduledStatus] in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/scheduled_statuses/#cancel">Mastodon API documentation: methods/scheduled_statuses/#cancel</a>
     */
    fun cancelScheduledStatus(withId: String): Completable = Completable.fromAction {
        scheduledStatusMethods.cancelScheduledStatus(withId)
    }
}
