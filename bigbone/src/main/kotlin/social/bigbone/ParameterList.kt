package social.bigbone

import java.net.URLEncoder
import java.util.ArrayList

class ParameterList {
    private val parameters = ArrayList<Pair<String, String>>()

    fun append(key: String, value: String): ParameterList {
        parameters.add(Pair(key, value))
        return this
    }

    fun append(key: String, value: Int): ParameterList = append(key, value.toString())

    fun append(key: String, value: Long): ParameterList = append(key, value.toString())

    fun append(key: String, value: Boolean): ParameterList = append(key, value.toString())

    fun <T> append(key: String, value: List<T>): ParameterList {
        value.forEach {
            append("$key[]", it.toString())
        }
        return this
    }

    fun build(): String =
        parameters
            .map {
                "${it.first}=${URLEncoder.encode(it.second, "utf-8")}"
            }
            .joinToString(separator = "&")
}
