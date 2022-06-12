package com.rechit.share.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Riwayat (
    var date: String,
    var receiver: String,
    var progress: String
) : Parcelable