package social.bigbone

import social.bigbone.api.exception.BigBoneVersionException
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.math.max
import kotlin.reflect.KFunction
import kotlin.reflect.full.findAnnotation

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

/**
 * Tries to get the version defined in the [MastodonMinServerVersion] of this [KFunction], if any.
 *
 * @return [String] version of the [MastodonMinServerVersion] if this function is annotated, `null` otherwise
 */
internal fun <T> KFunction<T>.minMastodonVersion(): String? = findAnnotation<MastodonMinServerVersion>()?.version

/**
 * Helper function to ensure that the annotated [MastodonMinServerVersion] of this [KFunction] is lower than that of the
 * instance the [client] is connected to.
 */
@Throws(BigBoneVersionException::class)
fun <T> KFunction<T>.requireMinVersion(client: MastodonClient) {
    val minMastodonVersion = minMastodonVersion() ?: return
    val instanceVersion = client.getInstanceVersion() ?: return
    if (SemanticVersion(instanceVersion) >= SemanticVersion(minMastodonVersion)) return

    throw BigBoneVersionException(
        methodName = name,
        minVersion = minMastodonVersion,
        actualVersion = instanceVersion
    )
}

/**
 * Wrapper to allow comparison of version [String]s that follow semantic versioning.
 * @see <a href="https://semver.org">SemVer.org</a>
 */
private class SemanticVersion(val version: String) {

    init {
        require(version.matches(versionRegex)) { "String $version doesn't appear to contain a semantic version" }
    }

    operator fun compareTo(other: SemanticVersion): Int {
        val thisParts = parts()
        val otherParts = other.parts()
        for (i in 0 until max(thisParts.size, otherParts.size)) {
            val thisPart = if (i < thisParts.size) thisParts[i].toInt() else 0
            val thatPart = if (i < otherParts.size) otherParts[i].toInt() else 0
            if (thisPart < thatPart) return -1
            if (thisPart > thatPart) return 1
        }
        return 0
    }

    private fun parts() = version.split(".")

    companion object {
        /**
         * Suggested regular expression to parse all valid SemVer strings.
         * @see <a href="https://semver.org/#is-there-a-suggested-regular-expression-regex-to-check-a-semver-string">SemVer RegEx</a>
         */
        private val versionRegex = (
            """^(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)""" +
                """(?:-((?:0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*)""" +
                """(?:\.(?:0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?""" +
                """(?:\+([0-9a-zA-Z-]+(?:\.[0-9a-zA-Z-]+)*))?${'$'}"""
            ).toRegex()
    }
}
