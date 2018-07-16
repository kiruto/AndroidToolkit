package com.exyui.androidtoolkit

import kotlin.math.min

fun <T>MutableList<T>.pop() = last().also { remove(it) }
fun <T>MutableList<T>.splice(index: Int, count: Int = 0, vararg items: T): List<T> {
    val i = when {
        index in 0..(size - 1) -> index
        index >= size -> size - 1
        index in (1 - size)..-1 -> size + index
        else -> 0
    }
    return mutableListOf<T>().also { result ->
        if (count > 0) {
            for (j in i..min(i + count, size - 1)) {
                result.add(this[j])
                removeAt(j)
            }
        }
        addAll(items)
    }
}