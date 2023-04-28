package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.api.Scope
import social.bigbone.api.exception.BigBoneRequestException
import java.util.*

object OAuthGetAccessToken {
    @Throws(BigBoneRequestException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val instanceName = args[0]
        val clientId = args[1]
        val clientSecret = args[2]
        val redirectUri = args[3]
        val client = MastodonClient.Builder(instanceName).build()
        val url = client.oauth.getOAuthUrl(clientId, Scope(), redirectUri)
        println("Open authorization page and copy code:")
        println(url)
        println("Paste code:")
        var authCode: String
        Scanner(System.`in`).use { s -> authCode = s.nextLine() }
        val token = client.oauth.getUserAccessTokenWithAuthorizationCodeGrant(
            clientId,
            clientSecret,
            redirectUri,
            authCode
        )
        println("Access Token:")
        println(token.execute().accessToken)
    }
}
