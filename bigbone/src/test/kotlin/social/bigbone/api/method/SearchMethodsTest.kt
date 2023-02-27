package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
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
    fun searchWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val searchMethodsMethod = SearchMethods(client)
            searchMethodsMethod.searchContent("test").execute()
        }
    }
}
