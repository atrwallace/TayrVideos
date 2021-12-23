package com.example.tayrvideos.repo

import com.example.tayrvideos.rest.RetrofitService

class MainRepository (private val retrofitService: RetrofitService){

fun getLiveData() = retrofitService.getLiveData()

}