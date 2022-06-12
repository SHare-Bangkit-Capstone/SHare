package com.rechit.share.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: String ?= null,
    var name: String ?= null,
    var email: String ?= null,
    var phone_number: String ?= null,
    var age: String ?= null
): Parcelable