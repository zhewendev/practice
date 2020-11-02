package com.zhewen.kotlinpracticefourth

import java.io.File

fun main() {
    val htmlContent = html {
        head {
            "meta" { "charset"("UTF-8") }
        }
        body {
            "div" {
                "style"(
                    """
                    width: 200px;
                    height: 200px;
                    line-height: 200px;
                    background-color: #C9394A;
                    text-align: center
                    """.trimIndent()
                )
                "span" {
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
    }.render()

    File("kotlin.html").writeText(htmlContent)
}

interface Node {
    fun render(): String
}

class StringNode(val content: String) : Node {
    override fun render(): String {
        return content
    }
}

class BlockNode(val name: String) : Node {

    val children = ArrayList<Node>()
    val properties = HashMap<String, Any>()

    override fun render(): String {
        return "<$name ${
            properties.map { "${it.key}='${it.value}'" }.joinToString(" ")
        }>\n${children.joinToString("") { it.render() }}\n</$name>"
    }

    operator fun String.invoke(block: BlockNode.() -> Unit): Node {
        val node = BlockNode(this)
        node.block()
        this@BlockNode.children += node
        return node
    }

    operator fun String.invoke(value: Any) {
        this@BlockNode.properties[this] = value
    }

    operator fun String.unaryPlus() {
        this@BlockNode.children += StringNode(this)
    }

}

fun html(block: BlockNode.() -> Unit): Node {
    val html = BlockNode("html")
    html.block()
    return html
}

fun BlockNode.head(block: BlockNode.() -> Unit): Node {
    val head = BlockNode("head")
    head.block()
    this.children += head
    return head
}

fun BlockNode.body(block: BlockNode.() -> Unit): Node {
    val body = BlockNode("body")
    body.block()
    this.children += body
    return body
}










