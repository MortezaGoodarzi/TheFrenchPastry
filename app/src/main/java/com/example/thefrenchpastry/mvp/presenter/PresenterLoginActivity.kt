package com.example.thefrenchpastry.mvp.presenter

import com.example.thefrenchpastry.mvp.ext.BaseLifecycle
import com.example.thefrenchpastry.mvp.model.ModelLoginActivity
import com.example.thefrenchpastry.mvp.view.ViewLoginActivity

class PresenterLoginActivity(
    private val model: ModelLoginActivity,
    private val view: ViewLoginActivity
): BaseLifecycle {

    override fun onCreate() {
        sendDeviceInfo()
        onClickForCode()

    }

    private fun onClickForCode() {
        view.pressedSendCode(model.getUID(), model.getPublicKey())
    }

    private fun sendDeviceInfo() {

        view.setDeviceInfo(model.getDeviceInfo())
    }
}


