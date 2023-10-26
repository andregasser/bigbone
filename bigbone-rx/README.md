# bigbone-rx

**bigbone-rx** is a lightweight wrapper for [RxJava3](https://github.com/ReactiveX/RxJava/) designed to seamlessly
integrate with the [`bigbone` main module](../bigbone).

This module allows easy consumption of Mastodon endpoints as RxJava types, allowing you to work with `Single`s
and `Completable`s that wrap the methods available in the main module.

## Error Handling

**bigbone-rx** places no restrictions on your error handling approach. All encountered errors are directly passed on to
consumers. In the event that a sequence has already been disposed of, the
default [`RxJavaPlugins.onError`](https://github.com/ReactiveX/RxJava/blob/e9a422f84aea151169272505f9e7141c40b19ccb/src/main/java/io/reactivex/rxjava3/plugins/RxJavaPlugins.java#L344-L365)
handler takes over. These errors will be encapsulated within an `UndeliverableException`, with the underlying cause
being the error that couldn't reach downstream due to disposal.

Please be aware that, depending on the platform, your application may experience a crash unless you implement a default
error handler within your application. This ensures graceful handling of such scenarios.

> _Quote
from [`RxJavaPlugins.onError` documentation](https://github.com/ReactiveX/RxJava/blob/e9a422f84aea151169272505f9e7141c40b19ccb/src/main/java/io/reactivex/rxjava3/plugins/RxJavaPlugins.java#L344-L365)_
>
> Undeliverable errors are those `Observer.onError()` invocations that are not allowed to happen on the given consumer
> type (`Observer`, `Subscriber`, etc.) due to protocol restrictions because the consumer has either disposed/cancelled
> its `Disposable`/`Subscription` or has already terminated with an `onError()` or `onComplete()` signal.
>
> By default, this global error handler prints the stacktrace via `Throwable#printStackTrace()`
> and calls `java.lang.Thread.UncaughtExceptionHandler#uncaughtException(Thread, Throwable)` on the current thread.
>
> Note that on some platforms, the platform runtime terminates the current application with an error if such uncaught
> exceptions happen. In this case, it is recommended the application installs a global error handler via
> the `#setErrorHandler(Consumer)` plugin method.

Very basic example error handler:

```kotlin
RxJavaPlugins.setErrorHandler { throwable: Throwable ->
    // Handle the error here, e.g. by outputting it to System.err:
    System.err.println("An error occurred: $throwable")
}

// Now, any unhandled errors in RxJava streams will be passed to this handler.
```

