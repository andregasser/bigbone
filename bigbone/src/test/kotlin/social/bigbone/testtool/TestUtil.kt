package social.bigbone.testtool

object TestUtil {
    fun normalizeLineBreaks(content: String): String =
        content.replace("\r\n", "\n")
}
