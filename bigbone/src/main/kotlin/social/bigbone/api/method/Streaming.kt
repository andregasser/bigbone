package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.Parameter
import social.bigbone.api.Dispatcher
import social.bigbone.api.Handler
import social.bigbone.api.Shutdownable
import social.bigbone.api.entity.Notification
import social.bigbone.api.entity.Status
import social.bigbone.api.exception.BigboneRequestException

class Streaming(private val client: MastodonClient) {
    @Throws(BigboneRequestException::class)
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
                            val start = payload.indexOf(":") + 1
                            val json = payload.substring(start).trim()
                            val status = client.getSerializer().fromJson(
                                json,
                                Status::class.java
                            )
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
            throw BigboneRequestException(response)
        }
    }

    @Throws(BigboneRequestException::class)
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
                            val start = payload.indexOf(":") + 1
                            val json = payload.substring(start).trim()
                            val status = client.getSerializer().fromJson(
                                json,
                                Status::class.java
                            )
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
            throw BigboneRequestException(response)
        }
    }

    @Throws(BigboneRequestException::class)
    fun federatedHashtag(tag: String, handler: Handler): Shutdownable {
        val response = client.get(
            "api/v1/streaming/hashtag",
            Parameter().append("tag", tag)
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
                            val start = payload.indexOf(":") + 1
                            val json = payload.substring(start).trim()
                            val status = client.getSerializer().fromJson(
                                json,
                                Status::class.java
                            )
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
            throw BigboneRequestException(response)
        }
    }

    @Throws(BigboneRequestException::class)
    fun localHashtag(tag: String, handler: Handler): Shutdownable {
        val response = client.get(
            "api/v1/streaming/hashtag/local",
            Parameter().append("tag", tag)
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
                            val start = payload.indexOf(":") + 1
                            val json = payload.substring(start).trim()
                            val status = client.getSerializer().fromJson(
                                json,
                                Status::class.java
                            )
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
            throw BigboneRequestException(response)
        }
    }

    @Throws(BigboneRequestException::class)
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

                        val start = payload.indexOf(":") + 1
                        val json = payload.substring(start).trim()
                        if (event == "update") {
                            val status = client.getSerializer().fromJson(
                                json,
                                Status::class.java
                            )
                            handler.onStatus(status)
                        }
                        if (event == "notification") {
                            val notification = client.getSerializer().fromJson(
                                json,
                                Notification::class.java
                            )
                            handler.onNotification(notification)
                        }
                        if (event == "delete") {
                            val id = client.getSerializer().fromJson(
                                json,
                                String::class.java
                            )
                            handler.onDelete(id)
                        }
                    } catch (e: java.io.InterruptedIOException) {
                        break
                    }
                }
                reader.close()
            }
            return Shutdownable(dispatcher)
        } else {
            throw BigboneRequestException(response)
        }
    }

    @Throws(BigboneRequestException::class)
    fun userList(handler: Handler, listID: String): Shutdownable {
        val response = client.get(
            "api/v1/streaming/list",
            Parameter().apply {
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
                            val status = client.getSerializer().fromJson(
                                json,
                                Status::class.java
                            )
                            handler.onStatus(status)
                        }
                        if (event == "notification") {
                            val notification = client.getSerializer().fromJson(
                                json,
                                Notification::class.java
                            )
                            handler.onNotification(notification)
                        }
                        if (event == "delete") {
                            val id = client.getSerializer().fromJson(
                                json,
                                String::class.java
                            )
                            handler.onDelete(id)
                        }
                    } catch (e: java.io.InterruptedIOException) {
                        break
                    }
                }
                reader.close()
            }
            return Shutdownable(dispatcher)
        } else {
            throw BigboneRequestException(response)
        }
    }
}
