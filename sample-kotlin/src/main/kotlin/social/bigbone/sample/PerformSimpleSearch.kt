package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.api.method.Public

object PerformSimpleSearch {
    @JvmStatic
    fun main(args: Array<String>) {
        val instance = "<YOUR INSTANCE>"
        val accessToken = "<YOUR ACCESS TOKEN>"
        val searchTerm = "<YOUR SEARCH TERM>"

        // Instantiate client
        val client = MastodonClient.Builder(instance)
            .accessToken(accessToken)
            .build()

        // Perform search and print results
        val public = Public(client)
        val searchResult = public.getSearch(searchTerm).execute()
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
