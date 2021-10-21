package com.uin.stoichiofyapp.Trivia

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Trivia (
    var key : String ?= "",
    var url : String ?= "",
    var title : String ?= ""

) : Parcelable

@Parcelize
data class LearningPoint (
//    var key: String? = "",
    var url: String? = "",
    var title : String ?= "",
    var subtitle : String ?= ""
) : Parcelable