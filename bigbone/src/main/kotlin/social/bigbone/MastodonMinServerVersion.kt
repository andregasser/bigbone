package social.bigbone

import kotlin.annotation.AnnotationTarget.FUNCTION

/**
 * Specifies the first version of a Mastodon server where a declaration has appeared.
 * In essence, this defines the minimum server version that is required in order to make a call successful.
 *
 * @property version the version in the following formats: `<major>.<minor>` or `<major>.<minor>.<patch>`, where major, minor and patch
 * are non-negative integer numbers without leading zeros.
 */
@Target(FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
internal annotation class MastodonMinServerVersion(val version: String)
