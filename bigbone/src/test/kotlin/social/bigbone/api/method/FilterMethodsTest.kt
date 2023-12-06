package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.entity.Filter
import social.bigbone.api.entity.FilterKeyword
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

@Suppress("ktlint:standard:max-line-length")
class FilterMethodsTest {
    @Test
    fun listFilters() {
        val client = MockClient.mock("filter_list.json")
        val filterMethods = FilterMethods(client)
        val filters = filterMethods.listFilters().execute()
        filters.size shouldBeEqualTo 2
        filters.first().id shouldBeEqualTo "20060"
    }

    @Test
    fun listFiltersWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val filterMethods = FilterMethods(client)
            filterMethods.listFilters().execute()
        }
    }

    @Test
    fun viewFilter() {
        val client = MockClient.mock("filter.json")
        val filterMethods = FilterMethods(client)
        val filter = filterMethods.viewFilter("filterId").execute()
        filter.id shouldBeEqualTo "20060"
        filter.title shouldBeEqualTo "Remove Twitter crossposts from public timeline"
        filter.keywords.size shouldBeEqualTo 3
        filter.keywords.first().id shouldBeEqualTo "1311"
    }

    @Test
    fun viewFilterWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val filterMethods = FilterMethods(client)
            filterMethods.viewFilter("filterId").execute()
        }
    }

    @Test
    fun createFilter() {
        val client = MockClient.mock("filter.json")
        val filterMethods = FilterMethods(client)
        val filterKeyword = FilterKeyword("0", "keyword", false)
        val filter = filterMethods.createFilter(
            "title",
            listOf(Filter.FilterContext.PUBLIC),
            listOf(filterKeyword)
        ).execute()
        filter.context.contains(Filter.FilterContext.PUBLIC) shouldBeEqualTo true
        filter.keywords.first().keyword shouldBeEqualTo "from birdsite"
        filter.keywords.first().wholeWord shouldBeEqualTo true
    }

    @Test
    fun createFilterWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val filterMethods = FilterMethods(client)
            val filterKeyword = FilterKeyword("0", "keyword", false)
            filterMethods.createFilter("title", listOf(Filter.FilterContext.PUBLIC), listOf(filterKeyword)).execute()
        }
    }

    @Test
    fun updateFilter() {
        val client = MockClient.mock("filter.json")
        val filterMethods = FilterMethods(client)
        val filter = filterMethods.updateFilter(
            filterId = "filterId",
            filterAction = Filter.FilterAction.HIDE,
            expiresIn = 3600
        ).execute()
        filter.filterAction.name.lowercase() shouldBeEqualTo "hide"
    }

    @Test
    fun updateFilterWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val filterMethods = FilterMethods(client)
            filterMethods.updateFilter(
                filterId = "filterId",
                filterAction = Filter.FilterAction.HIDE,
                expiresIn = 3600
            ).execute()
        }
    }

    @Test
    fun deleteFilterWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val filterMethods = FilterMethods(client)
            filterMethods.deleteFilter("filterId")
        }
    }

    @Test
    fun listKeywords() {
        val client = MockClient.mock("filter_keywords_list.json")
        val filterMethods = FilterMethods(client)
        val keywords = filterMethods.listKeywords("filterId").execute()
        keywords.size shouldBeEqualTo 3
        keywords[0].keyword shouldBeEqualTo "from birdsite"
        keywords[0].wholeWord shouldBeEqualTo true
        keywords[1].keyword shouldBeEqualTo "@twitter.com"
        keywords[1].wholeWord shouldBeEqualTo false
    }

    @Test
    fun listKeywordsWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val filterMethods = FilterMethods(client)
            filterMethods.listKeywords("filterId").execute()
        }
    }

    @Test
    fun addKeyword() {
        val client = MockClient.mock("filter_keyword.json")
        val filterMethods = FilterMethods(client)
        val filterKeyword = FilterKeyword("0", "testKeyword", true)
        val keyword = filterMethods.addKeyword("filterId", filterKeyword).execute()
        keyword.id shouldBeEqualTo "35583"
        keyword.keyword shouldBeEqualTo "some"
        keyword.wholeWord shouldBeEqualTo false
    }

    @Test
    fun addKeywordWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val filterMethods = FilterMethods(client)
            val filterKeyword = FilterKeyword("0", "testKeyword", true)
            filterMethods.addKeyword("filterId", filterKeyword).execute()
        }
    }

    @Test
    fun viewKeyword() {
        val client = MockClient.mock("filter_keyword.json")
        val filterMethods = FilterMethods(client)
        val keyword = filterMethods.viewKeyword("keywordId").execute()
        keyword.id shouldBeEqualTo "35583"
        keyword.keyword shouldBeEqualTo "some"
        keyword.wholeWord shouldBeEqualTo false
    }

    @Test
    fun viewKeywordWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val filterMethods = FilterMethods(client)
            filterMethods.viewKeyword("keywordId").execute()
        }
    }

    @Test
    fun updateKeyword() {
        val client = MockClient.mock("filter_keyword.json")
        val filterMethods = FilterMethods(client)
        val filterKeyword = FilterKeyword("0", "testKeyword", true)
        val keyword = filterMethods.updateKeyword(filterKeyword).execute()
        keyword.id shouldBeEqualTo "35583"
        keyword.keyword shouldBeEqualTo "some"
        keyword.wholeWord shouldBeEqualTo false
    }

    @Test
    fun updateKeywordWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val filterMethods = FilterMethods(client)
            val filterKeyword = FilterKeyword("0", "testKeyword", true)
            filterMethods.updateKeyword(filterKeyword).execute()
        }
    }

    @Test
    fun deleteKeywordWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val filterMethods = FilterMethods(client)
            filterMethods.deleteKeyword("keywordId")
        }
    }

    @Test
    fun listStatusFilters() {
        val client = MockClient.mock("filter_status_list.json")
        val filterMethods = FilterMethods(client)
        val filterStatuses = filterMethods.listStatusFilters("filterId").execute()
        filterStatuses.size shouldBeEqualTo 5
        filterStatuses[0].id shouldBeEqualTo "897"
        filterStatuses[0].statusId shouldBeEqualTo "109416512469928632"
        filterStatuses[1].id shouldBeEqualTo "898"
        filterStatuses[1].statusId shouldBeEqualTo "109416512469928633"
        filterStatuses[2].id shouldBeEqualTo "899"
        filterStatuses[2].statusId shouldBeEqualTo "109416512469928634"
        filterStatuses[3].id shouldBeEqualTo "900"
        filterStatuses[3].statusId shouldBeEqualTo "109416512469928635"
        filterStatuses[4].id shouldBeEqualTo "901"
        filterStatuses[4].statusId shouldBeEqualTo "109416512469928636"
    }

    @Test
    fun listStatusFiltersWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val filterMethods = FilterMethods(client)
            filterMethods.listStatusFilters("filterId").execute()
        }
    }

    @Test
    fun addStatusToFilter() {
        val client = MockClient.mock("filter_status.json")
        val filterMethods = FilterMethods(client)
        val filterStatus = filterMethods.addStatusToFilter("filterId", "statusId").execute()
        filterStatus.id shouldBeEqualTo "897"
        filterStatus.statusId shouldBeEqualTo "109416512469928632"
    }

    @Test
    fun addStatusToFilterWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val filterMethods = FilterMethods(client)
            filterMethods.addStatusToFilter("filterId", "statusId").execute()
        }
    }

    @Test
    fun viewStatusFilter() {
        val client = MockClient.mock("filter_status.json")
        val filterMethods = FilterMethods(client)
        val filterStatus = filterMethods.viewStatusFilter("filterStatusId").execute()
        filterStatus.id shouldBeEqualTo "897"
        filterStatus.statusId shouldBeEqualTo "109416512469928632"
    }

    @Test
    fun viewStatusFilterWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val filterMethods = FilterMethods(client)
            filterMethods.viewStatusFilter("filterStatusId").execute()
        }
    }

    @Test
    fun removeStatusFromFilter() {
        val client = MockClient.mock("filter_status_deleted.json")
        val filterMethods = FilterMethods(client)
        val filterStatus = filterMethods.removeStatusFromFilter("filterStatusId").execute()
        filterStatus.id shouldBeEqualTo "0"
        filterStatus.statusId shouldBeEqualTo ""
    }

    @Test
    fun removeStatusFromFilterWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val filterMethods = FilterMethods(client)
            filterMethods.removeStatusFromFilter("filterStatusId").execute()
        }
    }
}
