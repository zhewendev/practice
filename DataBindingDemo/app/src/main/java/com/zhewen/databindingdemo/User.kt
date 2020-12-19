package com.zhewen.databindingdemo

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt


//class User(name: String, age: Int) : BaseObservable() {
//    @field:Bindable
//    var name: String = name
//        set(value) {
//            field = value
//            notifyPropertyChanged(BR.name)
//
//        }
//
//    @get:Bindable
//    var age: Int = age
//        set(value) {
//            field = value
//            notifyPropertyChanged(BR.age)
//        }
//
//}

class User(name: String, age: Int){
    var age = ObservableInt()
    var name = ObservableField<String>()
    init {
        this.age.set(age)
        this.name.set(name)
    }
}
