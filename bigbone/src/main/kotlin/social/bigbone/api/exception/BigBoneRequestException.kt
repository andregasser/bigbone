package social.bigbone.api.exception

import okhttp3.Response

class BigBoneRequestException : Exception {
    val response: Response?

    constructor(response: Response) : super(response.message) {
        this.response = response
    }

    constructor(e: Exception) : super(e) {
        this.response = null
    }

    fun isErrorResponse() = response != null
}
