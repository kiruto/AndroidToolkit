package com.exyui.androidtoolkit

import android.os.Bundle
import kotlin.reflect.KProperty

class StateDelegate<T: Any>(private val bundle: Bundle, private val key: String, private val default: T) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T? = (bundle.get(key) as? T) ?: default

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        bundle
    }
}