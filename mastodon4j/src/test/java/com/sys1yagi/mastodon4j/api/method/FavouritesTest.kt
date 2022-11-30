package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class FavouritesTest {
    @Test
    fun getFavourites() {
        val client = MockClient.mock("favourites.json")

        val favorites = Favourites(client)
        val pageable = favorites.getFavourites().execute()
        val status = pageable.part.first()
        status.id shouldBeEqualTo 1111
    }

    @Test
    fun exception() {
        Assertions.assertThrows(Mastodon4jRequestException::class.java) {
            val client = MockClient.ioException()
            val favorites = Favourites(client)
            favorites.getFavourites().execute()
        }
    }
}
