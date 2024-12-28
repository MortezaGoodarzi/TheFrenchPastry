package com.example.thefrenchpastry.data.remote.dataModel

data class RequestSendPhone(
    val success: Int,
    val message: String,
    val seconds: Int,
    val expire_at: String
)

data class RequestVerifyCode(
    val message: String,
    val api: String
)