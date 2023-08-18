package social.bigbone.sample

import social.bigbone.MastodonClient

object GetBookmarks {
    @JvmStatic
    fun main(args: Array<String>) {
        val instance = args[0]
        val accessToken = args[1]

        // instantiate client
        val client = MastodonClient.Builder(instance)
            .accessToken(accessToken)
            .build()

        // Get bookmarks
        val bookmarks = client.bookmarks.getBookmarks().execute()
        bookmarks.part.forEach { bookmark ->
            val statusText = bookmark.content
            print("$statusText\n")
        }
    }
}
