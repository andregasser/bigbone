package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class SuggestionMethodsTest {

    @Test
    fun getSuggestions() {
        val client = MockClient.mock("suggestions.json")
        val suggestionMethods = SuggestionMethods(client)
        val suggestions = suggestionMethods.getSuggestions(2).execute()
        suggestions shouldHaveSize 2
        suggestions[0].source.name.lowercase() shouldBeEqualTo "past_interactions"
        suggestions[1].source.name.lowercase() shouldBeEqualTo "global"
    }

    @Test
    fun removeSuggestionWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val suggestionMethods = SuggestionMethods(client)
            suggestionMethods.removeSuggestion("accountId")
        }
    }

    @Test
    fun overRangedLimitValueThrowsException() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            val client = MockClient.ioException()
            val suggestionMethods = SuggestionMethods(client)
            suggestionMethods.getSuggestions(100).execute()
        }
    }
}
