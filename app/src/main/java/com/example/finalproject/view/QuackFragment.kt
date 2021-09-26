package com.example.finalproject.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.finalproject.ViewModel.QuackViewModel
import com.example.finalproject.databinding.QuackFragmentBinding
import com.example.finalproject.model.AdviceResponse
import com.example.finalproject.model.AffirmationResponse
import com.example.finalproject.model.DuckResponse
import com.example.finalproject.model.common.ServiceType
import com.example.finalproject.model.remote.HttpRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

private const val TAG = "QuackFragment"
class QuackFragment(var infoChoice:Int): Fragment() {

    lateinit var binding: QuackFragmentBinding
    lateinit var viewModel:QuackViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = QuackFragmentBinding.inflate(
            inflater,
            container,
            false
        )



        viewModel = ViewModelProvider(
            viewModelStore,
            object: ViewModelProvider.Factory{
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return QuackViewModel()
                        as T
                }
            }
        )[QuackViewModel::class.java]
        getQuack()

//        viewModel.getData(infoChoice)
//        updateImage(viewModel)

        binding.btnNextQuack.setOnClickListener(::updateData)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        //getQuack()
        //viewModel.getData(infoChoice)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: APPLYING IMAGES!!")
        Log.d(TAG, "onResume: APPLYING IMAGES!!")
        Log.d(TAG, "onResume: APPLYING IMAGES!!")
        Log.d(TAG, "onResume: APPLYING IMAGES!!")
        Log.d(TAG, "onResume: APPLYING IMAGES!!")
        updateImage(viewModel)
        updateMessage(viewModel)
    }

    fun getQuack(){
        HttpRequest.getService(ServiceType.Duck)
            .getDuckPicture()
            /*
       .subscribeOn(Schedulers.io())
       .observeOn(AndroidSchedulers.mainThread())
       .subscribe(
           object : Observer<DuckResponse>{
               override fun onSubscribe(d: Disposable) {
                   Log.d(TAG, "onSubscribe: Duck Log")
               }

               override fun onNext(t: DuckResponse) {
                   Log.d(TAG, "onNext: Duck Log")
                   quackViewModel.setQuackImage(currentDuck)
                   t?.let{ Log.d(TAG, "onNext: ${it}")
                   //currentDuck = it
                   }

               }

               override fun onError(e: Throwable) {
                   Log.d(TAG, "onError: Duck Log")
               }

               override fun onComplete() {
                   Log.d(TAG, "onComplete: Duck log")
               }


           }
       )
   */

            /*
    //            .execute()
    //            response.body()?.let { quackViewModel.setQuackImage(it) }
    //        Log.d(TAG, "onResponse: ${response.body()}")
    //                    Log.d(TAG, "onResponse: ${response.body()}")
    //                    Log.d(TAG, "onResponse: ${response.body()}")
    //                    Log.d(TAG, "onResponse: ${response.body()}")
    //                    Log.d(TAG, "onResponse: ${response.body()}")
    //            .awaitResponse()
    */
            .enqueue(object: Callback<DuckResponse> {
                override fun onResponse(
                    call: Call<DuckResponse>,
                    response: Response<DuckResponse>
                ) {
                    //netQuack.url = response.body()?.url.toString()
                    //currentImage =
                    response.body()?.let { viewModel.setQuackImage(it)}//.url.toString()
                    Log.d(TAG, "onResponse: ${response.body()}")
                    Log.d(TAG, "onResponse: ${response.body()}")
                    Log.d(TAG, "onResponse: ${response.body()}")
                    Log.d(TAG, "onResponse: ${response.body()}")
                    Log.d(TAG, "onResponse: ${response.body()}")

                    //Toast.makeText( quackViewModel , "${response.body()}", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<DuckResponse>, t: Throwable) {

                    Log.d(TAG, "getData: Failed Duck")

                }})

        if(infoChoice%2==0) {
            HttpRequest.getService(ServiceType.Affirmation)
                .getAffirmation()
                //.awaitResponse()
                .enqueue(object: Callback<AffirmationResponse> {
                    override fun onResponse(
                        call: Call<AffirmationResponse>,
                        response: Response<AffirmationResponse>
                    ) {
                        //response.body()?.let{viewModel.applyDuckPicture(it)}
                        //quackViewModel.binding.tvInfo.text = it.affirmation
                        //netQuack.message = response.body()?.affirmation.toString()
                        //currentMessage =  response.body()?.affirmation.toString()
                        response.body()?.let { viewModel.setQuackMessage(it)}
                        Log.d(TAG, "onResponse: ${response.body()}")
                        Log.d(TAG, "onResponse: ${response.body()}")
                        Log.d(TAG, "onResponse: ${response.body()}")
                        Log.d(TAG, "onResponse: ${response.body()}")
                        Log.d(TAG, "onResponse: ${response.body()}")
                        Log.d(TAG, "onResponse: ${response.body()}")

                        //Toast.makeText(quackViewModel.binding.root.context, "${response.body()}", Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<AffirmationResponse>, t: Throwable) {

                        Log.d(TAG, "onFailure: Failed Affirmation")
                    }})
        }
        else{

            HttpRequest.getService(ServiceType.Advice)
                .getAdvice()
                //.awaitResponse()
                .enqueue(object: Callback<AdviceResponse> {
                    override fun onResponse(
                        call: Call<AdviceResponse>,
                        response: Response<AdviceResponse>
                    ) {
                        //quackViewModel.binding.tvInfo.text = it.slip.advice
                        //netQuack.message = response.body()?.slip?.advice.toString()
                        //currentMessage = response.body()?.slip?.advice.toString()
                        response.body()?.let { viewModel.setQuackMessage(it) }
                        Log.d(TAG, "onResponse: ${response.body()}")
                        Log.d(TAG, "onResponse: ${response.body()}")
                        Log.d(TAG, "onResponse: ${response.body()}")
                        Log.d(TAG, "onResponse: ${response.body()}")
                        Log.d(TAG, "onResponse: ${response.body()}")
                        Log.d(TAG, "onResponse: ${response.body()}")
                        Log.d(TAG, "onResponse: ${response.body()}")
                        //Toast.makeText(quackViewModel.binding.root.context, "${response.body()}", Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<AdviceResponse>, t: Throwable) {

                        Log.d(TAG, "onFailure: Failed Advice")
                    }})
        }
    }


    fun updateData(view:View){
        //save quacks here
        infoChoice = Random().nextInt(10000)
        getQuack()
        //viewModel.getData(infoChoice)
    }

    fun updateImage(quackvm: QuackViewModel){
        Glide.with(binding.root)
            .load(quackvm.getQuackImage())
            .into(binding.ivDuckImg)
    }

    fun updateMessage(quackvm: QuackViewModel){
        binding.tvInfo.text = quackvm.getQuackMessage()
    }



}