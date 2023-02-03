package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.api.Scope
import social.bigbone.api.entity.auth.AppRegistration
import social.bigbone.api.entity.auth.Token
import social.bigbone.api.method.OAuthMethods
import java.io.File
import java.util.Properties

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
            val accessToken = getAccessToken(
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

    private fun getAccessToken(
        instanceName: String,
        clientId: String,
        clientSecret: String,
        email: String,
        password: String
    ): Token {
        val client = MastodonClient.Builder(instanceName).build()
        val oAuthMethods = OAuthMethods(client)
        return oAuthMethods.getAccessTokenWithPasswordGrant(clientId, clientSecret, Scope(), email, password).execute()
    }

    private fun appRegistration(instanceName: String): AppRegistration {
        val client = MastodonClient.Builder(instanceName).build()
        return client.apps.createApp(
            "bigbone-sample-app",
            scope = Scope()
        ).execute()
    }
}
