package social.bigbone.rx

import io.reactivex.Single
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
    val publicMethod = Public(client)

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

    fun getLocalPublic(range: Range = Range()): Single<Pageable<Status>> {
        return single {
            publicMethod.getLocalPublic(range).execute()
        }
    }

    fun getFederatedPublic(range: Range = Range()): Single<Pageable<Status>> {
        return single {
            publicMethod.getFederatedPublic(range).execute()
        }
    }

    fun getLocalTag(tag: String, range: Range = Range()): Single<Pageable<Status>> {
        return single {
            publicMethod.getLocalTag(tag, range).execute()
        }
    }

    fun getFederatedTag(tag: String, range: Range = Range()): Single<Pageable<Status>> {
        return single {
            publicMethod.getFederatedTag(tag, range).execute()
        }
    }
}
