package com.exyui.androidtoolkit

import kotlin.reflect.KClass

fun switch(vararg params: Any?) = SwitchContext(*params)

fun <T>case(vararg pattern: Any?, block: () -> T?) = CaseContext(*pattern, default = false, block = block)
fun <T>default(block: () -> T?) = CaseContext(default = true, block = block)

fun <T: Any>type(content: KClass<T>) = Pattern.Type(content)
fun <T: Any>some(p: T) = Pattern.Some(p)
fun <T: Any>maybe(content: T?) = Pattern.MayBe(content)
val any = Pattern.All()
val none = Pattern.None()

sealed class Pattern(internal val content: Any?) {
    class Some<T: Any>(content: T): Pattern(content)
    class None: Pattern(null)
    class MayBe<T: Any>(content: T?): Pattern(content)
    class All: Pattern(null)
    class Type<T: Any>(t: KClass<T>): Pattern(t)
}

class SwitchContext(private vararg val params: Any?) {
    operator fun <T>invoke(vararg cases: CaseContext<T>): T? = match(*cases)
    operator fun <T>get(vararg cases: CaseContext<T>): T? = match(*cases)

    fun <T>match(vararg cases: CaseContext<T>): T? {
        var defaultImpl: CaseContext<T>? = null

        cases.forEach { case ->
            if(case.default) {
                if (defaultImpl == null) {
                    defaultImpl = case
                } else {
                    throw MatchException("")
                }
            } else if(case.size != params.size)
                return@forEach
            case.pattern.forEachIndexed { index, pattern ->
                when(pattern) {
                    is Pattern.All, params[index] -> return@forEachIndexed
                    is Pattern.Some<*> -> if(params[index] == pattern.content) return@forEachIndexed
                    is Pattern.None -> if(params[index] == null) return@forEachIndexed
                    is Pattern.MayBe<*> -> if(params[index] == pattern.content || params[index] == null) return@forEachIndexed
                    is Pattern.Type<*> -> params[index]?.takeIf { it::class == pattern.content }?.let { return@forEachIndexed }
                }
                return@forEach
            }
            return case()
        }
        return defaultImpl?.invoke() ?: null
    }
}

class CaseContext<T> internal constructor(vararg p: Any?, internal val default: Boolean, private val block: () -> T?) {
    internal val pattern = Array(p.size) { index ->
        p[index].let {
            when(it) {
                is Pattern -> it
                null -> none
                else -> some(it)
            }
        }
    }
    val size: Int
        get() = pattern.size
    operator fun invoke() = block.invoke()
}

class MatchException(msg: String): Exception(msg)
