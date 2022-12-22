package social.bigbone.extension

import okhttp3.Response
import social.bigbone.api.Link
import social.bigbone.api.Pageable

fun <T> List<T>.toPageable(response: Response): Pageable<T> {
    val linkHeader = response.header("link")
    val link = Link.parse(linkHeader)
    return Pageable(this, link)
}
