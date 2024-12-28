package com.example.thefrenchpastry.data.remote

import com.example.thefrenchpastry.data.remote.apiRepository.LoginApiService
import com.example.thefrenchpastry.data.remote.apiRepository.MainApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RetrofitService {

    private const val url = "https://pastry.alirezaahmadi.info/api/v1/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val loginApiService: LoginApiService = retrofit.create(LoginApiService::class.java)

    val mainApiService: MainApiService = retrofit.create(MainApiService::class.java)

}