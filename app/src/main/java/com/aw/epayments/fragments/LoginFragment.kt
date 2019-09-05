package com.aw.epayments.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Application
import android.app.Dialog
import android.app.DialogFragment

import android.graphics.Color
import android.os.Build

import android.support.design.widget.TextInputLayout

import android.os.Bundle
import android.support.annotation.RequiresApi
import android.text.Editable
import android.text.TextWatcher
import android.util.Log

import android.view.View

import android.widget.Toast
import com.aw.epayments.R

import com.aw.epayments.api.Api
import com.aw.epayments.models.Response.LoginResponse
import com.aw.epayments.models.Response.RegistrationResponse
import com.github.ybq.android.spinkit.style.FadingCircle
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import java.lang.Exception

import java.util.Objects

class LoginFragment : DialogFragment() {


    internal lateinit var view: View
    var signUp = FadingCircle()
    var login_progress = FadingCircle()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // Use the Builder class for convenient dialog construction
        val builder = AlertDialog.Builder(activity)

        val inflater = activity.layoutInflater

        view = inflater.inflate(R.layout.fragment_login, null)

        val textInputCustomEndIcon = view.findViewById<TextInputLayout>(R.id.confirm_password_layout)


        view.confirm_password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                //This sets a textview to the current length
                //Toast.makeText(getContext(), String.valueOf(s.length()), Toast.LENGTH_SHORT).show();


            }

            override fun afterTextChanged(s: Editable) {
                if (view.password.text.toString() == view.confirm_password.text.toString()) {

                    view.confirm_password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done_black_24dp, 0)

                } else {
                    view.confirm_password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error, 0)

                }
            }
        })
        if (isLogin) {
            view.sign_up_form.visibility = View.GONE
            view.login_form.visibility = View.VISIBLE
        } else {
            view.sign_up_form.visibility = View.VISIBLE
            view.login_form.visibility = View.GONE
        }
        view.sign_up.setOnClickListener {
            try {
                if (view.password.text.toString() != view.confirm_password.text.toString()) {
                    view.confirm_password.error = "did not match"
                } else if (view.email.text.toString().isEmpty()) {
                    view.email.error = getString(R.string.required)
                } else if (view.phoneNumber.text.toString().isEmpty()) {
                    view.phoneNumber.error = getString(R.string.required)
                } else if (view.id_number.text.toString().isEmpty()) {
                    view.id_number.error = getString(R.string.required)
                }else {
                    SignUp()
                }
            }catch (ex:Exception){
                Log.e("sign up",ex.localizedMessage)
            }

        }
        view.login.setOnClickListener {
            if (view.login_user_name.text.toString().isEmpty()) {
                view.login_user_name.error = "Please fill"
            }
            if (view.login_password.text.toString().isEmpty()) {
                view.login_password.error = "Please fill"
            }
            if (!view.login_user_name.text.toString().isEmpty() && !view.login_password.text.toString().isEmpty()) {
                Login()
            }
        }
        view.nav_to_signup.setOnClickListener {
            view.sign_up_form.visibility = View.VISIBLE
            view.login_form.visibility = View.GONE
        }

        view.nav_to_login.setOnClickListener {
            view.sign_up_form.visibility = View.GONE
            view.login_form.visibility = View.VISIBLE
        }
        view.cancel_form.setOnClickListener { dismiss() }

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)

        // Create the AlertDialog object and return it
        return builder.create()
    }

    fun Login() {
        try {
            login_progress.setBounds(0, 0, 1000, 100)
            login_progress.color = Color.WHITE
            view.login.setCompoundDrawables(null, login_progress, null, null)
            view.login.text = ""
            login_progress.start()
            Api.getVolley(activity, Api.POST, Api.GetToken, "{\n" +
                    "  \"user_name\": \"" + view.login_user_name.text + "\",\n" +
                    "  \"password\": \"" + view.login_password.text + "\"\n" +
                    "}", object : Api.VolleyCallback {
                override fun onSuccess(result: String) {

                    view.login.setCompoundDrawables(null, null, null, null)
                    view.login.text = "LOGIN"
                    view.login.setTextColor(Color.WHITE)
                    login_progress.stop()

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        LoadToken(result)
                    }


                }
            })
        }catch (ex:Exception){
            ex.localizedMessage
        }

    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.M)
    fun LoadToken(result: String) = try {

        val response = Api.mGson.fromJson(result, LoginResponse::class.java)
        if (response.authenticationStatus!!) {
            activity.finish()
            startActivity(activity.intent)
            Api.save(Objects.requireNonNull(activity), "token", response.token)
            Api.save(Objects.requireNonNull(activity), "LoginStatus", "login")
            Api.save(Objects.requireNonNull(activity), "FullNames", response.userFullName)
            dismiss()
        } else {
            view.login.setCompoundDrawables(null, null, null, null)
            view.login.text = "LOGIN"
            view.login.setTextColor(Color.WHITE)
            login_progress.stop()
            view.login_password.error = response.message

        }
    }catch (ex:Exception){}

    fun SignUp() {
        try {
            var fName = ""
            var mName = ""
            var lName = ""
            signUp = FadingCircle()
            signUp.setBounds(0, 0, 100, 100)
            view.sign_up.setCompoundDrawables(null, signUp, null, null)
            view.sign_up.text = ""
            /*signUp.start()*/
            val splits = view.full_names.text.toString().split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (splits.size >= 3) {
                fName = splits[0]
                mName = splits[1]
                lName = splits[2]
            } else if (splits.size == 2) {
                fName = splits[0]
                mName = splits[1]
                lName = "Name"
            } else if (splits.size == 1) {
                fName = splits[0]
                mName = "Name"
                lName = "Name"

            }
            Api.getVolley(activity, Api.POST, Api.Register, "{\n" +
                    "  \"first_name\": \"" + fName + "\",\n" +
                    "  \"middle_name\": \"" + mName + "\",\n" +
                    "  \"last_name\": \"" + lName + "\",\n" +
                    "  \"email\": \"" + view.email.text.toString() + "\",\n" +
                    "  \"user_name\": \"" + view.sign_up_username.text.toString() + "\",\n" +
                    "  \"password\": \"" + view.password.text.toString() + "\",\n" +
                    "  \"phone_number\": \"" + view.phoneNumber.text.toString() + "\",\n" +
                    "  \"role\": \"CUSTOMER\",\n" +
                    "  \"roles_List\": [\n" +
                    "    \"string\"\n" +
                    "  ]\n" +
                    "}", object : Api.VolleyCallback {
                @SuppressLint("SetTextI18n")
                override fun onSuccess(result: String) {
                    val response = Api.mGson.fromJson(result, RegistrationResponse::class.java)
                    if (response.statusCode == 200) {
                        view.sign_up_form.visibility = View.GONE
                        view.login_form.visibility = View.VISIBLE
                        Toast.makeText(activity, "Signed Up successful", Toast.LENGTH_SHORT).show()
                        signUp.stop()
                        view.sign_up.setCompoundDrawables(null, null, null, null)
                        view.sign_up.text = "CREATE ACCOUNT"

                    } else {
                        signUp.stop()
                        view.sign_up.setCompoundDrawables(null, null, null, null)
                        view.sign_up.text = "CREATE ACCOUNT"
                        Toast.makeText(activity, "Failed  to sign up." + response.message, Toast.LENGTH_LONG).show()
                    }
                }
            })
        }catch (ex:Exception){
            Log.e("",ex.localizedMessage)
        }

    }

    companion object {
        var isLogin = true
    }


}
