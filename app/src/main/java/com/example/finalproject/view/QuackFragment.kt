package com.example.finalproject.view

import android.media.Image
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.finalproject.QuackApplication
import com.example.finalproject.QuackApplication.Companion.quackDao
import com.example.finalproject.R
import com.example.finalproject.ViewModel.QuackViewModel
import com.example.finalproject.databinding.QuackFragmentBinding
import com.example.finalproject.model.DuckResponse

import com.example.finalproject.model.ServiceType
import com.example.finalproject.model.local.Quack
import com.example.finalproject.model.local.QuackDatabase

import com.example.finalproject.model.remote.HttpRequest
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
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

        binding.btnBackQuack.visibility = View.INVISIBLE


        viewModel = ViewModelProvider(
            viewModelStore,
            object: ViewModelProvider.Factory{
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return QuackViewModel()
                        as T
                }
            }
        )[QuackViewModel::class.java]

        binding.btnNextQuack.setOnClickListener {

            infoChoice = Random().nextInt(10000)
            viewModel.readingOldQuacks = false
            binding.btnBackQuack.visibility = View.VISIBLE
            binding.ivDuckImg.visibility = View.VISIBLE
            binding.tvInfo.visibility = View.VISIBLE

            binding.ivInsp.visibility = View.INVISIBLE
            binding.ivQck.visibility = View.INVISIBLE
            viewModel.getData()
            updateImage()
            updateMessage()
            insertNewQuack()

        }

        binding.btnBackQuack.setOnClickListener{
            readOldQuack()
            updateImage()
            updateMessage()
        }

        viewModel.getData()
            return binding.root
        }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.deleteAll(binding.root.context)
    }



    fun updateImage(){
        if(viewModel.readingOldQuacks){
            Glide.with(binding.root)
                .load(viewModel.getQuackDataSet().value?.let{it.Image})
                .into(binding.ivDuckImg)

        }else {
            Glide.with(binding.root)
                .load(viewModel.getDuckDataSet().value?.let { it.url })
                .into(binding.ivDuckImg)
        }
    }

    fun updateMessage(){

        if(viewModel.readingOldQuacks){
            binding.tvInfo.text = viewModel.getQuackDataSet().value?.let { it.Message }
        }else {
            if (infoChoice % 2 == 0) {
                binding.tvInfo.text =
                    viewModel.getAffirmationDataSet().value?.let { it.affirmation }
            } else {
                binding.tvInfo.text = viewModel.getAdviceDataSet().value?.let { it.slip.advice }
            }
        }
    }

    fun readOldQuack(){
        viewModel.read(binding.root.context)

    }

    fun insertNewQuack(){
        viewModel.insert(binding.root.context)
    }




}