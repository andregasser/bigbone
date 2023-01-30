package social.bigbone.extension

import com.google.gson.Gson
import okhttp3.Response
import social.bigbone.api.exception.BigBoneRequestException
import java.io.IOException
import java.lang.reflect.Type

inline fun <reified T> Response.fromJson(gson: Gson): T {
    try {
        val json = body?.string()
        return gson.fromJson(json, T::class.java)
    } catch (e: IOException) {
        throw BigBoneRequestException(e)
    }
}

inline fun <reified T> Response.fromJson(gson: Gson, type: Type): T {
    try {
        val json = body?.string()
        return gson.fromJson(json, type)
    } catch (e: IOException) {
        throw BigBoneRequestException(e)
    }
}
