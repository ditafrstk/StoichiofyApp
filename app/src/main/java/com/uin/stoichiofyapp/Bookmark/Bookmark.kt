package com.uin.stoichiofyapp.Bookmark

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Bookmark (
    var key: String ?= "",
    var judul: String ?= "",
    var url: String ?= "",
    var tujuan: String ?= "",
    var tujuan2: String ?= "",
    var tujuan3: String ?= ""
        ) : Parcelable