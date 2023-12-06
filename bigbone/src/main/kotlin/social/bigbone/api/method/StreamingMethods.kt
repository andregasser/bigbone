package social.bigbone.api.method

import social.bigbone.JSON_SERIALIZER
import social.bigbone.MastodonClient
import social.bigbone.Parameters
import social.bigbone.api.Dispatcher
import social.bigbone.api.Handler
import social.bigbone.api.Shutdownable
import social.bigbone.api.entity.Status
import social.bigbone.api.exception.BigBoneRequestException

/**
 * Allows access to API methods with endpoints having an "api/vX/streaming" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/streaming/">Mastodon streaming API methods</a>
 */
class StreamingMethods(private val client: MastodonClient) {
    @Throws(BigBoneRequestException::class)
    fun federatedPublic(handler: Handler): Shutdownable {
        val response = client.get("api/v1/streaming/public")
        if (response.isSuccessful) {
            val reader = response.body?.byteStream()?.bufferedReader()
            val dispatcher = Dispatcher()
            dispatcher.invokeLater {
                while (true) {
                    try {
                        val line = reader?.readLine()
                        if (line == null || line.isEmpty()) {
                            continue
                        }
                        val type = line.split(":")[0].trim()
                        if (type != "event") {
                            continue
                        }
                        val event = line.split(":")[1].trim()
                        val payload = reader.readLine()
                        val payloadType = payload.split(":")[0].trim()
                        if (payloadType != "data") {
                            continue
                        }
                        if (event == "update") {
                            val json = payload.substringAfter(':').trim()
                            handler.onStatus(status = JSON_SERIALIZER.decodeFromString(json))
                        }
                    } catch (e: java.io.InterruptedIOException) {
                        break
                    }
                }
                reader.close()
            }
            return Shutdownable(dispatcher)
        } else {
            throw BigBoneRequestException(response)
        }
    }

    @Throws(BigBoneRequestException::class)
    fun localPublic(handler: Handler): Shutdownable {
        val response = client.get("api/v1/streaming/public/local")
        if (response.isSuccessful) {
            val reader = response.body?.byteStream()?.bufferedReader()
            val dispatcher = Dispatcher()
            dispatcher.invokeLater {
                while (true) {
                    try {
                        val line = reader?.readLine()
                        if (line == null || line.isEmpty()) {
                            continue
                        }
                        val type = line.split(":")[0].trim()
                        if (type != "event") {
                            continue
                        }
                        val event = line.split(":")[1].trim()
                        val payload = reader.readLine()
                        val payloadType = payload.split(":")[0].trim()
                        if (payloadType != "data") {
                            continue
                        }
                        if (event == "update") {
                            val json = payload.substringAfter(':').trim()
                            handler.onStatus(status = JSON_SERIALIZER.decodeFromString(json))
                        }
                    } catch (e: java.io.InterruptedIOException) {
                        break
                    }
                }
                reader.close()
            }
            return Shutdownable(dispatcher)
        } else {
            throw BigBoneRequestException(response)
        }
    }

    @Throws(BigBoneRequestException::class)
    fun federatedHashtag(
        tag: String,
        handler: Handler
    ): Shutdownable {
        val response = client.get(
            "api/v1/streaming/hashtag",
            Parameters().append("tag", tag)
        )
        if (response.isSuccessful) {
            val reader = response.body?.byteStream()?.bufferedReader()
            val dispatcher = Dispatcher()
            dispatcher.invokeLater {
                while (true) {
                    try {
                        val line = reader?.readLine()
                        if (line == null || line.isEmpty()) {
                            continue
                        }
                        val type = line.split(":")[0].trim()
                        if (type != "event") {
                            continue
                        }
                        val event = line.split(":")[1].trim()
                        val payload = reader.readLine()
                        val payloadType = payload.split(":")[0].trim()
                        if (payloadType != "data") {
                            continue
                        }
                        if (event == "update") {
                            val json = payload.substringAfter(':').trim()
                            val status: Status = JSON_SERIALIZER.decodeFromString(json)
                            handler.onStatus(status)
                        }
                    } catch (e: java.io.InterruptedIOException) {
                        break
                    }
                }
                reader.close()
            }
            return Shutdownable(dispatcher)
        } else {
            throw BigBoneRequestException(response)
        }
    }

    @Throws(BigBoneRequestException::class)
    fun localHashtag(
        tag: String,
        handler: Handler
    ): Shutdownable {
        val response = client.get(
            "api/v1/streaming/hashtag/local",
            Parameters().append("tag", tag)
        )
        if (response.isSuccessful) {
            val reader = response.body?.byteStream()?.bufferedReader()
            val dispatcher = Dispatcher()
            dispatcher.invokeLater {
                while (true) {
                    try {
                        val line = reader?.readLine()
                        if (line == null || line.isEmpty()) {
                            continue
                        }
                        val type = line.split(":")[0].trim()
                        if (type != "event") {
                            continue
                        }
                        val event = line.split(":")[1].trim()
                        val payload = reader.readLine()
                        val payloadType = payload.split(":")[0].trim()
                        if (payloadType != "data") {
                            continue
                        }
                        if (event == "update") {
                            val json = payload.substringAfter(':').trim()
                            handler.onStatus(status = JSON_SERIALIZER.decodeFromString(json))
                        }
                    } catch (e: java.io.InterruptedIOException) {
                        break
                    }
                }
                reader.close()
            }
            return Shutdownable(dispatcher)
        } else {
            throw BigBoneRequestException(response)
        }
    }

    @Throws(BigBoneRequestException::class)
    fun user(handler: Handler): Shutdownable {
        val response = client.get(
            "api/v1/streaming/user"
        )
        if (response.isSuccessful) {
            val reader = response.body?.byteStream()?.bufferedReader()
            val dispatcher = Dispatcher()
            dispatcher.invokeLater {
                while (true) {
                    try {
                        val line = reader?.readLine()
                        if (line == null || line.isEmpty()) {
                            continue
                        }
                        val type = line.split(":")[0].trim()
                        if (type != "event") {
                            continue
                        }
                        val event = line.split(":")[1].trim()
                        val payload = reader.readLine()
                        val payloadType = payload.split(":")[0].trim()
                        if (payloadType != "data") {
                            continue
                        }

                        val json = payload.substringAfter(':').trim()
                        if (event == "update") {
                            handler.onStatus(status = JSON_SERIALIZER.decodeFromString(json))
                        }
                        if (event == "notification") {
                            handler.onNotification(notification = JSON_SERIALIZER.decodeFromString(json))
                        }
                        if (event == "delete") {
                            handler.onDelete(id = JSON_SERIALIZER.decodeFromString(json))
                        }
                    } catch (e: java.io.InterruptedIOException) {
                        break
                    }
                }
                reader.close()
            }
            return Shutdownable(dispatcher)
        } else {
            throw BigBoneRequestException(response)
        }
    }

    @Throws(BigBoneRequestException::class)
    fun userList(
        handler: Handler,
        listID: String
    ): Shutdownable {
        val response = client.get(
            "api/v1/streaming/list",
            Parameters().apply {
                append("list", listID)
            }
        )
        if (response.isSuccessful) {
            val reader = response.body?.byteStream()?.bufferedReader()
            val dispatcher = Dispatcher()
            dispatcher.invokeLater {
                while (true) {
                    try {
                        val line = reader?.readLine()
                        if (line == null || line.isEmpty()) {
                            continue
                        }
                        val type = line.split(":")[0].trim()
                        if (type != "event") {
                            continue
                        }
                        val event = line.split(":")[1].trim()
                        val payload = reader.readLine()
                        val payloadType = payload.split(":")[0].trim()
                        if (payloadType != "data") {
                            continue
                        }

                        val start = payload.indexOf(":") + 1
                        val json = payload.substring(start).trim()
                        if (event == "update") {
                            handler.onStatus(status = JSON_SERIALIZER.decodeFromString(json))
                        }
                        if (event == "notification") {
                            handler.onNotification(notification = JSON_SERIALIZER.decodeFromString(json))
                        }
                        if (event == "delete") {
                            handler.onDelete(id = JSON_SERIALIZER.decodeFromString(json))
                        }
                    } catch (e: java.io.InterruptedIOException) {
                        break
                    }
                }
                reader.close()
            }
            return Shutdownable(dispatcher)
        } else {
            throw BigBoneRequestException(response)
        }
    }
}
