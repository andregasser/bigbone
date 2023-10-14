package social.bigbone.api.entity.data

/**
 * Specifies the privacy or visibility for new posts.
 */
enum class Privacy(val value: String) {
    PUBLIC("public"),
    UNLISTED("unlisted"),
    PRIVATE("private"),
    DIRECT("direct")
}