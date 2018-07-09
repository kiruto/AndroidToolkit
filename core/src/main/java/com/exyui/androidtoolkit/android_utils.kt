package com.exyui.androidtoolkit

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment

internal var appInstance by weak<Application>()

val Activity.state
    get() = intent.extras ?: run {
        Bundle().apply {
            intent.putExtras(this)
        }
    }

internal fun Application.install() {
    appInstance = this
    registerActivityLifecycleCallbacks(object: Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onActivityStarted(activity: Activity?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onActivityResumed(activity: Activity?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onActivityPaused(activity: Activity?) {

        }

        override fun onActivityStopped(activity: Activity?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onActivityDestroyed(activity: Activity?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

        }
    })
}

val Fragment.state
    get() = arguments ?: run {
        Bundle().apply {
            arguments = this
        }
    }
