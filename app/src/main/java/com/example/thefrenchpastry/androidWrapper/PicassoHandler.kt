package com.example.thefrenchpastry.androidWrapper

import android.widget.ImageView
import com.example.thefrenchpastry.R
import com.squareup.picasso.Picasso


object PicassoHandler {

    fun setPicasso(img: ImageView, url: String) {

        Picasso.get()
            .load(url)
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.img_place_holder)
            .fit()
            .into(img)

    }

    fun setPicassoBanner(img: ImageView, url: String) {

        Picasso.get()
            .load(url)
            .placeholder(R.drawable.img_banner_place_holder)
            .error(R.drawable.img_banner_place_holder)
            .fit()
            .into(img)

    }

    fun setPicassoCats(img: ImageView, url: String) {

        Picasso.get()
            .load(url)
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.ic_pastry_place_holder)
            .fit()
            .into(img)

    }

}