package com.example.thefrenchpastry.data.remote.apiRepository


import com.example.thefrenchpastry.data.remote.RetrofitService
import com.example.thefrenchpastry.data.remote.dataModel.DefaultModel
import com.example.thefrenchpastry.data.remote.dataModel.RequestSendPhone
import com.example.thefrenchpastry.data.remote.dataModel.RequestVerifyCode
import com.example.thefrenchpastry.data.remote.ext.CallBackRequest
import com.example.thefrenchpastry.data.remote.ext.ErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import java.net.SocketTimeoutException


class LoginAPIRepository private constructor() {

    companion object {
        private var apiRepository: LoginAPIRepository? = null

        val instance: LoginAPIRepository
            get() {
                if (apiRepository == null)
                    apiRepository = LoginAPIRepository()
                return apiRepository!!
            }
    }


    fun sendPhoneAuth(
        id: String,
        key: String,
        phone: String,
        callbackRequest: CallBackRequest<RequestSendPhone>
    ) {
        RetrofitService.loginApiService.sendPhone(
            id,
            key,
            phone
        ).enqueue(
            object : Callback<RequestSendPhone> {
                override fun onResponse(
                    call: Call<RequestSendPhone>,
                    response: Response<RequestSendPhone>
                ) {
                    if (response.isSuccessful) {
                        callbackRequest.onSuccess(
                            response.code(),
                            response.body() as RequestSendPhone
                        )
                    } else {
                        val error = ErrorUtils.parseError(response)
                        callbackRequest.onNotSuccess(response.code(), error)
                    }

                }

                override fun onFailure(call: Call<RequestSendPhone>, t: Throwable) {
                    if (t is SocketTimeoutException) {
                        callbackRequest.onError("تایم اوت")
                    } else {
                        callbackRequest.onError(t.message.toString())
                    }
                }
            }
        )
    }

    fun verifyCodeAuth(
        code: String,
        number: String,
        callbackRequest: CallBackRequest<RequestVerifyCode>
    ) {
        RetrofitService.loginApiService.verifyCode(
            code,
            number
        ).enqueue(
            object : Callback<RequestVerifyCode> {
                override fun onResponse(
                    call: Call<RequestVerifyCode>,
                    response: Response<RequestVerifyCode>
                ) {

                    if (response.isSuccessful) {
                        callbackRequest.onSuccess(
                            response.code(),
                            response.body() as RequestVerifyCode
                        )

                    } else {
                        val error = ErrorUtils.parseError(response)
                        callbackRequest.onNotSuccess(
                            response.code(),
                            error
                        )
                    }
                }

                override fun onFailure(call: Call<RequestVerifyCode>, t: Throwable) {

                    callbackRequest.onError(t.message.toString())
                }
            }
        )
    }


    fun editUser(
        apiKey: String,
        id: String,
        pubKey: String,
        fullName: String,
        callbackRequest: CallBackRequest<DefaultModel>
    ) {

        RetrofitService.loginApiService.editUser(apiKey, id, pubKey, fullName).enqueue(

            object : Callback<DefaultModel> {

                override fun onResponse(
                    call: Call<DefaultModel>,
                    response: Response<DefaultModel>
                ) {

                    if (response.isSuccessful)
                        callbackRequest.onSuccess(
                            response.code(),
                            response.body() as DefaultModel
                        )
                    else {
                        val error = ErrorUtils.parseError(response)
                        callbackRequest.onNotSuccess(
                            response.code(),
                            error
                        )
                    }
                }

                override fun onFailure(call: Call<DefaultModel>, t: Throwable) {
                    callbackRequest.onError(t.message.toString())
                }
            }
        )
    }
}
interface LoginApiService {

    @FormUrlEncoded
    @POST("auth/phone/login")
    fun sendPhone(
        @Header("app-device-uid") id: String,
        @Header("app-public-key") key: String,
        @Field("phone") phone: String
    ): Call<RequestSendPhone>


    @FormUrlEncoded
    @POST("auth/phone/login/verify")
    fun verifyCode(
        @Field("code") code: String,
        @Field("phone") phone: String
    ): Call<RequestVerifyCode>


    @FormUrlEncoded
    @POST("user/profile")
    fun editUser(
        @Header("app-api-key") apiKey: String,
        @Header("app-device-uid") id: String,
        @Header("app-public-key") pubKey: String,
        @Field("fullname") fullName: String
    ): Call<DefaultModel>

}