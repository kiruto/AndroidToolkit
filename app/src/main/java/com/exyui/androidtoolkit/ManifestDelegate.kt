package com.exyui.androidtoolkit

import android.content.pm.PackageManager
import kotlin.reflect.KProperty

class ManifestDelegate<T>(private val key: String, private val value: T) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T? = appInstance?.let { context ->
        context.packageManager
                ?.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
                ?.metaData
                ?.let { meta ->
                    when(value) {
                        is String -> meta.getString(key, value) as? T
                        is Int -> meta.getInt(key, value) as? T
                        is Float -> meta.getFloat(key, value) as? T
                        is Double -> meta.getDouble(key, value) as? T
                        else -> meta.get(key) as? T
                    }
                }
    }
}
