package social.bigbone.api.method

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class ReportMethodsTest {
    @Test
    fun fileReportWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val reportMethods = ReportMethods(client)
            val statusIds = listOf("12345", "67890")
            reportMethods.fileReport("10", statusIds, "test").execute()
        }
    }
}
