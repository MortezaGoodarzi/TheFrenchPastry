package com.example.thefrenchpastry.mvp.view

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.CountDownTimer
import android.text.InputFilter
import android.text.InputType
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import com.example.thefrenchpastry.androidWrapper.ActivityUtils
import com.example.thefrenchpastry.androidWrapper.DeviceInfo
import com.example.thefrenchpastry.androidWrapper.NetworkInfo
import com.example.thefrenchpastry.data.local.preferences.SecureSharePref
import com.example.thefrenchpastry.data.local.preferences.SharedPrefKey
import com.example.thefrenchpastry.data.remote.apiRepository.LoginAPIRepository
import com.example.thefrenchpastry.data.remote.dataModel.DefaultModel
import com.example.thefrenchpastry.data.remote.dataModel.RequestSendPhone
import com.example.thefrenchpastry.data.remote.dataModel.RequestVerifyCode
import com.example.thefrenchpastry.data.remote.ext.CallBackRequest
import com.example.thefrenchpastry.databinding.ActivityLoginBinding
import com.example.thefrenchpastry.databinding.LoginCustomDialogBinding
import com.example.thefrenchpastry.mvp.ext.ToastUtils
import com.example.thefrenchpastry.ui.activity.MainActivity

class ViewLoginActivity(
    contextInstance: Context
) : FrameLayout(contextInstance), ActivityUtils {

    private val inflater = LayoutInflater.from(context)
    val binding = ActivityLoginBinding.inflate(inflater)


    private lateinit var number: String
    private lateinit var deviceInfo: DeviceInfo

    private var resendState = false


    fun setDeviceInfo(info: DeviceInfo) {
        deviceInfo = info
    }


    fun pressedSendCode(id: String, key: String) {

        binding.btnLogin.getView().setOnClickListener {
            number = binding.textInput.getText()

            if (numberValidation(number)) {
                if (isCheckedNetwork())
                    binding.btnLogin.enableProgress()
                sendCode(true, id, key)
            }
        }
    }

    private fun numberValidation(number: String): Boolean {

        if (number.isEmpty()) {
            binding.textInput.setError("لطفا شماره خود را وارد کنید")
            return false
        }

        if (number.length < 11) {
            binding.textInput.setError(" شماره خود را کامل وارد کنید")
            return false
        }
        if (!number.matches(Regex("(\\+98|0)?9\\d{9}"))) {
            binding.textInput.setError("شماره را صحیح وارد کنید")
            return false
        }
        binding.textInput.setError(null)
        return true
    }

    private fun isCheckedNetwork() = NetworkInfo.internetInfo(context, this)


    private fun sendCode(dialog: Boolean, id: String, key: String) {

        LoginAPIRepository.instance.sendPhoneAuth(id,
            key,
            number,
            object : CallBackRequest<RequestSendPhone> {
                override fun onSuccess(code: Int, data: RequestSendPhone) {
                    binding.btnLogin.disableProgress()
                    if (dialog) {
                        ToastUtils.toast(context,key)
                        createDialog(id, key)
                    }
                }

                override fun onNotSuccess(code: Int, error: String) {
                    binding.btnLogin.disableProgress()
                    ToastUtils.toast(context, error)

                }

                override fun onError(error: String) {
                    binding.btnLogin.disableProgress()
                    ToastUtils.toast(context, error)
                }

            })

    }

    private fun createDialog(id: String, key: String) {

        val view = LoginCustomDialogBinding.inflate(inflater)

        val text = "کد تایید به شماره $number ارسال شد"
        view.txtSendedTo.text = text

        view.txtResend.setTextColor(Color.parseColor("#D9888383"))

        createTimer(view, id, key)
        val dialog = Dialog(context)
        dialog.setContentView(view.root)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        view.ButtonConfirm.getView().setOnClickListener {

            val code = view.edtTextVerifyCode.text.toString()
            if (code.length < 4) {
                view.inputLayoutVerifyCode.error = "کد 5 رقمی را وارد کنید"
                return@setOnClickListener
            } else
                view.inputLayoutVerifyCode.error = null

            if (isCheckedNetwork()) {

                view.ButtonConfirm.enableProgress()

                LoginAPIRepository.instance.verifyCodeAuth(
                    code,
                    number,
                    object : CallBackRequest<RequestVerifyCode> {
                        override fun onSuccess(code: Int, data: RequestVerifyCode) {

                            view.ButtonConfirm.disableProgress()
                            dialog.dismiss()

                            val nameView = LoginCustomDialogBinding.inflate(inflater)
                            val nameDialog = Dialog(context)
                            nameDialog.setContentView(nameView.root)
                            nameDialog.setCancelable(false)

                            createNameView(nameView)

                            nameDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                            nameDialog.show()

                            val shared = SecureSharePref.getSharedPref(context)
                            val edit = shared.edit().putString(SharedPrefKey.API_KEY, data.api)
                            edit.apply()

                            nameView.ButtonConfirm.getView().setOnClickListener {

                                nameView.ButtonConfirm.enableProgress()
                                val name = nameView.edtTextVerifyCode.text.toString().trim()

                                if (name.isEmpty())
                                    nameView.edtTextVerifyCode.error = "لطفا نام خود را وارد کنید"
                                else
                                    nameView.edtTextVerifyCode.error = null

                                if (isCheckedNetwork()) {

                                    nameView.ButtonConfirm.enableProgress()

                                    LoginAPIRepository.instance.editUser(
                                        DeviceInfo.getApi(context),
                                        DeviceInfo.getDeviceID(context),
                                        DeviceInfo.getPublicKey(context),
                                        name,
                                        object : CallBackRequest<DefaultModel> {
                                            override fun onSuccess(code: Int, data: DefaultModel) {

                                                nameView.ButtonConfirm.disableProgress()

                                                val editLogin = shared.edit()
                                                editLogin.putBoolean(
                                                    SharedPrefKey.LOGIN_STATE_KEY,
                                                    true
                                                )
                                                editLogin.apply()

                                                context.startActivity(
                                                    Intent(context, MainActivity::class.java)
                                                )
                                            }

                                            override fun onNotSuccess(code: Int, error: String) {
                                                nameView.ButtonConfirm.disableProgress()
                                                ToastUtils.toast(context, "$key")

                                            }

                                            override fun onError(error: String) {
                                                nameView.ButtonConfirm.disableProgress()
                                                ToastUtils.toastServerError(context)

                                            }
                                        }
                                    )
                                }
                            }
                        }

                        override fun onNotSuccess(code: Int, error: String) {
                            view.ButtonConfirm.disableProgress()
                            ToastUtils.toast(context, error)
                        }

                        override fun onError(error: String) {
                            view.ButtonConfirm.disableProgress()
                            ToastUtils.toastServerError(context)
                        }
                    }
                )
            }
        }


        view.txtEditNumber.setOnClickListener {
            dialog.dismiss()
        }


    }

    private fun createNameView(nameView: LoginCustomDialogBinding) {

        nameView.txtResend.visibility = GONE
        nameView.txtTime.visibility = GONE
        nameView.txtEditNumber.visibility = GONE
        nameView.txtSendedTo.visibility = GONE
        nameView.edtTextVerifyCode.inputType = InputType.TYPE_CLASS_TEXT
        nameView.textView8.text = "اطلاعات کاربری"
        nameView.edtTextVerifyCode.hint = "نام و نام خانوادگی"
        nameView.edtTextVerifyCode.gravity = Gravity.START
        nameView.edtTextVerifyCode.textDirection = TEXT_DIRECTION_RTL
        nameView.edtTextVerifyCode.filters = arrayOf(InputFilter.LengthFilter(40))
        nameView.ButtonConfirm.getView().text = "ثبت اطلاعات"

    }

    private fun createTimer(view: LoginCustomDialogBinding, id: String, key: String) {

        object : CountDownTimer(70000, 1000) {
            override fun onTick(p0: Long) {
                view.txtTime.text = "00: ${p0 / 1000}"
            }

            override fun onFinish() {

                view.txtTime.text = "00:00"
                resendState = true
                view.txtResend.setTextColor(Color.parseColor("#101219"))

                view.txtResend.setOnClickListener {
                    if (resendState) {
                        view.txtResend.setTextColor(Color.parseColor("#D9888383"))
                        resendState = false
                        sendCode(false, id, key)
                        createTimer(view, id, key)
                    }
                }
            }
        }.start()
    }

    override fun activeNetwork() {
        Toast.makeText(
            context,
            "اتصال شما به اینترنت برقرار شد",
            Toast.LENGTH_SHORT
        )
            .show() // TODO: نوشتن ادامه کد که بعد از اینکه به اینترنت وصل شد بتونه بره به اکتیویتی بعدی
    }

}