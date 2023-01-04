package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigboneRequestException
import social.bigbone.testtool.MockClient

class FavouritesTest {
    @Test
    fun getFavourites() {
        val client = MockClient.mock("favourites.json")

        val favorites = Favourites(client)
        val pageable = favorites.getFavourites().execute()
        val status = pageable.part.first()
        status.id shouldBeEqualTo "103186075217296344"
    }

    @Test
    fun exception() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val favorites = Favourites(client)
            favorites.getFavourites().execute()
        }
    }
}
