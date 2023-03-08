package social.bigbone.v410

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import social.bigbone.TestConstants
import social.bigbone.TestHelpers
import social.bigbone.api.entity.Token
import social.bigbone.api.exception.BigBoneRequestException

/**
 * Integration tests for MediaMethods running on Mastodon 4.1.0.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class V410MediaMethodsIntegrationTest {
    private lateinit var user1AppToken: Token
    private lateinit var user1UserToken: Token

    @BeforeAll
    fun beforeAll() {
        val user1Application = TestHelpers.createApp(TestConstants.USER1_APP_NAME)
        user1AppToken = TestHelpers.getAppToken(user1Application)
        user1UserToken = TestHelpers.getUserToken(user1Application, TestConstants.USER1_EMAIL, TestConstants.USER1_PASSWORD)
    }

    @Test
    fun `should upload image if file size is less than 10mb`() {
        val user1Client = TestHelpers.getTrustAllClient(user1UserToken.accessToken)
        val uploadedMedia = TestHelpers.uploadMediaFromResourcesFolder("castle-1280x853.jpg", "image/jpg", user1Client)
        assertNotNull(uploadedMedia)
        assertNotNull(uploadedMedia.id)
    }

    /**
     * According to https://github.com/mastodon/mastodon/issues/2252 there seems to be a file upload max image
     * resolution present on Mastodon servers. If media uploads exceed this size, an HTTP status code 422 is returned.
     */
    @Test
    fun `should discard image upload if file size is bigger than 10mb`() {
        val exception = assertThrows(BigBoneRequestException::class.java) {
            val user1Client = TestHelpers.getTrustAllClient(user1UserToken.accessToken)
            TestHelpers.uploadMediaFromResourcesFolder("bridge-5500x8251.jpg", "image/jpg", user1Client)
        }
        assertEquals(422, exception.httpStatusCode)
    }
}
