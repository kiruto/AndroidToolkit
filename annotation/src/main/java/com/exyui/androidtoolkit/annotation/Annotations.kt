package com.exyui.androidtoolkit.annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class RxObservable(val value: ObserveAt = ObserveAt.BEFORE_CALL)

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION)
annotation class RxProducer(val value: ObserveAt = ObserveAt.BEFORE_CALL)

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION)
annotation class Injector


@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION)
annotation class RxDisposer(val value: ObserveAt = ObserveAt.AFTER_CALL)

enum class ObserveAt { BEFORE_CALL, AFTER_CALL }