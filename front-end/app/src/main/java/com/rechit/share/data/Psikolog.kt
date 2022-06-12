package com.rechit.share.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Psikolog(
    var name: String,
    var photo: Int
): Parcelable