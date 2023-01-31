package social.bigbone.api.entity.auth

import com.google.gson.Gson
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import social.bigbone.testtool.AssetsUtil

class TokenTest {

    @Test
    fun deserialize() {
        val json = AssetsUtil.readFromAssets("access_token.json")
        val accessToken: Token = Gson().fromJson(json, Token::class.java)
        accessToken.accessToken shouldBeEqualTo "test"
        accessToken.tokenType shouldBeEqualTo "bearer"
        accessToken.scope shouldBeEqualTo "read write follow"
        accessToken.createdAt shouldBeEqualTo 1_493_188_835L
    }

    @Test
    fun constructor() {
        val accessToken: Token = Token(accessToken = "123", scope = "scope")
        accessToken.accessToken shouldBeEqualTo "123"
        accessToken.tokenType shouldBeEqualTo ""
        accessToken.scope shouldBeEqualTo "scope"
    }
}
