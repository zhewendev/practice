package com.zhewen.databindingdemo

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataViewModel:ViewModel() {
    var nameLiveData = MutableLiveData<String>()

    fun click(view: View) {
        Log.d("haha","hahah")
    }

}