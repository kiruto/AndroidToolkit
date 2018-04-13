package com.exyui.androidtoolkit

import android.content.Context

internal var context by weak<Context>()

fun initToolKit(ctx: Context) {
    context = ctx.applicationContext
}