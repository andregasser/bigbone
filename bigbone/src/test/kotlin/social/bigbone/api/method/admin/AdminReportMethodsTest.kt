package social.bigbone.api.method.admin

import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeFalse
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldBeNullOrEmpty
import org.amshove.kluent.shouldBeTrue
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test
import social.bigbone.Parameters
import social.bigbone.PrecisionDateTime.InvalidPrecisionDateTime
import social.bigbone.PrecisionDateTime.ValidPrecisionDateTime.ExactTime
import social.bigbone.api.Range
import social.bigbone.api.entity.data.ReportCategory
import social.bigbone.testtool.MockClient
import java.time.Instant

class AdminReportMethodsTest {

    @Test
    fun `Given client returning success, when getting all reports, then call correct endpoint and parse response`() {
        val client = MockClient.mock("admin_reports_get_all_reports_success.json")
        val adminReportMethods = AdminReportMethods(client)
        val range = Range(limit = 23, minId = "42", maxId = "1337")
        val resolved = false
        val filedByAccountId = null
        val targetingAccountId = null

        val adminReports = adminReportMethods.getAllReports(
            range = range,
            resolved = resolved,
            filedByAccountId = filedByAccountId,
            targetingAccountId = targetingAccountId
        ).execute()

        with(adminReports.part) {
            shouldHaveSize(1)

            with(first()) {
                id shouldBeEqualTo "3"
                actionTaken.shouldBeFalse()
                actionTakenAt shouldBeEqualTo InvalidPrecisionDateTime.Unavailable
                category shouldBeEqualTo ReportCategory.SPAM
                comment.shouldBeNullOrEmpty()
                forwarded.shouldBeFalse()
                createdAt shouldBeEqualTo ExactTime(Instant.parse("2022-09-09T21:19:23.085Z"))
                updatedAt shouldBeEqualTo ExactTime(Instant.parse("2022-09-09T21:19:23.085Z"))

                with(reportCreator) {
                    id shouldBeEqualTo "108965218747268792"
                    username shouldBeEqualTo "admin"
                    domain.shouldBeNull()
                    email shouldBeEqualTo "admin@mastodon.local"
                    createdAt shouldBeEqualTo ExactTime(Instant.parse("2022-09-08T22:48:07.985Z"))
                    with(account) {
                        id shouldBeEqualTo "108965218747268792"
                        username shouldBeEqualTo "admin"
                        acct shouldBeEqualTo "admin"
                    }
                }

                with(reportedAccount) {
                    id shouldBeEqualTo "108965430868193066"
                    username shouldBeEqualTo "goody"
                    domain.shouldBeNull()
                    createdAt shouldBeEqualTo ExactTime(Instant.parse("2022-09-08T23:42:04.731Z"))
                    email shouldBeEqualTo "goody@mastodon.local"
                }

                assignedModerator.shouldBeNull()
                handledBy.shouldBeNull()
                attachedStatuses.shouldBeEmpty()
                attachedRules.shouldBeEmpty()
            }
        }
        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.get(
                path = adminReportMethods.endpoint,
                query = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "resolved=false" +
                "&max_id=1337" +
                "&min_id=42" +
                "&limit=23"
        }
    }

    @Test
    fun `Given client returning success, when getting a specific report, then call correct endpoint and parse response`() {
        val client = MockClient.mock("admin_reports_get_single_report_success.json")
        val adminReportMethods = AdminReportMethods(client)

        val adminReport = adminReportMethods.getReport(
            reportId = "1234"
        ).execute()

        with(adminReport) {
            id shouldBeEqualTo "2"
            actionTaken.shouldBeTrue()
            actionTakenAt shouldBeEqualTo ExactTime(Instant.parse("2022-09-09T21:38:54.679Z"))
            category shouldBeEqualTo ReportCategory.SPAM
            comment.shouldBeNullOrEmpty()
            forwarded.shouldBeFalse()
            createdAt shouldBeEqualTo ExactTime(Instant.parse("2022-09-09T21:19:44.021Z"))
            updatedAt shouldBeEqualTo ExactTime(Instant.parse("2022-09-09T21:38:54.681Z"))

            with(reportCreator) {
                id shouldBeEqualTo "108965218747268792"
                username shouldBeEqualTo "admin"
                domain.shouldBeNull()
                email shouldBeEqualTo "admin@mastodon.local"
                createdAt shouldBeEqualTo ExactTime(Instant.parse("2022-09-08T22:48:07.985Z"))
                with(account) {
                    id shouldBeEqualTo "108965218747268792"
                    username shouldBeEqualTo "admin"
                    acct shouldBeEqualTo "admin"
                }
            }

            with(reportedAccount) {
                id shouldBeEqualTo "108965430868193066"
                username shouldBeEqualTo "goody"
                domain.shouldBeNull()
                createdAt shouldBeEqualTo ExactTime(Instant.parse("2022-09-08T23:42:04.731Z"))
                email shouldBeEqualTo "goody@mastodon.local"
            }

            assignedModerator.shouldBeNull()

            with(handledBy) {
                shouldNotBeNull()
                id shouldBeEqualTo "108965218747268792"
                username shouldBeEqualTo "admin"
                domain.shouldBeNull()
                email shouldBeEqualTo "admin@mastodon.local"
                createdAt shouldBeEqualTo ExactTime(Instant.parse("2022-09-08T22:48:07.985Z"))
                with(account) {
                    id shouldBeEqualTo "108965218747268792"
                    username shouldBeEqualTo "admin"
                    acct shouldBeEqualTo "admin"
                }
            }
            attachedStatuses.shouldBeEmpty()
            attachedRules.shouldBeEmpty()
        }
        verify {
            client.get(
                path = "${adminReportMethods.endpoint}/1234",
                query = null
            )
        }
    }

    @Test
    fun `Given client returning success, when updating a report, then call correct endpoint and parse response`() {
        val client = MockClient.mock("admin_reports_update_report_success.json")
        val adminReportMethods = AdminReportMethods(client)

        val adminReport = adminReportMethods.updateReportMetadata(
            reportId = "1234",
            updatedCategory = ReportCategory.OTHER,
            violationRuleIds = null
        ).execute()

        with(adminReport) {
            id shouldBeEqualTo "3"
            actionTaken.shouldBeFalse()
            actionTakenAt shouldBeEqualTo InvalidPrecisionDateTime.Unavailable
            category shouldBeEqualTo ReportCategory.OTHER
            attachedRules.shouldBeEmpty()
        }
        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.put(
                path = "${adminReportMethods.endpoint}/1234",
                body = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            toQuery() shouldBeEqualTo "category=other"
        }
    }

    @Test
    fun `Given client returning success, when assigning report to self, then call correct endpoint and parse response`() {
        val client = MockClient.mock("admin_reports_assign_to_self_success.json")
        val adminReportMethods = AdminReportMethods(client)

        val adminReport = adminReportMethods.assignReportToSelf(
            reportId = "1234"
        ).execute()

        with(adminReport) {
            assignedModerator.shouldNotBeNull()
        }
        verify {
            client.post(
                path = "${adminReportMethods.endpoint}/1234/assign_to_self",
                body = null
            )
        }
    }

    @Test
    fun `Given client returning success, when unassigning report, then call correct endpoint and parse response`() {
        val client = MockClient.mock("admin_reports_unassign_success.json")
        val adminReportMethods = AdminReportMethods(client)

        val adminReport = adminReportMethods.unassignReport(
            reportId = "1234"
        ).execute()

        with(adminReport) {
            assignedModerator.shouldBeNull()
        }
        verify {
            client.post(
                path = "${adminReportMethods.endpoint}/1234/unassign",
                body = null
            )
        }
    }

    @Test
    fun `Given client returning success, when resolving report, then call correct endpoint and parse response`() {
        val client = MockClient.mock("admin_reports_resolve_success.json")
        val adminReportMethods = AdminReportMethods(client)

        val adminReport = adminReportMethods.resolveReport(
            reportId = "1234"
        ).execute()

        with(adminReport) {
            actionTaken.shouldBeTrue()
            actionTakenAt shouldBeEqualTo ExactTime(Instant.parse("2022-09-11T14:46:22.936Z"))
        }
        verify {
            client.post(
                path = "${adminReportMethods.endpoint}/1234/resolve",
                body = null
            )
        }
    }

    @Test
    fun `Given client returning success, when reopening report, then call correct endpoint and parse response`() {
        val client = MockClient.mock("admin_reports_reopen_success.json")
        val adminReportMethods = AdminReportMethods(client)

        val adminReport = adminReportMethods.reopenReport(
            reportId = "1234"
        ).execute()

        with(adminReport) {
            actionTaken.shouldBeFalse()
            actionTakenAt shouldBeEqualTo InvalidPrecisionDateTime.Unavailable
        }
        verify {
            client.post(
                path = "${adminReportMethods.endpoint}/1234/reopen",
                body = null
            )
        }
    }
}
