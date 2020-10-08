package com.zhewen.kotlinpracticesecond

fun Person.eat(food: String) {
    println("name = ${this.name}, age = ${this.age}, sex = ${this.sex}, food = $food")
}

val Person.color: String
    get() = "yellow"

open class Person : Animal {
    var name = "Peter"
    var age = 22
    var sex = "male"
    override fun sleep() {
        println("$name sleeping...")
    }

    open fun say(name: String) {
        println("Hello $name")
    }

}

fun main() {
    val person = Person()
    person.sleep()
    person.say("Smith")
    person.eat("noodles")
    println(person.color)
    val student = Student(100,2)
    student.say("Djokovic")
    student.show()
}
