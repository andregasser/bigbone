package social.bigbone.extension

import okhttp3.RequestBody.Companion.toRequestBody

fun emptyRequestBody() = byteArrayOf().toRequestBody(null, 0, 0)
