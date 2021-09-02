package com.uin.stoichiofyapp.Dashboard.Category

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Pembelajaran (
    var agenda: String ?= "",
    var image:String ?= ""
) : Parcelable
