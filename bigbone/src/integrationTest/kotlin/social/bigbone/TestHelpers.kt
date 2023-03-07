package social.bigbone

import social.bigbone.api.Scope
import social.bigbone.api.entity.Application
import social.bigbone.api.entity.MediaAttachment
import social.bigbone.api.entity.Token
import java.io.File

/**
 * This class provides helper methods used in integration tests which are not specific to any
 * version of Mastodon. If a version-specific helper needs to be written, please create a new
 * class (e.g. V402TestHelpers.kt) in the appropriate integration test directory.
 */
object TestHelpers {
    /**
     * Uploads an image file using the POST /api/v1/media REST call.
     * @param filename the filename. Must be located in the "resources" directory".
     * @param mediaType the media type of the file (e.g. "image/jpg", ...).
     * @param client an instance of MastodonClient. This instance is used to conduct the upload.
     * @return the media attachment information.
     */
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
}
