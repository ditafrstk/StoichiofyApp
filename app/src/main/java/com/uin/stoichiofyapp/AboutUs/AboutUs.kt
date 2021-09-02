package com.uin.stoichiofyapp.AboutUs

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class getAbout (
    var key: String ?= "",
    var desc: String? = ""
) : Parcelable


@Parcelize
data class addAbout (
    var desc: String ?= ""
        ) : Parcelable