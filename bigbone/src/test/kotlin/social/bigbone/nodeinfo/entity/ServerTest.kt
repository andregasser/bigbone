package social.bigbone.nodeinfo.entity

import kotlinx.serialization.json.Json
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import social.bigbone.testtool.AssetsUtil

class ServerTest {

    private val jsonSerializer: Json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Test
    fun deserialize() {
        // given
        val json = AssetsUtil.readFromAssets("nodeinfo_server_info.json")

        // when
        val server: Server = jsonSerializer.decodeFromString(json)

        // then
        server.schemaVersion shouldBeEqualTo "2.0"
        server.software?.let {
            it.name shouldBeEqualTo "mastodon"
            it.version shouldBeEqualTo "4.1.2+nightly-20230523"
        }
    }
}
