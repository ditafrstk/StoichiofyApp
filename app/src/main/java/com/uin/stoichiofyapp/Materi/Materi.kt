package com.uin.stoichiofyapp.Materi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Materi (
    var judul:String ?= "",
    var desc1: String ?= "",
    var desc2: String ?= "",
    var bab: String ?="",
    var url: String ?= "",
    var key : String ?= ""
) : Parcelable

@Parcelize
data class Bab (
    var judul:String ?= "",
    var url : String ?= "",
    var tujuan : String ?= "",
    var tujuan2 : String ?= "",
    var tujuan3 : String ?= ""
) : Parcelable

@Parcelize
data class Bab1 (
    var judul:String ?= "",
    var desc1: String ?= "",
    var desc2: String ?= ""
) : Parcelable

@Parcelize
data class Bab2 (
    var judul:String ?= "",
    var desc1: String ?= "",
    var desc2: String ?= ""
) : Parcelable

@Parcelize
data class Bab3 (
    var judul:String ?= "",
    var desc1: String ?= "",
    var desc2: String ?= ""
) : Parcelable

@Parcelize
data class Bab4 (
    var judul:String ?= "",
    var desc1: String ?= "",
    var desc2: String ?= ""
) : Parcelable