package social.bigbone.api.method

import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContainAll
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test
import social.bigbone.Parameters
import social.bigbone.PrecisionDateTime.ValidPrecisionDateTime.ExactTime
import social.bigbone.api.Range
import social.bigbone.api.entity.NotificationType
import social.bigbone.testtool.MockClient
import java.time.Instant

class GroupedNotificationMethodsTest {

    @Test
    fun testGroupedNotificationResponseParsing() {
        val client = MockClient.mock("grouped_notifications_get_all_success.json")
        val groupedNotificationMethods = GroupedNotificationMethods(client)

        val pageable = groupedNotificationMethods.getAllGroupedNotifications(
            range = Range(limit = 2)
        ).execute()

        with(pageable.part.first()) {
            with(accounts) {
                shouldNotBeNull()
                shouldHaveSize(4)
                with(first()) {
                    id shouldBeEqualTo "16"
                    username shouldBeEqualTo "eve"
                    acct shouldBeEqualTo "eve"
                }
                with(get(1)) {
                    id shouldBeEqualTo "3547"
                    username shouldBeEqualTo "alice"
                    acct shouldBeEqualTo "alice"
                }
            }
            with(statuses) {
                shouldNotBeNull()
                shouldHaveSize(2)
                with(first()) {
                    id shouldBeEqualTo "113010503322889311"
                    createdAt shouldBeEqualTo ExactTime(Instant.parse("2024-08-23T08:57:12.057Z"))
                    with(account) {
                        shouldNotBeNull()
                        id shouldBeEqualTo "55911"
                        username shouldBeEqualTo "user"
                        acct shouldBeEqualTo "user"
                    }
                }
            }
            with(notificationGroups) {
                shouldHaveSize(2)
                with(first()) {
                    groupKey shouldBeEqualTo "favourite-113010503322889311-479000"
                    notificationsCount shouldBeEqualTo 2
                    type shouldBeEqualTo NotificationType.FAVOURITE
                    mostRecentNotificationId shouldBeEqualTo 196_014
                    pageMinId shouldBeEqualTo "196013"
                    pageMaxId shouldBeEqualTo "196014"
                    latestPageNotificationAt shouldBeEqualTo ExactTime(Instant.parse("2024-08-23T08:59:56.743Z"))
                    with(sampleAccountIds) {
                        shouldHaveSize(2)
                        get(0) shouldBeEqualTo "16"
                        get(1) shouldBeEqualTo "3547"
                    }
                    statusId shouldBeEqualTo "113010503322889311"
                }
            }
        }
    }

    @Test
    fun `When getting all grouped notifications with all parameters, then call endpoint with correct parameters`() {
        val client = MockClient.mock("grouped_notifications_get_all_success.json")
        val groupedNotificationMethods = GroupedNotificationMethods(client)

        val includeTypes = listOf(NotificationType.FOLLOW, NotificationType.MENTION)
        val excludeTypes = listOf(NotificationType.FAVOURITE)
        val groupedTypes = listOf(NotificationType.FOLLOW)
        groupedNotificationMethods.getAllGroupedNotifications(
            includeTypes = includeTypes,
            excludeTypes = excludeTypes,
            accountId = "1234567",
            expandAccounts = GroupedNotificationMethods.ExpandAccounts.FULL,
            groupedTypes = groupedTypes,
            includeFiltered = true,
            range = Range(
                minId = "23",
                maxId = "42",
                sinceId = "7",
                limit = 2
            )
        ).execute()

        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.get(
                path = "api/v2/notifications",
                query = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            parameters["types[]"]?.shouldContainAll(includeTypes.map(NotificationType::apiName))
            parameters["exclude_types[]"]?.shouldContainAll(excludeTypes.map(NotificationType::apiName))
            parameters["grouped_types[]"]?.shouldContainAll(groupedTypes.map(NotificationType::apiName))
            parameters["account_id"]?.shouldContainAll(listOf("1234567"))

            toQuery() shouldBeEqualTo "max_id=42" +
                "&min_id=23" +
                "&since_id=7" +
                "&limit=2" +
                "&types[]=follow" +
                "&types[]=mention" +
                "&exclude_types[]=favourite" +
                "&account_id=1234567" +
                "&expand_accounts=full" +
                "&grouped_types[]=follow" +
                "&include_filtered=true"
        }
    }
}
