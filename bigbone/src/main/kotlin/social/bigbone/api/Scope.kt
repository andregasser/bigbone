package social.bigbone.api

/**
 * Represents the access permissions that can be requested when registering an app or when
 * requesting access tokens.
 * @param scopes individual scopes that should be requested.
 */
class Scope(private vararg val scopes: Name) {

    /**
     * The available scopes. These scopes are hierarchical, i.e. if you have access to [READ], you automatically have
     * access to [READ_ACCOUNTS]. It is recommended that you request as little as possible for your application.
     */
    enum class Name(val scopeName: String) {
        /**
         * Grants access to read data. Requesting this scope will also grant its child scopes.
         */
        READ("read"),

        /**
         * Child scope of [READ].
         */
        READ_ACCOUNTS("read:accounts"),

        /**
         * Child scope of [READ].
         */
        READ_BLOCKS("read:blocks"),

        /**
         * Child scope of [READ].
         */
        READ_BOOKMARKS("read:bookmarks"),

        /**
         * Child scope of [READ].
         */
        READ_FAVOURITES("read:favourites"),

        /**
         * Child scope of [READ].
         */
        READ_FILTERS("read:filters"),

        /**
         * Child scope of [READ].
         */
        READ_FOLLOWS("read:follows"),

        /**
         * Child scope of [READ].
         */
        READ_LISTS("read:lists"),

        /**
         * Child scope of [READ].
         */
        READ_MUTES("read:mutes"),

        /**
         * Child scope of [READ].
         */
        READ_NOTIFICATIONS("read:notifications"),

        /**
         * Child scope of [READ].
         */
        READ_SEARCH("read:search"),

        /**
         * Child scope of [READ].
         */
        READ_STATUSES("read:statuses"),

        /**
         * Grants access to write data. Requesting this scope will also grant its child scopes
         */
        WRITE("write"),

        /**
         * Child scope of [WRITE].
         */
        WRITE_ACCOUNTS("write:accounts"),

        /**
         * Child scope of [WRITE].
         */
        WRITE_BLOCKS("write:blocks"),

        /**
         * Child scope of [WRITE].
         */
        WRITE_BOOKMARKS("write:bookmarks"),

        /**
         * Child scope of [WRITE].
         */
        WRITE_CONVERSATIONS("write:conversations"),

        /**
         * Child scope of [WRITE].
         */
        WRITE_FAVOURITES("write:favourites"),

        /**
         * Child scope of [WRITE].
         */
        WRITE_FILTERS("write:filters"),

        /**
         * Child scope of [WRITE].
         */
        WRITE_FOLLOWS("write:follows"),

        /**
         * Child scope of [WRITE].
         */
        WRITE_LISTS("write:lists"),

        /**
         * Child scope of [WRITE].
         */
        WRITE_MEDIA("write:media"),

        /**
         * Child scope of [WRITE].
         */
        WRITE_MUTES("write:mutes"),

        /**
         * Child scope of [WRITE].
         */
        WRITE_NOTIFICATIONS("write:notifications"),

        /**
         * Child scope of [WRITE].
         */
        WRITE_REPORTS("write:reports"),

        /**
         * Child scope of [WRITE].
         */
        WRITE_STATUSES("write:statuses"),

        /**
         * Grants access to Web Push API subscriptions.
         */
        PUSH("push"),

        /**
         * Used for moderation API. Requesting this scope will also grant its child scopes.
         */
        ADMIN_READ("admin:read"),

        /**
         * Child scope of [ADMIN_READ].
         */
        ADMIN_READ_ACCOUNTS("admin:read:accounts"),

        /**
         * Child scope of [ADMIN_READ].
         */
        ADMIN_READ_REPORTS("admin:read:reports"),

        /**
         * Child scope of [ADMIN_READ].
         */
        ADMIN_READ_DOMAIN_ALLOWS("admin:read:domain_allows"),

        /**
         * Child scope of [ADMIN_READ].
         */
        ADMIN_READ_DOMAIN_BLOCKS("admin:read:domain_blocks"),

        /**
         * Child scope of [ADMIN_READ].
         */
        ADMIN_READ_IP_BLOCKS("admin:read:ip_blocks"),

        /**
         * Child scope of [ADMIN_READ].
         */
        ADMIN_READ_EMAIL_DOMAIN_BLOCKS("admin:read:email_domain_blocks"),

        /**
         * Child scope of [ADMIN_READ].
         */
        ADMIN_READ_CANONICAL_EMAIL_BLOCKS("admin:read:canonical_email_blocks"),

        /**
         * Used for moderation API. Requesting this scope will also grant its child scopes.
         */
        ADMIN_WRITE("admin:write"),

        /**
         * Child scope of [ADMIN_WRITE].
         */
        ADMIN_WRITE_ACCOUNTS("admin:write:accounts"),

        /**
         * Child scope of [ADMIN_WRITE].
         */
        ADMIN_WRITE_REPORTS("admin:write:reports"),

        /**
         * Child scope of [ADMIN_WRITE].
         */
        ADMIN_WRITE_DOMAIN_ALLOWS("admin:write:domain_allows"),

        /**
         * Child scope of [ADMIN_WRITE].
         */
        ADMIN_WRITE_DOMAIN_BLOCKS("admin:write:domain_blocks"),

        /**
         * Child scope of [ADMIN_WRITE].
         */
        ADMIN_WRITE_IP_BLOCKS("admin:write:ip_blocks"),

        /**
         * Child scope of [ADMIN_WRITE].
         */
        ADMIN_WRITE_EMAIL_DOMAIN_BLOCKS("admin:write:email_domain_blocks"),

        /**
         * Child scope of [ADMIN_WRITE].
         */
        ADMIN_WRITE_CANONICAL_EMAIL_BLOCKS("admin:write:canonical_email_blocks"),

        /**
         * Grants access to manage relationships. Requesting this scope will also grant the child scopes
         * [READ_BLOCKS], [WRITE_BLOCKS], [READ_FOLLOWS], [WRITE_FOLLOWS], [READ_MUTES] and [WRITE_MUTES].
         */
        @Deprecated(
            message = "Deprecated since Mastodon 3.5.0 and will be removed in the future. Use child scopes of READ and WRITE instead.",
            level = DeprecationLevel.WARNING
        )
        FOLLOW("follow"),

        /**
         * Grants full non-admin access by requesting the top-level scopes [READ], [WRITE] and [FOLLOW].
         * It is recommended that you request as little as possible for your application, so consider using individual
         * scopes instead.
         */
        @Deprecated(
            message = "Includes FOLLOW scope deprecated since Mastodon 3.5.0, and will be removed in the future. " +
                "If necessary, switch to requesting all top-level scopes directly.",
            replaceWith = ReplaceWith("Scope(PUSH, READ, WRITE)"),
            level = DeprecationLevel.WARNING
        )
        ALL(Scope(READ, WRITE, FOLLOW).toString())
    }

    fun validate() {
        require(scopes.size == scopes.distinct().size) { "There is a duplicate scope. : $this" }
    }

    override fun toString(): String = scopes.distinct().joinToString(separator = " ", transform = { it.scopeName })
}
