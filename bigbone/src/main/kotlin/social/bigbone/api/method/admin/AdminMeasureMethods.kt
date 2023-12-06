package social.bigbone.api.method.admin

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.entity.admin.AdminMeasure
import social.bigbone.api.entity.admin.AdminMeasure.Key
import java.time.Instant

/**
 * Obtain quantitative metrics about the server.
 * @see <a href="https://docs.joinmastodon.org/methods/admin/measures/">Mastodon admin/measures API methods</a>
 */
class AdminMeasureMethods(private val client: MastodonClient) {
    private val adminMeasuresEndpoint = "api/v1/admin/measures"

    /**
     * Obtain statistical measures for your server.
     *
     * @param measures Request specific measures. Uses helper wrapper [RequestMeasure] to ensure that required fields are set for any given [Key].
     * @param startAt The start date for the time period. If a time is provided, it will be ignored.
     * @param endAt The end date for the time period. If a time is provided, it will be ignored.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/measures/#get">Mastodon API documentation: admin/measures/#get</a>
     */
    fun getMeasurableData(
        measures: List<RequestMeasure>,
        startAt: Instant,
        endAt: Instant
    ): MastodonRequest<List<AdminMeasure>> {
        return client.getMastodonRequestForList(
            endpoint = adminMeasuresEndpoint,
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                measures
                    .flatMap(RequestMeasure::getRequestKeyValues)
                    .forEach { (key, value) -> append(key, value) }

                append("start_at", startAt.toString())
                append("end_at", endAt.toString())
            }
        )
    }
}

/**
 * Wrapper class to ensure that required parameters are added when sending a specific [Key] for which to get measures.
 */
sealed class RequestMeasure(val key: Key, val apiName: String = key.apiName) {
    /**
     * Get the key=value pairs to request this measure.
     * Can be used to craft a [Parameters] entry from.
     * Defaults to [KEYS_NAME] = [apiName] for implementations of [RequestMeasure] that do not have additional properties.
     */
    open fun getRequestKeyValues(): List<Pair<String, String>> = listOf(KEYS_NAME to apiName)

    companion object {
        private const val KEYS_NAME = "keys[]"
        private const val TAG_ID_NAME = "id"
        private const val REMOTE_DOMAIN_NAME = "domain"
    }

    /**
     * Requests [Key.ACTIVE_USERS], i.e. Total active users on your instance within the time period.
     */
    data object ActiveUsers : RequestMeasure(key = Key.ACTIVE_USERS)

    /**
     * Requests [Key.NEW_USERS], i.e. Users who joined your instance within the time period.
     */
    data object NewUsers : RequestMeasure(key = Key.NEW_USERS)

    /**
     * Requests [Key.INTERACTIONS], i.e., Total interactions (favourites, boosts, replies) on local statuses within the time period.
     */
    data object Interactions : RequestMeasure(key = Key.INTERACTIONS)

    /**
     * Requests [Key.OPENED_REPORTS], i.e., Total reports filed within the time period.
     */
    data object OpenedReports : RequestMeasure(key = Key.OPENED_REPORTS)

    /**
     * Requests [Key.RESOLVED_REPORTS], i.e., Total reports resolved within the time period.
     */
    data object ResolvedReports : RequestMeasure(key = Key.RESOLVED_REPORTS)

    /**
     * Requests [Key.TAG_ACCOUNTS], i.e., Total accounts who used a tag in at least one status within the time period.
     */
    data class TagAccounts(
        /**
         * When [Key.TAG_ACCOUNTS] is one of the requested keys, you must provide a tag ID
         * to obtain the measure of how many accounts used that hashtag in at least one status
         * within the given time period.
         */
        val tagId: String
    ) : RequestMeasure(key = Key.TAG_ACCOUNTS) {
        override fun getRequestKeyValues(): List<Pair<String, String>> = super.getRequestKeyValues() + listOf("$apiName[$TAG_ID_NAME]" to tagId)
    }

    /**
     * Requests [Key.TAG_USES], i.e., Total statuses which used a tag within the time period.
     */
    data class TagUses(
        /**
         * When [Key.TAG_USES] is one of the requested keys, you must provide a tag ID
         * to obtain the measure of how many statuses used that hashtag
         * within the given time period.
         */
        val tagId: String
    ) : RequestMeasure(Key.TAG_USES) {
        override fun getRequestKeyValues(): List<Pair<String, String>> = super.getRequestKeyValues() + listOf("$apiName[$TAG_ID_NAME]" to tagId)
    }

    /**
     * Requests [Key.TAG_SERVERS], i.e., Total remote origin servers for statuses which used a tag within the time period.
     */
    data class TagServers(
        /**
         * When [Key.TAG_SERVERS] is one of the requested keys, you must provide a tag ID
         * to obtain the measure of how many servers used that hashtag in at least one status
         * within the given time period.
         */
        val tagId: String
    ) : RequestMeasure(Key.TAG_SERVERS) {
        override fun getRequestKeyValues(): List<Pair<String, String>> = super.getRequestKeyValues() + listOf("$apiName[$TAG_ID_NAME]" to tagId)
    }

    /**
     * Requests [Key.INSTANCE_ACCOUNTS], i.e., Total accounts originating from a remote domain within the time period.
     */
    data class InstanceAccounts(
        /**
         * When [Key.INSTANCE_ACCOUNTS] is one of the requested keys, you must provide a remote domain
         * to obtain the measure of how many accounts have been discovered from that server
         * within the given time period.
         */
        val remoteDomain: String
    ) : RequestMeasure(Key.INSTANCE_ACCOUNTS) {
        override fun getRequestKeyValues(): List<Pair<String, String>> = super.getRequestKeyValues() + listOf("$apiName[$REMOTE_DOMAIN_NAME]" to remoteDomain)
    }

    /**
     * Requests [Key.INSTANCE_MEDIA_ATTACHMENTS], i.e., Total space used by media attachments from a remote domain within the time period.
     */
    data class InstanceMediaAttachments(
        /**
         * When [Key.INSTANCE_MEDIA_ATTACHMENTS] is one of the requested keys, you must provide a remote domain
         * to obtain the measure of how much space is used by media attachments from that server
         * within the given time period.
         */
        val remoteDomain: String
    ) : RequestMeasure(Key.INSTANCE_MEDIA_ATTACHMENTS) {
        override fun getRequestKeyValues(): List<Pair<String, String>> = super.getRequestKeyValues() + listOf("$apiName[$REMOTE_DOMAIN_NAME]" to remoteDomain)
    }

    /**
     * Requests [Key.INSTANCE_REPORTS], i.e., Total reports filed against accounts from a remote domain within the time period.
     */
    data class InstanceReports(
        /**
         * When [Key.INSTANCE_REPORTS] is one of the requested keys, you must provide a remote domain
         * to obtain the measure of how many reports have been filed against accounts from that server
         * within the given time period.
         */
        val remoteDomain: String
    ) : RequestMeasure(Key.INSTANCE_REPORTS) {
        override fun getRequestKeyValues(): List<Pair<String, String>> = super.getRequestKeyValues() + listOf("$apiName[$REMOTE_DOMAIN_NAME]" to remoteDomain)
    }

    /**
     * Requests [Key.INSTANCE_STATUSES], i.e., Total statuses originating from a remote domain within the time period.
     */
    data class InstanceStatuses(
        /**
         * When [Key.INSTANCE_STATUSES] is one of the requested keys, you must provide a remote domain
         * to obtain the measure of how many statuses originate from that server
         * within the given time period.
         */
        val remoteDomain: String
    ) : RequestMeasure(Key.INSTANCE_STATUSES) {
        override fun getRequestKeyValues(): List<Pair<String, String>> = super.getRequestKeyValues() + listOf("$apiName[$REMOTE_DOMAIN_NAME]" to remoteDomain)
    }

    /**
     * Requests [Key.INSTANCE_FOLLOWS], i.e., Total accounts from a remote domain followed by a local user within the time period.
     */
    data class InstanceFollows(
        /**
         * When [Key.INSTANCE_FOLLOWS] is one of the requested keys, you must provide a remote domain
         * to obtain the measure of how many follows were performed on accounts from that server by local accounts
         * within the given time period.
         */
        val remoteDomain: String
    ) : RequestMeasure(Key.INSTANCE_FOLLOWS) {
        override fun getRequestKeyValues(): List<Pair<String, String>> = super.getRequestKeyValues() + listOf("$apiName[$REMOTE_DOMAIN_NAME]" to remoteDomain)
    }

    /**
     * Requests [Key.INSTANCE_FOLLOWERS], i.e., Total local accounts followed by accounts from a remote domain within the time period.
     */
    data class InstanceFollowers(
        /**
         * When [Key.INSTANCE_STATUSES] is one of the requested keys, you must provide a remote domain
         * to obtain the measure of how many follows were performed by accounts from that server on local accounts
         * within the given time period.
         */
        val remoteDomain: String
    ) : RequestMeasure(Key.INSTANCE_FOLLOWERS) {
        override fun getRequestKeyValues(): List<Pair<String, String>> = super.getRequestKeyValues() + listOf("$apiName[$REMOTE_DOMAIN_NAME]" to remoteDomain)
    }
}
