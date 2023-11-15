package social.bigbone.api.method

import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContainAll
import org.amshove.kluent.shouldNotContain
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.Parameters
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class SearchMethodsTest {

    @Test
    fun search() {
        val client = MockClient.mock("search.json")

        val searchMethodsMethod = SearchMethods(client)
        val result = searchMethodsMethod.searchContent("test").execute()
        result.accounts.size shouldBeEqualTo 6
        result.statuses.size shouldBeEqualTo 2
        result.hashtags.size shouldBeEqualTo 1
    }

    @Test
    fun searchOnlyForSpecificType() {
        val client = MockClient.mock("search_only_accounts.json")
        val type = SearchMethods.SearchType.ACCOUNTS
        val searchMethodsMethod = SearchMethods(client)
        val result = searchMethodsMethod.searchContent("test", type = type).execute()
        result.accounts.size shouldBeEqualTo 6
        result.statuses.size `should be equal to` 0
        result.hashtags.size `should be equal to` 0
    }

    @Test
    fun searchRangingTheIdsForSpecificType() {
        val client = MockClient.mock("search_for_statuses_ranging_ids.json")
        val type = SearchMethods.SearchType.STATUSES
        val maxId = "10000"
        val minId = "900"
        val searchMethodsMethod = SearchMethods(client)
        val result = searchMethodsMethod.searchContent("test", type = type, maxId = maxId, minId = minId).execute()
        result.accounts.size `should be equal to` 0
        result.statuses.size shouldBeEqualTo 4
        result.hashtags.size `should be equal to` 0
        result.statuses.all { it.id.toLong() in minId.toLong()..maxId.toLong() }
    }

    @Test
    fun searchTypeParameterIsProperlyCapitalized() {
        val client = MockClient.mock("search.json")
        val searchMethodsMethod = SearchMethods(client)

        searchMethodsMethod.searchContent("query", SearchMethods.SearchType.STATUSES).execute()
        val parametersCapturingSlot = slot<Parameters>()
        verify {
            client.get(
                path = "api/v2/search",
                query = capture(parametersCapturingSlot)
            )
        }
        with(parametersCapturingSlot.captured) {
            parameters["type"]?.shouldContainAll(listOf("statuses"))
            parameters["type"]?.shouldNotContain(listOf("STATUSES"))
        }
    }

    @Test
    fun searchWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val searchMethodsMethod = SearchMethods(client)
            searchMethodsMethod.searchContent("test").execute()
        }
    }
}
