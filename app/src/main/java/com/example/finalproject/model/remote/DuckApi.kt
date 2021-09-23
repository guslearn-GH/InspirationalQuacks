package com.example.finalproject.model.remote

import com.example.finalproject.BuildConfig
import com.example.finalproject.model.DuckResponse
import retrofit2.Call
import retrofit2.http.GET

interface DuckApi {
    @GET(BuildConfig.DUCK_URL)//R.string.DuckApi.toString())
    fun getDuckPicture(): Call<DuckResponse>
}