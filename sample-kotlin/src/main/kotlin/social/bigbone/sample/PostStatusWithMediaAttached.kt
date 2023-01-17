package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.api.entity.Status
import social.bigbone.api.method.MediaMethods
import social.bigbone.api.method.StatusesMethods
import java.io.File

object PostStatusWithMediaAttached {
    @JvmStatic
    fun main(args: Array<String>) {
        val instance = args[0]
        val accessToken = args[1]

        // Instantiate client
        val client = MastodonClient.Builder(instance)
            .accessToken(accessToken)
            .build()

        // Read file from resources folder
        val classLoader = Thread.currentThread().contextClassLoader
        val uploadFile = File(classLoader.getResource("castle.jpg")!!.file)

        // Upload image to Mastodon
        val mediaMethods = MediaMethods(client)
        val uploadedFile = mediaMethods.uploadMedia(uploadFile, "image/jpg").execute()
        val mediaId = uploadedFile.id

        // Post status with media attached
        val statusesMethods = StatusesMethods(client)
        val statusText = "Status posting test"
        val inReplyToId: String? = null
        val mediaIds = listOf(mediaId)
        val sensitive = false
        val spoilerText = "A castle"
        val visibility = Status.Visibility.Private
        statusesMethods.postStatus(statusText, inReplyToId, mediaIds, sensitive, spoilerText, visibility).execute()
    }
}
