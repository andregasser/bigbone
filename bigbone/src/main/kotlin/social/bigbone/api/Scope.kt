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
    interface Name {
        val name: String
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
        val ALL = object : Name {
            override val name: String
                get() = "read"
        }

        /**
         * Child scope of [READ].
         */
        @JvmField
        val ACCOUNTS = object : Name {
            override val name: String get() = "read:accounts"
        }

        /**
         * Child scope of [READ].
         */
        @JvmField
        val BLOCKS = object : Name {
            override val name: String get() = "read:blocks"
        }

        /**
         * Child scope of [READ].
         */
        @JvmField
        val BOOKMARKS = object : Name {
            override val name: String get() = "read:bookmarks"
        }

        /**
         * Child scope of [READ].
         */
        @JvmField
        val FAVOURITES = object : Name {
            override val name: String get() = "read:favourites"
        }

        /**
         * Child scope of [READ].
         */
        @JvmField
        val FILTERS = object : Name {
            override val name: String get() = "read:filters"
        }

        /**
         * Child scope of [READ].
         */
        @JvmField
        val FOLLOWS = object : Name {
            override val name: String get() = "read:follows"
        }

        /**
         * Child scope of [READ].
         */
        @JvmField
        val LISTS = object : Name {
            override val name: String get() = "read:lists"
        }

        /**
         * Child scope of [READ].
         */
        @JvmField
        val MUTES = object : Name {
            override val name: String get() = "read:mutes"
        }

        /**
         * Child scope of [READ].
         */
        @JvmField
        val NOTIFICATIONS = object : Name {
            override val name: String get() = "read:notifications"
        }

        /**
         * Child scope of [READ].
         */
        @JvmField
        val SEARCH = object : Name {
            override val name: String get() = "read:search"
        }

        /**
         * Child scope of [READ].
         */
        @JvmField
        val STATUSES = object : Name {
            override val name: String get() = "read:statuses"
        }
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
        val ALL = object : Name {
            override val name: String
                get() = "write"
        }

        /**
         * Child scope of [WRITE].
         */
        @JvmField
        val ACCOUNTS = object : Name {
            override val name: String get() = "write:accounts"
        }

        /**
         * Child scope of [WRITE].
         */
        @JvmField
        val BLOCKS = object : Name {
            override val name: String get() = "write:blocks"
        }

        /**
         * Child scope of [WRITE].
         */
        @JvmField
        val BOOKMARKS = object : Name {
            override val name: String get() = "write:bookmarks"
        }

        /**
         * Child scope of [WRITE].
         */
        @JvmField
        val CONVERSATIONS = object : Name {
            override val name: String get() = "write:conversations"
        }

        /**
         * Child scope of [WRITE].
         */
        @JvmField
        val FAVOURITES = object : Name {
            override val name: String get() = "write:favourites"
        }

        /**
         * Child scope of [WRITE].
         */
        @JvmField
        val FILTERS = object : Name {
            override val name: String get() = "write:filters"
        }

        /**
         * Child scope of [WRITE].
         */
        @JvmField
        val FOLLOWS = object : Name {
            override val name: String get() = "write:follows"
        }

        /**
         * Child scope of [WRITE].
         */
        @JvmField
        val LISTS = object : Name {
            override val name: String get() = "write:lists"
        }

        /**
         * Child scope of [WRITE].
         */
        @JvmField
        val MEDIA = object : Name {
            override val name: String get() = "write:media"
        }

        /**
         * Child scope of [WRITE].
         */
        @JvmField
        val MUTES = object : Name {
            override val name: String get() = "write:mutes"
        }

        /**
         * Child scope of [WRITE].
         */
        @JvmField
        val NOTIFICATIONS = object : Name {
            override val name: String get() = "write:notifications"
        }

        /**
         * Child scope of [WRITE].
         */
        @JvmField
        val REPORTS = object : Name {
            override val name: String get() = "write:reports"
        }

        /**
         * Child scope of [WRITE].
         */
        @JvmField
        val STATUSES = object : Name {
            override val name: String get() = "write:statuses"
        }
    }

    /**
     * Scopes granting access to Web Push API subscriptions are grouped here. Currently, only [PUSH.ALL] exists.
     */
    object PUSH {

        /**
         * Grants access to Web Push API subscriptions.
         */
        @JvmField
        val ALL = object : Name {
            override val name: String get() = "push"
        }
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
            val ALL = object : Name {
                override val name: String get() = "admin:read"
            }

            /**
             * Child scope of [ADMIN.READ].
             */
            @JvmField
            val ACCOUNTS = object : Name {
                override val name: String get() = "admin:read:accounts"
            }

            /**
             * Child scope of [ADMIN.READ].
             */
            @JvmField
            val REPORTS = object : Name {
                override val name: String get() = "admin:read:reports"
            }

            /**
             * Child scope of [ADMIN.READ].
             */
            @JvmField
            val DOMAIN_ALLOWS = object : Name {
                override val name: String get() = "admin:read:domain_allows"
            }

            /**
             * Child scope of [ADMIN.READ].
             */
            @JvmField
            val DOMAIN_BLOCKS = object : Name {
                override val name: String get() = "admin:read:domain_blocks"
            }

            /**
             * Child scope of [ADMIN.READ].
             */
            @JvmField
            val IP_BLOCKS = object : Name {
                override val name: String get() = "admin:read:ip_blocks"
            }

            /**
             * Child scope of [ADMIN.READ].
             */
            @JvmField
            val EMAIL_DOMAIN_BLOCKS = object : Name {
                override val name: String get() = "admin:read:email_domain_blocks"
            }

            /**
             * Child scope of [ADMIN.READ].
             */
            @JvmField
            val CANONICAL_EMAIL_BLOCKS = object : Name {
                override val name: String get() = "admin:read:canonical_email_blocks"
            }
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
            val ALL = object : Name {
                override val name: String get() = "admin:write"
            }

            /**
             * Child scope of [ADMIN.WRITE].
             */
            @JvmField
            val ACCOUNTS = object : Name {
                override val name: String get() = "admin:write:accounts"
            }

            /**
             * Child scope of [ADMIN.WRITE].
             */
            @JvmField
            val REPORTS = object : Name {
                override val name: String get() = "admin:write:reports"
            }

            /**
             * Child scope of [ADMIN.WRITE].
             */
            @JvmField
            val DOMAIN_ALLOWS = object : Name {
                override val name: String get() = "admin:write:domain_allows"
            }

            /**
             * Child scope of [ADMIN.WRITE].
             */
            @JvmField
            val DOMAIN_BLOCKS = object : Name {
                override val name: String get() = "admin:write:domain_blocks"
            }

            /**
             * Child scope of [ADMIN.WRITE].
             */
            @JvmField
            val IP_BLOCKS = object : Name {
                override val name: String get() = "admin:write:ip_blocks"
            }

            /**
             * Child scope of [ADMIN.WRITE].
             */
            @JvmField
            val EMAIL_DOMAIN_BLOCKS = object : Name {
                override val name: String get() = "admin:write:email_domain_blocks"
            }

            /**
             * Child scope of [ADMIN.WRITE].
             */
            @JvmField
            val CANONICAL_EMAIL_BLOCKS = object : Name {
                override val name: String get() = "admin:write:canonical_email_blocks"
            }
        }
    }

    /**
     * Grants access to manage relationships. Requesting this scope will also grant the child scopes
     * [READ.BLOCKS], [WRITE.BLOCKS], [READ.FOLLOWS], [WRITE.FOLLOWS], [READ.MUTES] and [WRITE.MUTES].
     */
    @Deprecated(
        message = "Deprecated since Mastodon 3.5.0 and will be removed in the future. Use child scopes of READ and WRITE instead.",
        level = DeprecationLevel.WARNING
    )
    object FOLLOW : Name {
        override val name: String get() = "follow"
    }

    /**
     * Grants full non-admin access by requesting the top-level scopes [READ], [WRITE] and [FOLLOW].
     * It is recommended that you request as little as possible for your application, so consider using individual
     * scopes instead.
     */
    @Deprecated(
        message = "Includes FOLLOW scope deprecated since Mastodon 3.5.0, and will be removed in the future. " +
            "If necessary, switch to requesting all top-level scopes directly.",
        replaceWith = ReplaceWith("Scope(READ, WRITE, PUSH)"),
        level = DeprecationLevel.WARNING
    )
    object ALL : Name {
        override val name: String get() = "read write follow"
    }

    override fun toString(): String = scopes.distinct().joinToString(separator = " ", transform = { it.name })
}
