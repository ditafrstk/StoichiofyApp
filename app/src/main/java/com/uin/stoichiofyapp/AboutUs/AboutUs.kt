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
    var nama : String ?= "",
    var nomor_induk : String ? ="",
    var email : String ?= "",
    var jurusan : String ?= "",
    var universitas : String ?= "",
    var url : String ?= ""
        ) : Parcelable

@Parcelize
data class getDosen (
    var nama : String ?= "",
    var nomor_induk : String ? ="",
    var email : String ?= "",
    var jurusan : String ?= "",
    var universitas : String ?= "",
    var url : String ?= ""
) : Parcelable