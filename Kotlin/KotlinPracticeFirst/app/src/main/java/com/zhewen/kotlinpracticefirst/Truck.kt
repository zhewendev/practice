package com.zhewen.kotlinpracticefirst

import javax.inject.Inject


class Truck @Inject constructor() {


    fun deliver() {
        println("Truck is delivering cargo.")
    }

}

