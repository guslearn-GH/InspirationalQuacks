package com.example.finalproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.finalproject.ViewModel.QuackViewModel
import com.example.finalproject.databinding.QuackFragmentBinding
import java.util.*

private const val TAG = "QuackFragment"
class QuackFragment(var infoChoice:Int): Fragment() {

    lateinit var binding: QuackFragmentBinding
    lateinit var viewModel:QuackViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = QuackFragmentBinding.inflate(
            inflater,
            container,
            false
        )



        viewModel = ViewModelProvider(
            viewModelStore,
            object: ViewModelProvider.Factory{
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return QuackViewModel(binding)
                        as T
                }
            }
        )[QuackViewModel::class.java]

//        viewModel.getData(infoChoice)
//        updateImage(viewModel)

        binding.btnNextQuack.setOnClickListener(::updateData)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getData(infoChoice)
        //updateImage(viewModel)
        //updateMessage(viewModel)
    }


    fun updateData(view:View){
        //save quacks here
        infoChoice = Random().nextInt(10000)
        viewModel.getData(infoChoice)
    }

    fun updateImage(quackvm: QuackViewModel){
        Glide.with(binding.root)
            .load(quackvm.getQuackImage())
            .into(binding.ivDuckImg)
    }

    fun updateMessage(quackvm: QuackViewModel){
        binding.tvInfo.text = quackvm.getQuackMessage()
    }



}