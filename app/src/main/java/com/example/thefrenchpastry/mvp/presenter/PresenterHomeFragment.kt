package com.example.thefrenchpastry.mvp.presenter

import android.content.Context
import com.example.thefrenchpastry.androidWrapper.ActivityUtils
import com.example.thefrenchpastry.androidWrapper.NetworkInfo
import com.example.thefrenchpastry.data.remote.dataModel.RequestMain
import com.example.thefrenchpastry.data.remote.ext.CallBackRequest
import com.example.thefrenchpastry.mvp.ext.BaseLifecycle
import com.example.thefrenchpastry.mvp.ext.ToastUtils
import com.example.thefrenchpastry.mvp.model.ModelHomeFragment
import com.example.thefrenchpastry.mvp.view.ViewHomeFragment

class PresenterHomeFragment(
    val view: ViewHomeFragment,
    val model: ModelHomeFragment,
    val context: Context
) : BaseLifecycle, ActivityUtils {

    override fun onCreate() {

        createSlider()
    }

    private fun createSlider() {
        view.startGetData()
        if (NetworkInfo.internetInfo(context, this))
            sendRequest()
    }

    private fun sendRequest() {

        model.getContent(
            object : CallBackRequest<RequestMain> {
                override fun onSuccess(code: Int, data: RequestMain) {
                    view.endGetData()
                    view.initialized(data)
                }

                override fun onNotSuccess(code: Int, error: String) {
                    view.endGetData()
                    ToastUtils.toast(context, error)
                }

                override fun onError(error: String) {
                    view.endProgress()
                    ToastUtils.toastServerError(context)
                }
            })
    }
}