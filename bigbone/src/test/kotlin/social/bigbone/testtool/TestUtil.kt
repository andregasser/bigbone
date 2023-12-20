package social.bigbone.testtool

import java.net.URLEncoder

object TestUtil {
    fun normalizeLineBreaks(content: String): String = content.replace("\r\n", "\n")

    fun String.urlEncode(encoding: String = "utf-8"): String = URLEncoder.encode(this, encoding)
}
