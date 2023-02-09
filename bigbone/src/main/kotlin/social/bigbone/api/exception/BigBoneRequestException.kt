package social.bigbone.api.exception

import okhttp3.Response

/**
 * Exception which is thrown when there was a problem executing the associated [social.bigbone.MastodonRequest].
 * Some additional error information is provided in the [response] property.
 */
class BigBoneRequestException : Exception {
    val response: Response?

    constructor(response: Response) : super(response.message) {
        this.response = response
    }

    constructor(e: Exception) : super(e) {
        this.response = null
    }

    constructor(message: String) : super(message) {
        this.response = null
    }

    constructor(message: String, e: Exception) : super(message, e) {
        this.response = null
    }
}
