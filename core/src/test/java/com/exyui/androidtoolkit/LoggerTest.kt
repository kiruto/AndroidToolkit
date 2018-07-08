package com.exyui.androidtoolkit

import junit.framework.TestCase
import org.junit.Test

class LoggerTest: TestCase() {
    override fun setUp() {
        addLogger(object: ToolKitLogger {
            override fun d(tag: String, msg: String) {
                println("d")
                println(tag)
                println(msg)
            }

            override fun e(tag: String, msg: String) {
                println("e")
                println(tag)
                println(msg)
            }
        })
    }

    @Test fun testLogger() {
        trace("test")
        errorlog("test")
    }
}