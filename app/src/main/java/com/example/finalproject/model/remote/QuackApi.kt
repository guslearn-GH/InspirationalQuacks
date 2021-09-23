package com.example.finalproject.model.remote

import com.example.finalproject.BuildConfig
import com.example.finalproject.R
import com.example.finalproject.model.AdviceResponse
import com.example.finalproject.model.AffirmationResponse
import com.example.finalproject.model.DuckResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET

interface QuackApi {

    @GET("/api/quack")//R.string.DuckApi.toString())
    fun getDuckPicture(): Observable<DuckResponse>

    @GET("/")//R.string.AffirmationApi.toString())
    fun getAffirmation():Observable<AffirmationResponse>

    @GET("/advice")//R.string.AdviceApi.toString())
    fun getAdvice():Observable<AdviceResponse>

}