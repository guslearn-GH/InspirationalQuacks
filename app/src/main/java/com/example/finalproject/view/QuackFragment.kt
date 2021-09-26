package com.example.finalproject.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.finalproject.ViewModel.QuackViewModel
import com.example.finalproject.databinding.QuackFragmentBinding
import com.example.finalproject.model.DuckResponse
import com.example.finalproject.model.ServiceType
import com.example.finalproject.model.remote.HttpRequest
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

private const val TAG = "QuackFragment"
class QuackFragment(var infoChoice:Int): Fragment() {

    lateinit var binding: QuackFragmentBinding
    //lateinit var viewModel:QuackViewModel


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


        var viewModel = ViewModelProvider(
            viewModelStore,
            object: ViewModelProvider.Factory{
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return QuackViewModel()
                        as T
                }
            }
        )[QuackViewModel::class.java]

        viewModel.getData(infoChoice)
        updateImage(viewModel)
        updateMessage(viewModel)

        binding.btnNextQuack.setOnClickListener {

            infoChoice = Random().nextInt(10000)
            viewModel.getData(infoChoice)
            updateImage(viewModel)
            updateMessage(viewModel)
        }

            return binding.root
        }



    fun updateImage(viewModel: QuackViewModel){
        Glide.with(binding.root)
            .load(viewModel.getDuckDataSet().value?.let{it.url})
            .into(binding.ivDuckImg)
    }

    fun updateMessage(viewModel: QuackViewModel){
        if(infoChoice%2==0){
            binding.tvInfo.text = viewModel.getAffirmationDataSet().value?.let{ it.affirmation }
        }else{
            binding.tvInfo.text = viewModel.getAdviceDataSet().value?.let{ it.slip.advice}
        }
    }



}