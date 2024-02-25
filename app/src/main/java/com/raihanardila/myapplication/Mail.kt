package com.raihanardila.myapplication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class Mail(
    val name: String,
    val description: String,
    val subject: String,
    val photo: Int
) : Parcelable
