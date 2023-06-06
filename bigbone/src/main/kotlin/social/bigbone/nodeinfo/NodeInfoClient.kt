package social.bigbone.nodeinfo

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.nodeinfo.entity.NodeInfo
import social.bigbone.nodeinfo.entity.Server

/**
 * Client to retrieve information about a server via NodeInfo, given its hostname.
 * @see <a href="https://github.com/jhass/nodeinfo">NodeInfo information</a>
 */
object NodeInfoClient {

    private val gson = Gson()
    private val client = OkHttpClient.Builder()
        .followRedirects(true)
        .build()

    /**
     * Retrieve server information.
     * @param host hostname of the server to retrieve information from
     * @return server information, including the name and version of the software running on this server
     * @throws BigBoneRequestException if server info can not be retrieved via NodeInfo for any reason
     */
    fun retrieveServerInfo(host: String): Server {
        try {
            val serverInfoUrl = getServerInfoUrl(host)
            val response = client.newCall(
                Request.Builder()
                    .url(serverInfoUrl)
                    .get()
                    .build()
            ).execute()
            return gson.fromJson(response.body?.string(), Server::class.java)
        } catch (e: Exception) {
            throw BigBoneRequestException("invalid NodeInfo response")
        }
    }

    /**
     * Get the URL to retrieve server information from.
     * @param host the hostname of the server to request information from
     * @return String containing the URL holding server information
     */
    private fun getServerInfoUrl(host: String): String {
        val response = client.newCall(
            Request.Builder()
                .url("https://$host/.well-known/nodeinfo")
                .get()
                .build()
        ).execute()

        if (!response.isSuccessful) {
            response.close()
            throw BigBoneRequestException("request for well-known NodeInfo URL unsuccessful")
        }

        val nodeInfo = gson.fromJson(response.body?.string(), NodeInfo::class.java)

        // attempt returning URL to schema 2.0 information, but fall back to any - software information exists in all schemas
        for (link in nodeInfo.links) {
            if (link.rel == "http://nodeinfo.diaspora.software/ns/schema/2.0") {
                return link.href
            }
        }
        if (nodeInfo.links.isNotEmpty()) {
            return nodeInfo.links[0].href
        }

        throw BigBoneRequestException("empty link list in well-known NodeInfo location")
    }
}
