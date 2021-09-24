package com.example.finalproject.ViewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.finalproject.databinding.QuackFragmentBinding
import com.example.finalproject.model.*
import com.example.finalproject.model.remote.HttpRequest
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

private const val TAG = "QuackViewModel"
class QuackViewModel(private val _binding:QuackFragmentBinding): ViewModel() {

    var image:String = "image/url"
    //= getData(R.string.DuckApi.toString())
    var info:String = "Good Advice"
    //= getData(R.string.AffirmationApi.toString())
    //lateinit var quack:QuackEntities //= QuackEntities()

    var binding : QuackFragmentBinding = _binding

    private val duckDataSet: MutableLiveData<DuckResponse> = MutableLiveData()
    private val affirmationDataSet: MutableLiveData<AffirmationResponse> = MutableLiveData()
    private val adviceDataSet: MutableLiveData<AdviceResponse> = MutableLiveData()




    fun getData(infoChoice:Int){
        HttpRequest.getService(ServiceType.Duck)
            .getDuckPicture()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Glide.with(binding.root)
                    .load(it.url)
                    .into(binding.ivDuckImg)
                Toast.makeText( binding.root.context , "${it}", Toast.LENGTH_LONG).show()
            },{
                Log.d(TAG, "getData: Failed Duck")

            })
        if(infoChoice%2==0) {
            HttpRequest.getService(ServiceType.Affirmation)
                .getAffirmation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //response.body()?.let{viewModel.applyDuckPicture(it)}
                    binding.tvInfo.text = it.affirmation
                    Toast.makeText(binding.root.context, "${it}", Toast.LENGTH_LONG).show()
                }, {
                    Log.d(TAG, "onFailure: Failed Affirmation")
                })
        }
        else{
            HttpRequest.getService(ServiceType.Advice)
                .getAdvice()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    binding.tvInfo.text = it.slip.advice
                    Toast.makeText(binding.root.context, "${it}", Toast.LENGTH_LONG).show()
                },{
                    Log.d(TAG, "onFailure: Failed Advice")
                })
        }

        //applyQuack()

        //insertQuack()

    }

    fun applyQuack(){
//        quack = QuackEntities(url=image, message = info)
//        Glide.with(binding.root)
//                   .load(quack.url)
//                    .into(binding.ivDuckImg)
//        binding.tvInfo.text = quack.message
    }

    fun insertQuack(){
//        val qdb = QuackDatabase.getInstance(binding.root.context).getDao()
//
//        qdb.saveDataIntoCache(quack)
//            QuackResponse(
//            url = quack.url,
//            message = quack.message
//        ))
    }

}