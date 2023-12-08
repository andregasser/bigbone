package social.bigbone

import social.bigbone.api.Scope
import social.bigbone.api.entity.Application
import social.bigbone.api.entity.MediaAttachment
import social.bigbone.api.entity.Token
import java.io.File

object TestHelpers {
    private val fullScope = Scope(Scope.Name.READ, Scope.Name.WRITE, Scope.Name.PUSH)

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
        val client = getTrustAllClient()
        return client.apps.createApp(appName, TestConstants.REDIRECT_URI, null, fullScope).execute()
    }

    fun getAppToken(application: Application): Token {
        val client = getTrustAllClient()
        return client.oauth.getAccessTokenWithClientCredentialsGrant(
            clientId = application.clientId!!,
            clientSecret = application.clientSecret!!,
            redirectUri = TestConstants.REDIRECT_URI,
            scope = fullScope
        ).execute()
    }

    fun getUserToken(application: Application, username: String, password: String): Token {
        val client = getTrustAllClient()
        return client.oauth.getUserAccessTokenWithPasswordGrant(
            clientId = application.clientId!!,
            clientSecret = application.clientSecret!!,
            scope = fullScope,
            redirectUri = TestConstants.REDIRECT_URI,
            username = username,
            password = password
        ).execute()
    }

    fun unpinAllPinnedStatuses(client: MastodonClient) {
        val account = client.accounts.verifyCredentials().execute()
        val pinnedStatuses = client.accounts.getStatuses(account.id, pinned = true).execute()
        pinnedStatuses.part.forEach {
            client.statuses.unpinStatus(it.id).execute()
        }
    }
}
