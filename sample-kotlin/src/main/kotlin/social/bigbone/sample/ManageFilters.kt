package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.api.entity.Filter
import social.bigbone.api.entity.FilterKeyword

/**
 * This object's main function accepts the following parameters.
 *  - &lt;instance&gt; &lt;accessToken&gt; list: list all existing filters for this account
 *  - &lt;instance&gt; &lt;accessToken&gt; create &lt;keyword&gt: create a new filter for the keyword
 *  - &lt;instance&gt; &lt;accessToken&gt; delete &lt;filterId&gt: delete the filter with this filterId
 *  - &lt;instance&gt; &lt;accessToken&gt; addKeyword &lt;filterId&gt &lt;filterId&gt: add keyword to the filter with ID filterId
 */
object ManageFilters {
    @JvmStatic
    fun main(args: Array<String>) {
        val instance = args[0]
        val accessToken = args[1]
        val action = args[2]

        // Instantiate client
        val client = MastodonClient.Builder(instance)
            .accessToken(accessToken)
            .build()

        when (action) {
            "list" -> listExistingFilters(client)
            "create" -> createNewFilter(client, args[3])
            "delete" -> deleteFilter(client, args[3])
            "addKeyword" -> addKeywordToFilter(client, args[3], args[4])
            else -> println("see code documentation for usage")
        }
    }

    /**
     * Get a list of filters, then for each filter print out title, ID, filter action and contexts, and some of its keywords.
     * Similar functionality exists to view an individual filter.
     *
     * @param client a [MastodonClient] with an authenticated user
     */
    private fun listExistingFilters(client: MastodonClient) {
        val existingFilters = client.filters.listFilters().execute()
        for (filter in existingFilters) {
            println("${filter.title} (ID ${filter.id}):")
            println("${filter.filterAction} in the following contexts: ${filter.context.joinToString()}")
            println("keywords: ${filter.keywords.joinToString(limit = 10)}")
            println("-------------------------------------------------------")
        }
    }

    /**
     * Creates a new filter for the given keyword. This filter will expire automatically after an hour.
     * Similar functionality exists to update a given filter.
     *
     * @param client a [MastodonClient] with an authenticated user
     * @param keywordToFilter string that should be filtered by the new filter
     */
    private fun createNewFilter(client: MastodonClient, keywordToFilter: String) {
        // title for new filter
        val title = "BigBone sample filter: $keywordToFilter"

        // filter context - where do we want statuses to be filtered? (here: in Home and Public timelines)
        val context = listOf(Filter.FilterContext.HOME, Filter.FilterContext.PUBLIC)

        // create a proper filter keywords list - filters typically contain more than one keyword, and each keyword
        // can be matched either as a whole word or as part of a string (e.g. "@example.org" matching any "user@example.org").
        val filterKeyword = FilterKeyword("0", keywordToFilter, true)
        val keywords = listOf(filterKeyword)

        // set filter to expire in one hour - this is optional, but done here to not interfere with account used in testing
        val expiryInSeconds = 3600

        // filter action - should filtered statuses be shown with a warning, or be hidden completely?
        val action = Filter.FilterAction.WARN

        // create filter and output its ID
        val createdFilter = client.filters.createFilter(title, context, keywords, expiryInSeconds, action).execute()
        println("New filter was created with ID ${createdFilter.id}")
    }

    /**
     * Delete a filter with the given filter ID.
     * Similar functionality exists to view a given filter.
     *
     * @param client a [MastodonClient] with an authenticated user
     * @param filterId ID string for the filter that should be deleted
     */
    private fun deleteFilter(client: MastodonClient, filterId: String) {
        client.filters.deleteFilter(filterId)
        println("Filter was deleted")
    }

    /**
     * Add a keyword to an existing filter.
     * Similar functionality exists to view, delete or update individual keywords, or to list all keywords of a given filter.
     *
     * @param client a [MastodonClient] with an authenticated user
     * @param filterId ID string for the filter that should be edited
     * @param keywordToFilter string for a new keyword that should be filtered by the filter
     */
    private fun addKeywordToFilter(client: MastodonClient, filterId: String, keywordToFilter: String) {
        // create a proper filter keyword
        val localFilterKeyword = FilterKeyword("0", keywordToFilter, true)

        // add keyword to filter
        val newFilterKeyword = client.filters.addKeyword(filterId, localFilterKeyword).execute()
        println("Keyword \"$keywordToFilter\" was added to filter with ID ${newFilterKeyword.id}")
    }
}
