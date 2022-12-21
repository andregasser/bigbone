package com.sys1yagi.mastodon4j.api

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/OAuth-details.md
 */
class Scope
@JvmOverloads
constructor(private vararg val scopes: Name = arrayOf(Name.ALL)) {
    enum class Name(val scopeName: String) {
        READ("read"),
        WRITE("write"),
        FOLLOW("follow"),
        ALL(Scope(READ, WRITE, FOLLOW).toString())
    }

    fun validate() {
        require(scopes.size == scopes.distinct().size) { "There is a duplicate scope. : $this" }
    }

    override fun toString(): String = scopes.joinToString(separator = " ", transform = { it.scopeName })
}
