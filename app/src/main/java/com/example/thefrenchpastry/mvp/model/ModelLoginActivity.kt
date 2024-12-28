package com.example.thefrenchpastry.mvp.model

import android.content.Context
import com.example.thefrenchpastry.androidWrapper.DeviceInfo

class ModelLoginActivity(
    private val context: Context
) {

    fun getDeviceInfo()=  DeviceInfo()

    fun getUID() = DeviceInfo.getDeviceID(context)

    fun getPublicKey() = DeviceInfo.getPublicKeyWithoutApi(context)
}