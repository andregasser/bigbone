package social.bigbone.api.method

import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldContainIgnoringCase
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.entity.Report.ReportType
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

class ReportMethodsTest {

    @Test
    fun reportFile() {
        val client = MockClient.mock("reports.json")
        val fileReportMethod = ReportMethods(client)

        val result = fileReportMethod.fileReport(
            accountId = "testId",
            category = ReportType.SPAM,
            comment = "spam"
        ).execute()

        with(result) {
            category `should be equal to` ReportType.SPAM.type
            comment shouldContainIgnoringCase "Spam account"
            id shouldBeEqualTo "testId"
            actionTakenAt.shouldBeNull()
            createdAt shouldBeEqualTo Instant.parse("2022-08-25T09:56:16.763Z")
        }

        with(result.targetAccount) {
            shouldNotBeNull()
            createdAt shouldBeEqualTo Instant.parse("2022-05-26T00:00:00.000Z")
            lastStatusAt shouldBeEqualTo LocalDate.parse("2022-08-25").atStartOfDay(ZoneOffset.UTC).toInstant()
        }
    }

    @Test
    fun fileReportWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val reportMethods = ReportMethods(client)
            val statusIds = listOf("12345", "67890")
            reportMethods.fileReport("10", statusIds = statusIds, comment = "test").execute()
        }
    }
}
