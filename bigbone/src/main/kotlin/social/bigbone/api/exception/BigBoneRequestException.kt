package social.bigbone.api.exception

import okhttp3.Response
import social.bigbone.api.entity.Error

/**
 * Exception which is thrown when there was a problem executing the associated [social.bigbone.MastodonRequest].
 */
class BigBoneRequestException : Exception {
    /**
     * If BigBoneRequestException was constructed using a [Response], this will be set to the HTTP status code of the
     * unsuccessful request. Use [statusCodeValid] to check whether this contains valid information.
     */
    var httpStatusCode = INVALID_STATUS_CODE

    /**
     * If BigBoneRequestException was constructed after an unsuccessful request,
     * error details as returned by the Mastodon instance might be available here.
     */
    var errorDetails: Error? = null

    /**
     * true if [httpStatusCode] contains a valid HTTP Status code, false if not.
     */
    val statusCodeValid: Boolean =
        httpStatusCode != INVALID_STATUS_CODE

    /**
     * Create a BigBoneRequestException using a [Response].
     * @param response an unsuccessful response; the HTTP status message will be used as the detail message for this
     *  exception, HTTP status code will be available via [httpStatusCode]
     * @param error optionally, an [Error] containing additional details about request errors that led to the unsuccessful response
     */
    constructor(response: Response, error: Error? = null) : super(response.message) {
        httpStatusCode = response.code
        errorDetails = error
    }

    /**
     * Create a BigBoneRequestException using another [Exception].
     * @param e another exception
     */
    constructor(e: Exception) : super(e)

    /**
     * Create a BigBoneRequestException using a message string.
     * @param message the message string
     */
    constructor(message: String) : super(message)

    /**
     * Create a BigBoneRequestException using another [Exception] and an additional message string.
     * @param message the message string
     * @param e another exception
     */
    constructor(message: String, e: Exception) : super(message, e)

    companion object {
        private const val INVALID_STATUS_CODE = -1
    }
}
