package social.bigbone.api.method.admin

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.entity.admin.AdminDimension
import social.bigbone.api.entity.admin.AdminDimension.Key
import java.time.Instant

/**
 * Obtain qualitative metrics about the server.
 * @see <a href="https://docs.joinmastodon.org/methods/admin/dimensions/">Mastodon admin/dimensions API methods</a>
 */
class AdminDimensionMethods(private val client: MastodonClient) {

    private val adminDimensionsEndpoint = "api/v1/admin/dimensions"

    /**
     * Obtain information about popularity of certain accounts, servers, languages, etc.
     *
     * @param dimensions Request specific dimensions. Uses helper wrapper [RequestDimension] to ensure that
     * required fields are set for any given [Key].
     * @param startAt The start date for the time period. If a time is provided, it will be ignored.
     * @param endAt The end date for the time period. If a time is provided, it will be ignored.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/dimensions/#get">Mastodon API documentation: admin/dimensions/#get</a>
     */
    fun getDimensionalData(
        dimensions: List<RequestDimension>,
        startAt: Instant,
        endAt: Instant
    ): MastodonRequest<List<AdminDimension>> {
        return client.getMastodonRequestForList(
            endpoint = adminDimensionsEndpoint,
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                dimensions
                    .flatMap(RequestDimension::getRequestKeyValues)
                    .forEach { (key, value) -> append(key, value) }

                append("start_at", startAt.toString())
                append("end_at", endAt.toString())
            }
        )
    }
}

/**
 * Wrapper class to ensure that required parameters are added when sending a specific [Key] for which to get dimensions.
 */
sealed class RequestDimension(val key: Key, val apiName: String = key.apiName) {

    /**
     * Get the key=value pairs to request this dimension.
     * Can be used to craft a [Parameters] entry from.
     * Defaults to [KEYS_NAME] = [apiName] for implementations of [RequestDimension] that do not have additional properties.
     */
    open fun getRequestKeyValues(): List<Pair<String, String>> = listOf(
        KEYS_NAME to apiName
    )

    companion object {
        private const val KEYS_NAME = "keys[]"
        private const val TAG_ID_NAME = "id"
        private const val REMOTE_DOMAIN_NAME = "domain"
    }

    /**
     * Requests [Key.LANGUAGES], i.e., Most-used languages on this server.
     */
    data object Languages : RequestDimension(key = Key.LANGUAGES)

    /**
     * Requests [Key.SOURCES], i.e., Most-used client apps on this server.
     */
    data object Sources : RequestDimension(key = Key.SOURCES)

    /**
     * Requests [Key.SERVERS], i.e., Remote servers with the most statuses.
     */
    data object Servers : RequestDimension(key = Key.SERVERS)

    /**
     * Requests [Key.SPACE_USAGE], i.e., How much space is used by your software stack.
     */
    data object SpaceUsage : RequestDimension(key = Key.SPACE_USAGE)

    /**
     * Requests [Key.SOFTWARE_VERSIONS], i.e., The version numbers for your software stack.
     */
    data object SoftwareVersions : RequestDimension(key = Key.SOFTWARE_VERSIONS)

    /**
     * Requests [Key.TAG_SERVERS], i.e., Most-common servers for statuses including a trending tag.
     */
    data class TagServers(
        /**
         * When [Key.TAG_SERVERS] is one of the requested keys, you must provide a trending tag ID
         * to obtain information about which servers are posting the tag.
         */
        val trendingTagId: String,
        /**
         * The maximum number of results to return for sources, servers, languages, tag or instance dimensions.
         */
        val limit: Int? = null
    ) : RequestDimension(key = Key.TAG_SERVERS) {
        override fun getRequestKeyValues(): List<Pair<String, String>> {
            return super.getRequestKeyValues() + buildList {
                add("$apiName[$TAG_ID_NAME]" to trendingTagId)
                limit?.let { add("limit" to limit.toString()) }
            }
        }
    }

    /**
     * Requests [Key.TAG_LANGUAGES], i.e., Most-used languages for statuses including a trending tag.
     */
    data class TagLanguages(
        /**
         * When [Key.TAG_LANGUAGES] is one of the requested keys, you must provide a trending tag ID
         * to obtain information about which languages are posting the tag.
         */
        val trendingTagId: String,
        /**
         * The maximum number of results to return for sources, servers, languages, tag or instance dimensions.
         */
        val limit: Int? = null
    ) : RequestDimension(key = Key.TAG_LANGUAGES) {
        override fun getRequestKeyValues(): List<Pair<String, String>> {
            return super.getRequestKeyValues() + buildList {
                add("$apiName[$TAG_ID_NAME]" to trendingTagId)
                limit?.let { add("limit" to limit.toString()) }
            }
        }
    }

    /**
     * Requests [Key.INSTANCE_ACCOUNTS], i.e., Most-followed accounts from a remote server.
     */
    data class InstanceAccounts(
        /**
         * When [Key.INSTANCE_ACCOUNTS] is one of the requested keys, you must provide a domain
         * to obtain information about popular accounts from that server.
         */
        val remoteDomain: String,
        /**
         * The maximum number of results to return for sources, servers, languages, tag or instance dimensions.
         */
        val limit: Int? = null
    ) : RequestDimension(key = Key.INSTANCE_ACCOUNTS) {
        override fun getRequestKeyValues(): List<Pair<String, String>> {
            return super.getRequestKeyValues() + buildList {
                add("$apiName[$REMOTE_DOMAIN_NAME]" to remoteDomain)
                limit?.let { add("limit" to limit.toString()) }
            }
        }
    }

    /**
     * Requests [Key.INSTANCE_LANGUAGES], i.e., Most-used languages from a remote server.
     */
    data class InstanceLanguages(
        /**
         * When [Key.INSTANCE_LANGUAGES] is one of the requested keys, you must provide a domain
         * to obtain information about popular languages from that server.
         */
        val remoteDomain: String,
        /**
         * The maximum number of results to return for sources, servers, languages, tag or instance dimensions.
         */
        val limit: Int? = null
    ) : RequestDimension(key = Key.INSTANCE_LANGUAGES) {
        override fun getRequestKeyValues(): List<Pair<String, String>> {
            return super.getRequestKeyValues() + buildList {
                add("$apiName[$REMOTE_DOMAIN_NAME]" to remoteDomain)
                limit?.let { add("limit" to limit.toString()) }
            }
        }
    }
}
