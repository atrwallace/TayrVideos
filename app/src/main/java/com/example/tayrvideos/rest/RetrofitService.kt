package com.example.tayrvideos.rest

import com.example.tayrvideos.models.Live
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface RetrofitService {

    @GET("607dbc12-22e4-444e-8128-635e87ef6ac4")
    fun getLiveData(): Call<List<Live>>

    companion object {

        private val retrofitService : RetrofitService by lazy {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://mocki.io/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            retrofit.create(RetrofitService::class.java)
        }

        fun getInstance(): RetrofitService {
            return retrofitService
        }
    }
}