package com.example.finalproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.finalproject.ViewModel.QuackViewModel
import com.example.finalproject.databinding.ActivityMainBinding
import com.example.finalproject.view.QuackFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    var infoCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, QuackFragment(0))
            .commit()



//        var viewModel = ViewModelProvider(
//            viewModelStore,
//            object : ViewModelProvider.Factory{
//                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                    return QuackViewModel(infoCounter)
//                            as T
//                }
//            }
//        )[QuackViewModel::class.java]



        //binding.btnNextQuack.setOnClickListener(::updateData)

    }



    private fun updateData(view: View) {
        infoCounter++
        if(infoCounter>1)
            infoCounter=0

    }
//
//    private fun getData(viewModel: QuackViewModel) {
//
//
//        Glide.with(binding.root)
//            .load(viewModel.image)
//            .into(binding.ivDuckImg)
//
//        binding.tvInfo.text = viewModel.text
//
//
//    }
}