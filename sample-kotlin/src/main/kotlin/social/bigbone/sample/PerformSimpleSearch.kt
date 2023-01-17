package social.bigbone.sample

import social.bigbone.MastodonClient

object PerformSimpleSearch {
    @JvmStatic
    fun main(args: Array<String>) {
        val instance = args[0]
        val accessToken = args[1]
        val searchTerm = args[2]

        // Instantiate client
        val client = MastodonClient.Builder(instance)
            .accessToken(accessToken)
            .build()

        // Perform search and print results
        val searchResult = client.search.searchContent(searchTerm).execute()
        searchResult.accounts.forEach {
            println(it.displayName)
        }
        searchResult.statuses.forEach {
            println(it.content)
        }
        searchResult.hashtags.forEach {
            println(it.name)
        }
    }
}
