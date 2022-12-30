package social.bigbone.rx.extensions

import io.reactivex.rxjava3.core.SingleEmitter

fun <T> SingleEmitter<T>.onErrorIfNotDisposed(t: Throwable) {
    if (!isDisposed) {
        onError(t)
    }
}
