package social.bigbone.rx.extensions

import io.reactivex.CompletableEmitter

fun CompletableEmitter.onErrorIfNotDisposed(t: Throwable) {
    if (!isDisposed) {
        onError(t)
    }
}
