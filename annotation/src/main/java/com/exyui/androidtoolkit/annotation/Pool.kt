package com.exyui.androidtoolkit.annotation


object Pool {
    val ObjectMap: MutableMap<Int, Element> = mutableMapOf()

    fun createElementFor(obj: Any) {
        ObjectMap[System.identityHashCode(obj)] = Element()
    }

    fun destoryElementFor(obj: Any) {
        ObjectMap.remove(System.identityHashCode(obj))
    }

    class Element {
        val subjects = mutableListOf<Any>()
    }
}