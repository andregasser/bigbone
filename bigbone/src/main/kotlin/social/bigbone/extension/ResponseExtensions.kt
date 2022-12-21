package social.bigbone.extension

import com.google.gson.Gson
import okhttp3.Response
import social.bigbone.api.exception.Mastodon4jRequestException
import java.io.IOException
import java.lang.reflect.Type

inline fun <reified T> Response.fromJson(gson: Gson, clazz: Class<T>): T {
    try {
        val json = body?.string()
        return gson.fromJson(json, clazz)
    } catch (e: IOException) {
        throw Mastodon4jRequestException(e)
    }
}

inline fun <reified T> Response.fromJson(gson: Gson, type: Type): T {
    try {
        val json = body?.string()
        return gson.fromJson(json, type)
    } catch (e: IOException) {
        throw Mastodon4jRequestException(e)
    }
}
