import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import social.bigbone.MastodonClient

class Test2 {

    private lateinit var client: MastodonClient

    @BeforeEach
    fun setUp() {
        client = MastodonClient.Builder("localhost")
            .withHttpsDisabled()
            .withPort(3000)
            .build()
    }

    @Test
    fun getInstance() {
        val instance = client.instances.getInstance().execute()
        println(instance)
    }

    @Test
    fun postStatus() {
        val accessToken = client.accounts.registerAccount(
            username = "andre",
            email = "andre.gasser@protonmail.com",
            password = "test",
            agreement = true,
            locale = "en",
            reason = null).execute()
        val response = client.statuses.postStatus("This is my status", null, null, false, "Test").execute()

    }

}
