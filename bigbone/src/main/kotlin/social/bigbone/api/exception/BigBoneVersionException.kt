package social.bigbone.api.exception

import social.bigbone.MastodonMinServerVersion

/**
 * Exception which is thrown if a method annotated with a [MastodonMinServerVersion] requires a higher version of the
 * Mastodon software to be running on a server than is actually running on it.
 */
class BigBoneVersionException(methodName: String, minVersion: String, actualVersion: String) : Exception(
    "$methodName requires the server to run at least Mastodon $minVersion but it runs $actualVersion"
)
