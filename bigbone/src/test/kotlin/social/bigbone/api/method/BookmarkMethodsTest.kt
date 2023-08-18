package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class BookmarkMethodsTest {
    @Test
    fun getBookmarks() {
        val client = MockClient.mock("bookmarks.json")

        val bookmarkMethods = BookmarkMethods(client)
        val pageable = bookmarkMethods.getBookmarks().execute()
        val bookmark = pageable.part.first()
        bookmark.content shouldBeEqualTo "Bookmarked status"
        bookmark.account shouldNotBe null
        bookmark.account!!.displayName shouldBeEqualTo "test"
        bookmark.account!!.username shouldBeEqualTo "test"
    }

    @Test
    fun getBlocksException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val bookmarkMethods = BookmarkMethods(client)
            bookmarkMethods.getBookmarks().execute()
        }
    }
}
