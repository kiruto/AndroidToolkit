package com.exyui.androidtoolkit.components

import android.app.Activity
import io.reactivex.Observable

abstract class AbstractRxActivity: Activity() {
    abstract fun main()

    fun asObservable(): Observable<Unit> {
        TODO()
    }


}