package social.bigbone.api

/**
 * Represents the access permissions that can be requested when registering an app or when
 * requesting access tokens.
 */
@Suppress("ktlint:standard:annotation")
class Scope @JvmOverloads constructor(private vararg val scopes: Name = arrayOf(Name.ALL)) {
    /**
     * The available scopes.
     */
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
