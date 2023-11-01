package social.bigbone.api.method

import io.mockk.verify
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test
import social.bigbone.MastodonClient
import social.bigbone.Parameters
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class AnnouncementMethodsTest {
    @Test
    fun `Get all read announcements`() {
        val client = MockClient.mock("announcements.json")
        val announcementMethods = AnnouncementMethods(client)
        val announcements = announcementMethods.getAllAnnouncements(withDismissed = true).execute()

        announcements shouldHaveSize 3

        verify {
            client.get(
                path = "/api/v1/announcements",
                query = any<Parameters>()
            )
        }
    }

    @Test
    fun `Dismiss an announcement and verify that the method was called correctly`() {
        val client = MockClient.mock("announcement_dismiss_success.json")
        val announcementMethods = AnnouncementMethods(client)

        announcementMethods.dismissAnnouncement("8")

        verify {
            client.performAction(
                endpoint = "/api/v1/announcements/8/dismiss",
                method = MastodonClient.Method.POST
            )
        }
    }

    @Test
    fun `Dismiss announcement with exception`() {
        val client = MockClient.ioException()
        val announcementMethods = AnnouncementMethods(client)

        invoking { announcementMethods.dismissAnnouncement("8") } shouldThrow BigBoneRequestException::class

        verify {
            client.performAction(
                endpoint = "/api/v1/announcements/8/dismiss",
                method = MastodonClient.Method.POST
            )
        }
    }

    @Test
    fun `Add reaction to announcement and verify that the method was called correctly`() {
        val client = MockClient.mock("announcements_add_reaction_success.json")
        val announcementMethods = AnnouncementMethods(client)

        announcementMethods.addReactionToAnnouncement(
            "😀",
            "8"
        )

        verify {
            client.performAction(
                endpoint = "/api/v1/announcements/8/reactions/😀",
                method = MastodonClient.Method.PUT
            )
        }
    }

    @Test
    fun `Remove reaction from announcement and verify that the method was called correctly`() {
        val client = MockClient.mock("announcements_remove_reaction_success.json")
        val announcementMethods = AnnouncementMethods(client)

        announcementMethods.removeReactionFromAnnouncement(
            "😀",
            "8"
        )

        verify {
            client.performAction(
                endpoint = "/api/v1/announcements/8/reactions/😀",
                method = MastodonClient.Method.DELETE
            )
        }
    }
}
