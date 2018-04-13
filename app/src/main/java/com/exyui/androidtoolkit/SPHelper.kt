package com.exyui.androidtoolkit

import android.content.Context
import kotlin.reflect.KProperty

class SPHelper<T>(
        context: Context,
        private val key: String,
        private val defaultValue: T,
        table: String = "com.exyui.androidtoolkit.COMMON",
        private val sep: String = "{%SEP%}") {

    private val context by weak(context)
    private val sp = context.getSharedPreferences(table, Context.MODE_PRIVATE)

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        @Suppress("UNCHECKED_CAST")
        return when(defaultValue) {
            is Int -> sp.getInt(key, defaultValue) as? T
            is Boolean -> sp.getBoolean(key, defaultValue) as? T
            is Float -> sp.getFloat(key, defaultValue) as? T
            is Long -> sp.getLong(key, defaultValue) as? T
            is String -> sp.getString(key, defaultValue) as? T
            is List<*> -> sp.getString(key, defaultValue.joinToString(sep))?.split(sep) as? T
            else -> null
        } ?: defaultValue
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        sp.edit()
                .also { sp ->
                    when(value) {
                        null -> sp.remove(key)
                        is Int -> sp.putInt(key, value)
                        is Boolean -> sp.putBoolean(key, value)
                        is Float -> sp.putFloat(key, value)
                        is Long -> sp.putLong(key, value)
                        is String -> sp.putString(key, value)
                        is List<*> -> sp.putString(key, value.joinToString(sep))
                        else -> sp.putString(key, "$value")
                    }
                }
                .apply()
    }
}