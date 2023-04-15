package social.bigbone

import java.net.URLEncoder
import java.util.ArrayList

/**
 * Parameters holds a list of String key/value pairs that can be used as query part of a URL, or in the body of a request.
 * Add new pairs using one of the available append() functions.
 */
class Parameters {
    private val parameterList = ArrayList<Pair<String, String>>()

    /**
     * Appends a new key/value pair with a String value.
     * @param key the key of this pair
     * @param value the value of this pair
     * @return this Parameters instance
     */
    fun append(key: String, value: String): Parameters {
        parameterList.add(Pair(key, value))
        return this
    }

    /**
     * Appends a new key/value pair with an Int value.
     * @param key the key of this pair
     * @param value the value of this pair
     * @return this Parameters instance
     */
    fun append(key: String, value: Int): Parameters = append(key, value.toString())

    /**
     * Appends a new key/value pair with a Long value.
     * @param key the key of this pair
     * @param value the value of this pair
     * @return this Parameters instance
     */
    fun append(key: String, value: Long): Parameters = append(key, value.toString())

    /**
     * Appends a new key/value pair with a Boolean value.
     * @param key the key of this pair
     * @param value the value of this pair
     * @return this Parameters instance
     */
    fun append(key: String, value: Boolean): Parameters = append(key, value.toString())

    /**
     * Appends a new key/value pair, with the value being a list.
     * @param T
     * @param key the key of this pair
     * @param value a list of objects; individual elements will be stored via their .toString() method
     * @return this Parameters instance
     */
    fun <T> append(key: String, value: List<T>): Parameters {
        value.forEach {
            append("$key[]", it.toString())
        }
        return this
    }

    /**
     * Appends a new key/value pair, with the value being a list of integer values.
     * @param key the key of this pair
     * @param value a list of integer values
     * @return this Parameters instance
     */
    fun appendInts(key: String, value: List<Int>): Parameters {
        value.forEach {
            append("$key[]", it)
        }
        return this
    }

    /**
     * Converts this Parameters instance into a query string.
     * @return String, formatted like: "key1=value1&key2=value2&..."
     */
    fun toQuery(): String =
        parameterList.joinToString(separator = "&") {
            "${it.first}=${URLEncoder.encode(it.second, "utf-8")}"
        }
}
