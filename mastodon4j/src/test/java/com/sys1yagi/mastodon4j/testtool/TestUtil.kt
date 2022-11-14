package com.sys1yagi.mastodon4j.testtool

object TestUtil {
    fun normalizeLineBreaks(content: String): String =
        content.replace("\r\n", "\n")
}
