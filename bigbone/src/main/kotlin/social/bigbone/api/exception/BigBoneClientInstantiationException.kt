package social.bigbone.api.exception

import okhttp3.Response

open class BigBoneClientInstantiationException : Exception {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}

class ServerInfoRetrievalException : BigBoneClientInstantiationException {
    constructor(cause: Throwable?) : super(cause)
    constructor(message: String, cause: Throwable?) : super(message, cause)
    constructor(response: Response, message: String? = null) : super(
        message = "${message ?: ""}${response.message}"
    )
}

class ServerInfoUrlRetrievalException(
    response: Response,
    message: String? = null
) :
    BigBoneClientInstantiationException(
        message = "${message ?: ""}${response.message}"
    )

class InstanceVersionRetrievalException : BigBoneClientInstantiationException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
    constructor(response: Response, message: String? = null) : super(
        message = "${response.code} – ${message ?: ""}${response.message}"
    )
}
