package social.bigbone

import com.google.gson.Gson
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import social.bigbone.api.Pageable
import social.bigbone.api.exception.BigboneRequestException
import social.bigbone.extension.emptyRequestBody
import java.io.IOException
import java.util.concurrent.TimeUnit

open class MastodonClient
private constructor(
    private val instanceName: String,
    private val client: OkHttpClient,
    private val gson: Gson
) {
    private var debug = false

    enum class Method {
        DELETE,
        GET,
        POST,
        PATCH
    }

    open fun getSerializer() = gson

    open fun getInstanceName() = instanceName

    /**
     * Returns a MastodonRequest for the defined action, allowing to retrieve returned data.
     * @param endpoint the Mastodon API endpoint to call
     * @param method the HTTP method to use
     * @param parameters parameters to use in the action; can be null
     */
    internal inline fun <reified T : Any> getMastodonRequest(
        endpoint: String,
        method: Method,
        parameters: Parameter? = null
    ): MastodonRequest<T> {
        return MastodonRequest(
            {
                when (method) {
                    Method.DELETE -> delete(endpoint)
                    Method.GET -> get(endpoint, parameters)
                    Method.PATCH -> patch(endpoint, parameters)
                    Method.POST -> post(endpoint, parameters)
                }
            },
            { getSerializer().fromJson(it, T::class.java) }
        )
    }

    /**
     * Returns a MastodonRequest for the defined action, allowing to retrieve returned data as a Pageable.
     * @param endpoint the Mastodon API endpoint to call
     * @param method the HTTP method to use
     * @param parameters parameters to use in the action; can be null
     */
    internal inline fun <reified T : Any> getPageableMastodonRequest(
        endpoint: String,
        method: Method,
        parameters: Parameter? = null
    ): MastodonRequest<Pageable<T>> {
        return MastodonRequest<Pageable<T>>(
            {
                when (method) {
                    Method.DELETE -> delete(endpoint)
                    Method.GET -> get(endpoint, parameters)
                    Method.PATCH -> patch(endpoint, parameters)
                    Method.POST -> post(endpoint, parameters)
                }
            },
            { getSerializer().fromJson(it, T::class.java) }
        ).toPageable()
    }

    /**
     * Returns a MastodonRequest for the defined action, allowing to retrieve returned data as a List.
     * @param endpoint the Mastodon API endpoint to call
     * @param method the HTTP method to use
     * @param parameters parameters to use in the action; can be null
     */
    internal inline fun <reified T : Any> getMastodonRequestForList(
        endpoint: String,
        method: Method,
        parameters: Parameter? = null
    ): MastodonRequest<List<T>> {
        return MastodonRequest(
            {
                when (method) {
                    Method.DELETE -> delete(endpoint)
                    Method.GET -> get(endpoint, parameters)
                    Method.PATCH -> patch(endpoint, parameters)
                    Method.POST -> post(endpoint, parameters)
                }
            },
            { getSerializer().fromJson(it, T::class.java) }
        )
    }

    /**
     * Performs the defined action and throws an exception if unsuccessful, without returning any data.
     * @param endpoint the Mastodon API endpoint to call
     * @param method the HTTP method to use
     */
    @Throws(BigboneRequestException::class)
    internal fun performAction(endpoint: String, method: Method) {
        val response = when (method) {
            Method.DELETE -> delete(endpoint)
            Method.GET -> get(endpoint)
            Method.PATCH -> patch(endpoint, null)
            Method.POST -> post(endpoint)
        }
        if (!response.isSuccessful) {
            throw BigboneRequestException(response)
        }
    }

    /**
     * Get a response from the Mastodon instance defined for this client using the DELETE method.
     * @param path an absolute path to the API endpoint to call
     */
    open fun delete(path: String): Response {
        try {
            val url = fullUrl(instanceName, path)
            debugPrintUrl(url)
            val call = client.newCall(
                Request.Builder()
                    .url(url)
                    .delete()
                    .build()
            )
            return call.execute()
        } catch (e: IOException) {
            throw BigboneRequestException(e)
        }
    }

    /**
     * Get a response from the Mastodon instance defined for this client using the GET method.
     * @param path an absolute path to the API endpoint to call
     * @param query the parameters to use as query string for this request; may be null
     */
    open fun get(path: String, query: Parameter? = null): Response {
        try {
            val url = fullUrl(instanceName, path, query)
            debugPrintUrl(url)
            val call = client.newCall(
                Request.Builder()
                    .url(url)
                    .get()
                    .build()
            )
            return call.execute()
        } catch (e: IOException) {
            throw BigboneRequestException(e)
        }
    }

    /**
     * Get a response from the Mastodon instance defined for this client using the PATCH method.
     * @param path an absolute path to the API endpoint to call
     * @param body the parameters to use in the request body for this request
     */
    open fun patch(path: String, body: Parameter?): Response {
        if (body == null) {
            throw BigboneRequestException(Exception("body must not be empty"))
        }

        try {
            val url = fullUrl(instanceName, path)
            debugPrintUrl(url)
            val call = client.newCall(
                Request.Builder()
                    .url(url)
                    .patch(parameterBody(body))
                    .build()
            )
            return call.execute()
        } catch (e: IOException) {
            throw BigboneRequestException(e)
        }
    }

    /**
     * Get a response from the Mastodon instance defined for this client using the POST method.
     * @param path an absolute path to the API endpoint to call
     * @param body the parameters to use in the request body for this request; may be null
     */
    open fun post(path: String, body: Parameter? = null): Response =
        postRequestBody(path, parameterBody(body))

    /**
     * Get a response from the Mastodon instance defined for this client using the POST method. Use this method if
     * you need to build your own RequestBody; see post() otherwise.
     * @param path an absolute path to the API endpoint to call
     * @param body the RequestBody to use for this request
     *
     * @see post
     */
    open fun postRequestBody(path: String, body: RequestBody): Response {
        try {
            val url = fullUrl(instanceName, path)
            debugPrintUrl(url)
            val call = client.newCall(
                Request.Builder()
                    .url(url)
                    .post(body)
                    .build()
            )
            return call.execute()
        } catch (e: IllegalArgumentException) {
            throw BigboneRequestException(e)
        } catch (e: IOException) {
            throw BigboneRequestException(e)
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
         * Returns a HttpUrl
         * @param instanceName host of a Mastodon instance
         * @param path Mastodon API endpoint to be called
         * @param query query part of the URL to build; may be null
         */
        fun fullUrl(instanceName: String, path: String, query: Parameter? = null): HttpUrl {
            val urlBuilder = HttpUrl.Builder()
                .scheme("https")
                .host(instanceName)
                .addEncodedPathSegments(path)
            query?.let {
                urlBuilder.encodedQuery(it.build())
            }
            return urlBuilder.build()
        }

        /**
         * Returns a RequestBody matching the given parameters.
         * @param parameters list of parameters to use; may be null, in which case the returned RequestBody will be empty
         */
        fun parameterBody(parameters: Parameter?): RequestBody {
            return parameters
                ?.build()
                ?.toRequestBody("application/x-www-form-urlencoded; charset=utf-8".toMediaTypeOrNull())
                ?: emptyRequestBody()
        }
    }

    class Builder(
        private val instanceName: String
    ) {
        private val okHttpClientBuilder = OkHttpClient.Builder()
        private val gson = Gson()
        private var accessToken: String? = null
        private var debug = false

        fun accessToken(accessToken: String) = apply {
            this.accessToken = accessToken
        }

        fun useStreamingApi() = apply {
            okHttpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
        }

        fun debug() = apply {
            this.debug = true
        }

        fun build(): MastodonClient {
            return MastodonClient(
                instanceName,
                okHttpClientBuilder.addNetworkInterceptor(AuthorizationInterceptor(accessToken)).build(),
                gson
            ).also {
                it.debug = debug
            }
        }
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
