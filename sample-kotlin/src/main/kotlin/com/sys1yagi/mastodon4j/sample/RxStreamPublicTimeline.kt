package com.sys1yagi.mastodon4j.sample

import com.sys1yagi.mastodon4j.rx.RxStreaming
import io.reactivex.schedulers.Schedulers

object RxStreamPublicTimeline {
    private const val TEN_SECONDS = 10_000L

    @JvmStatic fun main(args: Array<String>) {
        val instanceName = args[0]
        val credentialFilePath = args[1]

        // require authentication even if public streaming
        val client = Authenticator.appRegistrationIfNeeded(instanceName, credentialFilePath, true)

        val streaming = RxStreaming(client)

        println("init")
        val disposable = streaming.localPublic()
                .subscribeOn(Schedulers.io())
                .subscribe {
                    println("${it.createdAt}: ${it.account?.acct} < ${it.content.replace("<.*?>".toRegex(), "")}")
                }
        Thread.sleep(TEN_SECONDS)
        disposable.dispose()
    }
}
