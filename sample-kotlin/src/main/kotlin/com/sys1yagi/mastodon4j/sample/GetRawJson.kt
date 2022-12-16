package com.sys1yagi.mastodon4j.sample

import com.sys1yagi.mastodon4j.api.method.Public
import kotlinx.coroutines.runBlocking

object GetRawJson {
    @JvmStatic
    fun main(args: Array<String>) {
        val instanceName = args[0]
        val credentialFilePath = args[1]
        val client = Authenticator.appRegistrationIfNeeded(instanceName, credentialFilePath)
        runBlocking {
            val public = Public(client)
            public.getLocalPublic().doOnJson {
                println(it)
            }.execute()
        }
    }
}
