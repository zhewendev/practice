package com.zhewen.databindingdemo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableInt
import com.zhewen.databindingdemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        setContentView(R.layout.activity_main);
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        val user = User("西门吹雪",22)
//        binding.user = user
//        user.name.set("东方不败")
        val map = ObservableArrayMap<String, String>().apply { put("name", "东方不败") }
        binding.map = map
        val list = ObservableArrayList<String>().apply { add("22") }
        binding.list = list
        binding.key = "name"
        binding.index = 0
        val viewModel:DataViewModel = DataViewModel()
        viewModel.nameLiveData.value = "独孤求败"
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        binding.click = this
    }

    override fun onClick(v: View?) {
        Toast.makeText(this@MainActivity, "hahah",Toast.LENGTH_LONG).show()
    }
}