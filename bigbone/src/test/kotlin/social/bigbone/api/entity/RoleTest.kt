package social.bigbone.api.entity

import org.amshove.kluent.shouldBeTrue
import org.amshove.kluent.shouldContainSame
import org.junit.jupiter.api.Test
import social.bigbone.api.entity.Role.Permission.InviteUsers
import social.bigbone.api.entity.Role.Permission.ManageAnnouncements
import social.bigbone.api.entity.Role.Permission.ManageAppeals
import social.bigbone.api.entity.Role.Permission.ManageBlocks
import social.bigbone.api.entity.Role.Permission.ManageCustomEmojis
import social.bigbone.api.entity.Role.Permission.ManageFederation
import social.bigbone.api.entity.Role.Permission.ManageInvites
import social.bigbone.api.entity.Role.Permission.ManageReports
import social.bigbone.api.entity.Role.Permission.ManageRoles
import social.bigbone.api.entity.Role.Permission.ManageRules
import social.bigbone.api.entity.Role.Permission.ManageSettings
import social.bigbone.api.entity.Role.Permission.ManageTaxonomies
import social.bigbone.api.entity.Role.Permission.ManageUserAccess
import social.bigbone.api.entity.Role.Permission.ManageUsers
import social.bigbone.api.entity.Role.Permission.ManageWebhooks

class RoleTest {

    @Test
    fun `Given role with Administrator permission, when getting parsed permission, then return only Administrator permission`() {
        val role = Role(
            id = 1,
            name = "irrelevant",
            color = "irrelevant",
            highlighted = false,
            rawPermissions = 1
        )

        val parsedPermissions: List<Role.Permission> = role.getParsedPermissions()

        parsedPermissions shouldContainSame listOf(Role.Permission.Administrator)
    }

    @Test
    fun `Given role with InviteUsers permission, when getting parsed permission, then return only InviteUsers permission`() {
        val role = Role(
            id = 1,
            name = "irrelevant",
            color = "irrelevant",
            highlighted = false,
            rawPermissions = 65_536
        )

        val parsedPermissions: List<Role.Permission> = role.getParsedPermissions()

        parsedPermissions shouldContainSame listOf(Role.Permission.InviteUsers)
    }

    @Test
    fun `Given role with InviteUsers permission, when checking for that permission, then return true`() {
        val role = Role(
            id = 1,
            name = "irrelevant",
            color = "irrelevant",
            highlighted = false,
            rawPermissions = 65_536
        )

        val hasPermission: Boolean = role.hasPermission(InviteUsers)

        hasPermission.shouldBeTrue()
    }

    @Test
    fun `Given role with all manage permissions, when getting parsed permission, then return all manage permissions`() {
        val role = Role(
            id = 1,
            name = "irrelevant",
            color = "irrelevant",
            highlighted = false,
            rawPermissions = 458_736
        )

        val parsedPermissions: List<Role.Permission> = role.getParsedPermissions()

        parsedPermissions shouldContainSame listOf(
            ManageAnnouncements,
            ManageAppeals,
            ManageBlocks,
            ManageCustomEmojis,
            ManageFederation,
            ManageInvites,
            ManageReports,
            ManageRoles,
            ManageRules,
            ManageSettings,
            ManageTaxonomies,
            ManageUserAccess,
            ManageUsers,
            ManageWebhooks
        )
    }
}
