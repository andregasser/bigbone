package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonClient.Method
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.ScheduledStatus
import java.time.Duration
import java.time.Instant

/**
 * Minimum [Duration] a scheduled status needs to be scheduled into the future.
 */
private val SCHEDULED_AT_MIN_AHEAD: Duration = Duration.ofMinutes(5)

/**
 * Manage statuses that were scheduled to be published at a future date.
 *
 * @see <a href="https://docs.joinmastodon.org/methods/scheduled_statuses/">Mastodon scheduled_statuses API methods</a>
 */
class ScheduledStatusMethods(private val client: MastodonClient) {

    private val endpoint = "api/v1/scheduled_statuses"

    /**
     * View scheduled statuses.
     *
     * @param range optional Range for the pageable return value.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/scheduled_statuses/#get">Mastodon API documentation: methods/scheduled_statuses/#get</a>
     */
    @JvmOverloads
    fun getScheduledStatuses(range: Range = Range()): MastodonRequest<Pageable<ScheduledStatus>> {
        return client.getPageableMastodonRequest<ScheduledStatus>(
            endpoint = endpoint,
            method = Method.GET,
            parameters = range.toParameters()
        )
    }

    /**
     * View a single scheduled status.
     *
     * @param withId The ID of the [ScheduledStatus] in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/scheduled_statuses/#get-one">Mastodon API documentation: methods/scheduled_statuses/#get-one</a>
     */
    fun getScheduledStatus(withId: String): MastodonRequest<ScheduledStatus> {
        return client.getMastodonRequest(
            endpoint = "$endpoint/$withId",
            method = Method.GET
        )
    }

    /**
     * Update a scheduled status’ publishing date.
     *
     * @param ofId The ID of the [ScheduledStatus] in the database.
     * @param newPublishingDate Datetime at which the status will be published.
     * Must lie at least [SCHEDULED_AT_MIN_AHEAD] minutes ahead.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/scheduled_statuses/#update">Mastodon API documentation: methods/scheduled_statuses/#update</a>
     * @throws IllegalArgumentException if [newPublishingDate] is not at least [SCHEDULED_AT_MIN_AHEAD] minutes ahead.
     */
    fun updatePublishingDate(
        ofId: String,
        newPublishingDate: Instant
    ): MastodonRequest<ScheduledStatus> {
        require(newPublishingDate.isAfter(Instant.now().plus(SCHEDULED_AT_MIN_AHEAD))) {
            "New publishing date must lie ahead at least ${SCHEDULED_AT_MIN_AHEAD.toMinutes()} minutes"
        }

        return client.getMastodonRequest(
            endpoint = "$endpoint/$ofId",
            method = Method.PUT,
            parameters = Parameters().apply {
                append("scheduled_at", newPublishingDate.toString())
            }
        )
    }

    /**
     * Cancel a scheduled status.
     *
     * @param withId The ID of the [ScheduledStatus] in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/scheduled_statuses/#cancel">Mastodon API documentation: methods/scheduled_statuses/#cancel</a>
     */
    fun cancelScheduledStatus(withId: String) {
        return client.performAction(
            endpoint = "$endpoint/$withId",
            method = Method.DELETE
        )
    }
}
