package social.bigbone.nodeinfo.entity

import com.google.gson.Gson
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import social.bigbone.testtool.AssetsUtil

class ServerTest {

    @Test
    fun deserialize() {
        // given
        val json = AssetsUtil.readFromAssets("nodeinfo_server_info.json")

        // when
        val server = Gson().fromJson(json, Server::class.java)

        // then
        server.schemaVersion shouldBeEqualTo "2.0"
        server.software?.let {
            it.name shouldBeEqualTo "mastodon"
            it.version shouldBeEqualTo "4.1.2+nightly-20230523"
        }
    }
}
