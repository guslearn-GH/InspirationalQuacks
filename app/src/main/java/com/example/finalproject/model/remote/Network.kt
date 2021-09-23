package com.example.finalproject.model.remote

import android.app.PendingIntent.getActivity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.finalproject.BuildConfig
import com.example.finalproject.MainActivity
import com.example.finalproject.ViewModel.QuackViewModel
import com.example.finalproject.model.AdviceResponse
import com.example.finalproject.model.AffirmationResponse
import com.example.finalproject.model.DuckResponse
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

private const val TAG = "Network"
class Network(val viewModel: QuackViewModel, val context: Context) {

lateinit var okHttpClient:OkHttpClient



        //Log.d(TAG, "initRetrofit:")
fun initRetrofit(baseUrl: String, okHttpClient: OkHttpClient){
//            getQuackApi(okHttpClient).getDuckPicture().enqueue(
//    object : Callback<DuckResponse> {
//        override fun onFailure(call: Call<DuckResponse>, t: Throwable) {
//            Log.d(TAG, "onFailure: Failed Duck")
//        }
//
//        override fun onResponse(
//            call: Call<DuckResponse>,
//            response: Response<DuckResponse>
//        ) {
//            response.body()?.let{viewModel.applyDuckPicture(it)}
//            Toast.makeText( context , "${response.body()}", Toast.LENGTH_LONG).show()
//        }
//    })

//        getQuackApi(okHttpClient).getAffirmation().enqueue(
//                object: Callback<AffirmationResponse>{
//                    override fun onFailure(call: Call<AffirmationResponse>, t: Throwable) {
//                        Log.d(TAG, "onFailure: Affirmations")
//                    }
//
//                    override fun onResponse(
//                        call: Call<AffirmationResponse>,
//                        response: Response<AffirmationResponse>
//                    ) {
//                        response.body()?.let{viewModel.applyAffirmation(it)}
//                        Toast.makeText( context , "${response.body()}", Toast.LENGTH_LONG).show()
//                    }
//                }
//            )

//        getQuackApi(okHttpClient).getAdvice().enqueue(
//                object : Callback<AdviceResponse>{
//                    override fun onFailure(call: Call<AdviceResponse>, t: Throwable) {
//                        Log.d(TAG, "onFailure: Advice")
//                    }
//
//                    override fun onResponse(
//                        call: Call<AdviceResponse>,
//                        response: Response<AdviceResponse>
//                    ) {
//                        response.body()?.let{viewModel.applyAdvice(it)}
//                        Toast.makeText( context , "${response.body()}", Toast.LENGTH_LONG).show()
//                    }
//                }
//            )
    }






    fun getQuackApi(okHttpClient:OkHttpClient):QuackApi{

        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("").client(okHttpClient)
            .build().create(QuackApi::class.java)


    }

    fun affirmationApi(baseUrl: String, okHttpClient: OkHttpClient):QuackApi{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.AFFIRM_URL).client(okHttpClient)
            .build().create(QuackApi::class.java)
    }

    fun adviceApi(baseUrl: String, okHttpClient: OkHttpClient):AffirmationApi{
        //val affirmApi=
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.ADVICE_URL).client(okHttpClient)
            .build().create()//.create(AffirmationApi::class.java)
        //affirmApi.getAffirmation().enqueue()
    }

    fun duckApiCall(){
        //(baseUrl: String, okHttpClient: OkHttpClient):QuackApi
//        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(BuildConfig.ADVICE_URL).client(okHttpClient)
//            .build().create(QuackApi::class.java)
//        getQuackApi(okHttpClient)
//            .getDuckPicture()
//            .enqueue(
//            object : Callback<DuckResponse> {
//                override fun onFailure(call: Call<DuckResponse>, t: Throwable) {
//                    Log.d(TAG, "onFailure: Failed Duck")
//                }
//
//                override fun onResponse(
//                    call: Call<DuckResponse>,
//                    response: Response<DuckResponse>
//                ) {
//                    response.body()?.let{viewModel.applyDuckPicture(it)}
//                    Toast.makeText( context , "${response.body()}", Toast.LENGTH_LONG).show()
//                }
//            })
    }
}