package com.example.finalproject.model.repository

import android.content.Context
import android.util.Log
import com.example.finalproject.ViewModel.QuackViewModel

import com.example.finalproject.model.ServiceType
import com.example.finalproject.model.remote.HttpRequest
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private const val TAG = "Repository"
class QuackRepository(private val viewModel: QuackViewModel){

    fun getQuack(){

        viewModel.coroutineScope.launch {

            HttpRequest.getService(ServiceType.Duck)
                .getDuckPicture()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewModel.setDuckPicture(it)
                }, {
                    Log.d(TAG, "getData: Failed Duck")
                })

            HttpRequest.getService(ServiceType.Affirmation)
                .getAffirmation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewModel.setAffirmation(it)
                }, {
                    Log.d(TAG, "onFailure: Failed Affirmation")
                })

            HttpRequest.getService(ServiceType.Advice)
                .getAdvice()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewModel.setAdvice(it)
                }, {
                    Log.d(TAG, "onFailure: Failed Advice")
                })
        }
    }
}