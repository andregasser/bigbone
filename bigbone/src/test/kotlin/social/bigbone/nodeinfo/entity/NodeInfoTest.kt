package social.bigbone.nodeinfo.entity

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import social.bigbone.JSON_SERIALIZER
import social.bigbone.api.exception.ServerInfoRetrievalException
import social.bigbone.api.exception.ServerInfoUrlRetrievalException
import social.bigbone.nodeinfo.NodeInfoClient
import social.bigbone.testtool.AssetsUtil

class NodeInfoTest {

    @Test
    fun deserialize() {
        // given
        val json = AssetsUtil.readFromAssets("nodeinfo.json")

        // when
        val nodeInfo: NodeInfo = JSON_SERIALIZER.decodeFromString(json)

        // when/then
        nodeInfo.links.size shouldBeEqualTo 1
        val link = nodeInfo.links.first()
        link.rel shouldBeEqualTo "http://nodeinfo.diaspora.software/ns/schema/2.0"
        link.href shouldBeEqualTo "https://example.org/nodeinfo/2.0"
    }

    @Test
    fun retrieveServerInfoWithOverloads() {
        val mockWebServer = MockWebServer().apply {
            start()
        }

        try {
            val schemaVersion = "1.0.0"
            val softwareName = "name"
            val softwareVersion = "1.0.0"
            val nodeInfoResponseBody = "{\"links\":[{\"href\":\"${mockWebServer.url("")}\",\"rel\":\"http://nodeinfo.diaspora.software/ns/schema/2.0\"}]}"
            val serverResponseBody = "{\"version\":\"$schemaVersion\", \"software\":{ \"name\":\"$softwareName\", \"version\":\"$softwareVersion\"}}"

            mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(nodeInfoResponseBody))
            mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(serverResponseBody))

            val server = NodeInfoClient.retrieveServerInfo(host = mockWebServer.url("").toUrl().host, scheme = "http", port = mockWebServer.port)
            assertNotNull(server)
            assertNotNull(server?.software)
            assertEquals(schemaVersion, server?.schemaVersion)
            assertEquals(softwareName, server?.software?.name)
            assertEquals(softwareVersion, server?.software?.version)
        } finally {
            mockWebServer.shutdown()
        }
    }

    @Test
    fun retrieveServerInfoWithOverloadsUnsuccessfulNodeInfoResponse() {
        val mockWebServer = MockWebServer().apply {
            start()
        }

        try {
            val schemaVersion = "1.0.0"
            val softwareName = "name"
            val softwareVersion = "1.0.0"
            val nodeInfoResponseBody = "{\"links\":[{\"href\":\"${mockWebServer.url("")}\",\"rel\":\"http://nodeinfo.diaspora.software/ns/schema/2.0\"}]}"
            val serverResponseBody = "{\"version\":\"$schemaVersion\", \"software\":{ \"name\":\"$softwareName\", \"version\":\"$softwareVersion\"}}"

            mockWebServer.enqueue(MockResponse().setResponseCode(400).setBody(nodeInfoResponseBody))
            mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(serverResponseBody))

            assertThrows<ServerInfoUrlRetrievalException> {
                NodeInfoClient.retrieveServerInfo(host = mockWebServer.url("").toUrl().host, scheme = "http", port = mockWebServer.port)
            }
        } finally {
            mockWebServer.shutdown()
        }
    }

    @Test
    fun retrieveServerInfoWithOverloadsUnsuccessfulServerInfoResponse() {
        val mockWebServer = MockWebServer().apply {
            start()
        }

        try {
            val schemaVersion = "1.0.0"
            val softwareName = "name"
            val softwareVersion = "1.0.0"
            val nodeInfoResponseBody = "{\"links\":[{\"href\":\"${mockWebServer.url("")}\",\"rel\":\"http://nodeinfo.diaspora.software/ns/schema/2.0\"}]}"
            val serverResponseBody = "{\"version\":\"$schemaVersion\", \"software\":{ \"name\":\"$softwareName\", \"version\":\"$softwareVersion\"}}"

            mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(nodeInfoResponseBody))
            mockWebServer.enqueue(MockResponse().setResponseCode(400).setBody(serverResponseBody))

            assertThrows<ServerInfoRetrievalException> {
                NodeInfoClient.retrieveServerInfo(host = mockWebServer.url("").toUrl().host, scheme = "http", port = mockWebServer.port)
            }
        } finally {
            mockWebServer.shutdown()
        }
    }
}
