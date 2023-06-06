package social.bigbone.nodeinfo.entity

import com.google.gson.Gson
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import social.bigbone.testtool.AssetsUtil

class NodeInfoTest {

    @Test
    fun deserialize() {
        // given
        val json = AssetsUtil.readFromAssets("nodeinfo.json")

        // when
        val nodeInfo = Gson().fromJson(json, NodeInfo::class.java)

        // when/then
        nodeInfo.links.size shouldBeEqualTo 1
        val link = nodeInfo.links.first()
        link.rel shouldBeEqualTo "http://nodeinfo.diaspora.software/ns/schema/2.0"
        link.href shouldBeEqualTo "https://example.org/nodeinfo/2.0"
    }
}
