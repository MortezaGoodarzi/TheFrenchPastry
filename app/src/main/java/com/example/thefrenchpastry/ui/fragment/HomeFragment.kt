package com.example.thefrenchpastry.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.thefrenchpastry.androidWrapper.ActivityUtils
import com.example.thefrenchpastry.mvp.model.ModelHomeFragment
import com.example.thefrenchpastry.mvp.presenter.PresenterHomeFragment
import com.example.thefrenchpastry.mvp.view.ViewHomeFragment

class HomeFragment(
    private val contextInstance: Context,
    private val activityUtils: ActivityUtils
) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = ViewHomeFragment(contextInstance, activityUtils)
        val presenter = PresenterHomeFragment(view, ModelHomeFragment(), contextInstance)
        presenter.onCreate()
        return view.binding.root

    }


}