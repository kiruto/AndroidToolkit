package com.exyui.androidtoolkit

import org.junit.Assert.*
import org.junit.Test

class PatternMatchTest {
    @Test
    fun test() {
        switch(1, 2, 3, 4)[
                case(1, any, 2, 4) { 0 },
                case (1, 2, 3, any) { 1 },
                case(1, type(Int::class), maybe(3), 4) { 2 },
                default { 3 }
        ].let { assertEquals(it, 1) }

        switch(1, 2, 3, 4)[
                case(1, any, 2, 4) { 0 },
                case (1, 2, 3, 5) { 1 },
                case(1, type(Int::class), maybe(3), 4) { 2 },
                default { 3 }
        ].let { assertEquals(it, 2) }

        switch(1, 2, 3, 4)[
                case(1, any, 2, 4) { 0 },
                case (1, 2, 3, 5) { 1 },
                case(1, type(Int::class), maybe(3), 5) { 2 },
                default { 3 }
        ].let { assertEquals(it, 3) }

        switch(1, 2, null, 4)[
                case(1, any, 2, 4) { 0 },
                case (1, 2, 3, 5) { 1 },
                case(1, type(Int::class), maybe(3), 4) { 2 },
                default { 3 }
        ].let { assertEquals(it, 2) }
    }
}