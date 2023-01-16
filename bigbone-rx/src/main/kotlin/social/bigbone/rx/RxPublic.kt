package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Instance
import social.bigbone.api.entity.Results
import social.bigbone.api.method.Public
import social.bigbone.rx.extensions.onErrorIfNotDisposed

class RxPublic(client: MastodonClient) {
    private val publicMethod = Public(client)

    fun getInstance(): Single<Instance> {
        return Single.create {
            try {
                val instance = publicMethod.getInstance()
                it.onSuccess(instance.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun search(query: String, resolve: Boolean = true): Single<Results> {
        return Single.create {
            try {
                val results = publicMethod.search(query, resolve)
                it.onSuccess(results.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
