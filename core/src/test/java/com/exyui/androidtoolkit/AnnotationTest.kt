package com.exyui.androidtoolkit

import com.exyui.androidtoolkit.annotation.RxObservable
import org.junit.Test

class AnnotationTest {
    @Test fun testSimple() {
        ExampleObservable()
    }

    @RxObservable
    class ExampleObservable {

    }
}