package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Instance
import social.bigbone.api.entity.Results
import social.bigbone.api.entity.Status
import social.bigbone.api.method.Public
import social.bigbone.rx.extensions.onErrorIfNotDisposed
import social.bigbone.rx.extensions.single

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

    fun getSearch(query: String, resolve: Boolean = true): Single<Results> {
        return Single.create {
            try {
                val results = publicMethod.getSearch(query, resolve)
                it.onSuccess(results.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun getPublic(
        range: Range = Range(),
        statusOrigin: Public.StatusOrigin = Public.StatusOrigin.LOCAL_AND_REMOTE
    ): Single<Pageable<Status>> {
        return single {
            publicMethod.getPublic(range, statusOrigin).execute()
        }
    }

    fun getTag(
        tag: String,
        range: Range = Range(),
        statusOrigin: Public.StatusOrigin = Public.StatusOrigin.LOCAL_AND_REMOTE
    ): Single<Pageable<Status>> {
        return single {
            publicMethod.getTag(tag, range, statusOrigin).execute()
        }
    }
}
