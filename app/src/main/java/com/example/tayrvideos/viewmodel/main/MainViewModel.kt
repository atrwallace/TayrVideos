package com.example.tayrvideos.viewmodel.main

import android.telecom.Call
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tayrvideos.models.Live
import com.example.tayrvideos.repo.MainRepository
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    val livelist = MutableLiveData<List<Live>>()
    val errorMsg = MutableLiveData<String>()

    fun getLiveDataModel() {
        val request = mainRepository.getLiveData()
        request.enqueue(object : Callback<List<Live>> {
            override fun onResponse(call: retrofit2.Call<List<Live>>, response: Response<List<Live>>) {
                livelist.postValue(response.body())
            }

            override fun onFailure(call: retrofit2.Call<List<Live>>, t: Throwable) {
                errorMsg.postValue(t.message)
            }


        })
    }

}