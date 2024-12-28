package com.example.thefrenchpastry.androidWrapper

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.SettingInjectorService
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.thefrenchpastry.databinding.CustomDialogInternetBinding

object NetworkInfo {

    fun internetInfo(context: Context, activityUtils: ActivityUtils): Boolean {

        return if (netInfo(context)) {
            true
        } else {
            showNetDialog(context, activityUtils)
            false
        }
    }


    private fun showNetDialog(context: Context, activityUtils: ActivityUtils) {

        val view = CustomDialogInternetBinding.inflate(LayoutInflater.from(context))
        val dialog = Dialog(context)

        dialog.setContentView(view.root)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        view.textSettings.setOnClickListener {

            dialog.dismiss()
            context.startActivity(Intent(Settings.ACTION_SETTINGS))
        }
        view.textRetry.setOnClickListener {
            dialog.dismiss()
            internetInfoRetry(context, activityUtils)
        }
    }


    private fun internetInfoRetry(context: Context, activityUtils: ActivityUtils): Boolean {

        return if (netInfo(context)) {
            activityUtils.activeNetwork()
            true
        } else {
            showNetDialog(context, activityUtils)
            false
        }
    }


    private fun netInfo(context: Context): Boolean {

        val connectivityManager =
            context.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = connectivityManager.activeNetwork ?: return false

        val actNW =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        return when {
            actNW.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNW.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNW.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}