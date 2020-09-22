package com.zhewen.kotlinpracticefirst

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

fun main(args: Array<String>) {
    val expression = "3 / 1"
    firstQuestion()
    secondQuestion()
    thirdQuestion(expression)
}

fun firstQuestion() {

    println("-------firstQuestion------")
    val arr = Array(100000) {
        it + 1
    }
    operationTime {
        println("Array average value is ${arr.average()}")
    }
    val intArray = IntArray(100000) {
        it + 1
    }
    operationTime {
        println("intArray average value is ${intArray.average()}")
    }

    val list = List(100000) { it + 1 }
    operationTime {
        println("List average value is ${list.average()}")
    }
}

fun operationTime(action: (() -> Unit)?) {
    val startTime = System.currentTimeMillis()
    action?.invoke()
    println("time spent on this operation is ${System.currentTimeMillis() - startTime}")
}

fun secondQuestion() {

    println("-------secondQuestion------")
    listOf(21, 40, 11, 33, 78)
        .asSequence()
        .filter {
            it % 3 == 0
        }
        .forEach {
            println(it)
        }
}

fun thirdQuestion(expression: String) {
    println("-------thirdQuestion------")
    val arg = expression.split(" ")
    val operators = mapOf(
        "+" to ::plus,
        "-" to ::minus,
        "*" to ::multiply,
        "/" to ::div
    )
    println("Input:$expression")
    try {
        operators[arg[1]]?.let {
            println("output:${it(arg[0].toInt(), arg[2].toInt())}")
        }
    } catch (e: Exception) {
        println("Wrong expression,please enter the correct expression${e}")
    }
}

fun plus(arg0: Int, arg1: Int): Int {
    return arg0 + arg1
}

fun minus(arg0: Int, arg1: Int): Int {
    return arg0 - arg1
}

fun multiply(arg0: Int, arg1: Int): Int {
    return arg0 * arg1
}

fun div(arg0: Int, arg1: Int): Int {
    return arg0 / arg1
}
