package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Instance
import social.bigbone.api.method.InstanceMethods
import social.bigbone.rx.extensions.onErrorIfNotDisposed

class RxInstanceMethods(client: MastodonClient) {
    private val instanceMethods = InstanceMethods(client)

    fun getInstance(): Single<Instance> {
        return Single.create {
            try {
                val instance = instanceMethods.getInstance()
                it.onSuccess(instance.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
