package com.ogrvassiem.myfilms.ui.destinations.navArgs

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoriesNavArgs(
    val categories: ArrayList<String>
) : Parcelable