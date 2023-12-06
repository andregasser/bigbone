package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.entity.Preferences
import social.bigbone.api.entity.data.Visibility
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class PreferenceMethodsTest {
    @Test
    fun getPreferences() {
        val client = MockClient.mock("preferences.json")

        val preferenceMethods = PreferenceMethods(client)
        val preference = preferenceMethods.getPreferences().execute()
        preference.defaultLanguage shouldBeEqualTo "en"
        preference.defaultSensitive shouldBeEqualTo false
        preference.defaultVisibility shouldBeEqualTo Visibility.PUBLIC
        preference.expandMedia shouldBeEqualTo Preferences.Media.DEFAULT
        preference.expandSpoilers shouldBeEqualTo false
    }

    @Test
    fun getPreferencesException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val preferenceMethods = PreferenceMethods(client)
            preferenceMethods.getPreferences().execute()
        }
    }
}
