package com.exyui.androidtoolkit.annotation

import com.google.auto.service.AutoService
import com.sun.source.util.Trees
import javax.annotation.processing.*
import javax.lang.model.element.TypeElement
import com.sun.tools.javac.tree.JCTree
import com.sun.tools.javac.tree.TreeTranslator
import javax.lang.model.element.ElementKind
import java.util.*
import javax.annotation.processing.RoundEnvironment

@AutoService(RxObservable::class)
class RxObservableProcessor: AbstractProcessor() {
    private lateinit var trees: Trees
    private val mCodes = mutableMapOf<String, MutableList<JCTree.JCStatement>>()
    private val mTargets = mutableMapOf<String, CodeItem>()
    private val mSources = mutableMapOf<String, PriorityQueue<CodeItem>>()

    override fun init(env: ProcessingEnvironment?) {
        super.init(env)
        trees = Trees.instance(env)
    }

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        if (annotations == null || roundEnv == null) { return false }
        if (!roundEnv.processingOver()) {
            roundEnv.rootElements
                    .filter { it.kind == ElementKind.CLASS }
                    .forEach { (trees.getTree(it) as JCTree).accept(AOPTreeTranslator()) }
        } else {
            mTargets.entries
                    .filter { it.value.level == ObserveAt.AFTER_CALL }
                    .forEach {
                        mSources[it.key]?.forEach { node ->
                            mCodes.appendList(it.key, node.med.body.stats)
                        }
                    }
            mCodes.forEach { mTargets[it.key]?.med?.body?.stats = mTargets[it.key]?.med?.body?.stats?.prependList(it.value) }
        }
        return false

//        val annotatedElements = roundEnv.getElementsAnnotatedWith(RxObservable::class.java)
//        processingEnv.messager.printMessage(NOTE, "aaaaaaaa")
//        println("aaaaaaaabb")
//        if (annotatedElements?.isEmpty() != false) return false
//
//        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME] ?: run {
//            processingEnv.messager.printMessage(ERROR, "Can't find the target directory for generated Kotlin files.")
//            return false
//        }
//
//        val generatedKtFile = kotlinFile("test.generated") {
//            for (element in annotatedElements) {
//                val typeElement = element.toTypeElementOrNull() ?: continue
//
//                property("simpleClassName") {
//                    receiverType(typeElement.qualifiedName.toString())
//                    getterExpression("this::class.java.simpleName")
//                }
//            }
//        }
//
//        File(kaptKotlinGeneratedDir, "testGenerated.kt").apply {
//            parentFile.mkdirs()
//            writeText(generatedKtFile.accept(PrettyPrinter(PrettyPrinterConfiguration())))
//        }
//
//        return true
    }

    data class CodeItem(val level: ObserveAt, val med: JCTree.JCMethodDecl);

    private inner class AOPTreeTranslator : TreeTranslator() {
        override fun visitMethodDef(jcMethod: JCTree.JCMethodDecl) {
            super.visitMethodDef(jcMethod)
            if (jcMethod.modifiers.annotations.toString().contains(RxProducer::class.java.simpleName)) {
//                val type = getValue(jcMethod, 0)
//                val id = getValue(jcMethod, 1)
                val levelType = getValue(jcMethod, 0)
                if (type == "AST.TYPE.SOURCE") {
                    val level = ObserveAt.valueOf(id)
                    val s = mSources[id]
                    if (s != null) {
                        s.add(CodeItem(level, jcMethod))
                    } else {
                        val list = PriorityQueue<CodeItem>(Comparator.comparingInt {
                            when(it.level) {
                                ObserveAt.BEFORE_CALL -> 0
                                ObserveAt.AFTER_CALL -> 1
                            }
                        })
                        list.add(CodeItem(level, jcMethod))
                        mSources[id] = list
                    }
                } else {

                }
            } else if (jcMethod.modifiers.annotations.toString().contains(Injector::class.java.simpleName)) {
                val levelType = getValue(jcMethod, 0)
                val level = if (levelType == "ObserveAt.BEFORE_CALL") ObserveAt.BEFORE_CALL else ObserveAt.AFTER_CALL
                mTargets[id] = CodeItem(level, jcMethod)
            } else if (jcMethod.modifiers.annotations.toString().contains(RxObservable::class.java.simpleName)) {

            }
        }
    }

    private fun getValue(jcMethod: JCTree.JCMethodDecl, index: Int): String {
        return jcMethod.modifiers.annotations[0].arguments[index].toString()
                .split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].trim { it <= ' ' }
    }
}

fun MutableMap<String, MutableList<JCTree.JCStatement>>.appendList(key: String, stats: List<JCTree.JCStatement>) {
    val t = this[key]
    if (t == null) {
        this[key] = stats.toMutableList()
    } else {
        t.addAll(stats)
    }
}