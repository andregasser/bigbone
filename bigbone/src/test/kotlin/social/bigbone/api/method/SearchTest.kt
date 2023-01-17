package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class SearchTest {

    @Test
    fun search() {
        val client = MockClient.mock("search.json")

        val searchMethod = Search(client)
        val result = searchMethod.searchContent("test").execute()
        result.accounts.size shouldBeEqualTo 6
        result.statuses.size shouldBeEqualTo 2
        result.hashtags.size shouldBeEqualTo 1
    }

    @Test
    fun searchWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val searchMethod = Search(client)
            searchMethod.searchContent("test").execute()
        }
    }
}
