package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.api.Scope
import social.bigbone.api.exception.BigBoneRequestException
import java.util.Scanner

object OAuthGetAccessToken {
    @Throws(BigBoneRequestException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val instanceName = args[0]
        val clientId = args[1]
        val clientSecret = args[2]
        val redirectUri = args[3]
        val client = MastodonClient.Builder(instanceName).build()
        val fullScope = Scope(Scope.READ.ALL, Scope.WRITE.ALL, Scope.PUSH.ALL)
        val url = client.oauth.getOAuthUrl(clientId, redirectUri, fullScope)
        println("Open authorization page and copy code:")
        println(url)
        println("Paste code:")
        var authCode: String
        Scanner(System.`in`).use { s -> authCode = s.nextLine() }
        val token = client.oauth.getUserAccessTokenWithAuthorizationCodeGrant(
            clientId,
            clientSecret,
            redirectUri,
            authCode,
            fullScope
        )
        println("Access Token:")
        println(token.execute().accessToken)
    }
}
