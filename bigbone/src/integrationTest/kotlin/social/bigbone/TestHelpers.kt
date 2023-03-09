package social.bigbone

import social.bigbone.TestHelpers.toISO8601DateTime
import social.bigbone.api.Scope
import social.bigbone.api.entity.Application
import social.bigbone.api.entity.MediaAttachment
import social.bigbone.api.entity.Token
import java.io.File
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object TestHelpers {
    fun uploadMediaFromResourcesFolder(filename: String, mediaType: String, client: MastodonClient): MediaAttachment {
        val classLoader = Thread.currentThread().contextClassLoader
        val uploadFile = File(classLoader.getResource(filename)!!.file)
        return client.media.uploadMedia(uploadFile, mediaType).execute()
    }

    fun getTrustAllClient() =
        MastodonClient.Builder(TestConstants.REST_API_HOSTNAME)
            .withTrustAllCerts()
            .build()

    fun getTrustAllClient(accessToken: String) =
        MastodonClient.Builder(TestConstants.REST_API_HOSTNAME)
            .withTrustAllCerts()
            .accessToken(accessToken)
            .build()

    fun createApp(appName: String): Application {
        val client = TestHelpers.getTrustAllClient()
        return client.apps.createApp(appName, TestConstants.REDIRECT_URI, Scope(Scope.Name.ALL)).execute()
    }

    fun getAppToken(application: Application): Token {
        val client = TestHelpers.getTrustAllClient()
        return client.oauth.getAccessTokenWithClientCredentialsGrant(
            clientId = application.clientId!!,
            clientSecret = application.clientSecret!!,
            scope = Scope(Scope.Name.ALL)
        ).execute()
    }

    fun getUserToken(application: Application, username: String, password: String): Token {
        val client = TestHelpers.getTrustAllClient()
        return client.oauth.getUserAccessTokenWithPasswordGrant(
            clientId = application.clientId!!,
            clientSecret = application.clientSecret!!,
            scope = Scope(Scope.Name.ALL),
            redirectUri = TestConstants.REDIRECT_URI,
            username = username,
            password = password
        ).execute()
    }

    fun Instant.toISO8601DateTime(zoneId: ZoneId): String {
        val zonedDateTime = ZonedDateTime.ofInstant(this, zoneId)
        return DateTimeFormatter.ISO_INSTANT.format(zonedDateTime)
    }
}
