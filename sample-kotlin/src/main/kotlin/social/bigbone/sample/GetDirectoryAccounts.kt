package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.api.method.DirectoryMethods

object GetDirectoryAccounts {
    @JvmStatic
    fun main(args: Array<String>) {
        val instance = args[0]
        val accessToken = args[1]

        // instantiate client
        val client = MastodonClient.Builder(instance)
            .accessToken(accessToken)
            .build()

        // get 40 local accounts that were recently active, skipping the first ten
        val accounts = client.directories.listAccounts(
            local = true,
            order = DirectoryMethods.AccountOrder.ACTIVE,
            offset = 10,
            limit = 40
        ).execute()

        // do something with the result; here, we find the oldest account still active and output information about it
        val oldestAccount = accounts.sortedWith(compareBy { it.createdAt }).first()
        oldestAccount.let {
            println("@${it.acct}@$instance posted ${it.statusesCount} times since ${it.createdAt}")
        }
    }
}
