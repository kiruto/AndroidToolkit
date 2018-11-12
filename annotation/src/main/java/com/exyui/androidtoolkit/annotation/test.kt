package com.exyui.androidtoolkit.annotation

import androidx.test.InstrumentationRegistry.getArguments
import com.sun.tools.javac.tree.JCTree
import javax.swing.UIManager.put
import android.R.attr.level
import java.util.Comparator.comparingInt
import com.sun.tools.javac.tree.TreeTranslator
import jdk.nashorn.internal.objects.NativeArray.forEach
import javax.lang.model.element.ElementKind
import com.sun.tools.javac.util.BaseFileManager.getKind
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement
import com.sun.source.util.Trees
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.lang.model.SourceVersion
import javax.annotation.processing.SupportedSourceVersion
import com.google.auto.service.AutoService
import java.util.*


@AutoService(Processor::class)//自动生成 javax.annotation.processing.IProcessor 文件
@SupportedSourceVersion(SourceVersion.RELEASE_8)//java版本支持
@SupportedAnnotationTypes("com.easy.moduler.annotation.AST")//注意替换成你自己的注解名
class ASTProcessor : AbstractProcessor() {
    private var trees: Trees? = null
    private val mCodes = HashMap()
    private val mTargets = HashMap()
    private val mSources = HashMap()

    @Synchronized
    fun init(env: ProcessingEnvironment) {
        super.init(env)
        trees = Trees.instance(env)
    }

    fun process(set: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        if (!roundEnv.processingOver()) {
            roundEnv.rootElements.stream()
                    .filter { it -> it.kind == ElementKind.CLASS }
                    .forEach { it -> (trees!!.getTree(it) as JCTree).accept(AOPTreeTranslator()) }
        } else {
            mTargets.entrySet().stream()
                    .filter({ it -> it.getValue().level === AST.LEVEL.AFTER })
                    .forEach { it -> mSources.get(it.getKey()).forEach { node -> it.getValue().med.body.stats = it.getValue().med.body.stats.appendList(node.med.body.stats) } }

            mTargets.entrySet().stream()
                    .filter({ it -> it.getValue().level === AST.LEVEL.BEFORE })
                    .forEach { it ->
                        mSources.get(it.getKey())
                                .forEach { node ->
                                    val list: List<JCTree.JCStatement>
                                    if (mCodes.get(it.getKey()) != null)
                                        list = mCodes.get(it.getKey()).appendList(node.med.body.stats)
                                    else
                                        list = List.from(node.med.body.stats)
                                    mCodes.put(it.getKey(), list)
                                }
                    }
            mCodes.forEach({ key, value -> mTargets.get(key).med.body.stats = mTargets.get(key).med.body.stats.prependList(value) })
        }
        return false
    }

    internal inner class CodeItem(var level: Int//优先级
                                  , var med: JCTree.JCMethodDecl//方法体
    )

    private inner class AOPTreeTranslator : TreeTranslator() {
        override fun visitMethodDef(jcMethod: JCTree.JCMethodDecl) {
            super.visitMethodDef(jcMethod)
            if (jcMethod.modifiers.annotations.toString().contains(AST::class.java!!.getSimpleName())) {
                val type = getValue(jcMethod, 0)
                val id = getValue(jcMethod, 1)
                val levelType = getValue(jcMethod, 2)
                if (type == "AST.TYPE.SOURCE") {
                    val level = Integer.parseInt(levelType)
                    if (mSources.get(id) != null) {
                        mSources.get(id).add(CodeItem(level, jcMethod))
                    } else {
                        val list = PriorityQueue(comparingInt { p -> p.level })
                        list.add(CodeItem(level, jcMethod))
                        mSources.put(id, list)
                    }
                } else {
                    val level = if (levelType == "AST.LEVEL.BEFORE") AST.LEVEL.BEFORE else AST.LEVEL.AFTER
                    mTargets.put(id, CodeItem(level, jcMethod))
                }
            }
        }
    }

    private fun getValue(jcMethod: JCTree.JCMethodDecl, index: Int): String {
        return jcMethod.modifiers.annotations[0].arguments[index].toString().split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].trim { it <= ' ' }
    }
}

