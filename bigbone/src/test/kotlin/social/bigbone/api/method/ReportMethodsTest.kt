package social.bigbone.api.method

import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.shouldContainIgnoringCase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.entity.data.ReportCategory
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class ReportMethodsTest {

    @Test
    fun reportFile() {
        val client = MockClient.mock("reports.json")
        val fileReportMethod = ReportMethods(client)
        val result = fileReportMethod.fileReport(accountId = "testId", category = ReportCategory.SPAM, comment = "spam").execute()
        result.category `should be equal to` ReportCategory.SPAM.name.lowercase()
        result.comment shouldContainIgnoringCase "spam"
        result.id `should be equal to` "testId"
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
