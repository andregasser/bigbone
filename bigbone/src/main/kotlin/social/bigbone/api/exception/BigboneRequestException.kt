package social.bigbone.api.exception

import okhttp3.Response

class BigboneRequestException : Exception {
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
}
