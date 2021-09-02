package com.uin.stoichiofyapp.Dashboard.Pembelajaran

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Category (
    var agenda: String ?= "",
    var image: String ?= ""
) : Parcelable
