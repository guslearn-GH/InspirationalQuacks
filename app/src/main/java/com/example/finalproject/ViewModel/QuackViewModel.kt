package com.example.finalproject.ViewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.finalproject.MainActivity
import com.example.finalproject.R
import com.example.finalproject.databinding.QuackFragmentBinding
import com.example.finalproject.model.AdviceResponse
import com.example.finalproject.model.AffirmationResponse
import com.example.finalproject.model.DuckResponse
import com.example.finalproject.model.ServiceType
import com.example.finalproject.model.remote.HttpRequest
import com.example.finalproject.model.remote.QuackApi
import com.example.finalproject.model.remote.QuackRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.*

private const val TAG = "QuackViewModel"
class QuackViewModel(private val _binding:QuackFragmentBinding): ViewModel() {

    var image:String = "image/url"
    //= getData(R.string.DuckApi.toString())
    var text:String = "Good Advice"
    //= getData(R.string.AffirmationApi.toString())
    var binding : QuackFragmentBinding = _binding

    private val duckDataSet: MutableLiveData<DuckResponse> = MutableLiveData()
    private val affirmationDataSet: MutableLiveData<AffirmationResponse> = MutableLiveData()
    private val adviceDataSet: MutableLiveData<AdviceResponse> = MutableLiveData()




    fun getDuckDataSet(): LiveData<DuckResponse>{
        return duckDataSet
    }

    fun getAffirmationDataSet():LiveData<AffirmationResponse>{
        return affirmationDataSet
    }

    fun getAdviceDataSet():LiveData<AdviceResponse>{
        return adviceDataSet
    }

    fun getData(infoChoice:Int){
        HttpRequest.getService(ServiceType.Duck)
            .getDuckPicture()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //response.body()?.let{viewModel.applyDuckPicture(it)}
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
                    binding.tvInfo.text = it.affirmation //this.text
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
        //val repo = QuackRepository(this)
        //repo.getQuack(context)

//        if(infoChoice%2==0)
//            repo.getQuack(R.string.AffirmationApi.toString(), context)
//        else
//            repo.getQuack(R.string.AdviceApi.toString(), context)


    }

    fun applyDuckPicture(dataSet: DuckResponse){

        this.duckDataSet.value = dataSet
    }

    fun applyAffirmation(dataSet: AffirmationResponse){

        this.affirmationDataSet.value = dataSet
    }

    fun applyAdvice(dataSet: AdviceResponse){

        this.adviceDataSet.value = dataSet
    }
}