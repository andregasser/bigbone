package social.bigbone.api.entity.data

/**
 * Specifies the visibility of the status post.
 */
enum class Visibility(val value: String) {
    PUBLIC("public"),
    UNLISTED("unlisted"),
    PRIVATE("private"),
    DIRECT("direct")
}