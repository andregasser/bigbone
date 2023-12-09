package social.bigbone.sample

import io.reactivex.rxjava3.schedulers.Schedulers
import social.bigbone.MastodonClient
import social.bigbone.api.MastodonApiEvent
import social.bigbone.api.TechnicalEvent
import social.bigbone.rx.RxStreamingMethods
import java.time.Duration

object RxStreamPublicTimeline {

    @JvmStatic
    fun main(args: Array<String>) {
        val instanceName = args[0]
        val accessToken = args[1]

        // require authentication even if public streaming
        val client = MastodonClient.Builder(instanceName)
            .accessToken(accessToken)
            .build()
        val streaming = RxStreamingMethods(client)

        println("init")
        val disposable = streaming.federatedPublic(
            onlyMedia = false
        )
            .subscribeOn(Schedulers.io())
            .subscribe(
                /* onNext = */ {
                    when (it) {
                        is TechnicalEvent -> println("Technical event: $it")
                        is MastodonApiEvent -> println("Mastodon API event: $it")
                    }
                },
                /* onError = */ ::println,
                /* onComplete = */ { println("onComplete") }
            )

        Thread.sleep(Duration.ofSeconds(10).toSeconds())
        disposable.dispose()
    }
}
