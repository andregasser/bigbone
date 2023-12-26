package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime
import social.bigbone.api.entity.Role.Permission

/**
 * Represents a custom user role that grants permissions.
 *
 * @see <a href="https://docs.joinmastodon.org/entities/Role/">Mastodon API Role</a>
 */
@Serializable
data class Role(
    /**
     * The ID of the Role in the database.
     */
    @SerialName("id")
    val id: Int,

    /**
     * The name of the role.
     */
    @SerialName("name")
    val name: String,

    /**
     * The hex code assigned to this role.
     * If no hex code is assigned, the string will be empty.
     */
    @SerialName("color")
    val color: String,

    /**
     * A bitmask that represents the sum of all permissions granted to the role.
     * Possible values can be found in [Permission].
     *
     * For convenience, use [getParsedPermissions] to directly get a list of [Permission]s seen in this field.
     */
    @SerialName("permissions")
    val rawPermissions: Int,

    /**
     * Whether the role is publicly visible as a badge on user profiles.
     */
    @SerialName("highlighted")
    val highlighted: Boolean,

    /**
     * When the role was first assigned.
     */
    @SerialName("created_at")
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: PrecisionDateTime = PrecisionDateTime.InvalidPrecisionDateTime.Unavailable,

    /**
     * When the role was last updated.
     */
    @SerialName("updated_at")
    @Serializable(with = DateTimeSerializer::class)
    val updatedAt: PrecisionDateTime = PrecisionDateTime.InvalidPrecisionDateTime.Unavailable
) {

    /**
     * Returns whether this role grants the [Permission] specified in [permission].
     * The Mastodon API returns permissions as a bitmask in [rawPermissions].
     */
    @Suppress("DataClassContainsFunctions")
    fun hasPermission(permission: Permission): Boolean = rawPermissions and permission.bitValue == permission.bitValue

    /**
     * Gets [rawPermissions] and checks for all available [Permission]s which of them are part of the bitmask.
     *
     * @return [Permission]s masked in [rawPermissions], i.e. permissions this Role has.
     */
    @Suppress("DataClassContainsFunctions")
    fun getParsedPermissions(): List<Permission> = Permission.entries.filter(::hasPermission)

    /**
     * Possible Permission Flag values masked in [rawPermissions].
     *
     * Use [hasPermission] to check for a specific permission’s availability, or [getParsedPermissions] to get a list
     * of [Permission]s.
     */
    enum class Permission(val bitValue: Int) {
        Administrator(0b1), // 1
        DevOps(0b10), // 2

        ViewAuditLog(0b100), // 4
        ViewDashboard(0b1000), // 8

        ManageReports(0b10000), // 10
        ManageFederation(0b100000), // 20
        ManageSettings(0b1000000), // 40
        ManageBlocks(0b10000000), // 80
        ManageTaxonomies(0b100000000), // 100
        ManageAppeals(0b1000000000), // 200
        ManageUsers(0b10000000000), // 400
        ManageInvites(0b100000000000), // 800
        ManageRules(0b1000000000000), // 1_000
        ManageAnnouncements(0b10000000000000), // 2_000
        ManageCustomEmojis(0b100000000000000), // 4_000
        ManageWebhooks(0b1000000000000000), // 8_000
        ManageRoles(0b100000000000000000), // 20_000
        ManageUserAccess(0b1000000000000000000), // 40_000

        InviteUsers(0b10000000000000000), // 10_000

        DeleteUserData(0b10000000000000000000) // 80_000
    }
}
