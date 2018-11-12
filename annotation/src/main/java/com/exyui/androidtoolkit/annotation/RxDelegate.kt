package com.exyui.androidtoolkit.annotation

object RxDelegate {
    operator fun invoke(obj: Any): RxDelegate {
        Pool.createElementFor(obj)
        return this
    }
}