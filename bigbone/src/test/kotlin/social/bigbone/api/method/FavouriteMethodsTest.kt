package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class FavouriteMethodsTest {
    @Test
    fun getFavourites() {
        val client = MockClient.mock("favourites.json")

        val favorites = FavouriteMethods(client)
        val pageable = favorites.getFavourites().execute()
        val status = pageable.part.first()
        status.id shouldBeEqualTo "1111"
    }

    @Test
    fun exception() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val favorites = FavouriteMethods(client)
            favorites.getFavourites().execute()
        }
    }
}
