package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigboneRequestException
import social.bigbone.testtool.MockClient

class StatusesTest {
    @Test
    fun getStatus() {
        val client = MockClient.mock("status.json")
        val statuses = Statuses(client)
        val status = statuses.getStatus("1").execute()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun getStatusWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.getStatus("1").execute()
        }
    }

    @Test
    fun getContext() {
        val client = MockClient.mock("context.json")
        val statuses = Statuses(client)
        val context = statuses.getContext("1").execute()
        context.ancestors.size shouldBeEqualTo 2
        context.descendants.size shouldBeEqualTo 1
    }

    @Test
    fun getContextWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.getContext("1").execute()
        }
    }

    @Test
    fun getCard() {
        val client = MockClient.mock("card.json")
        val statuses = Statuses(client)
        val card = statuses.getCard("1").execute()
        card.url shouldBeEqualTo "The url associated with the card"
        card.title shouldBeEqualTo "The title of the card"
        card.description shouldBeEqualTo "The card description"
        card.image shouldNotBe null
    }

    @Test
    fun getCardWithExcetpion() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.getCard("1").execute()
        }
    }

    @Test
    fun getRebloggedBy() {
        val client = MockClient.mock("reblogged_by.json")
        val statuses = Statuses(client)
        val pageable = statuses.getRebloggedBy("1").execute()
        val account = pageable.part.first()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test
    fun getRebloggedByWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.getRebloggedBy("1").execute()
        }
    }

    @Test
    fun getFavouritedBy() {
        val client = MockClient.mock("reblogged_by.json")
        val statuses = Statuses(client)
        val pageable = statuses.getFavouritedBy("1").execute()
        val account = pageable.part.first()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test
    fun getFavouritedByWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.getFavouritedBy("1").execute()
        }
    }

    @Test
    fun postStatus() {
        val client = MockClient.mock("status.json")
        val statuses = Statuses(client)
        val status = statuses.postStatus("a", null, null, false, null).execute()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun postStatusWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.postStatus("a", null, null, false, null).execute()
        }
    }

    @Test
    fun postReblog() {
        val client = MockClient.mock("status.json")
        val statuses = Statuses(client)
        val status = statuses.postReblog("1").execute()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun postReblogWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.postReblog("1").execute()
        }
    }

    @Test
    fun postUnreblog() {
        val client = MockClient.mock("status.json")
        val statuses = Statuses(client)
        val status = statuses.postUnreblog("1").execute()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun postUnreblogWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.postUnreblog("1").execute()
        }
    }

    @Test
    fun postFavourite() {
        val client = MockClient.mock("status.json")
        val statuses = Statuses(client)
        val status = statuses.postFavourite("1").execute()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun postFavouriteWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.postFavourite("1").execute()
        }
    }

    @Test
    fun postUnfavourite() {
        val client = MockClient.mock("status.json")
        val statuses = Statuses(client)
        val status = statuses.postUnfavourite("1").execute()
        status.id shouldBeEqualTo "11111"
    }

    @Test
    fun postUnfavouriteWithException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val statuses = Statuses(client)
            statuses.postUnfavourite("1").execute()
        }
    }
}
