package com.zhewen.kotlinpracticefourth

import java.io.File
import java.lang.StringBuilder

fun main() {
    val htmlContent = html {
        head {
            meta { "charset"("UTF-8") }
        }
        body {
            div {
                "style"(
                        """
                    width: 200px; 
                    height: 200px; 
                    line-height: 200px; 
                    background-color: #C9394A;
                    text-align: center
                    """.trimIndent()
                )
                span {
                    "style"(
                            """
                        color: white;
                        font-family: Microsoft YaHei
                        """.trimIndent()
                    )
                    +"Hello HTML DSL!!"
                }
            }
        }
    }.toString()
    File("kotlin.html").writeText(htmlContent)
}

interface Element {
    fun render(build: StringBuilder, indent: String)
}

class TextElement(val text: String) : Element{
    override fun render(build: StringBuilder, indent: String) {
        build.append("$indent$text\n")
    }
}

/**
 * 标签基类
 */
abstract class Tag(val name: String) : Element{

    val children = arrayListOf<Element>()
    val attributes = hashMapOf<String, String>()

    protected fun <T: Element> initTag(tag: T, init:T.() -> Unit) : T {
        tag.init()
        children.add(tag)
        return tag
    }

    override fun render(build: StringBuilder, indent: String) {
        build.append("$indent<$name ${renderAttributes()}>\n")
        for (c in children) {
            c.render(build, "$indent ")
        }
        build.append("$indent</$name>\n")
    }

    private fun renderAttributes(): String{
        val build = StringBuilder()
        for ((attr, value) in attributes) {
            build.append(" $attr=\"$value\"")
        }
        return build.toString()
    }

    override fun toString(): String {
        val build = StringBuilder()
        render(build,"")
        return build.toString()
    }
}

abstract class TagWithName(name:String) : Tag(name) {
    operator fun String.unaryPlus() {
        children.add(TextElement(this))
    }

    operator fun String.invoke(value: String) {
        attributes[this] = value
    }
}

class HTML: TagWithName("html") {
    fun head(init: Head.() -> Unit) = initTag(Head(),init)
    fun body(init: Body.() -> Unit) = initTag(Body(),init)
}

class Head() : TagWithName("head") {
    fun meta(init: Meta.() -> Unit) = initTag(Meta(), init)
}
abstract class BodyTag(name: String): TagWithName(name) {
    fun div(init: Div.() -> Unit) = initTag(Div(),init)
    fun span(init:Span.() -> Unit) = initTag(Span(), init)

}
class Body: BodyTag("body")
class Div : BodyTag("div")
class Span: BodyTag("span")
class Meta: BodyTag("meta")

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()
    html.init()
    return html
}






