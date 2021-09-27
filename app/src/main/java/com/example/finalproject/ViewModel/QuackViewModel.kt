package com.example.finalproject.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.model.*
import com.example.finalproject.model.remote.HttpRequest
import com.example.finalproject.model.repository.QuackRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

private const val TAG = "QuackViewModel"
class QuackViewModel(): ViewModel() {

    var image:String = "image/url"
    //= getData(R.string.DuckApi.toString())
    var text:String = "Good Advice"
    //= getData(R.string.AffirmationApi.toString())
    //var binding : QuackFragmentBinding = _binding

    //private val quackDataSet:MutableLiveData<Quack> = MutableLiveData()
    private val duckDataSet: MutableLiveData<DuckResponse> = MutableLiveData()
    private val affirmationDataSet: MutableLiveData<AffirmationResponse> = MutableLiveData()
    private val adviceDataSet: MutableLiveData<AdviceResponse> = MutableLiveData()


/*
    fun getQuackDataSet():LiveData<Quack>{
        return quackDataSet
    }

    fun setQuackDataSet(quack: Quack){
        this.quackDataSet.value = quack
    }
*/

    fun getDuckDataSet(): LiveData<DuckResponse>{
        return duckDataSet
    }

    fun getAffirmationDataSet():LiveData<AffirmationResponse>{
        return affirmationDataSet
    }

    fun getAdviceDataSet():LiveData<AdviceResponse>{
        return adviceDataSet
    }

    fun getData(context: Context){
        /*
        HttpRequest.getService(ServiceType.Duck)
            .getDuckPicture()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //response.body()?.let{viewModel.applyDuckPicture(it)}
                setDuckPicture(it)
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
                    setAffirmation(it)
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
                    setAdvice(it)
                    //Toast.makeText(binding.root.context, "${it}", Toast.LENGTH_LONG).show()
                },{
                    Log.d(TAG, "onFailure: Failed Advice")
                })
       // }
        */
        val repo = QuackRepository(this)
        repo.getQuack(context)

//        if(infoChoice%2==0)
//            repo.getQuack(R.string.AffirmationApi.toString(), context)
//        else
//            repo.getQuack(R.string.AdviceApi.toString(), context)


    }

    fun setDuckPicture(dataSet: DuckResponse){

        this.duckDataSet.value = dataSet
    }

    fun setAffirmation(dataSet: AffirmationResponse){

        this.affirmationDataSet.value = dataSet
    }

    fun setAdvice(dataSet: AdviceResponse){

        this.adviceDataSet.value = dataSet
    }
}