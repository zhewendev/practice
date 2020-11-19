package com.zhewen.kotlinpracticeseventh

fun main(){

}

fun <T>fill(array: Array<in T>, t:T): Array<in T>{
    val arr = arrayOfNulls<Any>(array.size + 1)
    for (i in array.indices){
        arr[i] = array[i]
    }
    arr[array.size] = t
    return arr
}

fun <T>copy(arr1:Array<T>,array2: Array<T>){

}