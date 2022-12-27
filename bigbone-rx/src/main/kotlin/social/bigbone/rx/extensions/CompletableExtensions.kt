package social.bigbone.rx.extensions

import io.reactivex.rxjava3.core.CompletableEmitter

fun CompletableEmitter.onErrorIfNotDisposed(t: Throwable) {
    if (!isDisposed) {
        onError(t)
    }
}
