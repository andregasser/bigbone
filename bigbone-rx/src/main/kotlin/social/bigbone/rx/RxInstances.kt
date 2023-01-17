package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Instance
import social.bigbone.api.method.Instances
import social.bigbone.rx.extensions.onErrorIfNotDisposed

class RxInstances(client: MastodonClient) {
    private val instancesMethod = Instances(client)

    fun getInstance(): Single<Instance> {
        return Single.create {
            try {
                val instance = instancesMethod.getInstance()
                it.onSuccess(instance.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
