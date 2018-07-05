package com.exyui.androidtoolkit

import android.app.Activity
import android.content.Intent

fun Activity.whenOnNewIntent(intent: Intent?) {
    setIntent(intent)
}
