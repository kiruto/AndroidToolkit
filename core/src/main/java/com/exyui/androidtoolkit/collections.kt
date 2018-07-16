package com.exyui.androidtoolkit

fun <T>MutableList<T>.pop() = last().also { remove(it) }
