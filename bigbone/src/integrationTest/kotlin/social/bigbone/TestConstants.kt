package social.bigbone

import social.bigbone.api.Scope

class TestConstants {
    companion object {
        const val REST_API_HOSTNAME = "localhost"

        const val REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob"

        const val USER1_APP_NAME = "user1-client-app"
        const val USER1_USERNAME = "user1"
        const val USER1_EMAIL = "user1@email.dev"
        const val USER1_PASSWORD = "user1abcdef"

        const val USER2_APP_NAME = "user2-client-app"
        const val USER2_USERNAME = "user2"
        const val USER2_EMAIL = "user2@email.dev"
        const val USER2_PASSWORD = "user2abcdef"

        /**
         * This scope should allow accessing all non-admin endpoints in integration tests.
         */
        val fullScope = Scope(Scope.Name.READ, Scope.Name.WRITE, Scope.Name.PUSH)
    }
}
