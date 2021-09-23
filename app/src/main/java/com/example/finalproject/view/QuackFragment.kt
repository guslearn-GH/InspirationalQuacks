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
                    return QuackViewModel(binding)
                        as T
                }
            }
        )[QuackViewModel::class.java]

        viewModel.getData(0)

        binding.btnNextQuack.setOnClickListener{
            viewModel.getData(Random(10000).nextInt())
        }

        return binding.root
    }



}