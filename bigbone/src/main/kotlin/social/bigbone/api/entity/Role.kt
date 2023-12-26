package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime
import social.bigbone.api.entity.Role.PermissionFlag

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
     * Possible values can be found in [PermissionFlag].
     */
    @SerialName("permissions")
    val permissions: Int,

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
     * Possible Permission Flag values masked in [permissions].
     *
     * To determine the permissions available to a certain role,
     * convert the permissions attribute to binary and compare from the least significant bit upwards.
     *
     * For convenience (and to prevent the terms from growing too long),
     * permissions will be presented below using hexadecimal values.
     */
    enum class PermissionFlag(val bitValue: Int) {
        Administrator(0x01),
        DevOps(0x02),

        ViewAuditLog(0x04),
        ViewDashboard(0x08),

        ManageReports(0x10),
        ManageFederation(0x20),
        ManageSettings(0x40),
        ManageBlocks(0x80),
        ManageTaxonomies(0x100),
        ManageAppeals(0x200),
        ManageUsers(0x400),
        ManageInvites(0x800),
        ManageRules(0x1_000),
        ManageAnnouncements(0x2_000),
        ManageCustomEmojis(0x4_000),
        ManageWebhooks(0x8_000),
        ManageRoles(0x20_000),
        ManageUserAccess(0x40_000),

        InviteUsers(0x10_000),

        DeleteUserData(0x80_000)
    }
}
