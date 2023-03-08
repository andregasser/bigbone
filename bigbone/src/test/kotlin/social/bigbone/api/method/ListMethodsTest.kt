package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.entity.MastodonList
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class ListMethodsTest {
    @Test
    fun getLists() {
        val client = MockClient.mock("lists.json")
        val listMethods = ListMethods(client)
        val lists = listMethods.getLists().execute()
        val list = lists.first()
        list.id shouldBeEqualTo "12249"
        list.title shouldBeEqualTo "Friends"
        list.repliesPolicy shouldBeEqualTo MastodonList.RepliesPolicy.Followed.value
    }

    @Test
    fun getListsWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val listMethods = ListMethods(client)
            listMethods.getLists().execute()
        }
    }

    @Test
    fun getList() {
        val client = MockClient.mock("list.json")
        val listMethods = ListMethods(client)
        val list = listMethods.getList("listID").execute()
        list.id shouldBeEqualTo "13585"
        list.title shouldBeEqualTo "test"
        list.repliesPolicy shouldBeEqualTo MastodonList.RepliesPolicy.List.value
    }

    @Test
    fun getListWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val listMethods = ListMethods(client)
            listMethods.getList("listID").execute()
        }
    }

    @Test
    fun createList() {
        val client = MockClient.mock("list.json")
        val listMethods = ListMethods(client)
        val list = listMethods.createList("title", MastodonList.RepliesPolicy.List).execute()
        list.id shouldBeEqualTo "13585"
        list.title shouldBeEqualTo "test"
        list.repliesPolicy shouldBeEqualTo MastodonList.RepliesPolicy.List.value
    }

    @Test
    fun createListWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val listMethods = ListMethods(client)
            listMethods.createList("title", MastodonList.RepliesPolicy.List).execute()
        }
    }

    @Test
    fun updateList() {
        val client = MockClient.mock("list.json")
        val listMethods = ListMethods(client)
        val list = listMethods.updateList("listID", "title", MastodonList.RepliesPolicy.List).execute()
        list.id shouldBeEqualTo "13585"
        list.title shouldBeEqualTo "test"
        list.repliesPolicy shouldBeEqualTo MastodonList.RepliesPolicy.List.value
    }

    @Test
    fun updateListWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val listMethods = ListMethods(client)
            listMethods.updateList("listID", "title", MastodonList.RepliesPolicy.Followed).execute()
        }
    }

    @Test
    fun getAccountsInList() {
        val client = MockClient.mock("accounts.json")
        val listMethods = ListMethods(client)
        val pageable = listMethods.getAccountsInList("listID").execute()
        val account = pageable.part.first()
        account.id shouldBeEqualTo "11111"
        account.followingCount shouldBeEqualTo 14_547
    }

    @Test
    fun getAccountsInListWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val listMethods = ListMethods(client)
            listMethods.getAccountsInList("listID").execute()
        }
    }
}
