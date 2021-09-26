package com.example.finalproject.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.databinding.QuackFragmentBinding
import com.example.finalproject.model.*
import com.example.finalproject.model.repository.QuackRepository

private const val TAG = "QuackViewModel"
class QuackViewModel(): ViewModel() {


    val quackRepo:QuackRepository = QuackRepository(this)

    //var binding : QuackFragmentBinding = _binding

    private val quackImage: MutableLiveData<String> = MutableLiveData()
    private val quackMessage: MutableLiveData<String> = MutableLiveData()
    private val quackId: MutableLiveData<Int> = MutableLiveData()




    fun getData(infoChoice:Int){
        //val repo = QuackRepository(this)
        //val vmQuack:QuackResponse =
            quackRepo.getQuack(infoChoice)
//        Glide.with(binding.root)
//            .load(quackRepo.currentImage)//.load(vmQuack.url)
//            .into(binding.ivDuckImg)
        //binding.tvInfo.text = quackRepo.currentMessage //vmQuack.message
        //id =quackRepo.currentId //vmQuack.id

        /*
        HttpRequest.getService(ServiceType.Duck)
            .getDuckPicture()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Glide.with(binding.root)
                    .load(it.url)
                    .into(binding.ivDuckImg)
                Toast.makeText( binding.root.context , "${it}", Toast.LENGTH_LONG).show()
            },{
                Log.d(TAG, "getData: Failed Duck")

            })
        if(infoChoice%2==0) {
            HttpRequest.getService(ServiceType.Affirmation)
                .getAffirmation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //response.body()?.let{viewModel.applyDuckPicture(it)}
                    binding.tvInfo.text = it.affirmation
                    Toast.makeText(binding.root.context, "${it}", Toast.LENGTH_LONG).show()
                }, {
                    Log.d(TAG, "onFailure: Failed Affirmation")
                })
        }
        else{
            HttpRequest.getService(ServiceType.Advice)
                .getAdvice()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    binding.tvInfo.text = it.slip.advice
                    Toast.makeText(binding.root.context, "${it}", Toast.LENGTH_LONG).show()
                },{
                    Log.d(TAG, "onFailure: Failed Advice")
                })
        }
*/

    }

    fun getQuackImage():String?{
        return quackImage.value
    }
    fun setQuackImage(data: DuckResponse){
        this.quackImage.value = data.url
    }
    fun getQuackMessage():String?{
        return quackMessage.value
    }
    fun setQuackMessage(data: AffirmationResponse){
        this.quackMessage.value = data.affirmation
    }
    fun setQuackMessage(data: AdviceResponse){
        this.quackMessage.value = data.slip.advice
    }


}