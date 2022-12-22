package social.bigbone.rx.extensions

import io.reactivex.SingleEmitter

fun <T> SingleEmitter<T>.onErrorIfNotDisposed(t: Throwable) {
    if (!isDisposed) {
        onError(t)
    }
}
