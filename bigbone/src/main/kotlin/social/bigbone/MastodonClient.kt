package social.bigbone

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import social.bigbone.api.Pageable
import social.bigbone.api.entity.data.InstanceVersion
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.api.method.*
import social.bigbone.extension.emptyRequestBody
import social.bigbone.nodeinfo.NodeInfoClient
import java.io.IOException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.X509TrustManager

/**
 * This class is used by method classes (e.g. AccountMethods, RxAcccountMethods, ...) and performs HTTP calls
 * towards the Mastodon instance specified. Request/response data is serialized/deserialized accordingly.
 */
class MastodonClient
private constructor(
    private val instanceName: String,
    private val client: OkHttpClient
) {
    private var debug = false
    private var instanceVersion: String? = null
    private var scheme: String = "https"
    private var port: Int = 443

    /**
     * Access API methods under "api/vX/accounts" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("accounts")
    val accounts: AccountMethods by lazy { AccountMethods(this) }

    /**
     * Access API methods under "api/vX/apps" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("apps")
    val apps: AppMethods by lazy { AppMethods(this) }

    /**
     * Access API methods under "api/vX/blocks" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("blocks")
    val blocks: BlockMethods by lazy { BlockMethods(this) }

    /**
     * Access API methods under "api/vX/bookmarks" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("bookmarks")
    val bookmarks: BookmarkMethods by lazy { BookmarkMethods(this) }

    /**
     * Access API methods under "api/vX/conversations" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("conversations")
    val conversations: ConversationMethods by lazy { ConversationMethods(this) }

    /**
     * Access API methods under "api/vX/directory" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("directories")
    val directories: DirectoryMethods by lazy { DirectoryMethods(this) }

    /**
     * Access API methods under "api/vX/favourites" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("favourites")
    val favourites: FavouriteMethods by lazy { FavouriteMethods(this) }

    /**
     * Access API methods under "api/vX/featured_tags" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("featuredTags")
    val featuredTags: FeaturedTagsMethods by lazy { FeaturedTagsMethods(this) }

    /**
     * Access API methods under "api/vX/filters" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("filters")
    val filters: FilterMethods by lazy { FilterMethods(this) }

    /**
     * Access API methods under "api/vX/follow_requests" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("followRequests")
    val followRequests: FollowRequestMethods by lazy { FollowRequestMethods(this) }

    /**
     * Access API methods under "api/vX/instance" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("instances")
    val instances: InstanceMethods by lazy { InstanceMethods(this) }

    /**
     * Access API methods under "api/vX/lists" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("lists")
    val lists: ListMethods by lazy { ListMethods(this) }

    /**
     * Save and restore your position in timelines.
     */
    @Suppress("unused") // public API
    @get:JvmName("markers")
    val markers: MarkerMethods by lazy { MarkerMethods(this) }

    /**
     * Access API methods under "api/vX/media" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("media")
    val media: MediaMethods by lazy { MediaMethods(this) }

    /**
     * Access API methods under "api/vX/mutes" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("mutes")
    val mutes: MuteMethods by lazy { MuteMethods(this) }

    /**
     * Access API methods under "api/vX/notification[s]" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("notifications")
    val notifications: NotificationMethods by lazy { NotificationMethods(this) }

    /**
     * Access API methods under "oauth" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("oauth")
    val oauth: OAuthMethods by lazy { OAuthMethods(this) }

    /**
     * Access API methods under "polls" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("polls")
    val polls: PollMethods by lazy { PollMethods(this) }

    /**
     * Access API methods under "api/vX/reports" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("reports")
    val reports: ReportMethods by lazy { ReportMethods(this) }

    /**
     * Access API methods under "api/vX/search" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("search")
    val search: SearchMethods by lazy { SearchMethods(this) }

    /**
     * Access API methods under "api/vX/statuses" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("statuses")
    val statuses: StatusMethods by lazy { StatusMethods(this) }

    /**
     * Access API methods under "api/vX/streaming" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("streaming")
    val streaming: StreamingMethods by lazy { StreamingMethods(this) }

    /**
     * Access API methods under "api/vX/tags" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("tags")
    val tags: TagMethods by lazy { TagMethods(this) }

    /**
     * Access API methods under "api/vX/timelines" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("timelines")
    val timelines: TimelineMethods by lazy { TimelineMethods(this) }

    /**
     * Access API methods under "api/vX/suggestions" endpoint.
     */
    @Suppress("unused") // public API
    @get:JvmName("suggestions")
    val suggestions: SuggestionMethods by lazy { SuggestionMethods(this) }

    /**
     * Specifies the HTTP methods / HTTP verb that can be used by this class.
     */
    enum class Method {
        DELETE,
        GET,
        PATCH,
        POST,
        PUT
    }

    fun getInstanceName() = instanceName

    fun getInstanceVersion() = instanceVersion

    fun getScheme() = scheme

    fun getPort() = port

    /**
     * Returns a MastodonRequest for the defined action, allowing to retrieve returned data.
     * @param T
     * @param endpoint the Mastodon API endpoint to call
     * @param method the HTTP method to use
     * @param parameters parameters to use in the action; can be null
     * @param addIdempotencyKey if true, adds idempotency key to header to avoid duplicate POST requests
     */
    internal inline fun <reified T : Any> getMastodonRequest(
        endpoint: String,
        method: Method,
        parameters: Parameters? = null,
        addIdempotencyKey: Boolean = false
    ): MastodonRequest<T> {
        return MastodonRequest(
            executor = {
                when (method) {
                    Method.DELETE -> delete(endpoint, parameters)
                    Method.GET -> get(endpoint, parameters)
                    Method.PATCH -> patch(endpoint, parameters)
                    Method.POST -> post(endpoint, parameters, addIdempotencyKey)
                    Method.PUT -> put(endpoint, parameters)
                }
            },
            mapper = { JSON_SERIALIZER.decodeFromString<T>(it) }
        )
    }

    /**
     * Returns a MastodonRequest for the defined action, allowing to retrieve returned data as a Pageable.
     * @param T
     * @param endpoint the Mastodon API endpoint to call
     * @param method the HTTP method to use
     * @param parameters parameters to use in the action; can be null
     */
    internal inline fun <reified T : Any> getPageableMastodonRequest(
        endpoint: String,
        method: Method,
        parameters: Parameters? = null
    ): MastodonRequest<Pageable<T>> {
        return MastodonRequest<Pageable<T>>(
            executor = {
                when (method) {
                    Method.DELETE -> delete(endpoint, parameters)
                    Method.GET -> get(endpoint, parameters)
                    Method.PATCH -> patch(endpoint, parameters)
                    Method.POST -> post(endpoint, parameters)
                    Method.PUT -> put(endpoint, parameters)
                }
            },
            mapper = { JSON_SERIALIZER.decodeFromString<T>(it) }
        ).toPageable()
    }

    /**
     * Returns a MastodonRequest for the defined action, allowing to retrieve returned data as a List.
     * @param T
     * @param endpoint the Mastodon API endpoint to call
     * @param method the HTTP method to use
     * @param parameters parameters to use in the action; can be null
     */
    internal inline fun <reified T : Any> getMastodonRequestForList(
        endpoint: String,
        method: Method,
        parameters: Parameters? = null
    ): MastodonRequest<List<T>> {
        return MastodonRequest(
            executor = {
                when (method) {
                    Method.DELETE -> delete(endpoint, parameters)
                    Method.GET -> get(endpoint, parameters)
                    Method.PATCH -> patch(endpoint, parameters)
                    Method.POST -> post(endpoint, parameters)
                    Method.PUT -> put(endpoint, parameters)
                }
            },
            mapper = { JSON_SERIALIZER.decodeFromString<T>(it) }
        )
    }

    /**
     * Performs the defined action and throws an exception if unsuccessful, without returning any data.
     * @param endpoint the Mastodon API endpoint to call
     * @param method the HTTP method to use
     * @param parameters parameters to use in the action; can be null
     */
    @Throws(BigBoneRequestException::class)
    internal fun performAction(endpoint: String, method: Method, parameters: Parameters? = null) {
        val response = when (method) {
            Method.DELETE -> delete(endpoint, parameters)
            Method.GET -> get(endpoint, parameters)
            Method.PATCH -> patch(endpoint, parameters)
            Method.POST -> post(endpoint, parameters)
            Method.PUT -> put(endpoint, parameters)
        }
        response.close()
        if (!response.isSuccessful) {
            throw BigBoneRequestException(response)
        }
    }

    /**
     * Get a response from the Mastodon instance defined for this client using the DELETE method.
     * @param path an absolute path to the API endpoint to call
     * @param body the parameters to use in the request body for this request
     */
    fun delete(path: String, body: Parameters?): Response {
        try {
            val url = fullUrl(scheme, instanceName, port, path)
            debugPrintUrl(url)
            val call = client.newCall(
                Request.Builder()
                    .url(url)
                    .delete(parameterBody(body))
                    .build()
            )
            return call.execute()
        } catch (e: IOException) {
            throw BigBoneRequestException("Request not executed due to network IO issue", e)
        }
    }

    /**
     * Get a response from the Mastodon instance defined for this client using the GET method.
     * @param path an absolute path to the API endpoint to call
     * @param query the parameters to use as query string for this request; may be null
     */
    fun get(path: String, query: Parameters? = null): Response {
        try {
            val url = fullUrl(scheme, instanceName, port, path, query)
            debugPrintUrl(url)
            val call = client.newCall(
                Request.Builder()
                    .url(url)
                    .get()
                    .build()
            )
            return call.execute()
        } catch (e: IOException) {
            throw BigBoneRequestException("Request not executed due to network IO issue", e)
        }
    }

    /**
     * Get a response from the Mastodon instance defined for this client using the PATCH method.
     * @param path an absolute path to the API endpoint to call
     * @param body the parameters to use in the request body for this request
     */
    fun patch(path: String, body: Parameters?): Response {
        try {
            val url = fullUrl(scheme, instanceName, port, path)
            debugPrintUrl(url)
            val call = client.newCall(
                Request.Builder()
                    .url(url)
                    .patch(parameterBody(body))
                    .build()
            )
            return call.execute()
        } catch (e: IOException) {
            throw BigBoneRequestException("Request not executed due to network IO issue", e)
        }
    }

    /**
     * Get a response from the Mastodon instance defined for this client using the POST method.
     * @param path an absolute path to the API endpoint to call
     * @param body the parameters to use in the request body for this request; may be null
     * @param addIdempotencyKey if true, generate idempotency key for this request
     */
    fun post(path: String, body: Parameters? = null, addIdempotencyKey: Boolean = false): Response {
        val idempotencyKey = if (addIdempotencyKey) {
            body?.uuid()
        } else {
            null
        }
        return postRequestBody(path, parameterBody(body), idempotencyKey)
    }

    /**
     * Get a response from the Mastodon instance defined for this client using the POST method. Use this method if
     * you need to build your own RequestBody; see post() otherwise.
     * @param path an absolute path to the API endpoint to call
     * @param body the RequestBody to use for this request
     * @param idempotencyKey optional idempotency value to avoid duplicate calls
     *
     * @see post
     */
    fun postRequestBody(path: String, body: RequestBody, idempotencyKey: String? = null): Response {
        try {
            val url = fullUrl(scheme, instanceName, port, path)
            debugPrintUrl(url)
            val call = client.newCall(
                Request.Builder().apply {
                    url(url)
                    post(body)
                    idempotencyKey?.let {
                        header("Idempotency-Key", it)
                    }
                }.build()
            )
            return call.execute()
        } catch (e: IllegalArgumentException) {
            throw BigBoneRequestException(e)
        } catch (e: IOException) {
            throw BigBoneRequestException("Request not executed due to network IO issue", e)
        }
    }

    /**
     * Get a response from the Mastodon instance defined for this client using the PUT method.
     * @param path an absolute path to the API endpoint to call
     * @param body the parameters to use in the request body for this request
     */
    fun put(path: String, body: Parameters?): Response {
        try {
            val url = fullUrl(scheme, instanceName, port, path)
            debugPrintUrl(url)
            val call = client.newCall(
                Request.Builder()
                    .url(url)
                    .put(parameterBody(body))
                    .build()
            )
            return call.execute()
        } catch (e: IOException) {
            throw BigBoneRequestException("Request not executed due to network IO issue", e)
        }
    }

    /**
     * In debug mode, print out any accessed URL. Debug mode can be activated via MastodonClient.Builder.debug().
     */
    private fun debugPrintUrl(url: HttpUrl) {
        if (debug) {
            println(url.toUrl().toString())
        }
    }

    companion object {
        /**
         * Returns a HttpUrl.
         * @param scheme the schema to be used, either "http" or "https"
         * @param instanceName the Mastodon instance hostname
         * @param port the TCP/IP port to connect to, usually 443
         * @param path Mastodon API endpoint to be called
         * @param query query part of the URL to build; may be null
         */
        fun fullUrl(scheme: String, instanceName: String, port: Int, path: String, query: Parameters? = null): HttpUrl {
            val urlBuilder = HttpUrl.Builder()
                .scheme(scheme)
                .host(instanceName)
                .port(port)
                .addEncodedPathSegments(path)
            query?.let {
                urlBuilder.encodedQuery(it.toQuery())
            }
            return urlBuilder.build()
        }

        /**
         * Returns a RequestBody matching the given parameters.
         * @param parameters list of parameters to use; may be null, in which case the returned RequestBody will be empty
         */
        fun parameterBody(parameters: Parameters?): RequestBody {
            return parameters
                ?.toQuery()
                ?.toRequestBody("application/x-www-form-urlencoded; charset=utf-8".toMediaTypeOrNull())
                ?: emptyRequestBody()
        }
    }

    /**
     * The builder used to create a new instance of [MastodonClient]. New instances of [MastodonClient] should
     * be created using this builder only.
     */
    class Builder(
        private val instanceName: String
    ) {
        private val okHttpClientBuilder = OkHttpClient.Builder()
        private var accessToken: String? = null
        private var debug = false
        private var scheme = "https"
        private var port = 443
        private var trustAllCerts = false
        private var readTimeoutSeconds = 10L
        private var writeTimeoutSeconds = 10L
        private var connectTimeoutSeconds = 10L

        /**
         * Sets the access token required for calling authenticated endpoints.
         * @param accessToken the access token to be used
         */
        fun accessToken(accessToken: String) = apply {
            this.accessToken = accessToken
        }

        /**
         * Makes the client use an unsecured HTTP connection to the Mastodon server.
         *
         * IMPORTANT: Please do not use this on production environments, as it is considered
         * bad practice. Use it solely for testing purposes.
         */
        fun withHttpsDisabled() = apply {
            scheme = "http"
        }

        /**
         * Disables certificate validation and hostname verification.
         *
         * IMPORTANT: Please do not use this on production environments, as it is considered
         * bad practice. Use it solely for testing purposes.
         */
        fun withTrustAllCerts() = apply {
            trustAllCerts = true
            configureForTrustAll(okHttpClientBuilder)
        }

        /**
         * Makes the client use a different port than 443 for connecting to a Mastodon server.
         *
         * IMPORTANT: It is best practice to use the default port 443 when connecting to a
         * Mastodon server.
         */
        fun withPort(port: Int) = apply {
            this.port = port
        }

        /**
         * Enables this client to support Streaming API methods.
         */
        fun useStreamingApi() = apply {
            if (readTimeoutSeconds != 0L) { // a value of 0L means that read timeout is disabled already
                readTimeoutSeconds.coerceAtLeast(60L)
            }
        }

        /**
         * Sets the read timeout for connections of this client.
         * @param timeoutSeconds the new timeout value in seconds; default value is 10 seconds, setting this to 0
         *  disables the timeout completely
         */
        fun setReadTimeoutSeconds(timeoutSeconds: Long) = apply {
            readTimeoutSeconds = timeoutSeconds
        }

        /**
         * Sets the write timeout for connections of this client.
         * @param timeoutSeconds the new timeout value in seconds; default value is 10 seconds, setting this to 0
         *  disables the timeout completely
         */
        fun setWriteTimeoutSeconds(timeoutSeconds: Long) = apply {
            writeTimeoutSeconds = timeoutSeconds
        }

        /**
         * Sets the connect timeout for connections of this client.
         * @param timeoutSeconds the new timeout value in seconds; default value is 10 seconds, setting this to 0
         *  disables the timeout completely
         */
        fun setConnectTimeoutSeconds(timeoutSeconds: Long) = apply {
            connectTimeoutSeconds = timeoutSeconds
        }

        fun debug() = apply {
            this.debug = true
        }

        /**
         * Get the version string for this Mastodon instance.
         * @return a string corresponding to the version of this Mastodon instance
         * @throws BigBoneRequestException if instance version can not be retrieved using any known method or API version
         */
        private fun getInstanceVersion(): String {
            try {
                val serverInfoVersion = NodeInfoClient
                    .retrieveServerInfo(instanceName)
                    ?.software
                    ?.takeIf { it.name == "mastodon" }
                    ?.version
                if (serverInfoVersion != null) return serverInfoVersion
            } catch (_: BigBoneRequestException) {
            }

            // fall back to retrieving from Mastodon API itself
            val instanceVersion = getInstanceVersionFromApi(2) ?: getInstanceVersionFromApi(1)
            return instanceVersion ?: throw BigBoneRequestException("Unable to fetch instance version")
        }

        /**
         * Get the version string for this Mastodon instance, using a specific API version.
         * @param apiVersion the version of API call to use in this request
         * @return a string corresponding to the version of this Mastodon instance, or null if no version string can be
         *  retrieved using the specified API version.
         */
        private fun getInstanceVersionFromApi(apiVersion: Int): String? {
            return try {
                val response = versionedInstanceRequest(apiVersion)
                if (response.isSuccessful) {
                    val instanceVersion: InstanceVersion? = response.body?.string()?.let { responseBody: String ->
                        JSON_SERIALIZER.decodeFromString(responseBody)
                    }
                    instanceVersion?.version
                } else {
                    response.close()
                    null
                }
            } catch (e: Exception) {
                null
            }
        }

        private fun configureForTrustAll(clientBuilder: OkHttpClient.Builder) {
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, arrayOf(TrustAllX509TrustManager), SecureRandom())
            clientBuilder
                .sslSocketFactory(sslContext.socketFactory, TrustAllX509TrustManager)
                .hostnameVerifier(AcceptAllHostnameVerifier)
        }

        /**
         * Returns the server response for an instance request of a specific version. This response needs to be closed
         * by the caller, either by reading from it via response.body?.string(), or by calling response.close().
         * @param version value corresponding to the version that should be returned; falls
         *  back to returning version 1 for illegal values.
         * @return server response for this request
         */
        internal fun versionedInstanceRequest(version: Int): Response {
            val versionString = if (version == 2) {
                "v2"
            } else {
                "v1"
            }
            val clientBuilder = OkHttpClient.Builder()
            if (trustAllCerts) {
                configureForTrustAll(clientBuilder)
            }
            val client = clientBuilder.build()
            return client.newCall(
                Request.Builder().url(
                    fullUrl(
                        scheme, instanceName, port, "api/$versionString/instance"
                    )
                ).get().build()
            ).execute()
        }

        fun build(): MastodonClient {
            return MastodonClient(
                instanceName = instanceName,
                client = okHttpClientBuilder
                    .addNetworkInterceptor(AuthorizationInterceptor(accessToken))
                    .readTimeout(readTimeoutSeconds, TimeUnit.SECONDS)
                    .writeTimeout(writeTimeoutSeconds, TimeUnit.SECONDS)
                    .connectTimeout(connectTimeoutSeconds, TimeUnit.SECONDS)
                    .build()
            ).also {
                it.debug = debug
                it.instanceVersion = getInstanceVersion()
                it.scheme = scheme
                it.port = port
            }
        }
    }

    private object TrustAllX509TrustManager : X509TrustManager {
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
    }

    private object AcceptAllHostnameVerifier : HostnameVerifier {
        override fun verify(hostname: String?, session: SSLSession?): Boolean = true
    }

    private class AuthorizationInterceptor(val accessToken: String? = null) : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val compressedRequest = originalRequest.newBuilder()
                .headers(originalRequest.headers)
                .method(originalRequest.method, originalRequest.body)
                .apply {
                    accessToken?.let {
                        header("Authorization", String.format("Bearer %s", it))
                    }
                }
                .build()
            return chain.proceed(compressedRequest)
        }
    }
}
