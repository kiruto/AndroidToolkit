package com.exyui.androidtoolkit

import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

class WeakRefHolder<T>(private var value: WeakReference<T?>) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return value.get()
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        this.value = WeakReference(value)
    }
}
