package com.example.thefrenchpastry.ui.customView.customBottomNavigation

enum class FragmentType {
    HOME, CAKE, PASTRY, PROFILE
}

interface ActiveFragment {

    fun setFragment(type: FragmentType)

}