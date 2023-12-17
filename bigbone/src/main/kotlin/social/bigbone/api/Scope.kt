package social.bigbone.api

/**
 * Represents the access permissions that can be requested when registering an app or when requesting access tokens.
 * Existing scopes are available via members of this class, arranged hierarchically. It is recommended that you request
 * as little as possible for your application.
 * @param scopes individual scopes that should be requested.
 * @see <a href="https://docs.joinmastodon.org/api/oauth-scopes/">Mastodon OAuth scopes</a>
 */
@Suppress("UseDataClass") // data class can not be used with primary constructor vararg parameters
class Scope(private vararg val scopes: Name) {

    /**
     * Interface to define scopes. While existing Mastodon scopes are generally available as objects of the [Scope]
     * class, potential new scopes defined by Mastodon but not yet implemented here can be added by using this interface.
     */
    fun interface Name {
        fun name(): String
    }

    /**
     * Scopes granting access to read data are grouped here. Use [READ.ALL] to request full read access, or preferably
     * individual child scopes to limit access to a minimum.
     */
    object READ {

        /**
         * Grants access to read all data. Requesting this scope is equivalent to requesting all other [READ] child scopes.
         */
        @JvmField
        val ALL = Name { "read" }

        @JvmField
        val ACCOUNTS = Name { "read:accounts" }

        @JvmField
        val BLOCKS = Name { "read:blocks" }

        @JvmField
        val BOOKMARKS = Name { "read:bookmarks" }

        @JvmField
        val FAVOURITES = Name { "read:favourites" }

        @JvmField
        val FILTERS = Name { "read:filters" }

        @JvmField
        val FOLLOWS = Name { "read:follows" }

        @JvmField
        val LISTS = Name { "read:lists" }

        @JvmField
        val MUTES = Name { "read:mutes" }

        @JvmField
        val NOTIFICATIONS = Name { "read:notifications" }

        @JvmField
        val SEARCH = Name { "read:search" }

        @JvmField
        val STATUSES = Name { "read:statuses" }
    }

    /**
     * Scopes granting access to write data are grouped here. Use [WRITE.ALL] to request full write access, or preferably
     * individual child scopes to limit access to a minimum.
     */
    object WRITE {

        /**
         * Grants access to write all data. Requesting this scope is equivalent to requesting all other child scopes.
         */
        @JvmField
        val ALL = Name { "write" }

        @JvmField
        val ACCOUNTS = Name { "write:accounts" }

        @JvmField
        val BLOCKS = Name { "write:blocks" }

        @JvmField
        val BOOKMARKS = Name { "write:bookmarks" }

        @JvmField
        val CONVERSATIONS = Name { "write:conversations" }

        @JvmField
        val FAVOURITES = Name { "write:favourites" }

        @JvmField
        val FILTERS = Name { "write:filters" }

        @JvmField
        val FOLLOWS = Name { "write:follows" }

        @JvmField
        val LISTS = Name { "write:lists" }

        @JvmField
        val MEDIA = Name { "write:media" }

        @JvmField
        val MUTES = Name { "write:mutes" }

        @JvmField
        val NOTIFICATIONS = Name { "write:notifications" }

        @JvmField
        val REPORTS = Name { "write:reports" }

        @JvmField
        val STATUSES = Name { "write:statuses" }
    }

    /**
     * Scopes granting access to Web Push API subscriptions are grouped here. Currently, only [PUSH.ALL] exists.
     */
    object PUSH {

        /**
         * Grants access to Web Push API subscriptions.
         */
        @JvmField
        val ALL = Name { "push" }
    }

    /**
     * Scopes granting access to the moderation API are grouped here, split between [ADMIN.READ] and [ADMIN.WRITE] for
     * read and write access, respectively.
     */
    object ADMIN {

        /**
         * Scopes granting read access to the moderation API are grouped here. Use [ADMIN.READ.ALL] to request full
         * read access, or preferably individual child scopes to limit access to a minimum.
         */
        object READ {

            /**
             * Grants read access to the moderation API. Requesting this scope is equivalent to requesting all other
             * child scopes.
             */
            @JvmField
            val ALL = Name { "admin:read" }

            @JvmField
            val ACCOUNTS = Name { "admin:read:accounts" }

            @JvmField
            val REPORTS = Name { "admin:read:reports" }

            @JvmField
            val DOMAIN_ALLOWS = Name { "admin:read:domain_allows" }

            @JvmField
            val DOMAIN_BLOCKS = Name { "admin:read:domain_blocks" }

            @JvmField
            val IP_BLOCKS = Name { "admin:read:ip_blocks" }

            @JvmField
            val EMAIL_DOMAIN_BLOCKS = Name { "admin:read:email_domain_blocks" }

            @JvmField
            val CANONICAL_EMAIL_BLOCKS = Name { "admin:read:canonical_email_blocks" }
        }

        /**
         * Scopes granting write access to the moderation API are grouped here. Use [ADMIN.WRITE.ALL] to request full
         * write access, or preferably individual child scopes to limit access to a minimum.
         */
        object WRITE {

            /**
             * Grants write access to the moderation API. Requesting this scope is equivalent to requesting all other
             * child scopes.
             */
            @JvmField
            val ALL = Name { "admin:write" }

            @JvmField
            val ACCOUNTS = Name { "admin:write:accounts" }

            @JvmField
            val REPORTS = Name { "admin:write:reports" }

            @JvmField
            val DOMAIN_ALLOWS = Name { "admin:write:domain_allows" }

            @JvmField
            val DOMAIN_BLOCKS = Name { "admin:write:domain_blocks" }

            @JvmField
            val IP_BLOCKS = Name { "admin:write:ip_blocks" }

            @JvmField
            val EMAIL_DOMAIN_BLOCKS = Name { "admin:write:email_domain_blocks" }

            @JvmField
            val CANONICAL_EMAIL_BLOCKS = Name { "admin:write:canonical_email_blocks" }
        }
    }

    override fun toString(): String = scopes.distinct().joinToString(separator = " ", transform = { it.name() })
}
