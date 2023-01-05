package social.bigbone.sample

import social.bigbone.MastodonClient
import social.bigbone.api.entity.Status
import social.bigbone.api.method.Media
import social.bigbone.api.method.Statuses
import java.io.File

object PostStatusWithMediaAttached {
    @JvmStatic
    fun main(args: Array<String>) {
        val instance = "<YOUR INSTANCE>"
        val accessToken = "<YOUR ACCESS TOKEN>"

        // Instantiate client
        val client = MastodonClient.Builder(instance)
            .accessToken(accessToken)
            .build()

        // Read file from resources folder
        val classLoader = Thread.currentThread().contextClassLoader
        val uploadFile = File(classLoader.getResource("castle.jpg")!!.file)

        // Upload image to Mastodon
        val media = Media(client)
        val uploadedFile = media.postMedia(uploadFile, "image/jpg").execute()
        val mediaId = uploadedFile.id

        // Post status with media attached
        val statuses = Statuses(client)
        val statusText = "Status posting test"
        val inReplyToId: String? = null
        val mediaIds = listOf(mediaId)
        val sensitive = false
        val spoilerText = "A castle"
        val visibility = Status.Visibility.Private
        statuses.postStatus(statusText, inReplyToId, mediaIds, sensitive, spoilerText, visibility).execute()
    }
}
