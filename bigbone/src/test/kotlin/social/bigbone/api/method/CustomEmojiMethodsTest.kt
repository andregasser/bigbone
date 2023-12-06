package social.bigbone.api.method

import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeTrue
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.entity.CustomEmoji
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

@Suppress("ktlint:standard:max-line-length")
class CustomEmojiMethodsTest {
    @Test
    fun `Given a client returning success, when getting all custom emoji, then expect values of response`() {
        val client = MockClient.mock(jsonName = "custom_emojis_success.json")

        val customEmojiMethods = CustomEmojiMethods(client)

        with(customEmojiMethods.getAllCustomEmojis().execute()) {
            shouldHaveSize(5)

            all(CustomEmoji::visibleInPicker).shouldBeTrue()

            get(0).shortcode shouldBeEqualTo "aaaa"
            get(1).shortcode shouldBeEqualTo "AAAAAA"
            get(2).shortcode shouldBeEqualTo "blobaww"
            get(3).shortcode shouldBeEqualTo "yikes"
            get(4).shortcode shouldBeEqualTo "ziltoid"
        }

        verify {
            client.get(
                path = "api/v1/custom_emojis",
                query = null
            )
        }
    }

    @Test
    fun `Given a client failing with an IO exception, when getting all custom emoji, then expect BigBoneRequestException`() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val customEmojisMethods = CustomEmojiMethods(client)
            customEmojisMethods.getAllCustomEmojis().execute()
        }
    }
}
