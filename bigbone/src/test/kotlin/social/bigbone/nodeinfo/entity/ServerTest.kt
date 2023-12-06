package social.bigbone.nodeinfo.entity

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import social.bigbone.JSON_SERIALIZER
import social.bigbone.testtool.AssetsUtil

class ServerTest {
    @Test
    fun deserialize() {
        // given
        val json = AssetsUtil.readFromAssets("nodeinfo_server_info.json")

        // when
        val server: Server = JSON_SERIALIZER.decodeFromString(json)

        // then
        server.schemaVersion shouldBeEqualTo "2.0"
        server.software?.let {
            it.name shouldBeEqualTo "mastodon"
            it.version shouldBeEqualTo "4.1.2+nightly-20230523"
        }
    }
}
