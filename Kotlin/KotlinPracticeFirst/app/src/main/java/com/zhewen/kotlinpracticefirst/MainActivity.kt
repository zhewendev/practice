package com.zhewen.kotlinpracticefirst

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject
import kotlin.properties.Delegates

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var truck: Truck

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        truck.deliver()
    }
}


class User {
    var name: String by Delegates.observable("初始值") {
            prop, old, new ->
        println("旧值：$old -> 新值：$new")
    }

    fun fail() {
        throw RuntimeException("kkk")
    }
}


//fun main(args: Array<String>) {
//    val user = User()
//    user.name = "第一次赋值"
//    user.name = "第二次赋值"
//}

//调用
 	// User		User2

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
        println("Wrong expression,please enter the correct expression ${e}")
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
