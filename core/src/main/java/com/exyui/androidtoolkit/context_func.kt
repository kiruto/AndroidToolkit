package com.exyui.androidtoolkit

import android.app.Activity
import android.content.Intent
import android.os.Bundle


var Activity.store
    get() = intent.extras
            ?: Bundle().apply {
                    intent.putExtras(this)
            }
            .getBundle(KEY.BUNDLE)
            ?: Bundle().apply {
                intent.extras.putBundle(KEY.BUNDLE, this)
            }

    internal set(new) {
        intent.extras
                ?: Bundle().apply {
                    intent.putExtras(this)
                }.apply {
                    putBundle(KEY.BUNDLE, new)
                }
        if (this is StateUploadListener) {
            onStateUpdated(new)
        }
    }

fun <T: Any>Activity.state(key: String, default: T) = ActivityStateDelegate(this, key, default)
fun <T: Any>Activity.state(key: String) = ActivityStateDelegate<T>(this, key, null)

fun Activity.whenOnNewIntent(intent: Intent?) {
    setIntent(intent)
}

interface StateUploadListener {
    fun onStateUpdated(state: Bundle)
}
