package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Report
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.genericType
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#reports
 */
class Reports(val client: MastodonClient) {
    // GET /api/v1/reports
    fun getReports(range: Range = Range()): List<Report> {
        val response = client.get(
                "reports",
                range.toParameter()
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Report>>(
                    body,
                    genericType<List<Report>>()
            )
        } else {
            throw Mastodon4jRequestException(response.message())
        }
    }

    /**
     * POST /api/v1/reports
     * account_id: The ID of the account to report
     * status_ids: The IDs of statuses to report (can be an array)
     * comment: A comment to associate with the report.
     */
    fun postReport(accountId: Long, statusId: Long, comment: String): Report {
        val parameters = Parameter().apply {
            append("account_id", accountId)
            append("status_ids", statusId)
            append("comment", comment)
        }.build()

        val response = client.post("reports",
                RequestBody.create(
                        MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                        parameters
                ))
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Report::class.java
            )
        } else {
            throw Mastodon4jRequestException(response.message())
        }
    }
}