package com.example.thefrenchpastry.mvp.presenter

import com.example.thefrenchpastry.mvp.ext.BaseLifecycle
import com.example.thefrenchpastry.mvp.model.ModelMainActivity
import com.example.thefrenchpastry.mvp.view.ViewMainActivity

class PresenterMainActivity(
    private val view: ViewMainActivity,
    private val model: ModelMainActivity
) : BaseLifecycle {

    override fun onCreate() {
        view.initialize()
        view.initBottomNav()
        showNavigationDrawer()
    }

    private fun showNavigationDrawer() {
        view.showNavDrawer()
        //new
    }


}