package social.bigbone.rx.extensions

import io.reactivex.rxjava3.core.Single

fun <T> single(f: () -> T): Single<T> {
    return Single.create {
        try {
            val result = f()
            it.onSuccess(result)
        } catch (throwable: Throwable) {
            it.onErrorIfNotDisposed(throwable)
        }
    }
}
