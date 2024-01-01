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
    val id: Int = 0,

    /**
     * The name of the role.
     */
    @SerialName("name")
    val name: String = "",

    /**
     * The hex code assigned to this role.
     * If no hex code is assigned, the string will be empty.
     */
    @SerialName("color")
    val color: String = "",

    /**
     * A bitmask that represents the sum of all permissions granted to the role.
     * Possible values can be found in [Permission].
     *
     * For convenience, use [getParsedPermissions] to directly get a list of [Permission]s seen in this field.
     */
    @SerialName("permissions")
    val rawPermissions: Int = 0,

    /**
     * Whether the role is publicly visible as a badge on user profiles.
     */
    @SerialName("highlighted")
    val highlighted: Boolean = false,

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
     *
     * Example: [rawPermissions] reading 65536 (0b10000000000000000) would return true for [Permission.InviteUsers]
     * because its bits match when comparing bitwise.
     */
    @Suppress("DataClassContainsFunctions")
    fun hasPermission(permission: Permission): Boolean = rawPermissions and permission.bitValue == permission.bitValue

    /**
     * Gets [rawPermissions] and checks for all available [Permission]s which of them are part of the bitmask.
     *
     * 458_736 returned by the API.
     * In binary, that would be: 1101111111111110000
     *
     * 1101111111111110000 contains all the following permissions represented by their binary values:
     *               10000 --- ManageReports
     *              100000 --- ManageFederation
     *             1000000 --- ManageSettings
     *            10000000 --- ManageBlocks
     *           100000000 --- ManageTaxonomies
     *          1000000000 --- ManageAppeals
     *         10000000000 --- ManageUsers
     *        100000000000 --- ManageInvites
     *       1000000000000 --- ManageRules
     *      10000000000000 --- ManageAnnouncements
     *     100000000000000 --- ManageCustomEmojis
     *    1000000000000000 --- ManageWebhooks
     *  100000000000000000 --- ManageRoles
     * 1000000000000000000 --- ManageUserAccess
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
        Administrator(0b1), // 0x1 / 1
        DevOps(0b10), // 0x2 / 2

        ViewAuditLog(0b100), // 0x4 / 4
        ViewDashboard(0b1000), // 0x8 / 8

        ManageReports(0b10000), // 0x10 / 16
        ManageFederation(0b100000), // 0x20 / 32
        ManageSettings(0b1000000), // 0x40 / 64
        ManageBlocks(0b10000000), // 0x80 / 128
        ManageTaxonomies(0b100000000), // 0x100 / 256
        ManageAppeals(0b1000000000), // 0x200 / 512
        ManageUsers(0b10000000000), // 0x400 / 1024
        ManageInvites(0b100000000000), // 0x800 / 2048
        ManageRules(0b1000000000000), // 0x1000 / 4096
        ManageAnnouncements(0b10000000000000), // 0x2000 / 8192
        ManageCustomEmojis(0b100000000000000), // 0x4000 / 16384
        ManageWebhooks(0b1000000000000000), // 0x8000 / 32768
        ManageRoles(0b100000000000000000), // 0x20000 / 131072
        ManageUserAccess(0b1000000000000000000), // 0x40000 / 262144

        InviteUsers(0b10000000000000000), // 0x10000 / 65536

        DeleteUserData(0b10000000000000000000) // 0x80000 / 524288
    }
}
