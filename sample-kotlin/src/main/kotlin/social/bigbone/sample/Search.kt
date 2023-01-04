package social.bigbone.sample

import kotlinx.coroutines.runBlocking
import social.bigbone.api.method.Public

object Search {
    @JvmStatic
    fun main(args: Array<String>) {
        val instanceName = args[0]
        val credentialFilePath = args[1]
        val client = Authenticator.appRegistrationIfNeeded(instanceName, credentialFilePath)
        runBlocking {
            val publicMethod = Public(client)
            val searchResult = publicMethod.getSearch("bigbone").execute()
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
}
