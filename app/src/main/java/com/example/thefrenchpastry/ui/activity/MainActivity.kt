package com.example.thefrenchpastry.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.thefrenchpastry.R
import com.example.thefrenchpastry.adapter.viewPager.CustomSliderPagerAdapter
import com.example.thefrenchpastry.androidWrapper.ActivityUtils
import com.example.thefrenchpastry.databinding.ActivityMainBinding
import com.example.thefrenchpastry.mvp.model.ModelMainActivity
import com.example.thefrenchpastry.mvp.presenter.PresenterMainActivity
import com.example.thefrenchpastry.mvp.view.ViewMainActivity
import com.example.thefrenchpastry.ui.customView.customBottomNavigation.ActiveFragment
import com.example.thefrenchpastry.ui.customView.customBottomNavigation.FragmentType

class MainActivity : AppCompatActivity(), ActivityUtils {
    private lateinit var presenter: PresenterMainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewMainActivity(this, this)
        setContentView(view.binding.root)

        val model = ModelMainActivity()

        presenter = PresenterMainActivity(view, model)
        presenter.onCreate()

    }

    override fun setFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame_layout, fragment)
            .commit()
    }

    override fun setViewPagerFragment(viewPager: ViewPager2, data: ArrayList<String>) {

        viewPager.adapter =
            CustomSliderPagerAdapter(supportFragmentManager,lifecycle, data)
    }


}