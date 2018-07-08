package com.exyui.androidtoolkit

import android.app.Application
import android.content.Context

internal const val LOG_TAG = "ToolKit"

fun initToolKit(ctx: Context) {
    (ctx.applicationContext as? Application)?.let(Application::install)
}

interface ToolKitLogger {
    fun d(tag: String, msg: String)
    fun e(tag: String, msg: String)
}