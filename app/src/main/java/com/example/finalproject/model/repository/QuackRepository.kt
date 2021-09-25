package com.example.finalproject.model.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.finalproject.ViewModel.QuackViewModel
import com.example.finalproject.model.AdviceResponse
import com.example.finalproject.model.AffirmationResponse
import com.example.finalproject.model.DuckResponse
import com.example.finalproject.model.common.ServiceType
import com.example.finalproject.model.common.isConnected
import com.example.finalproject.model.remote.HttpRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.*
import java.util.*

private const val TAG = "QuackRepository"
class QuackRepository(private val quackViewModel: QuackViewModel) {

    companion object{
        const val CONNECTIVITY_SP = "connection_sp"
        const val DATE_REQUEST = "DATE_REQUEST"
    }


    fun getQuack(infoChoice: Int){
//        var response:QuackResponse =
//            QuackResponse(
//                url = "",
//                message = ""
//            )

        if(isConnected){
            if (false){//if(cacheIsFresh()){

            }else{
                getNetworkQuack(infoChoice)//get randomized numbers
                //insertQuack
            }
        }


        //return response

    }

    private fun getNetworkQuack(infoChoice:Int){
        //var netQuack:QuackResponse = QuackResponse()
        HttpRequest.getService(ServiceType.Duck)
            .getDuckPicture()
            //.awaitResponse()
            .enqueue(object: Callback<DuckResponse>{
                override fun onResponse(
                    call: Call<DuckResponse>,
                    response: Response<DuckResponse>
                ) {
                //netQuack.url = response.body()?.url.toString()
                    //currentImage =
                    response.body()?.let { quackViewModel.setQuackImage(it)}//.url.toString()
                Toast.makeText( quackViewModel.binding.root.context , "${response.body()}", Toast.LENGTH_LONG).show()
            }

                override fun onFailure(call: Call<DuckResponse>, t: Throwable) {

                Log.d(TAG, "getData: Failed Duck")

            }})
        if(infoChoice%2==0) {
            HttpRequest.getService(ServiceType.Affirmation)
                .getAffirmation()
                //.awaitResponse()
                .enqueue(object: Callback<AffirmationResponse>{
                override fun onResponse(
                    call: Call<AffirmationResponse>,
                    response: Response<AffirmationResponse>
                ) {
                    //response.body()?.let{viewModel.applyDuckPicture(it)}
                    //quackViewModel.binding.tvInfo.text = it.affirmation
                    //netQuack.message = response.body()?.affirmation.toString()
                    //currentMessage =  response.body()?.affirmation.toString()
                    response.body()?.let { quackViewModel.setQuackMessage(it)}
                    Toast.makeText(quackViewModel.binding.root.context, "${response.body()}", Toast.LENGTH_LONG).show()
                }

                    override fun onFailure(call: Call<AffirmationResponse>, t: Throwable) {

                        Log.d(TAG, "onFailure: Failed Affirmation")
                }})
        }
        else{

            HttpRequest.getService(ServiceType.Advice)
                .getAdvice()
                //.awaitResponse()
                .enqueue(object: Callback<AdviceResponse>{
                    override fun onResponse(
                        call: Call<AdviceResponse>,
                        response: Response<AdviceResponse>
                    ) {
                    //quackViewModel.binding.tvInfo.text = it.slip.advice
                    //netQuack.message = response.body()?.slip?.advice.toString()
                        //currentMessage = response.body()?.slip?.advice.toString()
                        response.body()?.let { quackViewModel.setQuackMessage(it) }
                    Toast.makeText(quackViewModel.binding.root.context, "${response.body()}", Toast.LENGTH_LONG).show()
                }

                        override fun onFailure(call: Call<AdviceResponse>, t: Throwable) {

                    Log.d(TAG, "onFailure: Failed Advice")
                }})
        }

        //return netQuack
    }

    private fun cacheIsFresh():Boolean{
        val oldDateString = quackViewModel.binding.root.context.getSharedPreferences(CONNECTIVITY_SP, Context.MODE_PRIVATE)
            .getString(DATE_REQUEST, null)?:return false

        val currentDate = Calendar.getInstance().timeInMillis - 604800000L

        return oldDateString.toLong() < currentDate
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