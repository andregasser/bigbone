package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.PrecisionDateTime
import social.bigbone.api.method.DirectoryMethods
import java.time.Instant

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
        val oldestAccount = accounts
            .filter { it.createdAt is PrecisionDateTime.ExactTime || it.createdAt is PrecisionDateTime.StartOfDay }
            .minBy {
                when (it.createdAt) {
                    is PrecisionDateTime.ExactTime -> (it.createdAt as PrecisionDateTime.ExactTime).instant
                    is PrecisionDateTime.StartOfDay -> (it.createdAt as PrecisionDateTime.StartOfDay).instant
                    // Shouldn't happen, but still returning an EPOCH Instant just in case
                    else -> Instant.ofEpochSecond(0)
                }
            }
        oldestAccount.let {
            println("@${it.acct}@$instance posted ${it.statusesCount} times since ${it.createdAt}")
        }
    }
}
