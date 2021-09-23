package com.example.finalproject.model.remote

import com.example.finalproject.model.AffirmationResponse
import retrofit2.Call
import retrofit2.http.GET

interface AffirmationApi {

    @GET()//R.string.AffirmationApi.toString())
    fun getAffirmation(): Call<AffirmationResponse>
}