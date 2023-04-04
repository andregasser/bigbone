package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.api.Scope
import social.bigbone.api.entity.Application
import social.bigbone.api.entity.Token
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.api.method.OAuthMethods
import java.io.File
import java.util.*

object Authenticator {
    private const val CLIENT_ID = "client_id"
    private const val CLIENT_SECRET = "client_secret"
    private const val ACCESS_TOKEN = "access_token"

    fun appRegistrationIfNeeded(instanceName: String, credentialFilePath: String, useStreaming: Boolean = false): MastodonClient {
        val file = File(credentialFilePath)
        if (!file.exists()) {
            println("create $credentialFilePath.")
            file.createNewFile()
        }
        val properties = Properties()
        println("load $credentialFilePath.")
        properties.load(file.inputStream())
        if (properties[CLIENT_ID] == null) {
            println("try app registration...")
            val appRegistration = appRegistration(instanceName)
            properties[CLIENT_ID] = appRegistration.clientId
            properties[CLIENT_SECRET] = appRegistration.clientSecret
            properties.store(file.outputStream(), "app registration")
        } else {
            println("app registration found...")
        }
        val clientId = properties[CLIENT_ID]?.toString() ?: error("client id not found")
        val clientSecret = properties[CLIENT_SECRET]?.toString() ?: error("client secret not found")

        if (properties[ACCESS_TOKEN] == null) {
            println("get access token for $instanceName...")
            println("please input your email...")
            val email = System.`in`.bufferedReader().readLine()
            println("please input your password...")
            val pass = System.`in`.bufferedReader().readLine()
            val accessToken = getUserAccessToken(
                instanceName,
                clientId,
                clientSecret,
                email,
                pass
            )
            properties[ACCESS_TOKEN] = accessToken.accessToken
            properties.store(file.outputStream(), "app registration")
        } else {
            println("access token found...")
        }
        return MastodonClient.Builder(instanceName)
            .accessToken(properties[ACCESS_TOKEN].toString())
            .apply {
                if (useStreaming) {
                    useStreamingApi()
                }
            }
            .build()
    }

    private fun getUserAccessToken(
        instanceName: String,
        clientId: String,
        clientSecret: String,
        email: String,
        password: String
    ): Token {
        val client = MastodonClient.Builder(instanceName).build()
        val oAuthMethods = OAuthMethods(client)
        return oAuthMethods.getUserAccessTokenWithPasswordGrant(clientId, clientSecret, Scope(), "urn:ietf:wg:oauth:2.0:oob", email, password).execute()
    }

    private fun appRegistration(instanceName: String): Application {
        val client = MastodonClient.Builder(instanceName).build()
        return client.apps.createApp(
            "bigbone-sample-app",
            scope = Scope()
        ).execute()
    }

    @Throws(BigBoneRequestException::class)
    private fun getAccessTokenByOAuth(
        instanceName: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String
    ): String {
        val client = MastodonClient.Builder(instanceName).build()
        val url = client.oauth.getOAuthUrl(clientId, Scope())
        println("Open authorization page and copy code: $url")
        println("Paste code")
        var authCode: String
        Scanner(System.`in`).use { s -> authCode = s.nextLine() }
        val token = client.oauth.getUserAccessTokenWithAuthorizationCodeGrant(
            clientId,
            clientSecret,
            redirectUri,
            authCode
        )
        return token.execute().accessToken
    }

    @Throws(BigBoneRequestException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val instanceName = args[0]
        val clientId = args[1]
        val clientSecret = args[2]
        val redirectUri = args[3]
        println("Access Token: " + getAccessTokenByOAuth(instanceName, clientId, clientSecret, redirectUri))
    }
}
