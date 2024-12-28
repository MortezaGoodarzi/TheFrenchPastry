package com.example.thefrenchpastry.data.remote.ext

import com.example.thefrenchpastry.data.remote.RetrofitService
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response


class ErrorUtils {

    companion object {

        fun parseError(response: Response<*>): String {

            val converter: Converter<ResponseBody, ErrorResponse> =
                RetrofitService.retrofit.responseBodyConverter(
                    ErrorResponse::class.java,
                    arrayOfNulls<Annotation>(0)
                )
            var error: String? = null

            try {
                response.errorBody().let {
                    val errorRes: ErrorResponse? = converter.convert(it)
                    error = errorRes?.message
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return error ?: "ارتباط با سرور ممکن نیست"
        }

    }
}

data class ErrorResponse(val message: String)