package com.zhewen.kotlinpracticesecond

class Student(var score:Int = 0,var grade: Int = 1) : Person() {
    override fun say(name: String) {
        println("$name, are you ok?")
    }
    fun show() {
        val result = """
            name = $name
            age = $age
            sex = $sex
            score = $score
            grade = $grade
        """.trimIndent()
        println(result)
    }
}