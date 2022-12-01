package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ReportsTest {
    @Test
    fun getReports() {
        val client = MockClient.mock("reports.json")
        val reports = Reports(client)
        val pageable = reports.getReports().execute()
        val report = pageable.part.first()
        report.id shouldBeEqualTo 100L
        report.actionTaken shouldBeEqualTo "test"
    }

    @Test
    fun getReportsWithException() {
        Assertions.assertThrows(Mastodon4jRequestException::class.java) {
            val client = MockClient.ioException()
            val reports = Reports(client)
            reports.getReports().execute()
        }
    }

    @Test
    fun postReportWithException() {
        Assertions.assertThrows(Mastodon4jRequestException::class.java) {
            val client = MockClient.ioException()
            val reports = Reports(client)
            reports.postReport(10, 20, "test").execute()
        }
    }
}
