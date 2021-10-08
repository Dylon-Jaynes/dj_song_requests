package com.example.djsongrequestsbusiness.ui.ViewModels

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.djsongrequestsbusiness.Data.DataClasses.ValidationModel
import com.example.djsongrequestsbusiness.Data.DataClasses.validateEmail
import com.example.djsongrequestsbusiness.Data.DataClasses.validatePassword

class BasicInfoViewModel: ViewModel() {
    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var errorEmail: MutableLiveData<String> = MutableLiveData()
    var errorPassword: MutableLiveData<String> = MutableLiveData()

    private var isEmailValid:Boolean = false
    private var isPasswordValid:Boolean = false
    var isValid:MutableLiveData<Boolean> = MutableLiveData()

    fun setup(lifecycleOwner: LifecycleOwner, context: Context){
        email.observe(lifecycleOwner, Observer { email ->
            val validationModel: ValidationModel = email.validateEmail(context)
            isEmailValid = validationModel.isValid
            validateInput(isEmailValid, isPasswordValid)
            errorEmail.postValue(validationModel.msg)
        })

        password.observe(lifecycleOwner, Observer { password ->
            val validationModel: ValidationModel = password.validatePassword(context)
            isPasswordValid = validationModel.isValid
            validateInput(isEmailValid, isPasswordValid)
            errorPassword.postValue(validationModel.msg)
        })
    }

    private fun validateInput(emailValid: Boolean, passwordValid: Boolean) {
        isValid.postValue(emailValid&&passwordValid)
    }
}