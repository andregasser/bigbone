package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.entity.Results
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#search
 */
class Search(val client: MastodonClient) {

    /**
     * GET /api/v1/search
     * q: The search query
     * resolve: Whether to resolve non-local accounts
     */
    fun getSearch(query: String, resolve: Boolean = true): Results {
        val response = client.get(
                "search",
                Parameter()
                        .append("q", query)
                        .append("resolve", resolve)
        )

        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<Results>(
                    body,
                    Results::class.java
            )
        } else {
            throw Mastodon4jRequestException(response.message())
        }
    }
}