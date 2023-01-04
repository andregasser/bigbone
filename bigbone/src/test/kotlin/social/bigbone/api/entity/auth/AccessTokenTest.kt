package social.bigbone.api.entity.auth

import com.google.gson.Gson
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import social.bigbone.testtool.AssetsUtil

class AccessTokenTest {

    @Test
    fun deserialize() {
        val json = AssetsUtil.readFromAssets("access_token.json")
        val accessToken: AccessToken = Gson().fromJson(json, AccessToken::class.java)
        accessToken.accessToken shouldBeEqualTo "ZA-Yj3aBD8U8Cm7lKUp-lm9O9BmDgdhHzDeqsY8tlL0"
        accessToken.tokenType shouldBeEqualTo "Bearer"
        accessToken.scope shouldBeEqualTo "read write follow push"
        accessToken.createdAt shouldBeEqualTo 1_573_979_017L
    }

    @Test
    fun constructor() {
        val accessToken = AccessToken(accessToken = "123", scope = "scope")
        accessToken.accessToken shouldBeEqualTo "123"
        accessToken.tokenType shouldBeEqualTo ""
        accessToken.scope shouldBeEqualTo "scope"
    }
}
