package com.example.djsongrequestsbusiness.data.dataClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginModel(
    val username: String,
    val email: String,
    val password: String,
) : Parcelable