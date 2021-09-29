package com.example.finalproject.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.finalproject.QuackApplication
import com.example.finalproject.QuackApplication.Companion.quackDao
import com.example.finalproject.R
import com.example.finalproject.model.*
import com.example.finalproject.model.local.Quack
import com.example.finalproject.model.local.QuackDatabase
import com.example.finalproject.model.remote.HttpRequest
import com.example.finalproject.model.repository.QuackRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

private const val TAG = "QuackViewModel"
class QuackViewModel(): ViewModel() {

    var image:String = "image/url"
    //= getData(R.string.DuckApi.toString())
    var text:String = "Good Advice"
    //= getData(R.string.AffirmationApi.toString())
    //var binding : QuackFragmentBinding = _binding

    private val quackDataSet:MutableLiveData<Quack> = MutableLiveData()
    private val duckDataSet: MutableLiveData<DuckResponse> = MutableLiveData()
    private val affirmationDataSet: MutableLiveData<AffirmationResponse> = MutableLiveData()
    private val adviceDataSet: MutableLiveData<AdviceResponse> = MutableLiveData()
    var readingOldQuacks:Boolean = false
    val coroutineScope:CoroutineScope = CoroutineScope(Dispatchers.IO)
    private var quackListPos:Int = 0



    fun getQuackDataSet():LiveData<Quack>{
        return quackDataSet
    }

    fun setQuackDataSet(quack: Quack){
        this.quackDataSet.postValue(quack)
    }

    fun setDuckToQuack(dr:DuckResponse){
        this.quackDataSet.value?.let { it.Image = dr.url }
    }

    fun setAffirmationToQuack(ar:AffirmationResponse){
        this.quackDataSet.value?.let{it.Message = ar.affirmation}
    }

    fun setAdviceToQuack(ar:AdviceResponse){
        this.quackDataSet.value?.let { it.Message = ar.slip.advice }
    }


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
        //var qvm = this
        //CoroutineScope(Dispatchers.IO).launch {
            val repo = QuackRepository(this)
            repo.getQuack(context)
        //}
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

    fun read(context: Context){
        coroutineScope.launch {
            quackDao = QuackDatabase.newInstance(context).getDao()
            var response = quackDao.getOldQuacks()
            if (readingOldQuacks) {
                if(quackListPos==0)
                    quackListPos = response.size-1
                else
                    quackListPos -= 1
                setQuackDataSet(response.get(quackListPos))
            } else {
                readingOldQuacks = true

                    quackListPos = response.size - 1
                setQuackDataSet(response.get(quackListPos))
            }
        }
    }

    fun insert(context:Context){
        var tq: Quack = Quack(
            Id = 0,
            Image = getDuckDataSet().value?.let{it.url} ?: "/",//viewModel.getDuckDataSet().value?.let{ it.url} ?: "",
            Message = getAffirmationDataSet().value?.let { it.affirmation } ?: ""//"problems with insert"//binding.tvInfo.text.toString()
        )
        coroutineScope.launch {
            quackDao = QuackDatabase.newInstance(context).getDao()
            quackDao.insertQuack(tq)
        }
    }
}