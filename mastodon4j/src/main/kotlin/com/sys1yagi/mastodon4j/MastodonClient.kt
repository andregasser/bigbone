package com.sys1yagi.mastodon4j

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.emptyRequestBody
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.util.concurrent.TimeUnit

open class MastodonClient
private constructor(
    private val instanceName: String,
    private val client: OkHttpClient,
    private val gson: Gson
) {
    private var debug = false
    private val baseUrl = "https://${instanceName}"

    private fun debugPrint(log: String) {
        if (debug) {
            println(log)
        }
    }

    open fun getSerializer() = gson

    open fun getInstanceName() = instanceName

    /**
     * Get a response from the Mastodon instance defined for this client using the GET method.
     * @param path an absolute path to the API endpoint to call
     * @param parameters the parameters to use for this request; may be null
     */
    open fun get(path: String, parameters: Parameter? = null): Response {
        try {
            val url = "$baseUrl/$path"
            debugPrint(url)
            val urlWithParams = parameters?.let {
                "$url?${it.build()}"
            } ?: url
            val call = client.newCall(
                Request.Builder()
                    .url(urlWithParams)
                    .get()
                    .build())
            return call.execute()
        } catch (e: IOException) {
            throw Mastodon4jRequestException(e)
        }
    }

    /**
     * Get a response from the Mastodon instance defined for this client using the POST method.
     * @param path an absolute path to the API endpoint to call
     * @param parameters the parameters to use for this request; may be null
     */
    open fun post(path: String, parameters: Parameter? = null): Response {
        val parameterBody = parameters
            ?.build()?.toRequestBody("application/x-www-form-urlencoded; charset=utf-8".toMediaTypeOrNull())
            ?: emptyRequestBody()

        return postRequestBody(path, parameterBody)
    }

    /**
     * Get a response from the Mastodon instance defined for this client using the POST method. Use this method if
     * you need to build your own RequestBody; see post() otherwise.
     * @param path an absolute path to the API endpoint to call
     * @param body the RequestBody to use for this request
     *
     * @see post
     */
    open fun postRequestBody(path: String, body: RequestBody): Response {
        val url = "$baseUrl/$path"
        try {
            debugPrint(url)
            val call = client.newCall(
                Request.Builder()
                    .url(url)
                    .post(body)
                    .build())
            return call.execute()
        } catch (e: IllegalArgumentException) {
            throw Mastodon4jRequestException(e)
        } catch (e: IOException) {
            throw Mastodon4jRequestException(e)
        }
    }

    /**
     * Get a response from the Mastodon instance defined for this client using the PATCH method.
     * @param path an absolute path to the API endpoint to call
     * @param parameters the parameters to use for this request
     */
    open fun patch(path: String, parameters: Parameter): Response {
        val parameterBody = parameters
            .build()
            .toRequestBody("application/x-www-form-urlencoded; charset=utf-8".toMediaTypeOrNull())

        try {
            val url = "$baseUrl/$path"
            debugPrint(url)
            val call = client.newCall(
                Request.Builder()
                    .url(url)
                    .patch(parameterBody)
                    .build()
            )
            return call.execute()
        } catch (e: IOException) {
            throw Mastodon4jRequestException(e)
        }
    }

    /**
     * Get a response from the Mastodon instance defined for this client using the DELETE method.
     * @param path an absolute path to the API endpoint to call
     */
    open fun delete(path: String): Response {
        try {
            val url = "$baseUrl/$path"
            debugPrint(url)
            val call = client.newCall(
                Request.Builder()
                    .url(url)
                    .delete()
                    .build()
            )
            return call.execute()
        } catch (e: IOException) {
            throw Mastodon4jRequestException(e)
        }
    }

    class Builder(private val instanceName: String,
                  private val gson: Gson) {

        private val okHttpClientBuilder = OkHttpClient.Builder()
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
