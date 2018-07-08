package com.exyui.androidtoolkit

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import java.lang.ref.WeakReference


fun <T> weak(value: T? = null) = WeakRefHolder(WeakReference(value))
fun <T> manifest(key: String, defaultValue: T) = ManifestDelegate(key, defaultValue)
fun <T> Context.sp(key: String, default: T, table: String = "com.exyui.androidtoolkit.COMMON") = SPHelper(key, default, this, table)
fun <T> sp(key: String, default: T, table: String = "com.exyui.androidtoolkit.COMMON") = appInstance?.sp(key, default, table)?: throw RuntimeException("Please call initToolKit(context) before use this")
fun <T: Any> Fragment.args(key: String, default: T, didSet: ((newValue: T?) -> Unit)? = null, willSet: ((newValue: T?) -> Unit)? = null): BundleDelegate<T> {
    return (arguments ?: Bundle().apply { arguments = this })
            .let { BundleDelegate(it, key, default, willSet, didSet) }
}
fun <T: Any> android.app.Activity.state(key: String, default: T, didSet: ((newValue: T?) -> Unit)? = null, willSet: ((newValue: T?) -> Unit)? = null): BundleDelegate<T> {
    return (intent.extras ?: Bundle().apply { intent.putExtras(this) })
            .let { BundleDelegate(it, key, default, willSet, didSet) }
}

fun <T1: Any, T2: Any, R: Any> unwrap(p1: T1?, p2: T2?, block: (T1, T2)->R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}
fun <T1: Any, T2: Any, T3: Any, R: Any> unwrap(p1: T1?, p2: T2?, p3: T3?, block: (T1, T2, T3)->R?): R? {
    return if (p1 != null && p2 != null && p3 != null) block(p1, p2, p3) else null
}
fun <T1: Any, T2: Any, T3: Any, T4: Any, R: Any> unwrap(p1: T1?, p2: T2?, p3: T3?, p4: T4?, block: (T1, T2, T3, T4)->R?): R? {
    return if (p1 != null && p2 != null && p3 != null && p4 != null) block(p1, p2, p3, p4) else null
}
fun <T1: Any, T2: Any, T3: Any, T4: Any, T5: Any, R: Any> unwrap(p1: T1?, p2: T2?, p3: T3?, p4: T4?, p5: T5?, block: (T1, T2, T3, T4, T5)->R?): R? {
    return if (p1 != null && p2 != null && p3 != null && p4 != null && p5 != null) block(p1, p2, p3, p4, p5) else null
}

fun <T : Any> tryOrNull(block: () -> T?): T? {
    return try {
        block()
    } catch (e: Exception) {
        trace(e)
        null
    }
}

fun <T: Any, K: Any> K.tryOrNull(block: K.() -> T?): T? {
    return try {
        block()
    } catch (e: Exception) {
        trace(e)
        null
    }
}

private val logger = mutableListOf<ToolKitLogger>()

fun addLogger(l: ToolKitLogger) {
    logger.add(l)
}

private fun logd(tag: String, msg: String) {
    for (s in logger) {
        s.d(tag, msg)
    }
}

private fun loge(tag: String, msg: String) {
    for (s in logger) {
        s.e(tag, msg)
    }
}
fun trace(message: Any?) {
    logd(LOG_TAG, "${getStackElementTag(Throwable().stackTrace[1])}\t${when(message) {
        is Throwable -> message
        else -> "$message"
    }}")
}

fun errorlog(message: Any?) {
    loge(LOG_TAG, "${getStackElementTag(Throwable().stackTrace[1])}\t${when(message) {
        is Throwable -> message
        else -> "$message"
    }}")
}

private fun getStackElementTag(e: StackTraceElement) = "${e.methodName} (${e.fileName}:${e.lineNumber})"
