package social.bigbone

import java.net.URLEncoder
import java.util.UUID

typealias Key = String
typealias Value = String

/**
 * Parameters holds a mapping of [Key] to [Value]s that can be used as query part of a URL, or in the body of a request.
 * Add new pairs using one of the available append() functions.
 */
class Parameters {

    internal val parameters: MutableMap<Key, MutableList<Value>> = mutableMapOf()

    /**
     * Appends a new key/value pair with a String value.
     * @param key the key of this pair
     * @param value the value of this pair
     * @return this Parameters instance
     */
    fun append(key: String, value: String): Parameters {
        parameters.getOrPut(key, ::mutableListOf).add(value)
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
    fun toQuery(): String {
        return parameters
            .map { (key, values) ->
                values.joinToString(separator = "&") { value ->
                    "$key=${URLEncoder.encode(value, "utf-8")}"
                }
            }
            .joinToString(separator = "&")
    }

    /**
     * Generates a UUID string for this parameter list. UUIDs returned for different Parameters instances should be
     *  the same if they contain the same list of key/value pairs, even if pairs were appended in different order,
     *  and should be different as soon as at least one parameter key or value differs.
     * @return Type 3 UUID as a String.
     */
    fun uuid(): String {
        return UUID
            .nameUUIDFromBytes(
                parameters
                    .entries
                    .sortedWith(compareBy { (key, _) -> key })
                    .joinToString { (key, value) -> "$key$value" }
                    .toByteArray()
            )
            .toString()
    }
}
