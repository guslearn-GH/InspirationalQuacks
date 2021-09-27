package com.example.finalproject.model.repository

import android.content.Context
import android.util.Log
import com.example.finalproject.ViewModel.QuackViewModel

import com.example.finalproject.model.ServiceType
import com.example.finalproject.model.remote.HttpRequest
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

private const val TAG = "Repository"
class QuackRepository(private val viewModel: QuackViewModel){

    fun getQuack(context:Context){

        HttpRequest.getService(ServiceType.Duck)
            .getDuckPicture()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //response.body()?.let{viewModel.applyDuckPicture(it)}
                viewModel.setDuckPicture(it)
//                Glide.with(binding.root)
//                    .load(it.url)
//                    .into(binding.ivDuckImg)
//                Toast.makeText( binding.root.context , "${it}", Toast.LENGTH_LONG).show()
            },{
                Log.d(TAG, "getData: Failed Duck")

            })
        //if(infoChoice%2==0) {
        HttpRequest.getService(ServiceType.Affirmation)
            .getAffirmation()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //response.body()?.let{viewModel.applyDuckPicture(it)}
                //binding.tvInfo.text = it.affirmation //this.text
                viewModel.setAffirmation(it)
                //Toast.makeText(binding.root.context, "${it}", Toast.LENGTH_LONG).show()
            }, {
                Log.d(TAG, "onFailure: Failed Affirmation")
            })
//        }
//        else{
        HttpRequest.getService(ServiceType.Advice)
            .getAdvice()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //binding.tvInfo.text = it.slip.advice
                viewModel.setAdvice(it)
                //Toast.makeText(binding.root.context, "${it}", Toast.LENGTH_LONG).show()
            },{
                Log.d(TAG, "onFailure: Failed Advice")
            })
    }
}