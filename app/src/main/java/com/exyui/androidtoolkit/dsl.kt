package com.exyui.androidtoolkit

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import java.lang.ref.WeakReference


fun <T> weak(value: T? = null) = WeakRefHolder(WeakReference(value))
fun <T> Context.sp(key: String, default: T, table: String = "com.exyui.androidtoolkit.COMMON") = SPHelper(this, key, default, table)
fun <T> sp(key: String, default: T, table: String = "com.exyui.androidtoolkit.COMMON") = context?.sp(key, default, table)?: throw RuntimeException("Please call initToolKit(context) before use this")
fun <T: Any> Fragment.args(key: String, default: T, didSet: ((newValue: T?) -> Unit)? = null, willSet: ((newValue: T?) -> Unit)? = null): BundleDelegate<T> {
    return (arguments ?: Bundle().apply { arguments = this })
            .let { BundleDelegate(it, key, default, willSet, didSet) }
}
fun <T: Any> android.app.Activity.state(key: String, default: T, didSet: ((newValue: T?) -> Unit)? = null, willSet: ((newValue: T?) -> Unit)? = null): BundleDelegate<T> {
    return (intent.extras ?: Bundle().apply { intent.putExtras(this) })
            .let { BundleDelegate(it, key, default, willSet, didSet) }
}
fun <T: Any> AppCompatActivity.state(key: String, default: T, didSet: ((newValue: T?) -> Unit)? = null, willSet: ((newValue: T?) -> Unit)? = null): BundleDelegate<T> {
    return (intent.extras ?: Bundle().apply { intent.putExtras(this) })
            .let { BundleDelegate(it, key, default, willSet, didSet) }
}
