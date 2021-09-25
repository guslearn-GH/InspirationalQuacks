package com.example.finalproject.model.remote

import com.example.finalproject.BuildConfig
import com.example.finalproject.model.common.ServiceType
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpRequest {
    private val builder: Retrofit.Builder =
        Retrofit.Builder()

    fun getService(serviceType: ServiceType): QuackApi{
        when(serviceType){
            is ServiceType.Duck->{
                builder.baseUrl(BuildConfig.DUCK_URL)
            }
            is ServiceType.Affirmation->{
                builder.baseUrl(BuildConfig.AFFIRM_URL)
            }
            is ServiceType.Advice->{
                builder.baseUrl(BuildConfig.ADVICE_URL)
            }
        }
        builder.addConverterFactory(GsonConverterFactory.create())
        builder.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        return builder.build().create(QuackApi::class.java)
    }
}