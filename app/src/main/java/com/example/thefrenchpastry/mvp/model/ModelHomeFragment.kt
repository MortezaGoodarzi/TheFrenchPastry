package com.example.thefrenchpastry.mvp.model

import com.example.thefrenchpastry.data.remote.apiRepository.MainAPIRepository
import com.example.thefrenchpastry.data.remote.dataModel.RequestMain
import com.example.thefrenchpastry.data.remote.ext.CallBackRequest

class ModelHomeFragment {

    fun getContent(callBackRequest: CallBackRequest<RequestMain>){
        MainAPIRepository.instance.getMainContent(callBackRequest)

    }
}