package social.bigbone

import com.google.gson.JsonParser
import okhttp3.Response
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.extension.toPageable
import java.lang.Exception

/**
 * Represents an HTTP request that is sent to a Mastodon instance, once the [execute]
 * method on this class is called.
 */
class MastodonRequest<T>(
    private val executor: () -> Response,
    private val mapper: (String) -> Any
) {
    /**
     * SAM interface provided for Java interoperability related to [doOnJson] method.
     */
    interface Action1<T> {
        fun invoke(arg: T)
    }

    private var action: (String) -> Unit = {}

    private var isPageable: Boolean = false

    internal fun toPageable() = apply {
        isPageable = true
    }

    /**
     * Allows you to execute some extra logic (such as logging) that is triggered when the JSON response
     * from the Mastodon API arrives.
     */
    @JvmSynthetic
    fun doOnJson(action: (String) -> Unit) = apply {
        this.action = action
    }

    /**
     * Allows you to execute some extra logic (such as logging) that is triggered when the JSON response
     * from the Mastodon API arrives. This method is provided for Java interoperability reasons.
     */
    fun doOnJson(action: Action1<String>) = apply {
        this.action = { action.invoke(it) }
    }

    @Suppress("UNCHECKED_CAST")
    @Throws(BigBoneRequestException::class)
    fun execute(): T {
        val response = executor()
        if (response.isSuccessful) {
            try {
                val body = response.body?.string()
                val element = JsonParser.parseString(body)
                if (element.isJsonObject) {
                    action(body!!)
                    return mapper(body) as T
                } else {
                    val list = arrayListOf<Any>()
                    element.asJsonArray.forEach {
                        val json = it.toString()
                        action(json)
                        list.add(mapper(json))
                    }
                    return if (isPageable) {
                        list.toPageable(response) as T
                    } else {
                        list as T
                    }
                }
            } catch (e: Exception) {
                throw BigBoneRequestException("Successful response could not be parsed", e)
            }
        } else {
            throw BigBoneRequestException(response)
        }
    }
}
