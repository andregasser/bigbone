package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class ReportMethodsTest {
    @Test
    fun getReports() {
        val client = MockClient.mock("reports.json")
        val reportMethods = ReportMethods(client)
        val pageable = reportMethods.getReports().execute()
        val report = pageable.part.first()
        report.id shouldBeEqualTo "100"
        report.actionTaken shouldBeEqualTo "test"
    }

    @Test
    fun getReportsWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val reportMethods = ReportMethods(client)
            reportMethods.getReports().execute()
        }
    }

    @Test
    fun fileReportWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val reportMethods = ReportMethods(client)
            reportMethods.fileReport("10", "20", "test").execute()
        }
    }
}
