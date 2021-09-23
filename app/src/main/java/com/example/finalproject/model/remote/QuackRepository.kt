package com.example.finalproject.model.remote

import android.content.Context
import com.example.finalproject.BuildConfig
import com.example.finalproject.R
import com.example.finalproject.ViewModel.QuackViewModel
import okhttp3.Cache
import okhttp3.OkHttpClient

class QuackRepository(private val quackViewModel: QuackViewModel) {
    val cacheSize = (5 * 1024 * 1024).toLong()
    lateinit var  myCache: Cache

    fun getQuack(context: Context){



        myCache = Cache(context.cacheDir, cacheSize)
        val okHttpClient = OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if(true)
                    request.newBuilder().header(
                        "New-Quack-Cache",
                        "public, maxAge=" + 5
                    ).build()
                else
                    request.newBuilder().header(
                            "Some-Quack-Cache",
                        "public, only-if-cached, max-stale="+ 60*10
                    ).build()
                chain.proceed(request)
            }.build()


        val network = Network(quackViewModel, context)
        network.okHttpClient=okHttpClient
        network.duckApiCall()

        //network.initRetrofit("", okHttpClient)
        //network.duckApi(BuildConfig.BASE_URL, okHttpClient)
        //network.initRetrofit(R.string.AffirmationApi.toString(), okHttpClient)
    }
}