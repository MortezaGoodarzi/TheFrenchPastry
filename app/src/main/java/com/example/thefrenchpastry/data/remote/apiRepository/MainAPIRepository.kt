package com.example.thefrenchpastry.data.remote.apiRepository

import com.example.thefrenchpastry.data.remote.RetrofitService
import com.example.thefrenchpastry.data.remote.dataModel.RequestMain
import com.example.thefrenchpastry.data.remote.ext.CallBackRequest
import com.example.thefrenchpastry.data.remote.ext.ErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

class MainAPIRepository private constructor() {

    companion object {
        private var apiRepository: MainAPIRepository? = null

        val instance: MainAPIRepository
            get() {
                if (apiRepository == null) apiRepository = MainAPIRepository()
                return apiRepository!!
            }
    }

    fun getMainContent(
        callBackRequest: CallBackRequest<RequestMain>
    ) {
        RetrofitService.mainApiService.getContent().enqueue(
            object : Callback<RequestMain> {
                override fun onResponse(call: Call<RequestMain>, response: Response<RequestMain>) {

                    if (response.isSuccessful) {
                        val data = response.body() as RequestMain
                        callBackRequest.onSuccess(response.code(), data)

                    } else {
                        val error = ErrorUtils.parseError(response)
                        callBackRequest.onNotSuccess(response.code(), error)
                    }

                }

                override fun onFailure(call: Call<RequestMain>, t: Throwable) {
                    callBackRequest.onError(t.message.toString())
                }
            }
        )

    }
}

interface MainApiService {

    @GET("main")
    fun getContent(): Call<RequestMain>

}
