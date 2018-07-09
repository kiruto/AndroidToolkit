package com.exyui.androidtoolkit

import android.app.Activity
import android.os.Bundle
import android.util.Size
import android.util.SizeF
import java.io.Serializable
import kotlin.reflect.KProperty

class ActivityStateDelegate<T: Any>(
        activity: Activity,
        private val key: String,
        private val default: T? = null,
        private val willSet: ((newValue: T?) -> Unit)? = null,
        private val didSet: ((newValue: T?) -> Unit)? = null
) {
    private val activity by weak(activity)
    private val bundle
        get() = activity?.store

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return (bundle?.get(key) as? T) ?: default
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        bundle?.let { bundle ->
            willSet?.invoke(value)
            activity?.intent?.extras?.putBundle(KEY.BUNDLE, Bundle().apply {
                putAll(bundle)
                when(value) {
                    is Size -> bundle.putSize(key, value)
                    is SizeF -> bundle.putSizeF(key, value)
                    is Boolean -> bundle.putBoolean(key, value)
                    is Byte -> bundle.putByte(key, value)
                    is Char -> bundle.putChar(key, value)
                    is Short -> bundle.putShort(key, value)
                    is Int -> bundle.putInt(key, value)
                    is Long -> bundle.putLong(key, value)
                    is Float -> bundle.putFloat(key, value)
                    is Double -> bundle.putDouble(key, value)
                    is CharSequence -> bundle.putCharSequence(key, value)
                    is Serializable -> bundle.putSerializable(key, value)
                    is String -> bundle.putString(key, value)
                    null -> bundle.remove(key)
                }
            })
            didSet?.invoke(value)
            (activity as? StateUploadListener)?.onStateUpdated(bundle)
        }
    }
}
